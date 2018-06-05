package com.example.first.testandroid;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class InternalStorageActivity extends AppCompatActivity {

    public static final String FILE_NAME="myfile.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);

        final TextView textGoes= (TextView) findViewById(R.id.tvShowData);

        Button btnLoadInternal=(Button) findViewById(R.id.btnLoadPref);
        Button btnSaveInternal=(Button) findViewById(R.id.btnSavePref);
        final EditText etData=(EditText) findViewById(R.id.etLoadData);

        final SharedPreferences preferences=getSharedPreferences("my_pref",MODE_PRIVATE);
        btnLoadInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String data= FileOperationsHelper.getInstance().readFile(getApplicationContext(),FILE_NAME);
                    textGoes.setText(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnSaveInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dataToSave=etData.getText().toString();

                try {
                    FileOperationsHelper.getInstance().saveFile(getApplicationContext(),FILE_NAME,dataToSave);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
