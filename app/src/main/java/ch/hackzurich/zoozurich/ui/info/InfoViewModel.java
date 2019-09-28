package ch.hackzurich.zoozurich.ui.info;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InfoViewModel extends ViewModel {
    private MutableLiveData<String> text;

    public InfoViewModel() {
    }

    public MutableLiveData<String> getText() {
        if (text == null) {
            text = new MutableLiveData<String>();
        }
        return text;
    }
}