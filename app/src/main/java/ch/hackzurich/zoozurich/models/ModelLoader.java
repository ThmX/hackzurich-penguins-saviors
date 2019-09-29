package ch.hackzurich.zoozurich.models;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.SkeletonNode;
import com.google.ar.sceneform.rendering.ModelRenderable;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public abstract class ModelLoader {
    private Optional<CompletableFuture<ModelRenderable>> optionalFuture = Optional.empty();

    private SkeletonNode skeletonNode;

    private int resource;

    ModelLoader(int resource) {
        this.resource = resource;
    }

    boolean load(Context context) {
        Log.i("Zoo", "loading...");

        CompletableFuture<ModelRenderable> future = ModelRenderable.builder()
                .setSource(context, resource)
                .build()
                .thenApply(this::setRenderable)
                .exceptionally(throwable -> this.onException(context, throwable));

        if (future != null) {
            optionalFuture = Optional.of(future);
        }

        return future != null;
    }

    abstract SkeletonNode createNode(ModelRenderable modelRenderable);

    abstract public void animate();

    ModelRenderable setRenderable(ModelRenderable modelRenderable) {
        skeletonNode = createNode(modelRenderable);
        return modelRenderable;
    }

    ModelRenderable onException(Context context, Throwable throwable) {
        Toast toast = Toast.makeText(context, "Unable to load renderable", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        Log.e("Zoo", "Unable to load andy renderable", throwable);

        optionalFuture = Optional.empty();
        return null;
    }

    public boolean isLoaded() {
        return skeletonNode != null;
    }

    public void setParent(AnchorNode anchorNode) {
        skeletonNode.setParent(anchorNode);
    }

    public Node getNode() {
        return skeletonNode;
    }
}
