package com.mit.ic.athiticard;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mit.ic.athiticard.Models.CompanyAddress;
import com.mit.ic.athiticard.Models.User;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static com.mit.ic.athiticard.ClickPhoto.currentPhotoPath;


public class JobDetails extends Fragment {

    public static com.mit.ic.athiticard.Models.JobDetails jd;
    private static DatabaseReference mDatabase;
    private static boolean status;

    private static User user;

    private FirebaseStorage storage;
    private StorageReference storageRef;

    public JobDetails() {

    }

    public static void writeNewUser(User user) {

        mDatabase.child("users").child(user.getCardNumber()).setValue(user);
        status = true;

    }

    private void removeUser(User user) {

        mDatabase.child("users").child(user.getCardNumber()).removeValue();
        status = false;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        status = false;
        jd = new com.mit.ic.athiticard.Models.JobDetails();

        final View view = inflater.inflate(R.layout.fragment_job_details, container, false);

        MaterialButton onJobButton = view.findViewById(R.id.jobButton);

        onJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabase = FirebaseDatabase.getInstance().getReference();

                CompanyAddress ad = new CompanyAddress();
                String reqText;

                TextInputEditText et = view.findViewById(R.id.CompanyAddressStreet);
                reqText = et.getText().toString();
                ad.setStreet(reqText);

                et = view.findViewById(R.id.CompanyAddressLandmark);
                reqText = et.getText().toString();
                ad.setLandmark(reqText);


                et = view.findViewById(R.id.CompanyAddressCity);
                reqText = et.getText().toString();
                ad.setCity(reqText);


                et = view.findViewById(R.id.CompanyAddressState);
                reqText = et.getText().toString();
                ad.setState(reqText);

                et = view.findViewById(R.id.CompanyAddressPIN);
                reqText = et.getText().toString();
                ad.setPinCode(Integer.parseInt(reqText));

                jd.setCompanyAddress(ad);

                et = view.findViewById(R.id.EmployerName);
                reqText = et.getText().toString();

                jd.setEmployerName(reqText);

                et = view.findViewById(R.id.CompanyPhoneNumber);
                reqText = et.getText().toString();

                jd.setCompanyNumber(reqText);

                et = view.findViewById(R.id.WorksFor);
                reqText = et.getText().toString();

                jd.setCompanyName(reqText);

                com.google.android.material.card.MaterialCardView mcv = view.findViewById(R.id.materialCardViewJob2);
                mcv.setVisibility(View.INVISIBLE);

                user = new User();
                user.setPersonalDetails(General.pd);
                user.setJobDetails(jd);
                user.setAadharNumber(EncDetails.aadharNumber);
                user.setPanNumber(EncDetails.panNumber);
                user.setCardNumber(TapCard.AthitiCardNumber);
                user.setValidity();

                storage = FirebaseStorage.getInstance();
                storageRef = storage.getReference();

                StorageReference imagesRef = storageRef.child("images");

                final File imgFile = new File(currentPhotoPath);

                Uri file = Uri.fromFile(new File(currentPhotoPath));
                final StorageReference riversRef = storageRef.child("images/"+user.getCardNumber()+".jpg");
                UploadTask uploadTask = riversRef.putFile(file);

                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getContext(),"There seems to be an error.",Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    }
                });

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        return riversRef.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri downloadUri = task.getResult();
                            user.setImageURL(downloadUri.toString());
                            writeNewUser(user);
                        } else {
                            Toast.makeText(getContext(), "An error was encountered. Please check your internet connection.", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                if(status) {

                    Intent intent = new Intent(getContext(), activity_initial.class);
                    intent.putExtra("key", "success");
                    startActivity(intent);

                    Toast.makeText(getContext(), "Great! The user has been added successfully!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getContext(), "An error was encountered. Please check your internet connection.", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getContext(), activity_initial.class);
                    intent.putExtra("key", "failed");
                    startActivity(intent);
                }
            }
        });

        return view;

    }



}
