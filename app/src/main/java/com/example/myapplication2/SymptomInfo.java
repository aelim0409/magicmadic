package com.example.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class SymptomInfo extends AppCompatActivity {


    CheckBox symptoms1,symptoms2,symptoms3,symptoms4,symptoms5,symptoms6,symptoms7,symptoms8,symptoms9,symptoms10,symptoms11,symptoms12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_info);
        Intent Intent = getIntent();
        String ID = Intent.getStringExtra("Id");
        //String [] init_info = getInformation(ID).split(" ");

        symptoms1=(CheckBox)findViewById(R.id.symptom1);
        symptoms2=(CheckBox)findViewById(R.id.symptom2);
        symptoms3=(CheckBox)findViewById(R.id.symptom3);
        symptoms4=(CheckBox)findViewById(R.id.symptom4);
        symptoms5=(CheckBox)findViewById(R.id.symptom5);
        symptoms6=(CheckBox)findViewById(R.id.symptom6);
        symptoms7=(CheckBox)findViewById(R.id.symptom7);
        symptoms8=(CheckBox)findViewById(R.id.symptom8);
        symptoms9=(CheckBox)findViewById(R.id.symptom9);
        symptoms10=(CheckBox)findViewById(R.id.symptom10);
        symptoms11=(CheckBox)findViewById(R.id.symptom11);
        symptoms12=(CheckBox)findViewById(R.id.symptom12);

        symptoms1.setButtonDrawable(R.drawable.my_selector);
        symptoms2.setButtonDrawable(R.drawable.my_selector);
        symptoms3.setButtonDrawable(R.drawable.my_selector);
        symptoms4.setButtonDrawable(R.drawable.my_selector);
        symptoms6.setButtonDrawable(R.drawable.my_selector);
        symptoms5.setButtonDrawable(R.drawable.my_selector);
        symptoms7.setButtonDrawable(R.drawable.my_selector);
        symptoms8.setButtonDrawable(R.drawable.my_selector);
        symptoms9.setButtonDrawable(R.drawable.my_selector);
        symptoms10.setButtonDrawable(R.drawable.my_selector);
        symptoms11.setButtonDrawable(R.drawable.my_selector);
        symptoms12.setButtonDrawable(R.drawable.my_selector);

        String [] symptom_Check={"false","false","false","false","false","false","false","false","false","false","false","false"};
        symptoms1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    symptom_Check[0]="true";
                } else {
                    symptom_Check[0]="false";
                }


            }
        });
        symptoms2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    symptom_Check[1]="true";
                } else {
                    symptom_Check[1]="false";
                }
            }
        });
        symptoms3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    symptom_Check[2]="true";
                } else {
                    symptom_Check[2]="false";
                }
            }
        });
        symptoms4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    symptom_Check[3]="true";
                } else {
                    symptom_Check[3]="false";
                }
            }
        });
        symptoms5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    symptom_Check[4]="true";
                } else {
                    symptom_Check[4]="false";
                }
            }
        });
        symptoms6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    symptom_Check[5]="true";
                } else {
                    symptom_Check[5]="false";
                }
            }
        });
        symptoms7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    symptom_Check[6]="true";
                } else {
                    symptom_Check[6]="false";
                }
            }
        });
        symptoms8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    symptom_Check[7]="true";
                } else {
                    symptom_Check[7]="false";
                }

            }
        });
        symptoms9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    symptom_Check[8]="true";
                } else {
                    symptom_Check[8]="false";
                }
            }
        });symptoms10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    symptom_Check[9]="true";
                } else {
                    symptom_Check[9]="false";
                }
            }
        });
        symptoms11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    symptom_Check[10]="true";
                } else {
                    symptom_Check[10]="false";
                }
            }
        });
        symptoms12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) {
                    symptom_Check[11]="true";
                } else {
                    symptom_Check[11]="false";
                }
            }
        });


    }
}