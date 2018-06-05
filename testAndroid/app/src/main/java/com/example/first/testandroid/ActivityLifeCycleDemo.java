package com.example.first.testandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class ActivityLifeCycleDemo extends AppCompatActivity {

    private static final String TAG = ActivityLifeCycleDemo.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle_demo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("Inside On Start");
        Log.i(TAG, "Inside On Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Inside On Resume");
        Log.i(TAG, "Inside On Resume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("Inside on Restart");
        Log.i(TAG, "Inside on Restart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("Inside on Stop");
        Log.i(TAG, "Inside on Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("Inside On Destroy");
        Log.i(TAG, "Inside On Destroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("Inside On Pause");
        Log.i(TAG,"Inside On Pause");
    }
}