package ch.hackzurich.zoozurich.ui.summary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import ch.hackzurich.zoozurich.MainActivity;
import ch.hackzurich.zoozurich.R;
import ch.hackzurich.zoozurich.core.Answer;
import ch.hackzurich.zoozurich.ui.questions.QuestionFragment;
import ch.hackzurich.zoozurich.core.ZooService;

public class SummaryFragment extends Fragment {

    private ZooService zooService;

    private SummaryViewModel summaryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        zooService = ((MainActivity) getActivity()).getZooService();

        summaryViewModel = ViewModelProviders.of(this).get(SummaryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        summaryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.question_placeholder, QuestionFragment.newInstance(zooService.getQuestions().get(0)));
        fragmentTransaction.commit();

        return root;
    }
}