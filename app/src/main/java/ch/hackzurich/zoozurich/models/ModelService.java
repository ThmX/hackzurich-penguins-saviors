package ch.hackzurich.zoozurich.models;

import android.content.Context;

public class ModelService {

    private KingPenguin kingPenguin = new KingPenguin();

    private CutePenguin cutePenguin = new CutePenguin();

    public void load(Context context) {
        kingPenguin.load(context);
        cutePenguin.load(context);
    }

    public KingPenguin getKingPenguin() {
        return kingPenguin;
    }

    public CutePenguin getCutePenguin() {
        return cutePenguin;
    }

    public boolean isLoaded() {
        return kingPenguin.isLoaded() && cutePenguin.isLoaded();
    }
}
