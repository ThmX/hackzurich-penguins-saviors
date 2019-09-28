package ch.hackzurich.zoozurich.models;

import android.util.Log;

import com.google.ar.sceneform.SkeletonNode;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;

import ch.hackzurich.zoozurich.R;

public class CutePenguin extends ModelLoader {
    CutePenguin() {
        super(R.raw.pinguin_small);
    }

    SkeletonNode createNode(ModelRenderable modelRenderable) {
        Log.i("Zoo", "King Penguin loaded");

        SkeletonNode skeletonNode = new SkeletonNode();

        skeletonNode.setRenderable(modelRenderable);
        skeletonNode.setLocalScale(Vector3.one().scaled(0.25f));
        skeletonNode.setLocalRotation(Quaternion.axisAngle(Vector3.up(), -180.0f));

        return skeletonNode;
    }

}
