package com.chaabene;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class CongratulationsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulations);
    }

    public void onDoneClick(View v){
        Intent intent = new Intent(this, com.chaabene.MainActivity.class);
        startActivity(intent);
    }
}
