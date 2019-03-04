package com.mit.ic.athiticard;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.roger.catloadinglibrary.CatLoadingView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;


public class BasicDetails extends Fragment {

    public BasicDetails() {
        // Required empty public constructor
    }

    public View view;
    public ImageView imageView;
    private CatLoadingView mView = new CatLoadingView();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_basic_details, container, false);

        imageView = view.findViewById(R.id.photoFrame);

        mView.show(getFragmentManager(), "");

        loadImage();

        return view;

    }

    public void loadImage(){
        String imageFile = UpdateUser.AthitiCardNumber;
        imageFile += ".jpg";

        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        String path = storageDir.toString();
        path += '/';
        path+=imageFile;

        File imgFile = new File(path);
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

