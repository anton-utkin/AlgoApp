package com.utkin.anton;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.List;


public class AlgorithmsListFragment extends ListFragment{

    AlgorithmsListAdapter mListAdapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new AlgorithmsListAdapter(getActivity());
        List<AlgorithmItem> list = AlgoList.getItems();
        mListAdapter.addAll(list);
        setListAdapter(mListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.listview, null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(getActivity(), AlgorithmDescriptionActivity.class);
        intent.putExtra(AlgorithmDescriptionActivity.EXTRA_ALGO_ID, ((AlgorithmItem)l.getItemAtPosition(position)).getTitle());
        startActivity(intent);
    }
}
