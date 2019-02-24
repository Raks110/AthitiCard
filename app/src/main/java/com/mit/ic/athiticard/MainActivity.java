package com.mit.ic.athiticard;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*public void changeFragment(View view){

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
    }*/

    /*private static final Pattern KEYCODE_PATTERN = Pattern.compile("/^[0-9]{10}$/");
    private com.google.android.material.button.MaterialButton viewNumber;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        viewNumber = findViewById(R.id.tapButton);
        String cardNumber;

        String key = KeyEvent.keyCodeToString(keyCode);
        Matcher matcher = KEYCODE_PATTERN.matcher(key);
        if (matcher.matches()) {
            viewNumber.append(matcher.group(1));
        }

        viewNumber.setVisibility(View.VISIBLE);

        return super.onKeyDown(keyCode, event);
    }*/
}
