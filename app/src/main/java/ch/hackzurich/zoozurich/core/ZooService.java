package ch.hackzurich.zoozurich.core;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import ch.hackzurich.zoozurich.R;

public class ZooService {
    private String name;
    private String specie;
    private HashMap<Integer, Question> questions;
    private HashMap<Integer, Info> infos;
    private int awarenessScore = 0;
    private int lifestyleScore = 0;
    private HashSet<Integer> answeredQuestions;

    public ZooService() {
        questions = new HashMap<Integer, Question>();
        infos = new HashMap<Integer, Info>();
        answeredQuestions = new HashSet<Integer>();

        questions.put(1, createQuestion(
            1,
            "How long would you have to eat vegetarian to save as much CO2 as a flight from Zurich to New York produces?",
            QuestionType.AWARENESS,
            "A) one year ",
            0,
            "B) 6 months",
            0,
            "C) two and a half years",
            0,
            "D) one year and 8 months",
            1
        ));
        questions.put(2, createQuestion(
            2,
            "How long did you have to travel by car to come to the Zoo Zurich?",
            QuestionType.LIFESTYLE,
            "A) We came by public transportation",
            1,
            "B) less than one hours",
            2,
            "C) one to two hours",
            3,
            "D) more than two hours",
            4
        ));
        questions.put(3, createQuestion(
            3,
            " How much plastic do big retail companies in switzerland save per year since plastic bags cost 5 rappen?",
            QuestionType.AWARENESS,
            "A) 850t ",
            1,
            "B) 500t",
            0,
            "C) 170t",
            0,
            "D) 90t",
            0
        ));
        questions.put(4, createQuestion(
            4,
            " What snack would you choose?",
            QuestionType.LIFESTYLE,
            "A) An apple",
            1,
            "B) Tomatos in a carton box",
            2,
            "C) Cracker in a plastic package",
            3,
            "D) A peeled orange in a plastic container",
            4
        ));
        questions.put(5, createQuestion(
            5,
            "How many times a month does an average swiss person eat imported fish?",
            QuestionType.AWARENESS,
            "A) 4 times per month ",
            1,
            "B) 1 time per month",
            0,
            "C) 8 times per month",
            0,
            "D) 3 times per month",
            0
        ));
        questions.put(6, createQuestion(
            6,
            " Which fish do you eat most often?",
            QuestionType.LIFESTYLE,
            "A) I don’t eat fish at all ",
            1,
            "B) Trout from the lake of Zurich",
            2,
            "C) Pangasius filet",
            3,
            "D) Tuna steak",
            4
        ));

        infos.put(1, new Info("It is 1 year and 8 months. With raising CO2 also the temperature raises. This also effects the ocean and fish will follow the cool water southwards. This means that our penguins have to travel farther from year to year to feed their chicks on the islands!",
                R.mipmap.penguin_routes));
        infos.put(3, new Info("It is in fact 850t, isn't that incredible? This means that there is less plastic ending up in the ocean and potentially being ingested by a penguin because it mistakes the plastic for his lunch",
                R.mipmap.garbage));
        infos.put(5, new Info("It is 160g per week, which corresponds to one fish filet. Did you know that penguins, if they were our size, would eat approximately 600 times more fish than us human?",
                R.mipmap.fish_consumption));
    }

    private Question createQuestion(int id, String text, QuestionType type, String answer1_text, int answer1_score, String answer2_text, int answer2_score, String answer3_text, int answer3_score, String answer4_text, int answer4_score) {
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer(answer1_score, answer1_text));
        answers.add(new Answer(answer2_score, answer2_text));
        answers.add(new Answer(answer3_score, answer3_text));
        answers.add(new Answer(answer4_score, answer4_text));

        return new Question(id, text, answers, type);
    }

    public Question getQuestionById(Integer id) {
        return questions.get(id);
    }

    public Info getInfoById(Integer id) {
        return infos.get(id);
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

    public void addAnsweredQuestionId(int questionId) {
        answeredQuestions.add(questionId);
    }

    public boolean isQuestionAnswered(int questionId) {
        return answeredQuestions.contains(questionId);
    }

    public int getAwarenessScore() {
        return awarenessScore;
    }

    public int getLifestyleScore() {
        return lifestyleScore;
    }

    public HashSet<Integer> getAnsweredQuestions() {
        return answeredQuestions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }
}
