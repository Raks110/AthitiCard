package com.mit.ic.athiticard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mit.ic.athiticard.Models.CompanyAddress;
import com.mit.ic.athiticard.Models.User;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;



public class JobDetails extends Fragment {

    public static com.mit.ic.athiticard.Models.JobDetails jd;
    private DatabaseReference mDatabase;
    private boolean status;

    public JobDetails() {

    }

    private void writeNewUser(User user) {

        mDatabase.child("users").child(user.getCardNumber()).setValue(user);
        status = true;

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

                User user = new User();
                user.setPersonalDetails(General.pd);
                user.setJobDetails(jd);
                user.setAadharNumber(EncDetails.aadharNumber);
                user.setPanNumber(EncDetails.panNumber);
                user.setCardNumber(TapCard.AthitiCardNumber);
                user.setValidity();
                writeNewUser(user);

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
