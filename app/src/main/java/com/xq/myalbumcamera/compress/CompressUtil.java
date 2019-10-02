package com.xq.myalbumcamera.compress;

import android.graphics.Bitmap;
import android.text.TextUtils;

import com.xq.myalbumcamera.MyApplication;
import com.xq.myalbumcamera.header.StorageManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import id.zelory.compressor.Compressor;

public class CompressUtil {

    public void compress(List<String> filePathList) {
        final List<String> result = new ArrayList<String>();
        Compressor ompressedImage = new Compressor(MyApplication.getInstance())
                .setMaxWidth(1280)
                .setMaxHeight(720)
                .setQuality(60)
                .setCompressFormat(Bitmap.CompressFormat.WEBP)
                .setDestinationDirectoryPath(StorageManager.getInstance().getCacheDir().getAbsolutePath());
        for (String path : filePathList) {
            System.out.println("path======================" + path);
            if (TextUtils.isEmpty(path)) continue;
            String newPath = path;
            try {
                File compressedFile = ompressedImage.compressToFile(new File(path));
                if (compressedFile != null) {
                    newPath = compressedFile.getAbsolutePath();
                    compressedFile.deleteOnExit();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("newPath======================" + newPath);
            result.add(newPath);
        }
    }

}
