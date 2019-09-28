package ch.hackzurich.zoozurich.ui.avatar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import ch.hackzurich.zoozurich.MainActivity;
import ch.hackzurich.zoozurich.R;
import ch.hackzurich.zoozurich.core.ZooService;

import static androidx.navigation.Navigation.findNavController;

public class AvatarFragment extends Fragment {

    private ZooService zooService;

    private AvatarViewModel avatarViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        zooService = ((MainActivity) getActivity()).getZooService();

        avatarViewModel = ViewModelProviders.of(this).get(AvatarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        avatarViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final NavController navController = findNavController(container);

        final Button bt = root.findViewById(R.id.home_button);
        bt.setOnClickListener((view) -> {
            Log.i("Zoo", "clicked");
            navController.navigate(R.id.navigation_guide);
            // TODO
        });

        return root;
    }
}
