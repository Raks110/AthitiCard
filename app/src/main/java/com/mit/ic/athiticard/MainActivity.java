package com.mit.ic.athiticard;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Fragment fragment;
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        fragment = new EncDetails();
//        ft.replace(R.id.initAct,fragment);
//        ft.commit();
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
