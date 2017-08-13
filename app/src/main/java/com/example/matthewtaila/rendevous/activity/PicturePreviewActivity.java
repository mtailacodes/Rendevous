package com.example.matthewtaila.rendevous.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.matthewtaila.rendevous.R;

import java.io.File;

import javax.microedition.khronos.opengles.GL;

public class PicturePreviewActivity extends AppCompatActivity {

    private final String TAG = "PICURE_PREVIEW";
    private ImageView iv_imagePreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_preview);
        iv_imagePreview = (ImageView) findViewById(R.id.iv_imagePreview);
        Intent intent = getIntent();
        String url = intent.getStringExtra("URI");

       Glide.with(this)
                .load(new File(url))
                .into(iv_imagePreview);
    }
}
