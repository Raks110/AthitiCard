package com.mit.ic.athiticard;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.mit.ic.athiticard.Models.Vendor;
import com.mit.ic.athiticard.Models.VendorAddress;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class vendorShop extends Fragment {

    private DatabaseReference mDatabase;
    private boolean status;
    private Vendor vendor;

    public vendorShop() {
        // Required empty public constructor
    }

    private void writeNewVendor(Vendor vendor) {

        mDatabase.child("vendors").child(vendor.getGstNumber()).setValue(vendor);
        status = true;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        status = false;
        vendor = new Vendor();

        final View view = inflater.inflate(R.layout.fragment_vendor_shop, container, false);

        MaterialButton onGenButton = view.findViewById(R.id.generalButton);

        onGenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabase = FirebaseDatabase.getInstance().getReference();

                VendorAddress ad = new VendorAddress();
                String reqText;

                TextInputEditText et = view.findViewById(R.id.AddressStreet);
                reqText = et.getText().toString();
                ad.setStreet(reqText);

                et = view.findViewById(R.id.AddressLandmark);
                reqText = et.getText().toString();
                ad.setLandmark(reqText);


                et = view.findViewById(R.id.AddressCity);
                reqText = et.getText().toString();
                ad.setCity(reqText);


                et = view.findViewById(R.id.AddressState);
                reqText = et.getText().toString();
                ad.setState(reqText);

                et = view.findViewById(R.id.AddressPIN);
                reqText = et.getText().toString();
                ad.setPinCode(Integer.parseInt(reqText));

                vendor.setVendorAddress(ad);

                et = view.findViewById(R.id.AadharName);
                reqText = et.getText().toString();

                vendor.setName(reqText);

                et = view.findViewById(R.id.PhoneNumber);
                reqText = et.getText().toString();

                vendor.setPhoneNumber(reqText);
                vendor.setAadharNumber(ShopDetails.aadharNumber);
                vendor.setPanNumber(ShopDetails.panNumber);
                vendor.setGstNumber(ShopDetails.gstNumber);

                writeNewVendor(vendor);

                if(status) {

                    Intent intent = new Intent(getContext(), activity_initial.class);
                    intent.putExtra("key", "success");
                    startActivity(intent);

                    Toast.makeText(getContext(), "Great! The vendor has been added successfully!", Toast.LENGTH_LONG).show();
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
