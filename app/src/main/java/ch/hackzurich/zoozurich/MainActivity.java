package ch.hackzurich.zoozurich;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ch.hackzurich.zoozurich.core.QuestionType;
import ch.hackzurich.zoozurich.core.ZooService;

import ch.hackzurich.zoozurich.models.ModelService;
import ch.hackzurich.zoozurich.ui.questions.QuestionFragment;

public class MainActivity extends AppCompatActivity {

    private ZooService zooService;

    private ModelService modelService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zooService = new ZooService();
        modelService = new ModelService();
        modelService.load(this);
    }

    public ZooService getZooService() {
        return zooService;
    }

    public ModelService getModelService() {
        return modelService;
    }
}
