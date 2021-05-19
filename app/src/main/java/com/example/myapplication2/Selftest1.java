package com.example.myapplication2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;



//0 자궁경부암
// 아르거시-갱년기
// 다낭성 난소 증후군
// 자궁근종
// 쿠퍼만-갱년기
// 폐경기

public class Selftest1 extends AppCompatActivity implements View.OnClickListener {
    int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selftest1);

        Button btn_back = (Button) findViewById(R.id.test1back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Selftest1.this, Selftest_main.class);
                startActivity(intent);
            }
        });

        Button btn_done = (Button) findViewById(R.id.test1done);
        btn_done.setOnClickListener(this);

        Intent intent1 = getIntent();

        findViewById(R.id.check1_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });
        findViewById(R.id.check2_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check3_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check4_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check5_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check6_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check7_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check8_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });
        findViewById(R.id.check9_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });
        findViewById(R.id.check10_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });
    }

    public int Checked(View v){
        CheckBox check1 = (CheckBox) findViewById(R.id.check1_1);
        CheckBox check2 = (CheckBox) findViewById(R.id.check2_1);
        CheckBox check3 = (CheckBox) findViewById(R.id.check3_1);
        CheckBox check4 = (CheckBox) findViewById(R.id.check4_1);
        CheckBox check5 = (CheckBox) findViewById(R.id.check5_1);
        CheckBox check6 = (CheckBox) findViewById(R.id.check6_1);
        CheckBox check7 = (CheckBox) findViewById(R.id.check7_1);
        CheckBox check8 = (CheckBox) findViewById(R.id.check8_1);
        CheckBox check9 = (CheckBox) findViewById(R.id.check9_1);
        CheckBox check10 = (CheckBox) findViewById(R.id.check10_1);

        int score = 100;
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

        return score;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.test1done){
            Intent intent = new Intent(Selftest1.this, SelftestResult.class);
            intent.putExtra("score", Checked(v));
            startActivity(intent);
        }
    }
}