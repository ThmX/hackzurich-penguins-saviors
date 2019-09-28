package ch.hackzurich.zoozurich.core;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ZooService {
    private ArrayList<Question> questions;

    public ZooService (Context context) {
        loadQuestions(context);
    }

    private void loadQuestions(Context context) {
        questions = new ArrayList<Question>();

        try {
            JSONObject obj = new JSONObject(readJSONFromAsset(context));

            JSONArray json_questions = obj.getJSONArray("questions");

            for (int i=0; i< json_questions.length(); i++) {
                JSONObject json_question = json_questions.getJSONObject(i);
                ArrayList<Answer> answers = new ArrayList<Answer>();
                JSONArray json_answers = json_question.getJSONArray("answers");

                for(int j=0; j< json_answers.length(); j++) {
                    JSONObject json_answer = json_answers.getJSONObject(i);
                    answers.add(new Answer(json_answer.getInt("score"), json_answer.getString("text")));
                }

                questions.add(new Question(json_question.getString("text"), answers));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String readJSONFromAsset(Context context) {
        String json;

        try {
            InputStream is = context.getAssets().open("raw/questions.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}
