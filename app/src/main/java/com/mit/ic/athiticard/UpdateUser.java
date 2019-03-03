package com.mit.ic.athiticard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateUser extends AppCompatActivity {

    private static String AthitiCardNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );

        MaterialButton onTapButton = findViewById(R.id.tapButton);

        onTapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MaterialCardView mcv = findViewById(R.id.materialCardViewTap2);
                mcv.setVisibility(View.GONE);

                MaterialButton mb = v.findViewById(R.id.tapButton);

                AthitiCardNumber = (String)mb.getText();

                mb.setVisibility(View.INVISIBLE);

                RelativeLayout rl = findViewById(R.id.rl_tap);
                rl.setVisibility(View.INVISIBLE);

                setContentView(R.layout.fragment_basic_details);

            }
        });

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
