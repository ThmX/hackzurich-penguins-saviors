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

import ch.hackzurich.zoozurich.R;
import ch.hackzurich.zoozurich.core.Answer;
import ch.hackzurich.zoozurich.core.Question;
import ch.hackzurich.zoozurich.core.QuestionType;

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
    private Question question;
    private QuestionViewModel questionViewModel;

    private OnFragmentInteractionListener mListener;

    public QuestionFragment() {
        // Required empty public constructor
    }

    public static QuestionFragment newInstance(Question question) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putSerializable(QUESTION, question);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            question = (Question) getArguments().getSerializable(QUESTION);
        }
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

        questionViewModel.getAnswer().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer radioId) {
                onQuestionAnswered(radioId);
            }
        });
        answers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                questionViewModel.getAnswer().setValue(checkedId);
            }
        });

        // Set initial values
        questionViewModel.getQuestion().setValue(question.getText());

        answer_1.setText(question.getAnswers().get(0).getText());
        answer_2.setText(question.getAnswers().get(1).getText());
        answer_3.setText(question.getAnswers().get(2).getText());
        answer_4.setText(question.getAnswers().get(3).getText());
        return root;
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

        if (mListener != null) {
            mListener.onQuestionAnswered(score, question.getType());
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
        void onQuestionAnswered(int score, QuestionType type);
    }

}
