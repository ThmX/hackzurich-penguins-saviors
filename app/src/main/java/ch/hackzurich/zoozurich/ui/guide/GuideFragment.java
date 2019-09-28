package ch.hackzurich.zoozurich.ui.guide;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.ux.ArFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import androidx.navigation.NavController;
import ch.hackzurich.zoozurich.MainActivity;
import ch.hackzurich.zoozurich.R;
import ch.hackzurich.zoozurich.core.ZooService;

import static androidx.navigation.Navigation.findNavController;

public class GuideFragment extends Fragment {

    private ZooService zooService;

    private GuideViewModel guideViewModel;

    private ArFragment arFragment;

    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        zooService = ((MainActivity) getActivity()).getZooService();

        guideViewModel = ViewModelProviders.of(this).get(GuideViewModel.class);
        View root = inflater.inflate(R.layout.fragment_guide, container, false);

        arFragment = (ArFragment) getChildFragmentManager().findFragmentById(R.id.ar_fragment);

        navController = findNavController(container);

        arFragment.setOnTapArPlaneListener(this::onPlaneTap);
        arFragment.getArSceneView().getScene().addOnUpdateListener(this::onFrameUpdate);

        return root;
    }

    private void onPlaneTap(HitResult hitResult, Plane unusedPlane, MotionEvent unusedMotionEvent) {
        Log.i("Zoo", "onPlaneTap");
        navController.navigate(R.id.navigation_summary);
        // TODO
    }

    private void onFrameUpdate(FrameTime unusedframeTime) {
        // TODO
    }
}
