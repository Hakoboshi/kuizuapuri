package com.lifeistech.kuizuapuri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        setTitle("STUDYクイズアプリ");
    }

    public void button (View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }

}
