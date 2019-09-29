package ch.hackzurich.zoozurich.models;

import android.util.Log;

import com.google.ar.sceneform.SkeletonNode;
import com.google.ar.sceneform.animation.ModelAnimator;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.AnimationData;
import com.google.ar.sceneform.rendering.ModelRenderable;

import ch.hackzurich.zoozurich.R;

public class CutePenguin extends ModelLoader {

    private ModelRenderable modelRenderable;
    private ModelAnimator animator;
    private int nextAnimation = 0;

    CutePenguin() {
        super(R.raw.pinguin_small);
    }

    SkeletonNode createNode(ModelRenderable modelRenderable) {
        Log.i("Zoo", "Cute Penguin loaded");

        this.modelRenderable = modelRenderable;

        SkeletonNode skeletonNode = new SkeletonNode();

        skeletonNode.setRenderable(modelRenderable);
        skeletonNode.setLocalScale(Vector3.one().scaled(0.25f));
        skeletonNode.setLocalRotation(Quaternion.axisAngle(Vector3.up(), -180.0f));

        return skeletonNode;
    }

    public void animate() {
        if (animator == null || !animator.isRunning()) {
            AnimationData data = modelRenderable.getAnimationData(nextAnimation);
            nextAnimation = (nextAnimation + 1) % modelRenderable.getAnimationDataCount();
            animator = new ModelAnimator(data, modelRenderable);
            animator.start();
        }
    }

}
