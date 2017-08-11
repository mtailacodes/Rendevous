package com.example.matthewtaila.rendevous;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.matthewtaila.rendevous.activity.CameraActivity;
import com.example.matthewtaila.rendevous.databinding.ActivityNavigationBinding;

public class NavigationActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityNavigationBinding mActivityNavigationBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityNavigationBinding = DataBindingUtil.setContentView(this, R.layout.activity_navigation);
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        mActivityNavigationBinding.cameraActivityButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cameraActivityButton:
                Intent intent = new Intent(this, CameraActivity.class);
                startActivity(intent);
                break;
        }
    }
}
