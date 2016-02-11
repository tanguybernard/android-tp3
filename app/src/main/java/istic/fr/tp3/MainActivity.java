package istic.fr.tp3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends Activity implements
        MainActivityFragment.OnRegSelectedListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reglist_fragment);
    }

    @Override
    public void onRegSelected(String tutUrl) {
        RegViewerFragment viewer = (RegViewerFragment) getFragmentManager()
                .findFragmentById(R.id.regview_fragment);

        if (viewer == null ) {//|| !viewer.isInLayout()) {
            System.out.println("GO VIEW");
            Intent showContent = new Intent(getApplicationContext(),
                    RegViewerActivity.class);
            showContent.setData(Uri.parse(tutUrl));
            startActivity(showContent);
        } else {
            System.out.println("ONE VIEW");
            viewer.updateUrl(tutUrl);
        }
    }
}
