package com.mit.ic.athiticard;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import com.google.android.material.button.MaterialButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;
import com.mit.ic.athiticard.Models.PersonalDetails;
import com.mit.ic.athiticard.Models.Address;
import com.mit.ic.athiticard.Utilities.RandomPin;
import com.mit.ic.athiticard.Utilities.SendSms;
import com.mit.ic.athiticard.Utilities.SmsListener;
import com.roger.catloadinglibrary.CatLoadingView;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class General extends Fragment {

    public static PersonalDetails pd;
    private RandomPin rpin;

    public General() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        pd = new PersonalDetails();
        rpin = new RandomPin();

        int randomNumber = rpin.generateRandomNumber();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("OTP_PIN", randomNumber);
        editor.apply();

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
                final String preqText = et.getText().toString();

                pd.setPhoneNumber("0");

                final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

                SendSms.sendMsg(preqText, preferences.getInt("OTP_PIN",0));

                final CatLoadingView clv = new CatLoadingView();
                clv.show(getFragmentManager(),"");
                
                SmsListener receiveSMS = new SmsListener() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        try {
                            clv.dismissAllowingStateLoss();
                            String smsBody = intent.getStringExtra("sms");
                            String pin = smsBody.replace("Athiti OTP is: ", "").trim();
                            int reqPin = Integer.parseInt(pin);
                            if(reqPin == preferences.getInt("OTP_PIN",0)){
                                pd.setPhoneNumber(preqText);
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                };

                if(pd.getPhoneNumber() == "0"){
                    Toast.makeText(getContext(), "OTP didn't match.", Toast.LENGTH_LONG).show();
                }

                else {
                    com.google.android.material.card.MaterialCardView mcv = view.findViewById(R.id.materialCardViewGen2);
                    mcv.setVisibility(View.INVISIBLE);

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.initAct, new JobDetails());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }

            }
        });

        return view;

    }

}
