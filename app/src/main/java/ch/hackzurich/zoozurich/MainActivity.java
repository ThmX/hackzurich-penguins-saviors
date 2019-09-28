package ch.hackzurich.zoozurich;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import ch.hackzurich.zoozurich.core.ZooService;

public class MainActivity extends AppCompatActivity {

    private ZooService zooService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zooService = new ZooService();
    }

    public ZooService getZooService() {
        return zooService;
    }
}
