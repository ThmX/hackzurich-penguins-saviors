package ch.hackzurich.zoozurich.ui.questions;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QuestionViewModel extends ViewModel {
    private MutableLiveData<Integer> userAnswer;
    private MutableLiveData<String> question;
    private MutableLiveData<String> answer1Text;
    private MutableLiveData<String> answer2Text;
    private MutableLiveData<String> answer3Text;
    private MutableLiveData<String> answer4Text;

    public QuestionViewModel() {
    }

    public MutableLiveData<String> getQuestion() {
        if (question == null) {
            question = new MutableLiveData<String>();
        }
        return question;
    }

    public MutableLiveData<Integer> getUserAnswer() {
        if (userAnswer == null) {
            userAnswer = new MutableLiveData<Integer>();
        }
        return userAnswer;
    }
    public MutableLiveData<String> getAnswer1Text() {
        if (answer1Text == null) {
            answer1Text = new MutableLiveData<String>();
        }
        return answer1Text;
    }
    public MutableLiveData<String> getAnswer2Text() {
        if (answer2Text == null) {
            answer2Text = new MutableLiveData<String>();
        }
        return answer2Text;
    }
    public MutableLiveData<String> getAnswer3Text() {
        if (answer3Text == null) {
            answer3Text = new MutableLiveData<String>();
        }
        return answer3Text;
    }
    public MutableLiveData<String> getAnswer4Text() {
        if (answer4Text == null) {
            answer4Text = new MutableLiveData<String>();
        }
        return answer4Text;
    }
}