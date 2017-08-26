package com.example.matthewtaila.rendevous;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.matthewtaila.rendevous.activity.Camera2Activity;
import com.example.matthewtaila.rendevous.activity.CameraActivity;
import com.example.matthewtaila.rendevous.activity.CameraLandingPageActivity;
import com.example.matthewtaila.rendevous.activity.CustomCameraActivity;
import com.example.matthewtaila.rendevous.databinding.ActivityNavigationBinding;

public class NavigationActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityNavigationBinding mActivityNavigationBinding;
    private Button customActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityNavigationBinding = DataBindingUtil.setContentView(this, R.layout.activity_navigation);
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        mActivityNavigationBinding.cameraActivityButton.setOnClickListener(this);
        mActivityNavigationBinding.camera2ActivityButton.setOnClickListener(this);
        mActivityNavigationBinding.libraryCamera2.setOnClickListener(this);
        customActivityButton = mActivityNavigationBinding.customCameraActivityButton;
        customActivityButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cameraActivityButton:
                Intent intent = new Intent(this, CameraActivity.class);
                startActivity(intent);
                break;
            case R.id.camera2ActivityButton:
                Intent Cintent = new Intent(this, Camera2Activity.class);
                startActivity(Cintent);
                break;
            case R.id.customCameraActivityButton:
                Intent intent3 = new Intent(this, CustomCameraActivity.class);
                startActivity(intent3);
                break;
            case R.id.libraryCamera2:
                Intent intent4 = new Intent(this, CameraLandingPageActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
