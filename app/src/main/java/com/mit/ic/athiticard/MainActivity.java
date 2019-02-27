package com.mit.ic.athiticard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("1.","inside Main");

        Intent intent = getIntent();
        String message = intent.getStringExtra("key");
        Log.e("key",message);
        setContentView(R.layout.activity_main);

    }

    private static final Pattern KEYCODE_PATTERN = Pattern.compile("KEYCODE_[0-9]");
    private com.google.android.material.button.MaterialButton viewNumber;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

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
