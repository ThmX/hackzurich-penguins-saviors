package ch.hackzurich.zoozurich.ui.box;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ch.hackzurich.zoozurich.MainActivity;
import ch.hackzurich.zoozurich.R;
import ch.hackzurich.zoozurich.core.QuestionType;
import ch.hackzurich.zoozurich.core.ZooService;
import ch.hackzurich.zoozurich.ui.info.InfoFragment;
import ch.hackzurich.zoozurich.ui.questions.QuestionFragment;


enum BoxViewEnum {
    QUESTION,
    INFO
}

public class BoxFragment extends Fragment {

    private BoxViewModel mViewModel;

    public static BoxFragment newInstance() {
        return new BoxFragment();
    }

    private InfoFragment infoFragment;
    private QuestionFragment questionFragment;
    private ZooService zooService;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel =
                ViewModelProviders.of(this).get(BoxViewModel.class);
        View root = inflater.inflate(R.layout.box_fragment, container, false);
        infoFragment = (InfoFragment) getChildFragmentManager().findFragmentById(R.id.info_fragment);
        questionFragment = (QuestionFragment) getChildFragmentManager().findFragmentById(R.id.question_fragment);
        questionFragment.getView().setVisibility(View.GONE);
        infoFragment.getView().setVisibility(View.GONE);

        mViewModel.getCurrentView().observe(this, new Observer<BoxViewEnum>() {
            @Override
            public void onChanged(@Nullable BoxViewEnum bv) {
                if (bv == BoxViewEnum.INFO) {
                    questionFragment.getView().setVisibility(View.GONE);
                    infoFragment.getView().setVisibility(View.VISIBLE);
                } else {
                    questionFragment.getView().setVisibility(View.VISIBLE);
                    infoFragment.getView().setVisibility(View.GONE);
                }
            }
        });

        return root;
    }

    public void initializeBox(String qrCodeId) {
        int awarenessQuestionId;
        int lifestyleQuestionId;
        int infoId;

        switch (qrCodeId) {
            case "Adelie":
                awarenessQuestionId = 1;
                lifestyleQuestionId = 2;
                infoId = 1;
                break;
            case "Emperor":
                awarenessQuestionId = 3;
                lifestyleQuestionId = 4;
                infoId = 3;
                break;
            case "Gentoo":
                awarenessQuestionId = 5;
                lifestyleQuestionId = 6;
                infoId = 5;
                break;
            default:
                return;
        }

        mViewModel.getAwarenessQuestionId().setValue(awarenessQuestionId);
        mViewModel.getLifestyleQuestionId().setValue(lifestyleQuestionId);
        mViewModel.getInfoId().setValue(infoId);
        setQuestion(awarenessQuestionId);
    }

    private void setQuestion(Integer questionId) {
        questionFragment.setQuestionById(questionId);
        mViewModel.getCurrentView().setValue(BoxViewEnum.QUESTION);
    }

    private void setInfo(Integer infoId) {
        infoFragment.setInfoById(infoId);
        mViewModel.getCurrentView().setValue(BoxViewEnum.INFO);
    }

    public void onQuestionAnswered(int score, QuestionType type) {
        zooService = ((MainActivity) getActivity()).getZooService();
        zooService.increaseScore(score, type);

        if(type == QuestionType.LIFESTYLE) {
            questionFragment.getView().setVisibility(View.GONE);
            infoFragment.getView().setVisibility(View.GONE);
        } else {
            setInfo(mViewModel.getInfoId().getValue());
        }
    }

    public void onInfoDisplayed() {
        setQuestion(mViewModel.getLifestyleQuestionId().getValue());
    }
}
