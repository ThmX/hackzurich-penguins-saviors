package ch.hackzurich.zoozurich.ui.welcome;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import ch.hackzurich.zoozurich.MainActivity;
import ch.hackzurich.zoozurich.R;
import ch.hackzurich.zoozurich.core.ZooService;

import static androidx.navigation.Navigation.findNavController;

public class WelcomeFragment extends Fragment implements View.OnClickListener {

    private WelcomeViewModel mViewModel;
    private NavController navController;
    private ZooService zooService;

    public static WelcomeFragment newInstance() {
        return new WelcomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        zooService = ((MainActivity) getActivity()).getZooService();
        View root = inflater.inflate(R.layout.welcome_fragment, container, false);
        navController = findNavController(container);
        Button btn = root.findViewById(R.id.lets_get_started_btn);
        ImageView imageView = root.findViewById(R.id.welcome_penguin_image);

        if (zooService.getSpecie().equals("Cute")) {
            imageView.setImageResource(R.mipmap.penguin_cute);
        } else {
            imageView.setImageResource(R.mipmap.penguin_king);
        }
        btn.setOnClickListener(this);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(WelcomeViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View v) {
        navController.navigate(R.id.navigation_guide);
    }
}
