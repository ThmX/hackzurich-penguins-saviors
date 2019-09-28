package ch.hackzurich.zoozurich.ui.guide;

import android.graphics.Point;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.ar.core.Anchor;
import com.google.ar.core.Config;
import com.google.ar.core.Frame;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.core.Session;
import com.google.ar.core.exceptions.NotYetAvailableException;
import com.google.ar.core.exceptions.UnavailableApkTooOldException;
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableSdkTooOldException;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.navigation.NavController;
import ch.hackzurich.zoozurich.MainActivity;
import ch.hackzurich.zoozurich.R;
import ch.hackzurich.zoozurich.core.ZooService;
import ch.hackzurich.zoozurich.models.ModelLoader;
import ch.hackzurich.zoozurich.models.ModelService;
import ch.hackzurich.zoozurich.ui.box.BoxFragment;

import static androidx.navigation.Navigation.findNavController;

public class GuideFragment extends Fragment implements OnSuccessListener<List<FirebaseVisionBarcode>>, OnFailureListener {

    private ZooService zooService;

    private ModelService modelService;

    private ArFragment arFragment;

    private BoxFragment boxFragment;

    private NavController navController;

    private float delay = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        zooService = ((MainActivity) getActivity()).getZooService();
        modelService = ((MainActivity) getActivity()).getModelService();

        View root = inflater.inflate(R.layout.fragment_guide, container, false);

        arFragment = (ArFragment) getChildFragmentManager().findFragmentById(R.id.ar_fragment);
        boxFragment = (BoxFragment) getChildFragmentManager().findFragmentById(R.id.box_fragment);

        try {
            Session session = new Session(getContext());
            Config config = new Config(session);
            config.setFocusMode(Config.FocusMode.AUTO);
            config.setUpdateMode(Config.UpdateMode.LATEST_CAMERA_IMAGE);
            session.configure(config);
            arFragment.getArSceneView().setupSession(session);
        } catch (UnavailableArcoreNotInstalledException e) {
            e.printStackTrace();
        } catch (UnavailableApkTooOldException e) {
            e.printStackTrace();
        } catch (UnavailableSdkTooOldException e) {
            e.printStackTrace();
        } catch (UnavailableDeviceNotCompatibleException e) {
            e.printStackTrace();
        }

        navController = findNavController(container);

        arFragment.setOnTapArPlaneListener(this::onPlaneTap);
        arFragment.getArSceneView().getScene().addOnUpdateListener(this::onFrameUpdate);

        return root;
    }

    @Override
    public void onSuccess(List<FirebaseVisionBarcode> barcodes) {
        for (FirebaseVisionBarcode barcode : barcodes) {
            Rect bounds = barcode.getBoundingBox();
            Point[] corners = barcode.getCornerPoints();

            String rawValue = barcode.getRawValue();

            int valueType = barcode.getValueType();
            // See API reference for complete list of supported types
            if (valueType == FirebaseVisionBarcode.TYPE_TEXT) {
                String text = barcode.getRawValue();
                boxFragment.initializeBox(text);
                Log.i("Zoo", "Text = " + text);
            } else if (valueType == FirebaseVisionBarcode.TYPE_URL) {
                String title = barcode.getUrl().getTitle();
                String url = barcode.getUrl().getUrl();
                Log.i("Zoo", "URL = " + title + " with " + url);

            } else {
                Log.w("Zoo", "QRCode of type " + valueType + " not supported");
            }
        }
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        Log.e("Zoo", "QRCode", e);
    }

    private ModelLoader getModelLoader() {
        switch (zooService.getSpecie()) {
            case "Cute":
                return modelService.getCutePenguin();

            case "King":
                return modelService.getKingPenguin();
        }

        // FIXME That is very very much ugly
        return null;
    }

    private void onPlaneTap(HitResult hitResult, Plane unusedPlane, MotionEvent unusedMotionEvent) {
        Log.i("Zoo", "onPlaneTap");
        if (!modelService.isLoaded()) {
            return;
        }

        Anchor anchor = hitResult.createAnchor();
        AnchorNode anchorNode = new AnchorNode(anchor);
        anchorNode.setParent(arFragment.getArSceneView().getScene());

        getModelLoader().setParent(anchorNode);

        Log.i("Zoo", "Displayed");

        // navController.navigate(R.id.navigation_summary);
    }

    private void onFrameUpdate(FrameTime frameTime) {

        delay += frameTime.getDeltaSeconds();
        if (delay < 5) {
            return;
        }
        delay = 0;

        Log.i("Zoo", "onFrameUpdate");
        Image image = null;
        try {
            Frame frame = arFragment.getArSceneView().getArFrame();
            image = frame.acquireCameraImage();
            searchQrCode(image);

        } catch (NotYetAvailableException e) {
            // TODO Do something?
            e.printStackTrace();
        } finally {
            if (image != null) {
                image.close();
            }
        }
    }

    private void searchQrCode(Image image) {
        if (image == null) {
            return;
        }

        FirebaseVisionBarcodeDetectorOptions options = new FirebaseVisionBarcodeDetectorOptions
                .Builder()
                .setBarcodeFormats(
                        FirebaseVisionBarcode.FORMAT_QR_CODE,
                        FirebaseVisionBarcode.FORMAT_AZTEC)
                .build();

        FirebaseVisionBarcodeDetector detector = FirebaseVision.getInstance()
                .getVisionBarcodeDetector(options);

        FirebaseVisionImage fbImage = FirebaseVisionImage.fromMediaImage(image,
                FirebaseVisionImageMetadata.ROTATION_0);

        detector.detectInImage(fbImage)
                .addOnSuccessListener(this)
                .addOnFailureListener(this);
    }

    private void saveImage() {
        Frame frame = arFragment.getArSceneView().getArFrame();
        try {
            Image image = frame.acquireCameraImage();


        } catch (NotYetAvailableException e) {
            e.printStackTrace();
        }
    }
}
