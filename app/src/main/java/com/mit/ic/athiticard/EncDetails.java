package com.mit.ic.athiticard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class EncDetails extends Fragment {

    public static String aadharNumber;
    public static String panNumber;

    public EncDetails() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_enc_details, container, false);

        MaterialButton onEncButton = view.findViewById(R.id.encButton);

        onEncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextInputEditText et = view.findViewById(R.id.PANNumber);
                panNumber = et.getText().toString();

                et = view.findViewById(R.id.AadharNumber);
                aadharNumber = et.getText().toString();

                com.google.android.material.card.MaterialCardView mcv = view.findViewById(R.id.materialCardViewEnc2);
                mcv.setVisibility(View.INVISIBLE);

                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.initAct, new ClickPhoto());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        return view;
    }

}
