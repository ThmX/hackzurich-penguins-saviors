package ch.hackzurich.zoozurich.ui.avatar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import ch.hackzurich.zoozurich.MainActivity;
import ch.hackzurich.zoozurich.R;
import ch.hackzurich.zoozurich.core.ZooService;

import static androidx.navigation.Navigation.findNavController;

public class AvatarFragment extends Fragment {

    private ZooService zooService;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        zooService = ((MainActivity) getActivity()).getZooService();

        View root = inflater.inflate(R.layout.fragment_avatar, container, false);

        final NavController navController = findNavController(container);

        final EditText etName = root.findViewById(R.id.editTextAvatar);

        final ImageButton btCute = root.findViewById(R.id.btCute);
        btCute.setOnClickListener((view) -> {
            Log.i("Zoo", "clicked cute");
            zooService.setName(etName.getText().toString());
            zooService.setSpecie("Cute");
            navController.navigate(R.id.navigation_guide);
        });

        final ImageButton btKing = root.findViewById(R.id.btKing);
        btKing.setOnClickListener((view) -> {
            Log.i("Zoo", "clicked king");
            zooService.setName(etName.getText().toString());
            zooService.setSpecie("King");
            navController.navigate(R.id.navigation_guide);
        });

        return root;
    }
}
