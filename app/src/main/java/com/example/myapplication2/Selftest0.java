package com.example.myapplication2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;



public class Selftest0 extends AppCompatActivity implements View.OnClickListener {
    int score = 0;
    String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selftest0);

        Intent Intent = getIntent();
        ID = Intent.getStringExtra("Id");

        Button btn_home = findViewById(R.id.home_btn);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),basic_information_page.class);
                intent.putExtra("Id", ID);
                startActivity(intent);
            }
        });

        Button fitness_btn = findViewById(R.id.fitness_btn);
        fitness_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Selftest_main.class);
                intent.putExtra("Id", ID);
                startActivity(intent);
            }
        });

        Button graph_btn = findViewById(R.id.graph_btn);
        graph_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),graph.class);
                intent.putExtra("Id", ID);
                startActivity(intent);
            }
        });

        Button calendar_btn = findViewById(R.id.calendar_btn);
        calendar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Calender_Main.class);

                intent.putExtra("Id", ID);
                startActivity(intent);
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        });

        Button remind_btn = findViewById(R.id.remind_btn);
        remind_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Reminder_Main.class);
                intent.putExtra("Id", ID);
                startActivity(intent);
            }
        });



        Button btn_back = (Button) findViewById(R.id.test0back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Selftest0.this, Selftest_main.class);
                intent.putExtra("Id", ID);
                startActivity(intent);
            }
        });

        Button btn_done = (Button) findViewById(R.id.test0done);
        btn_done.setOnClickListener(this);


        findViewById(R.id.check1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });
        findViewById(R.id.check2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });

        findViewById(R.id.check8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checked(v);
            }
        });
    }

    public int Checked(View v){
        CheckBox check1 = (CheckBox) findViewById(R.id.check1);
        CheckBox check2 = (CheckBox) findViewById(R.id.check2);
        CheckBox check3 = (CheckBox) findViewById(R.id.check3);
        CheckBox check4 = (CheckBox) findViewById(R.id.check4);
        CheckBox check5 = (CheckBox) findViewById(R.id.check5);
        CheckBox check6 = (CheckBox) findViewById(R.id.check6);
        CheckBox check7 = (CheckBox) findViewById(R.id.check7);
        CheckBox check8 = (CheckBox) findViewById(R.id.check8);

        int score = 0;
        if(check1.isChecked()) score++;
        if(check2.isChecked()) score++;
        if(check3.isChecked()) score++;
        if(check4.isChecked()) score++;
        if(check5.isChecked()) score++;
        if(check6.isChecked()) score++;
        if(check7.isChecked()) score++;
        if(check8.isChecked()) score++;

        return score;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.test0done){
            Intent intent = new Intent(this, SelftestResult.class);
            String[] strings = new String[2];
            strings[0] = ID;
            strings[1] = Integer.toString(Checked(v));
            intent.putExtra("score", strings);
            startActivity(intent);
        }
    }
}