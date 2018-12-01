package com.utkin.anton;

import android.app.Fragment;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AlgorithmDescriptionFragment extends Fragment {

    private WebView mTitle;

    public static AlgorithmDescriptionFragment createInstance(Bundle args) {
        AlgorithmDescriptionFragment fragment = new AlgorithmDescriptionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.algorithm_description, null);

        Bundle args = getArguments();
        String algoId = (String)args.getSerializable(AlgorithmDescriptionActivity.EXTRA_ALGO_ID);

        Cursor cursor = getActivity().getContentResolver().query(Uri.withAppendedPath(AlgorithmsProvider.CONTENT_URI, algoId), null, null, null, "name");
        if (cursor.moveToFirst()) {
            AlgorithmItem item = new AlgorithmItem(cursor);
            Log.i("TAG", item.getTitle() + " " + item.getCode());
            mTitle = (WebView)view.findViewById(R.id.text_id);
            if(item != null) {
                Log.i("TAG", readData(item.getCode()));
                mTitle.loadDataWithBaseURL("", readData(item.getCode()), "text/html", "UTF-8", "");
            }
        }
        return view;
    }

    private String readData(String filename){
        StringBuffer result = new StringBuffer();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getActivity().getAssets().open(filename)));
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                result.append(mLine );
            }
        } catch (IOException e) {
            Log.i("TAG" , "Can not read asset" + filename);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
            return result.toString();
        }
    }
}