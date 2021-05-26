package com.example.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;

public class MucusInfo extends AppCompatActivity {
    CheckBox mucus1,mucus2,mucus3, mucus4,mucus5,mucus6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mucus_info);

        Intent Intent = getIntent();
        String ID = Intent.getStringExtra("Id");
        //String [] init_info = getInformation(ID).split(" ");

        mucus1=(CheckBox)findViewById(R.id.mucus1);
        mucus2=(CheckBox)findViewById(R.id.mucus2);
        mucus3=(CheckBox)findViewById(R.id.mucus3);
        mucus4=(CheckBox)findViewById(R.id.mucus4);
        mucus5=(CheckBox)findViewById(R.id.mucus5);
        mucus6=(CheckBox)findViewById(R.id.mucus6);
        mucus1.setButtonDrawable(R.drawable.my_selector);
        mucus2.setButtonDrawable(R.drawable.my_selector);
        mucus3.setButtonDrawable(R.drawable.my_selector);
        mucus4.setButtonDrawable(R.drawable.my_selector);
        mucus5.setButtonDrawable(R.drawable.my_selector);
        mucus6.setButtonDrawable(R.drawable.my_selector);

    }
}