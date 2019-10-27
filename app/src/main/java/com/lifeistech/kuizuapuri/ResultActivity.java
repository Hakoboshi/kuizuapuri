package com.lifeistech.kuizuapuri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView textView;
    int point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textView = findViewById(R.id.textView);

        Intent intent = getIntent();
        point = intent.getIntExtra("tokuten",0);

        textView.setText(point + "点");

        setTitle("結果発表");
    }

    public void back(View v){
        finish();
    }
}
