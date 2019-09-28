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

import ch.hackzurich.zoozurich.R;
import ch.hackzurich.zoozurich.core.Answer;
import ch.hackzurich.zoozurich.core.Question;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link QuestionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String QUESTION = "question";
    // TODO: Maybe pass answers object here
    private static final String ANSWERS_1 = "answers_1";
    private static final String ANSWERS_2 = "answers_2";
    private static final String ANSWERS_3 = "answers_3";
    private static final String ANSWERS_4 = "answers_4";
    private String question;
    private Answer answers_1;
    private Answer answers_2;
    private Answer answers_3;
    private Answer answers_4;
    private QuestionViewModel questionViewModel;

    private OnFragmentInteractionListener mListener;

    public QuestionFragment() {
        // Required empty public constructor
    }


    public static QuestionFragment newInstance(Question question) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putString(QUESTION, question.getQuestionText());
        ArrayList<Answer> answers = question.getAnswers();
        args.putSerializable(ANSWERS_1, answers.get(0));
        args.putSerializable(ANSWERS_2, answers.get(1));
        args.putSerializable(ANSWERS_3, answers.get(2));
        args.putSerializable(ANSWERS_4, answers.get(3));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            question = getArguments().getString(QUESTION);
            answers_1 = (Answer) getArguments().getSerializable(ANSWERS_1);
            answers_2 = (Answer) getArguments().getSerializable(ANSWERS_2);
            answers_3 = (Answer) getArguments().getSerializable(ANSWERS_3);
            answers_4 = (Answer) getArguments().getSerializable(ANSWERS_4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        questionViewModel =
                ViewModelProviders.of(this).get(QuestionViewModel.class);
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_question, container, false);
        final Button button = root.findViewById(R.id.submit_button);
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

        questionViewModel.getAnswer().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer score) {
                if(!button.isEnabled()) {
                    button.setEnabled(true);
                }
            }
        });
        answers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                questionViewModel.getAnswer().setValue(checkedId);
            }
        });

        // Set initial values
        questionViewModel.getQuestion().setValue(question);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onQuestionAnswered(v);
            }
        });
        button.setEnabled(false);

        answer_1.setText(answers_1.getText());
        answer_2.setText(answers_2.getText());
        answer_3.setText(answers_3.getText());
        answer_4.setText(answers_4.getText());
        return root;
    }

    public void onQuestionAnswered(View v) {
        questionViewModel =
                ViewModelProviders.of(this).get(QuestionViewModel.class);

        int radioId = questionViewModel.getAnswer().getValue();

        int score = -1;

        switch(radioId) {
            case R.id.answer_1:
                score = answers_1.getScore();
                break;
            case R.id.answer_2:
                score = answers_2.getScore();
                break;
            case R.id.answer_3:
                score = answers_3.getScore();
                break;
            case R.id.answer_4:
                score = answers_4.getScore();
                break;
        }

        if (mListener != null) {
            mListener.onQuestionAnswered(score);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement onQuestionAnsweredListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onQuestionAnswered(int score);
    }

}
