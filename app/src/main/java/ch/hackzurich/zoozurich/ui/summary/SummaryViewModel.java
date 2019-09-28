package ch.hackzurich.zoozurich.ui.summary;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SummaryViewModel extends ViewModel {

    private MutableLiveData<Integer> awarenessScore;
    private MutableLiveData<Integer> lifestyleScore;

    public MutableLiveData<Integer> getAwarenessScore() {
        if (awarenessScore == null) {
            awarenessScore = new MutableLiveData<Integer>();
        }
        return awarenessScore;
    }

    public MutableLiveData<Integer> getLifestyleScore() {
        if (lifestyleScore == null) {
            lifestyleScore = new MutableLiveData<Integer>();
        }
        return lifestyleScore;
    }
}