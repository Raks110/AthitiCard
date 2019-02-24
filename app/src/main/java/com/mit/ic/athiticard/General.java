package com.mit.ic.athiticard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class General extends Fragment {


    public General() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_general, container, false);

        MaterialButton onGenButton = view.findViewById(R.id.generalButton);

        onGenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
