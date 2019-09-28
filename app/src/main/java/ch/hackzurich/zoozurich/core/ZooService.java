package ch.hackzurich.zoozurich.core;

import android.util.Log;

import java.util.ArrayList;

public class ZooService {
    private ArrayList<Question> lifestyleQuestions;
    private ArrayList<Question> awarenessQuestions;
    private int awarenessScore = 0;
    private int lifestyleScore = 0;

    public ZooService () {
        lifestyleQuestions = new ArrayList<Question>();
        awarenessQuestions = new ArrayList<Question>();

        // Lifestyle
        lifestyleQuestions.add(createQuestion(
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
        lifestyleQuestions.add(createQuestion(
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


        // Awareness
        awarenessQuestions.add(createQuestion(
                "Question awarness text",
                QuestionType.AWARENESS,
                "answer1",
                1,
                "answer2",
                0,
                "answer3",
                0,
                "answer4",
                0
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

    public ArrayList<Question> getLifestyleQuestions() {
        return lifestyleQuestions;
    }

    public ArrayList<Question> getAwarenessQuestions() {
        return awarenessQuestions;
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
