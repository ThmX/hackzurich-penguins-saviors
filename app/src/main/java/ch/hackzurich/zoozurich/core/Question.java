package ch.hackzurich.zoozurich.core;

import java.util.ArrayList;


public class Question {
    private String questionText;
    private ArrayList<Answer> answers;

    public Question(String _questionText, ArrayList<Answer> _answers) {
        questionText = _questionText;
        answers = _answers;
    }

    public String getQuestionText() {
        return questionText;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }
}
