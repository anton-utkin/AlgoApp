package com.utkin.anton;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import java.util.LinkedList;
import java.util.List;


public class AlgorithmsListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>{

    AlgorithmsListAdapter mListAdapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new AlgorithmsListAdapter(getActivity());
        setListAdapter(mListAdapter);
        getLoaderManager().initLoader(0, null, this);
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
        intent.putExtra(AlgorithmDescriptionActivity.EXTRA_ALGO_ID, ((AlgorithmItem)l.getItemAtPosition(position)).getId());
        startActivity(intent);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), AlgorithmsProvider.CONTENT_URI,
                null, null, null, AlgorithmsProvider.NAME);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mListAdapter.clear();
        List<AlgorithmItem> list = new LinkedList();
        if (data.moveToFirst()) {
            do {
                list.add(new AlgorithmItem(data));
            } while (data.moveToNext());
        }
        mListAdapter.addAll(list);
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mListAdapter.clear();
        mListAdapter.addAll(new LinkedList());
        mListAdapter.notifyDataSetChanged();
    }
}
