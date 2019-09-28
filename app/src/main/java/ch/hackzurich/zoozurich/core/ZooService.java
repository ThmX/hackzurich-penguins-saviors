package ch.hackzurich.zoozurich.core;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class ZooService {
    private HashMap<Integer, Question> questions;
    private int awarenessScore = 0;
    private int lifestyleScore = 0;

    public ZooService () {
        questions = new HashMap<Integer, Question>();

        questions.put(1, createQuestion(
                "Question text",
                QuestionType.LIFESTYLE,
                "answer1",
                1,
                "answer2",
                2,
                "answer3",
                3,
                "answer4",
                4
        ));
        questions.put(2, createQuestion(
                "Question 2 text",
                QuestionType.LIFESTYLE,
                "answer5",
                1,
                "answer6",
                2,
                "answer7",
                3,
                "answer8",
                4
        ));
    }

    private Question createQuestion(String text, QuestionType type, String answer1_text, int answer1_score, String answer2_text, int answer2_score, String answer3_text, int answer3_score, String answer4_text, int answer4_score) {
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer(answer1_score, answer1_text));
        answers.add(new Answer(answer2_score, answer2_text));
        answers.add(new Answer(answer3_score, answer3_text));
        answers.add(new Answer(answer4_score, answer4_text));

        return new Question(text, answers, type);
    }

    public Question getQuestionById(int id) {
        return questions.get(id);
    }

    public HashMap<Integer, Question> getQuestions() {
        return questions;
    }

    public void increaseScore(int score, QuestionType type) {
        if (type == QuestionType.AWARENESS) {
            awarenessScore += score;
        } else {
            lifestyleScore += score;
        }

        Log.e("Awareness SCORE", String.valueOf(awarenessScore));
        Log.e("Lifestyle SCORE", String.valueOf(lifestyleScore));
    }
}
