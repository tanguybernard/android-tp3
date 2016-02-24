package istic.fr.tp3;

/**
 * Created by tbernard on 24/02/16.
 */
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class MyListFragment extends ListFragment {

    OnURLSelectedListener mListener;

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String myUrl = "http://technoresto.org/vdf/";

        String selectedValue = (String) getListAdapter().getItem(position);
        Toast.makeText(this.getActivity().getApplicationContext(), selectedValue, Toast.LENGTH_LONG);

        String content =myUrl+selectedValue.toLowerCase()+"/index.html";


        mListener.OnURLSelectedListener(content);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(ArrayAdapter.createFromResource(getActivity()
                        .getApplicationContext(), R.array.regions,
                R.layout.region_item));
    }

    public interface OnURLSelectedListener {
        public void OnURLSelectedListener(String tutUrl);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnURLSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnRegSelectedListener");
        }
    }
}

