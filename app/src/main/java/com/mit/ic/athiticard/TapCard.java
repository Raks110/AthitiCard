package com.mit.ic.athiticard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class TapCard extends Fragment {

    public static String AthitiCardNumber;
    public static int flag;

    public TapCard() {
        flag = 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_tap_card, container, false);

        MaterialButton onTapButton = view.findViewById(R.id.tapButton);

        onTapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flag==1){
                    flag = 0;
                    return;
                }

                MaterialCardView mcv = view.findViewById(R.id.materialCardViewTap2);
                mcv.setVisibility(View.GONE);

                MaterialButton mb = v.findViewById(R.id.tapButton);

                AthitiCardNumber = mb.getText().toString();

                mb.setVisibility(View.INVISIBLE);

                RelativeLayout rl = view.findViewById(R.id.rl_tap);
                rl.setVisibility(View.INVISIBLE);

                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.initAct, new EncDetails());
                fragmentTransaction.commit();

            }
        });

        return view;
    }




}
