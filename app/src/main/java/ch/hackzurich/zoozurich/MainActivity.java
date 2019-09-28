package ch.hackzurich.zoozurich;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import ch.hackzurich.zoozurich.core.QuestionType;
import ch.hackzurich.zoozurich.core.ZooService;

import ch.hackzurich.zoozurich.ui.questions.QuestionFragment;

public class MainActivity extends AppCompatActivity implements QuestionFragment.OnFragmentInteractionListener {

    private ZooService zooService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zooService = new ZooService();
    }

    public void onQuestionAnswered(int score, QuestionType type) {
        zooService.increaseScore(score, type);
    }

    public ZooService getZooService() {
        return zooService;
    }
}
