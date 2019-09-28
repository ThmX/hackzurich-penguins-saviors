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
    private String answers_1;
    private String answers_2;
    private String answers_3;
    private String answers_4;
    private QuestionViewModel questionViewModel;

    private OnFragmentInteractionListener mListener;

    public QuestionFragment() {
        // Required empty public constructor
    }


    public static QuestionFragment newInstance(String question, String answers_1, String answers_2, String answers_3, String answers_4) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putString(QUESTION, question);
        args.putString(ANSWERS_1, answers_1);
        args.putString(ANSWERS_2, answers_2);
        args.putString(ANSWERS_3, answers_3);
        args.putString(ANSWERS_4, answers_4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            question = getArguments().getString(QUESTION);
            answers_1 = getArguments().getString(ANSWERS_1);
            answers_2 = getArguments().getString(ANSWERS_2);
            answers_3 = getArguments().getString(ANSWERS_3);
            answers_4 = getArguments().getString(ANSWERS_4);
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
        questionViewModel.getQuestion().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                questionText.setText(s);
            }
        });
        questionViewModel.getQuestion().setValue(question);
        final Button button = root.findViewById(R.id.submit_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onQuestionAnswered();
            }
        });
        return root;
    }

    public void onQuestionAnswered() {
        if (mListener != null) {
            questionViewModel =
                    ViewModelProviders.of(this).get(QuestionViewModel.class);

            mListener.onQuestionAnswered(questionViewModel.getQuestion().getValue());
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
        void onQuestionAnswered(String question);
    }

}
