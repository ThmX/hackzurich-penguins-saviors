package ch.hackzurich.zoozurich.ui.questions;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QuestionViewModel extends ViewModel {
    private MutableLiveData<Integer> answer;
    private MutableLiveData<String> question;


    public QuestionViewModel() {
    }

    public MutableLiveData<String> getQuestion() {
        if (question == null) {
            question = new MutableLiveData<String>();
        }
        return question;
    }

    public MutableLiveData<Integer> getAnswer() {
        if (answer == null) {
            answer = new MutableLiveData<Integer>();
        }
        return answer;
    }
}