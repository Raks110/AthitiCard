package com.mit.ic.athiticard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.textfield.TextInputLayout;

import com.google.android.material.button.MaterialButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;
import com.mit.ic.athiticard.Models.PersonalDetails;
import com.mit.ic.athiticard.Models.Address;

public class General extends Fragment {

    public static PersonalDetails pd;

    public General() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        pd = new PersonalDetails();

        final View view = inflater.inflate(R.layout.fragment_general, container, false);

        MaterialButton onGenButton = view.findViewById(R.id.generalButton);

        onGenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String reqText;

                Address ad = new Address();

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

                pd.setAddress(ad);

                et = view.findViewById(R.id.AadharName);
                reqText = et.getText().toString();

                pd.setName(reqText);


                et = view.findViewById(R.id.PhoneNumber);
                reqText = et.getText().toString();

                pd.setPhoneNumber(reqText);

                com.google.android.material.card.MaterialCardView mcv = view.findViewById(R.id.materialCardViewGen2);
                mcv.setVisibility(View.INVISIBLE);

                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.initAct, new JobDetails());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        return view;

    }

}
