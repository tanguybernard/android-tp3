package istic.fr.tp3;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
        import android.webkit.WebView;

public class RegViewerFragment extends Fragment {
    private WebView viewer = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewer = (WebView) inflater
                .inflate(R.layout.reg_view, container, false);
        return viewer;
    }

    public void updateUrl(String newUrl) {
        if (viewer != null) {
            viewer.loadUrl(newUrl);
        }
    }
}
