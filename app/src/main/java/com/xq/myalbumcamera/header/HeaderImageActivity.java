package com.xq.myalbumcamera.header;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xq.myalbumcamera.R;
import com.xq.myalbumcamera.compress.CompressActivity;

import java.io.FileNotFoundException;

public class HeaderImageActivity extends AppCompatActivity {

    private ImageView mIv;
    private TextView mTv1;
    private TextView mTv2;
    private AlbunPhotoHelper albunPhotoHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_image);

        mIv = findViewById(R.id.iv);
        mTv1 = findViewById(R.id.tv1);
        mTv2 = findViewById(R.id.tv2);

        albunPhotoHelper = new AlbunPhotoHelper(this);
        mTv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
        mTv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HeaderImageActivity.this, CompressActivity.class));
            }
        });
    }

    private void selectImage() {
        AppDialog.showPictureDialog(this, new AppDialog.OnSelectListener() {
            @Override
            public void onCamera() {
                albunPhotoHelper.openCamera();
            }

            @Override
            public void onPhoto() {
                albunPhotoHelper.openAlbum();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case AlbunPhotoHelper.REQ_ALBUM:
                if (resultCode == RESULT_OK) {
                    startActivityForResult(albunPhotoHelper.cutForPhoto(data.getData()), AlbunPhotoHelper.REQ_ZOOM);
                }
                break;

            case AlbunPhotoHelper.REQ_TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    startActivityForResult(albunPhotoHelper.cutForCamera(), AlbunPhotoHelper.REQ_ZOOM);
                }
                break;

            case AlbunPhotoHelper.REQ_ZOOM:
                if (data != null) {
                    try {
                        //获取裁剪后的图片，并显示出来
                        final Bitmap bitmap = albunPhotoHelper.getBitmap();
                        final Bitmap qualityBitmap = FileUtils.compressQuality(bitmap);

                        if (qualityBitmap != null) {
                            mIv.setImageBitmap(qualityBitmap);//用ImageView显示出来

//                            new Thread(new Runnable() {//流操作放在子线程
//                                @Override
//                                public void run() {
//                                    ImageUtils.saveImageToLocal(MainActivity.this, qualityBitmap);//保存在SD卡中
//                                }
//                            }).start();

                            if (bitmap.isRecycled()) {
                                bitmap.recycle();
                            }
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
                break;
            default:
                break;
        }
    }

}
