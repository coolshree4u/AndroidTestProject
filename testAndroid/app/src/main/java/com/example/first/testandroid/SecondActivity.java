package com.example.first.testandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView tvDataPass = (TextView) findViewById(R.id.tvDataPass);

        Bundle bundle = getIntent().getExtras();

        String data = "";

        if (bundle != null) {
            data = bundle.getString("data");
            int age = Integer.parseInt(bundle.getString("age"));
            tvDataPass.setText("Name is "+data+" And Age is "+age);
        }
    }
}
