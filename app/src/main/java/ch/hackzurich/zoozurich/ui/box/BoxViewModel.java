package ch.hackzurich.zoozurich.ui.box;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class BoxViewModel extends ViewModel {
    private MutableLiveData<BoxViewEnum> currentView;
    private MutableLiveData<Integer> awarenessQuestionId;
    private MutableLiveData<Integer> lifestyleQuestionId;
    private MutableLiveData<Integer> infoId;


    public MutableLiveData<BoxViewEnum> getCurrentView() {
        if (currentView == null) {
            currentView = new MutableLiveData<BoxViewEnum>();
        }
        return currentView;
    }

    public MutableLiveData<Integer> getAwarenessQuestionId() {
        if (awarenessQuestionId == null) {
            awarenessQuestionId = new MutableLiveData<Integer>();
        }
        return awarenessQuestionId;
    }

    public MutableLiveData<Integer> getLifestyleQuestionId() {
        if (lifestyleQuestionId == null) {
            lifestyleQuestionId = new MutableLiveData<Integer>();
        }
        return lifestyleQuestionId;
    }

    public MutableLiveData<Integer> getInfoId() {
        if (infoId == null) {
            infoId = new MutableLiveData<Integer>();
        }
        return infoId;
    }
}
