package com.mit.ic.athiticard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class activity_initial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("actinit","Inside onCreate");
        setContentView(R.layout.activity_initial);
    }

    public void launchUserReg(View view){

        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("key","success");
        startActivity(intent);
    }

    public void launchVendorReg(View view){
        Intent intent = new Intent(this,VendorInit.class);
        intent.putExtra("key","success");
        startActivity(intent);
    }

    public void launchUserUpdate(View view) {
        Intent intent = new Intent(this,UpdateUser.class);
        intent.putExtra("key","success");
        startActivity(intent);
    }

}
