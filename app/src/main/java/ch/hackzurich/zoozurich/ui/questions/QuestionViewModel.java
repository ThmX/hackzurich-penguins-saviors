package ch.hackzurich.zoozurich.ui.questions;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QuestionViewModel extends ViewModel {
    LiveData answer;
    private MutableLiveData<String> question;


    public QuestionViewModel() {
    }

    public MutableLiveData<String> getQuestion() {
        if (question == null) {
            question = new MutableLiveData<String>();
        }
        return question;
    }
}