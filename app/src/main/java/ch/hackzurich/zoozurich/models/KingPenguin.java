package ch.hackzurich.zoozurich.models;

import android.util.Log;

import com.google.ar.sceneform.SkeletonNode;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;

import ch.hackzurich.zoozurich.R;

public class KingPenguin extends ModelLoader {
    KingPenguin() {
        super(R.raw.penguin_v1_diffuse);
    }

    SkeletonNode createNode(ModelRenderable modelRenderable) {
        Log.i("Zoo", "King Penguin loaded");

        SkeletonNode skeletonNode = new SkeletonNode();

        skeletonNode.setRenderable(modelRenderable);
        skeletonNode.setLocalScale(Vector3.one().scaled(0.5f));
        skeletonNode.setLocalRotation(Quaternion.eulerAngles(new Vector3(-90.0f, 180.0f, 180.0f)));

        return skeletonNode;
    }

}
