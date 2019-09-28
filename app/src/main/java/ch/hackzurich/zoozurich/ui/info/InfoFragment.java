package ch.hackzurich.zoozurich.ui.info;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ch.hackzurich.zoozurich.MainActivity;
import ch.hackzurich.zoozurich.R;
import ch.hackzurich.zoozurich.core.Info;
import ch.hackzurich.zoozurich.core.ZooService;
import ch.hackzurich.zoozurich.ui.box.BoxFragment;

public class InfoFragment extends Fragment implements View.OnClickListener {

    private InfoViewModel mViewModel;
    private ZooService zooService;
    private WebView infoWebview;

    public static InfoFragment newInstance() {
        return new InfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        zooService = ((MainActivity) getActivity()).getZooService();
        mViewModel = ViewModelProviders.of(this).get(InfoViewModel.class);
        View root = inflater.inflate(R.layout.info_fragment, container, false);
        final TextView textView = root.findViewById(R.id.info_fragment_text);
        mViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        Button nextButton = root.findViewById(R.id.info_next_button);
        ImageView imageView = root.findViewById(R.id.info_image);
        imageView.setVisibility(View.GONE);

        nextButton.setOnClickListener(this);
        infoWebview = root.findViewById(R.id.info_webview);

        infoWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        return root;
    }

    @Override
    public void onClick(View v)
    {
        BoxFragment boxFragment = (BoxFragment) getParentFragment();
        boxFragment.onInfoDisplayed();
    }

    public void setInfoById(Integer infoId) {
        Info info = zooService.getInfoById(infoId);
        mViewModel.getText().setValue(info.getText());
        ImageView imageView = getView().findViewById(R.id.info_image);
        imageView.setImageResource(info.getImageResourceId());
        infoWebview.loadUrl("https://public.tableau.com/profile/gerome.wolf#!/vizhome/Penguins_15697106014940/Blatt1?:showVizHome=no&:embed=true");
        imageView.setVisibility(View.VISIBLE);
    }
}