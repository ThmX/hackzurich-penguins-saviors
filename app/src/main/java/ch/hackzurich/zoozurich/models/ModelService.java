package ch.hackzurich.zoozurich.models;

import android.content.Context;

public class ModelService {

    private KingPenguin kingPenguin = new KingPenguin();

    public void load(Context context) {
        kingPenguin.load(context);
    }

    public KingPenguin getKingPenguin() {
        return kingPenguin;
    }

    public boolean isLoaded() {
        return kingPenguin.isLoaded();
    }
}
