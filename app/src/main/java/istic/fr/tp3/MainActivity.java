package istic.fr.tp3;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v4.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;


public class MainActivity extends FragmentActivity
        implements OnMapReadyCallback, MainActivityFragment.OnRegSelectedListener {

    boolean detailPage = false;

    public DetailFragment detailFragment;

    public static FragmentManager fragmentManager;

    private String currentRegion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("MainActivity", "onCreate()");
        setContentView(R.layout.content_main);


        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            MyListFragment listFragment = (MyListFragment) new MyListFragment();
            ft.replace(R.id.displayList, listFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
        }


    }


    public void changeDetails(final int myId) {

        final OnMapReadyCallback onMapReadyCallback = this;
        detailFragment.setClickListener(new DetailFragment.ClickListener() {
            @Override
            public void onLocate() {


                SupportMapFragment supportMapFragment = new SupportMapFragment();
                supportMapFragment.getMapAsync(onMapReadyCallback);

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(myId, supportMapFragment).commit();
                detailFragment = null;


            }
        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        Resources res = getResources();
        String[] regionsName = res.getStringArray(R.array.regions);


        int myPosition=0;
        boolean isSetPosition=false;

        for( int i = 0; i < regionsName.length; i++)
        {
            //Objects.equals requires API level 19 min
            if(Objects.equals(regionsName[i],this.currentRegion)){
                myPosition=i;
                isSetPosition=true;
            }

        }



        if(isSetPosition){

            double longitude = Double.parseDouble(res.getStringArray(R.array.region_long)[myPosition]);
            double latitude =  Double.parseDouble(res.getStringArray(R.array.region_lat)[myPosition]);

            googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(this.currentRegion));
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(latitude, longitude), 7, 0, 0)));


        }



    }


    public void setRegion(String currentRegion) {
        this.currentRegion = currentRegion;
    }


    private void createDetailFragement(final int replace) {
        detailFragment = new DetailFragment();
        final OnMapReadyCallback mapReadyCallbac = this;
        detailFragment.setClickListener(new DetailFragment.ClickListener() {
            @Override
            public void onLocate() {
                SupportMapFragment supportMapFragment = new SupportMapFragment();
                supportMapFragment.getMapAsync(mapReadyCallbac);
                getSupportFragmentManager().beginTransaction().replace(replace, supportMapFragment).addToBackStack("detailFragment").commit();
                detailFragment = null;
            }
        });

    }


    public void refresh(String tutUrl) {
        Log.v("MainActivity Refresh", tutUrl);

        View view = findViewById(R.id.displayDetail);

        FragmentTransaction transaction =  getSupportFragmentManager().beginTransaction();
        if(view ==null){
            createDetailFragement(R.id.displayList);
            detailFragment.setURLContent(tutUrl);
            transaction.replace(R.id.displayList, detailFragment).addToBackStack("listRegion");
        }else{
            if(detailFragment == null){
                createDetailFragement(R.id.displayDetail);
                detailFragment.setURLContent(tutUrl);
                transaction.replace(R.id.displayDetail, detailFragment).addToBackStack("detailFragment");
            }else {
                detailFragment.updateURLContent(tutUrl);
            }
        }
        transaction.commit();


    }


    @Override
    public void onRegSelected(String tutUrl) {
    }
}