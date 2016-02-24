package istic.fr.tp3;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity
        implements MyListFragment.OnURLSelectedListener {

    boolean detailPage = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("MainActivity", "onCreate()");

        setContentView(R.layout.content_main);

        if(savedInstanceState == null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            MyListFragment listFragment = (MyListFragment) new MyListFragment();
            ft.replace(R.id.displayList, listFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }


        if(findViewById(R.id.displayDetail) != null){
            System.out.println("displayDetail");
            detailPage = true;
            getFragmentManager().popBackStack();

            DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.displayDetail);
            if(detailFragment == null){
                FragmentTransaction ft3 = getFragmentManager().beginTransaction();
                detailFragment = new DetailFragment();
                ft3.replace(R.id.displayDetail, detailFragment, "Detail_Fragment1");
                ft3.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft3.commit();
            }
        }

    }




    @Override
    public void OnURLSelectedListener(String tutUrl) {

        Log.v("AndroidFragmentActivity",tutUrl);

        if(detailPage){
            System.out.println("one");
            DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.displayDetail);
            detailFragment.updateURLContent(tutUrl);
        }
        else{
            System.out.println("two");
            DetailFragment detailFragment = new DetailFragment();
            detailFragment.setURLContent(tutUrl);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.displayList, detailFragment, "Detail_Fragment2");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        }

    }
}