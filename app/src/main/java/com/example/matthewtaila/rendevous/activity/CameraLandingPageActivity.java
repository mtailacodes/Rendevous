package com.example.matthewtaila.rendevous.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;

import com.example.matthewtaila.rendevous.R;
import com.example.matthewtaila.rendevous.databinding.ActivityCameraLandingPageBinding;
import com.flurgle.camerakit.CameraListener;

public class CameraLandingPageActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityCameraLandingPageBinding mActivityCameraLandingPageBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityCameraLandingPageBinding = DataBindingUtil.setContentView(this, R.layout.activity_camera_landing_page);
        onEnterViewPositioning();
        setupOnClickListeners();
    }

    private void setupOnClickListeners() {
        mActivityCameraLandingPageBinding.ivTakePicture.setOnClickListener(this);
        mActivityCameraLandingPageBinding.ivTakeAnotherIcon.setOnClickListener(this);
        mActivityCameraLandingPageBinding.ivTakeAnotherPicture.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mActivityCameraLandingPageBinding.camera.start();
    }

    @Override
    protected void onPause() {
        mActivityCameraLandingPageBinding.camera.stop();
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_takePicture:
                takePic();
                break;
            case R.id.iv_takeAnotherPicture:
                hidePictureContainer();
                break;
            case R.id.iv_takeAnotherIcon:
                hidePictureContainer();
                break;
        }
    }

    private void hidePictureContainer() {
        ObjectAnimator translateDownImagePreviewContainer = ObjectAnimator.ofFloat(mActivityCameraLandingPageBinding.cvImagePreviewContainer,
                View.TRANSLATION_Y,
                -(getResources().getDimension(R.dimen.imageTakenHeight)));
        translateDownImagePreviewContainer.setDuration(200);
        translateDownImagePreviewContainer.setInterpolator(new AccelerateInterpolator());
        translateDownImagePreviewContainer.start();

        ObjectAnimator alphaToOneImagePreviewContainer = ObjectAnimator.ofFloat(mActivityCameraLandingPageBinding.cvImagePreviewContainer,
                View.ALPHA,
                0);
        alphaToOneImagePreviewContainer.setDuration(10);
        alphaToOneImagePreviewContainer.setStartDelay(200);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translateDownImagePreviewContainer,alphaToOneImagePreviewContainer);
        animatorSet.start();

    }

    public void takePic(){
        mActivityCameraLandingPageBinding.camera.setCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(byte[] jpeg) {
                super.onPictureTaken(jpeg);
                Bitmap bitmap = BitmapFactory.decodeByteArray(jpeg, 0, jpeg.length);
                mActivityCameraLandingPageBinding.imagePreview.setImageBitmap(bitmap);
            }
        });
        mActivityCameraLandingPageBinding.camera.captureImage();
        animatePreviewContainer();
    }

    private void animatePreviewContainer() {

        mActivityCameraLandingPageBinding.cvImagePreviewContainer.setVisibility(View.VISIBLE);

        ObjectAnimator translateDownImagePreviewContainer = ObjectAnimator.ofFloat(mActivityCameraLandingPageBinding.cvImagePreviewContainer,
                View.TRANSLATION_Y,
                -(getResources().getDimension(R.dimen.imagePreviewMarginTop)/2));
        translateDownImagePreviewContainer.setDuration(200);
        translateDownImagePreviewContainer.setInterpolator(new AccelerateDecelerateInterpolator());
        translateDownImagePreviewContainer.start();

        ObjectAnimator alphaToOneImagePreviewContainer = ObjectAnimator.ofFloat(mActivityCameraLandingPageBinding.cvImagePreviewContainer,
                View.ALPHA,
                1);
        alphaToOneImagePreviewContainer.setDuration(10);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translateDownImagePreviewContainer,alphaToOneImagePreviewContainer);
        animatorSet.start();

    }

    private void onEnterViewPositioning() {
        mActivityCameraLandingPageBinding.cvImagePreviewContainer.setVisibility(View.INVISIBLE);

        ObjectAnimator translateDownImagePreviewContainer = ObjectAnimator.ofFloat(mActivityCameraLandingPageBinding.cvImagePreviewContainer,
                View.TRANSLATION_Y,
                -(getResources().getDimension(R.dimen.imageTakenHeight)));
        translateDownImagePreviewContainer.setDuration(0);
        translateDownImagePreviewContainer.start();

    }
}
