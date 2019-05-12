package com.mit.ic.athiticard;

import android.app.DatePickerDialog;
import android.content.Intent;
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
import android.widget.DatePicker;
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
import com.mit.ic.athiticard.Utilities.AddressFormats;
import com.roger.catloadinglibrary.CatLoadingView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.mit.ic.athiticard.Utilities.DateFormats.changeFormat;


public class BasicDetails extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private DatabaseReference privateDatabase;
    private User user;

    final Calendar myCalendar = Calendar.getInstance();
    private TextView validity;

    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

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
        validity = findViewById(R.id.textViewValidity);


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

                tv = findViewById(R.id.textViewValidity);

                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new DatePickerDialog(BasicDetails.this, date, myCalendar
                                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                    }
                });

                tv.setText(changeFormat(user.getValidity()));

                tv = findViewById(R.id.personalAddress);
                tv.setText(AddressFormats.changeFormat(user.getPersonalDetails().getAddress()));

                tv = findViewById(R.id.jobAddress);
                tv.setText(AddressFormats.changeFormatPro(user.getJobDetails().getCompanyAddress()));

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

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        Date setDate = myCalendar.getTime();
        privateDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(UpdateUser.AthitiCardNumber).child("validity");
        privateDatabase.setValue(setDate);
        validity.setText(sdf.format(myCalendar.getTime()));

        Toast.makeText(getApplicationContext(),"Validity Updated Successfully",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this,activity_initial.class);
        startActivity(intent);
    }
}

