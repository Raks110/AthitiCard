package com.mit.ic.athiticard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        fragment = new TapCard();
        ft.replace(R.id.initAct,fragment);
        ft.commit();
    }

    public void changeFragment(View view){

        Fragment fragment;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if(view == findViewById(R.id.tapCardNumber)){
            fragment = new EncDetails();
            ft.replace(R.id.initAct,fragment);
            ft.commit();
        }

        if(view == findViewById(R.id.encButton)){
            fragment = new General();
            ft.replace(R.id.initAct,fragment);
            ft.commit();
        }
        if(view == findViewById(R.id.generalButton)){
            fragment = new JobDetails();
            ft.replace(R.id.initAct,fragment);
            ft.commit();
        }
        if(view == findViewById(R.id.jobButton)){

        }
    }
}
