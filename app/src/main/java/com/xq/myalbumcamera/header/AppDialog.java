package com.xq.myalbumcamera.header;


import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.xq.myalbumcamera.R;

public class AppDialog {


    public interface OnSelectListener{
        void onCamera();
        void onPhoto();
    }
    public interface OnItemSelectedListener<T>{
        void onSelected(T position);
    }
    public interface OnOkListener<T>{
        void onOk(T data);
    }
    public static void showPictureDialog(Context context, final OnSelectListener listener){
        final Dialog dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        View camaraView = LayoutInflater.from(context).inflate(R.layout.dialog_camara, null);
        Button choosePhoto = (Button) camaraView.findViewById(R.id.choosePhoto);
        Button takePhoto = (Button) camaraView.findViewById(R.id.takePhoto);
        Button cancel = (Button) camaraView.findViewById(R.id.btn_cancel);
        choosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onPhoto();
                dialog.dismiss();
            }
        });
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCamera();
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(camaraView);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity( Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

}
