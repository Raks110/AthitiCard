package com.mit.ic.athiticard;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mit.ic.athiticard.Models.User;
import com.roger.catloadinglibrary.CatLoadingView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.mit.ic.athiticard.Utilities.DateFormats.changeFormat;


public class BasicDetails extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private User user;

    public BasicDetails() {
        // Required empty public constructor
    }

    public View view;
    public ImageView imageView;
    private CatLoadingView mView = new CatLoadingView();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_basic_details);

        imageView = findViewById(R.id.photoFrame);

        mView.show(getSupportFragmentManager(),"");

        loadImage();

        Log.e(TAG,"Almost returning the view");
    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_basic_details, container, false);

        imageView = view.findViewById(R.id.photoFrame);

        mView.show(getFragmentManager(), "");

        loadImage();

        Log.e(TAG,"Almost returning the view");

        return view;

    }*/

    public void loadImage(){
        String imageFile = UpdateUser.AthitiCardNumber;

        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(imageFile);

        Log.e("TAG","Image Loading");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);

                TextView tv = findViewById(R.id.textViewName);
                tv.setText(user.getPersonalDetails().getName());

                tv = findViewById(R.id.textViewNumber);
                tv.setText(user.getAadharNumber());

                MaterialButton validity = findViewById(R.id.textViewValidity);
                validity.setText(changeFormat(user.getValidity()));

                Log.e(TAG,user.getCardNumber());

                Picasso.with(getApplicationContext()).load(user.getImageURL()).into(imageView, new Callback.EmptyCallback() {
                    @Override public void onSuccess() {

                        Log.e("Picasso","Loaded Image");
                        mView.dismissAllowingStateLoss();

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.e(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        mDatabase.addValueEventListener(postListener);

        Log.e(TAG,"Returning");

    }
}

