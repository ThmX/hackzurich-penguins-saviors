package ch.hackzurich.zoozurich.ui.questions;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ch.hackzurich.zoozurich.MainActivity;
import ch.hackzurich.zoozurich.R;
import ch.hackzurich.zoozurich.core.Answer;
import ch.hackzurich.zoozurich.core.Question;
import ch.hackzurich.zoozurich.core.QuestionType;
import ch.hackzurich.zoozurich.core.ZooService;
import ch.hackzurich.zoozurich.ui.box.BoxFragment;


public class QuestionFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String QUESTION = "question";
    private Question question;
    private QuestionViewModel questionViewModel;

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        questionViewModel =
                ViewModelProviders.of(this).get(QuestionViewModel.class);
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_question, container, false);
        final TextView questionText = root.findViewById(R.id.question_text);
        final TextView answer_1 = root.findViewById(R.id.answer_1);
        final TextView answer_2 = root.findViewById(R.id.answer_2);
        final TextView answer_3 = root.findViewById(R.id.answer_3);
        final TextView answer_4 = root.findViewById(R.id.answer_4);
        final RadioGroup answers = root.findViewById(R.id.question_answers);

        questionViewModel.getQuestion().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                questionText.setText(s);
            }
        });

        questionViewModel.getUserAnswer().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer radioId) {
                onQuestionAnswered(radioId);
            }
        });
        questionViewModel.getAnswer1Text().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String text) {
                answer_1.setText(text);
            }
        });
        questionViewModel.getAnswer2Text().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String text) {
                answer_2.setText(text);
            }
        });
        questionViewModel.getAnswer3Text().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String text) {
                answer_3.setText(text);
            }
        });
        questionViewModel.getAnswer4Text().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String text) {
                answer_4.setText(text);
            }
        });
        answers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                questionViewModel.getUserAnswer().setValue(checkedId);
            }
        });

        return root;
    }

    public void setQuestionById(int id) {
        final RadioGroup radioGroup = getView().findViewById(R.id.question_answers);
        radioGroup.clearCheck();

        ZooService zooService = ((MainActivity) getActivity()).getZooService();
        question = zooService.getQuestionById(id);
        ArrayList<Answer> answers = question.getAnswers();

        // Set initial values
        questionViewModel.getQuestion().setValue(question.getText());
        questionViewModel.getAnswer1Text().setValue(answers.get(0).getText());
        questionViewModel.getAnswer2Text().setValue(answers.get(1).getText());
        questionViewModel.getAnswer3Text().setValue(answers.get(2).getText());
        questionViewModel.getAnswer4Text().setValue(answers.get(3).getText());
    }

    private void onQuestionAnswered(int radioId) {

        int score = -1;

        switch(radioId) {
            case R.id.answer_1:
                score = question.getAnswers().get(0).getScore();
                break;
            case R.id.answer_2:
                score = question.getAnswers().get(1).getScore();
                break;
            case R.id.answer_3:
                score = question.getAnswers().get(2).getScore();
                break;
            case R.id.answer_4:
                score = question.getAnswers().get(3).getScore();
                break;
        }

        BoxFragment boxFragment = (BoxFragment) getParentFragment();
        boxFragment.onQuestionAnswered(score, question.getType());
    }
}
