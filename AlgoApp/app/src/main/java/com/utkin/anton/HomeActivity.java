package com.utkin.anton;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class HomeActivity extends Activity {

    private WebView mWebView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mWebView = (WebView)findViewById(R.id.textContent);
        mWebView.setBackgroundColor(Color.TRANSPARENT);
        String text;
        text = "<html><style type=\"text/css\">body{color: #fff; }</style></head>" + "<body><p align=\"justify\">";
        text+= "Welcome to Algorithms application!</p> <p align=\"justify\"> This application is your notebook, which stores well-known algorithms. " +
                "All algorithms were developed in Python, which allows writing source code as brief as possible. " +
                "Python code is very close to pseudocode, so it makes easier to remember algorithm during interview preparation and " +
                "also anyone can compile this code an run on real hardware! </p><p align=\"justify\">You should know, that world known companies allow solving algorithmic problems " +
                "using Python during their interviews.";
        text+= "</p></body></html>";
        mWebView.loadData(text, "text/html", "utf-8");
    }

    public void onClick(View view)
    {
        Intent intent = new Intent(this, AlgorithmsListActivity.class);
        startActivity(intent);
    }
}
