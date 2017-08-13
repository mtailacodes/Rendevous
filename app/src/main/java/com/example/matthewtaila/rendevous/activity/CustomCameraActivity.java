package com.example.matthewtaila.rendevous.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Size;
import android.view.TextureView;

import com.example.matthewtaila.rendevous.R;
import com.example.matthewtaila.rendevous.databinding.ActivityCustomCameraBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CustomCameraActivity extends AppCompatActivity {

    ActivityCustomCameraBinding mActivityCustomCameraBinding;

    private Size mPreviewSize;
    private String mCameraID;

    // set textureView listener to send back width and height of textureview to use for camera preview
    private TextureView.SurfaceTextureListener mSurfaceTextureViewListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
            // when the surface view is inflate and becomes available, set up the camera
            setupCamera(width, height);
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityCustomCameraBinding = DataBindingUtil.setContentView(this, R.layout.activity_custom_camera);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mActivityCustomCameraBinding.textureViewCameraPreview.isAvailable()){

        } else {
            mActivityCustomCameraBinding.textureViewCameraPreview.setSurfaceTextureListener(mSurfaceTextureViewListener);
        }
    }

    private void setupCamera(int width, int height){
        // use the object mCameraManger to access all the information regarding camera's on the phone device
        CameraManager mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try{
            for (String cameraID : mCameraManager.getCameraIdList()){
                CameraCharacteristics mCameraCharacteristics = mCameraManager.getCameraCharacteristics(cameraID);
                if (mCameraCharacteristics.get(CameraCharacteristics.LENS_FACING) == CameraCharacteristics.LENS_FACING_FRONT){
                    continue;
                }
                StreamConfigurationMap mStreamConfigurationMap = mCameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                mPreviewSize = getPreferredPreviewSize(mStreamConfigurationMap.getOutputSizes(SurfaceTexture.class), width, height);
                mCameraID = cameraID;
                return;
            }
        } catch (CameraAccessException e){
            e.printStackTrace();
        }
    }

    private Size getPreferredPreviewSize(Size[] mapSize, int width, int height){

        List<Size> collectoreSize = new ArrayList<>();

        for (Size option : mapSize){
            if (width > height){
                if (option.getWidth() > width && option.getHeight() > height){
                    collectoreSize.add(option);
                }
            } else {
                if (option.getWidth() > height && option.getHeight() > width){
                    collectoreSize.add(option);
                }
            }
        }
        if (collectoreSize.size() > 0){
            return Collections.min(collectoreSize, new Comparator<Size>() {
                @Override
                public int compare(Size size, Size t1) {
                    return Long.signum(size.getWidth() * size.getHeight() - t1.getWidth() * t1.getHeight());
                }
            });
        }
        return mapSize[0];

    }
}
