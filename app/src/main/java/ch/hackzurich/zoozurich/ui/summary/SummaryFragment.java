package ch.hackzurich.zoozurich.ui.summary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import ch.hackzurich.zoozurich.MainActivity;
import ch.hackzurich.zoozurich.R;
import ch.hackzurich.zoozurich.core.ZooService;

public class SummaryFragment extends Fragment {

    private ZooService zooService;

    private SummaryViewModel summaryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        zooService = ((MainActivity) getActivity()).getZooService();
        summaryViewModel = ViewModelProviders.of(this).get(SummaryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_summary, container, false);
        final TextView awarenessScoreText = root.findViewById(R.id.awareness_score);
        final TextView lifestyleScoreText = root.findViewById(R.id.lifestyle_score);
        summaryViewModel.getAwarenessScore().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer s) {
                awarenessScoreText.setText(String.valueOf(s));
            }
        });
        summaryViewModel.getLifestyleScore().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer s) {
                lifestyleScoreText.setText(String.valueOf(s));
            }
        });

        summaryViewModel.getAwarenessScore().setValue(zooService.getAwarenessScore());
        summaryViewModel.getLifestyleScore().setValue(zooService.getLifestyleScore());

        return root;
    }
}