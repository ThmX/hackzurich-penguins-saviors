package ch.hackzurich.zoozurich.core;

import java.io.Serializable;

public class Answer implements Serializable {
    private int score;
    private String text;

    public Answer(int _score, String _text) {
        score = _score;
        text = _text;
    }

    public String getText() {
        return text;
    }

    public int getScore() {
        return score;
    }
}
