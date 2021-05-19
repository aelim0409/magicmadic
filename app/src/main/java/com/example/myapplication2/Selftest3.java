package com.example.myapplication2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class Selftest3 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selftest3);
        Button btn_back = (Button) findViewById(R.id.test3back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Selftest3.this, Selftest_main.class);
                startActivity(intent);
            }
        });

        Button btn_done = (Button) findViewById(R.id.test3done);
        btn_done.setOnClickListener(this);

        Intent intent1 = getIntent();

        findViewById(R.id.check1_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });
        findViewById(R.id.check2_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check3_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check4_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check5_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check6_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check7_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check8_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check9_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

    }

    public int Checked(View v){
        CheckBox check1 = (CheckBox) findViewById(R.id.check1_3);
        CheckBox check2 = (CheckBox) findViewById(R.id.check2_3);
        CheckBox check3 = (CheckBox) findViewById(R.id.check3_3);
        CheckBox check4 = (CheckBox) findViewById(R.id.check4_3);
        CheckBox check5 = (CheckBox) findViewById(R.id.check5_3);
        CheckBox check6 = (CheckBox) findViewById(R.id.check6_3);
        CheckBox check7 = (CheckBox) findViewById(R.id.check7_3);
        CheckBox check8 = (CheckBox) findViewById(R.id.check8_3);
        CheckBox check9 = (CheckBox) findViewById(R.id.check9_3);

        int score = 300;
        if(check1.isChecked()) score++;
        if(check2.isChecked()) score++;
        if(check3.isChecked()) score++;
        if(check4.isChecked()) score++;
        if(check5.isChecked()) score++;
        if(check6.isChecked()) score++;
        if(check7.isChecked()) score++;
        if(check8.isChecked()) score++;
        if(check9.isChecked()) score++;


        return score;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.test2done){
            Intent intent = new Intent(Selftest3.this, SelftestResult.class);
            intent.putExtra("score", Checked(v));
            startActivity(intent);
        }
    }
}