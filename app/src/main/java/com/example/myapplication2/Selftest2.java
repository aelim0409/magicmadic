package com.example.myapplication2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class Selftest2 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selftest2);
        Button btn_back = (Button) findViewById(R.id.test2back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Selftest2.this, Selftest_main.class);
                startActivity(intent);
            }
        });

        Button btn_done = (Button) findViewById(R.id.test2done);
        btn_done.setOnClickListener(this);

        Intent intent1 = getIntent();

        findViewById(R.id.check1_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });
        findViewById(R.id.check2_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check3_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check4_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check5_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check6_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check7_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check8_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check9_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check10_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check11_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });
        findViewById(R.id.check12_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check13_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check14_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });
    }

    public int Checked(View v){
        CheckBox check1 = (CheckBox) findViewById(R.id.check1_2);
        CheckBox check2 = (CheckBox) findViewById(R.id.check2_2);
        CheckBox check3 = (CheckBox) findViewById(R.id.check3_2);
        CheckBox check4 = (CheckBox) findViewById(R.id.check4_2);
        CheckBox check5 = (CheckBox) findViewById(R.id.check5_2);
        CheckBox check6 = (CheckBox) findViewById(R.id.check6_2);
        CheckBox check7 = (CheckBox) findViewById(R.id.check7_2);
        CheckBox check8 = (CheckBox) findViewById(R.id.check8_2);
        CheckBox check9 = (CheckBox) findViewById(R.id.check9_2);
        CheckBox check10 = (CheckBox) findViewById(R.id.check10_2);
        CheckBox check11 = (CheckBox) findViewById(R.id.check11_2);
        CheckBox check12 = (CheckBox) findViewById(R.id.check12_2);
        CheckBox check13 = (CheckBox) findViewById(R.id.check13_2);
        CheckBox check14 = (CheckBox) findViewById(R.id.check14_2);

        int score = 200;
        if(check1.isChecked()) score++;
        if(check2.isChecked()) score++;
        if(check3.isChecked()) score++;
        if(check4.isChecked()) score++;
        if(check5.isChecked()) score++;
        if(check6.isChecked()) score++;
        if(check7.isChecked()) score++;
        if(check8.isChecked()) score++;
        if(check9.isChecked()) score++;
        if(check10.isChecked()) score++;
        if(check11.isChecked()) score++;
        if(check12.isChecked()) score++;
        if(check13.isChecked()) score++;
        if(check14.isChecked()) score++;

        return score;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.test2done){
            Intent intent = new Intent(Selftest2.this, SelftestResult.class);
            intent.putExtra("score", Checked(v));
            startActivity(intent);
        }
    }
}