package com.mit.ic.athiticard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.Key;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mDatabase = FirebaseDatabase.getInstance().getReference();

        super.onCreate(savedInstanceState);
        Log.e("1.","inside Main");

        Intent intent = getIntent();
        String message = intent.getStringExtra("key");
        Log.e("key",message);
        setContentView(R.layout.activity_main);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }

    private static final Pattern KEYCODE_PATTERN = Pattern.compile("KEYCODE_[0-9]");
    private com.google.android.material.button.MaterialButton viewNumber;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(TapCard.flag == 0 || keyCode == KeyEvent.KEYCODE_BACK){
            return super.onKeyDown(keyCode, event);
        }

        viewNumber = findViewById(R.id.tapButton);
        String cardNumber = (String)viewNumber.getText();

        String key = KeyEvent.keyCodeToString(keyCode);
        Matcher matcher = KEYCODE_PATTERN.matcher(key);
        if (matcher.matches()) {
            cardNumber += matcher.group().charAt(matcher.group().length() -1);
        }

        viewNumber.setText(cardNumber);

        if(findViewById(R.id.materialCardViewTap2).getVisibility() == View.VISIBLE)
            viewNumber.setVisibility(View.VISIBLE);

        return super.onKeyDown(keyCode, event);
    }
}
