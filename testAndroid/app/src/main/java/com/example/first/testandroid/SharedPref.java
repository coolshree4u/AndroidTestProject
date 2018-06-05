package com.example.first.testandroid;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SharedPref extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pref);


        final TextView textGoes= (TextView) findViewById(R.id.tvtext);

        Button btnLoad=(Button) findViewById(R.id.btnLoad);
        Button btnSave=(Button) findViewById(R.id.btnSave);


        final SharedPreferences preferences=getSharedPreferences("my_pref",MODE_PRIVATE);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name= preferences.getString("name","Name key now found");

                textGoes.setText(name);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("name","Shree");
                editor.commit();
            }
        });
     }
}
