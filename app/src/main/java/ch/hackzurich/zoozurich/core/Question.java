package ch.hackzurich.zoozurich.core;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {
    private ArrayList<Answer> answers;
    private String text;
    private QuestionType type;

    public Question(String _text, ArrayList<Answer> _answers, QuestionType _type) {
        text = _text;
        answers = _answers;
        type = _type;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public String getText() {
        return text;
    }

    public QuestionType getType() {
        return type;
    }
}
