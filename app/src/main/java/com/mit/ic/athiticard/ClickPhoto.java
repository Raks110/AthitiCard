package com.mit.ic.athiticard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import com.roger.catloadinglibrary.CatLoadingView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static android.app.Activity.RESULT_OK;

public class ClickPhoto extends Fragment {

    public View view;
    public ImageView imageView;

    public ClickPhoto() {
        // Required empty public constructor
    }

    final CatLoadingView mView = new CatLoadingView();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_click_photo, container, false);
        imageView = view.findViewById(R.id.photoFrame);

        MaterialButton clickButton = view.findViewById(R.id.clickToLaunch);

        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //RelativeLayout rl = view.findViewById(R.id.relativePhotoHeading);
                TextView tv = view.findViewById(R.id.textViewPhoto);

                RelativeLayout.LayoutParams rllp = (RelativeLayout.LayoutParams) tv.getLayoutParams();
                rllp.addRule(RelativeLayout.CENTER_HORIZONTAL,0);

                tv.setLayoutParams(rllp);

                MaterialButton mb = view.findViewById(R.id.photoButton);
                mb.setVisibility(View.VISIBLE);

                MaterialButton cb = view.findViewById(R.id.clickToLaunch);
                cb.setText("Re-take Picture");

                mView.show(getFragmentManager(), "");

                dispatchTakePictureIntent();

            }
        });

        MaterialButton onPhotoButton = view.findViewById(R.id.photoButton);

        onPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MaterialCardView mcv = view.findViewById(R.id.materialCardViewPhoto2);
                mcv.setVisibility(View.INVISIBLE);

                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.initAct, new General());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        return view;
    }


    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private static String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = TapCard.AthitiCardNumber;
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        Log.e("Storage Loc",currentPhotoPath);
        return image;
    }

    private static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {

            File photoFile = null;
            try {
                Log.e("dispatchTakePicture","creatING file!!!!");
                Log.e("dispatchTakePicture",TapCard.AthitiCardNumber);
                photoFile = createImageFile();
                Log.e("Athiti","Created File!!!!!!!!!");
            } catch (IOException ex) {

            }
            if (photoFile != null) {
                Log.e("Not Null","File ain't null");
                Uri photoURI = FileProvider.getUriForFile(getActivity(),
                        "com.mit.ic.athiticard",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                Log.e("Cam started","YOLO!!");
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                Log.e("Cam done","YOLO!!!! Again!");
            }
        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            File imgFile = new File(currentPhotoPath);
            if(imgFile.exists()){
                final AtomicBoolean loaded = new AtomicBoolean();
                Picasso.with(getContext()).load(imgFile).into(imageView, new Callback.EmptyCallback() {
                    @Override public void onSuccess() {
                        loaded.set(true);
                        Log.e("Picasso","Loaded Image");
                        mView.dismissAllowingStateLoss();
                    }
                });
            }
        }
    }

}