package istic.fr.tp3;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ListView;

public class DetailFragment extends Fragment {


    private ClickListener clickListener;

    public interface ClickListener{
        public void onLocate();
    }


    String mURL = "";
    String currentRegion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("DetailFragment", "onCreate()");


    }




    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v("DetailFragment", "onActivityCreated()");
        if (savedInstanceState != null) {
            mURL = savedInstanceState.getString("currentURL", "");
        }
        if(!mURL.trim().equalsIgnoreCase("")){
            WebView myWebView = (WebView) getView().findViewById(R.id.pageInfo);
            myWebView.getSettings().setJavaScriptEnabled(true);
            myWebView.setWebViewClient(new MyWebViewClient());
            myWebView.loadUrl(mURL.trim());
        }

        getView().findViewById(R.id.location_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onLocate();
            }
        });

    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("currentURL", mURL);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v("DetailFragment", "onCreateView()");
        View view = inflater.inflate(R.layout.detail_view, container, false);
        return view;
    }



    public void setURLContent(String URL) {
        mURL = URL;
    }

    public void setCurrentRegion(String currentRegion){
        this.currentRegion = currentRegion;
    }

    public String getRegion(){
        return this.currentRegion;
    }

    public void updateURLContent(String URL) {
        mURL = URL;
        System.out.println("updateURLContent: "+URL);
        WebView myWebView = (WebView) getView().findViewById(R.id.pageInfo);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new MyWebViewClient());
        myWebView.loadUrl(mURL.trim());
    }


    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
    }



}
