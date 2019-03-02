package com.mit.ic.athiticard;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.google.android.material.button.MaterialButton;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class vendorShop extends Fragment {

    public vendorShop() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_vendor_shop, container, false);

        MaterialButton onGenButton = view.findViewById(R.id.generalButton);

        onGenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();

                com.google.android.material.card.MaterialCardView mcv = view.findViewById(R.id.materialCardViewGenShop2);
                mcv.setVisibility(View.INVISIBLE);

                RelativeLayout rv = view.findViewById(R.id.scrollGeneralShop);
                rv.setVisibility(View.INVISIBLE);

                fragmentTransaction.replace(R.id.initActVendor, new ShopDetails());
                fragmentTransaction.commit();

            }
        });

        return view;

    }


}
