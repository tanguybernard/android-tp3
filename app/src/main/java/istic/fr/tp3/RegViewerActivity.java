package istic.fr.tp3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class RegViewerActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regview_fragment);

        Intent launchingIntent = getIntent();
        String content = launchingIntent.getData().toString();

        RegViewerFragment viewer = (RegViewerFragment) getFragmentManager()
                .findFragmentById(R.id.regview_fragment);

        viewer.updateUrl(content);
    }

}
