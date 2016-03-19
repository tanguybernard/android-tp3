package istic.fr.tp3;

import android.app.Activity;
import android.support.v4.app.ListFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends ListFragment {
    private OnRegSelectedListener regSelectedListener;

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String myUrl = "http://technoresto.org/vdf/";

        String selectedValue = (String) getListAdapter().getItem(position);
        Toast.makeText(this.getActivity().getApplicationContext(),selectedValue,Toast.LENGTH_LONG);

        String content =myUrl+selectedValue.toLowerCase()+"/index.html";


        regSelectedListener.onRegSelected(content);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(ArrayAdapter.createFromResource(getActivity()
                        .getApplicationContext(), R.array.regions,
                R.layout.region_item));
    }

    public interface OnRegSelectedListener {
        public void onRegSelected(String tutUrl);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            regSelectedListener = (OnRegSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnRegSelectedListener");
        }
    }
}
