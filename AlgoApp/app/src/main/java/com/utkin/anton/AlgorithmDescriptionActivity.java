package com.utkin.anton;

import android.app.Activity;
import android.os.Bundle;

public class AlgorithmDescriptionActivity extends Activity {

    public static final String EXTRA_ALGO_ID = "com.utkin.anton.AlgorithmDescriptionActivity.ALGO_ID";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);

        if(getFragmentManager().findFragmentById(R.id.container) == null)
            getFragmentManager().beginTransaction()
                    .add(R.id.container, AlgorithmDescriptionFragment.createInstance(getIntent().getExtras())).commit();
    }
}

