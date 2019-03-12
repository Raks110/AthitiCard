package com.mit.ic.athiticard;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class ShopDetails extends Fragment {

    public static String aadharNumber;
    public static String panNumber;
    public static String gstNumber;

    public ShopDetails() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_shop_details, container, false);

        MaterialButton onVendorButton = view.findViewById(R.id.vendorButton);

        onVendorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextInputEditText et;
                et = view.findViewById(R.id.AadharNumber);
                aadharNumber = et.getText().toString();

                et = view.findViewById(R.id.PANNumber);
                panNumber = et.getText().toString();

                et = view.findViewById(R.id.GSTNumber);
                gstNumber = et.getText().toString();

                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();

                com.google.android.material.card.MaterialCardView mcv = view.findViewById(R.id.materialCardViewGenShop2);
                mcv.setVisibility(View.INVISIBLE);

                fragmentTransaction.replace(R.id.initActVendor, new vendorShop());
                fragmentTransaction.commit();

            }
        });

        return view;
    }

}
