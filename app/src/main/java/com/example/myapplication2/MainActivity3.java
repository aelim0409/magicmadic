package com.example.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Switch switch1=findViewById(R.id.switch1);
        Switch switch2=findViewById(R.id.switch2);
        Switch switch3=findViewById(R.id.switch3);
        Switch switch4=findViewById(R.id.switch4);
        Switch switch5=findViewById(R.id.switch5);

        class switch1Listener implements CompoundButton.OnCheckedChangeListener {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Intent intent = new Intent(getApplicationContext(), pills_mod_reminder.class);
                    startActivity(intent);
                }
            }
        }

        class switch3Listener implements CompoundButton.OnCheckedChangeListener {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Intent intent = new Intent(getApplicationContext(), HospitalModReminderActivity.class);
                    startActivity(intent);
                }
            }
        }

        switch1.setOnCheckedChangeListener(new switch1Listener());
        switch3.setOnCheckedChangeListener(new switch3Listener());






        Button btn_home = findViewById(R.id.home_btn);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),basic_information_page.class);
                startActivity(intent);
            }
        });

        Button fitness_btn = findViewById(R.id.fitness_btn);
        fitness_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),basic_information_page.class);
                startActivity(intent);
            }
        });


        Button calendar_btn = findViewById(R.id.calendar_btn);
        calendar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),basic_information_page.class);
                startActivity(intent);
            }
        });

        Button remind_btn = findViewById(R.id.remind_btn);
        remind_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),basic_information_page.class);
                startActivity(intent);
            }
        });
    }
}
