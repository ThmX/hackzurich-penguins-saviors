package ch.hackzurich.zoozurich.core;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {
    private int id;
    private ArrayList<Answer> answers;
    private String text;
    private QuestionType type;

    public Question(int _id, String _text, ArrayList<Answer> _answers, QuestionType _type) {
        id = _id;
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

    public int getId() {
        return id;
    }
}
