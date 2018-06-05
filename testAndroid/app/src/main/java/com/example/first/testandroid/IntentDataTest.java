package com.example.first.testandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IntentDataTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_data_test);

        final EditText etData=(EditText) findViewById(R.id.etData);
        final EditText etDataAge=(EditText) findViewById(R.id.etDataAge);
        Button btnPass=(Button) findViewById(R.id.btnPass);



        btnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("data",etData.getText().toString());
                intent.putExtra("age",etDataAge.getText().toString());
                startActivity(intent);
            }
        });
    }
}
