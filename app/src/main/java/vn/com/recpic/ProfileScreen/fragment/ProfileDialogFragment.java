package vn.com.recpic.ProfileScreen.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.io.File;

import vn.com.recpic.R;
import vn.com.recpic.Util.Util;

/**
 * Created by Administrator on 1/17/2017.
 */

public class ProfileDialogFragment extends DialogFragment {
    private static final String TAG = ProfileDialogFragment.class.getSimpleName();
    private static final int IMAGE_GALLERY = 10000;
    private static final int IMAGE_CAMERA = 10001;
    private Uri uriCameraImage;
    private TextView txtAlbum, txtCamera;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_profile, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        init(view);
        return view;
    }
    private void init(View view){
        LinearLayout lnConfirm = (LinearLayout) view.findViewById(R.id.lnConfirm);
        txtAlbum = (TextView) view.findViewById(R.id.txt_album);
        txtCamera = (TextView) view.findViewById(R.id.txt_camera);

        txtAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent, IMAGE_GALLERY);
            }
        });

        txtCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                String url = "tmp_" + String.valueOf(System.currentTimeMillis());
                uriCameraImage = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uriCameraImage);
                startActivityForResult(intent, IMAGE_CAMERA);
            }
        });
        lnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == getActivity().RESULT_OK && requestCode == IMAGE_GALLERY){
            Uri uri = data.getData();
            String path = Util.getPath(getContext(), uri);
            if(path == null)
                return;
            Bitmap bmpAlbum = Util.loadOrientationAdjustedBitmap(path, 1500, 1500);
            String strLimitedImageFilePath = Util.getSizeLimitedImageFilePath(bmpAlbum);
            if(strLimitedImageFilePath != null){
                path = strLimitedImageFilePath;
            }
        }
    }
}
