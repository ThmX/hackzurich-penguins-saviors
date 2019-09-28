package ch.hackzurich.zoozurich.ui.guide;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GuideViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GuideViewModel() {
        Log.i("GuideViewModel", "constructor");
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
