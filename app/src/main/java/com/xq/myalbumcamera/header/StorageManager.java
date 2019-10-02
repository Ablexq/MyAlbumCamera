package com.xq.myalbumcamera.header;

import android.content.Context;

import com.xq.myalbumcamera.MyApplication;

import java.io.File;

public class StorageManager {
    private static StorageManager instance;
    private Context context;
    static {
        instance = new StorageManager();
    }
    private StorageManager(){
        context = MyApplication.getInstance();
    }
    public static StorageManager getInstance(){
        return instance;
    }
    public File getFileDir(){
        return context.getExternalFilesDir(null);
    }
    public File getCacheDir(){
        File file = context.getExternalCacheDir();
        if(file != null && file.exists() && file.canWrite()){
            return file.getAbsoluteFile();
        }
        return context.getCacheDir().getAbsoluteFile();
    }
}