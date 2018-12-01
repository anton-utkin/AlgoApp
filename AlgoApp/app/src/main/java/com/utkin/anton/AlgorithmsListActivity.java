package com.utkin.anton;

import android.app.Activity;
import android.os.Bundle;

public class AlgorithmsListActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);

        if(getFragmentManager().findFragmentById(R.id.container) == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new AlgorithmsListFragment()).commit();
        }

    }
}
