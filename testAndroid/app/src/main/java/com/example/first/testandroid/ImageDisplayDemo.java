package com.example.first.testandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ImageDisplayDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display_demo);

        Button btnChangeImage=(Button) findViewById(R.id.btnChangeImage);
        final ImageView imageView=(ImageView) findViewById(R.id.imageView);

        btnChangeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageView.getDrawable().getConstantState()== getResources().getDrawable(R.drawable.android_image).getConstantState())
                    imageView.setImageResource(R.drawable.feedback);
                else
                    imageView.setImageResource(R.drawable.android_image);
            }
        });
    }
}
