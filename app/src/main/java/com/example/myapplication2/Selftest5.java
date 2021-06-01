package com.example.myapplication2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class Selftest5 extends AppCompatActivity implements View.OnClickListener {
    String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selftest5);
        Button btn_back = (Button) findViewById(R.id.test5back);

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
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);

                intent.putExtra("Id", ID);
                startActivity(intent);
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        });

        Button remind_btn = findViewById(R.id.remind_btn);
        remind_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity3.class);
                intent.putExtra("Id", ID);
                startActivity(intent);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Selftest5.this, Selftest_main.class);
                intent.putExtra("Id", ID);
                startActivity(intent);
            }
        });

        Button btn_done = (Button) findViewById(R.id.test5done);
        btn_done.setOnClickListener(this);

        Intent intent1 = getIntent();

        findViewById(R.id.radio_1_5_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_1_5_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_1_5_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_1_5_4).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_2_5_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_2_5_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_2_5_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_2_5_4).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_3_5_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_3_5_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_3_5_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_3_5_4).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_4_5_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_4_5_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_4_5_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_4_5_4).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_5_5_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_5_5_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_5_5_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_5_5_4).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_6_5_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_6_5_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_6_5_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_6_5_4).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_7_5_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_7_5_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_7_5_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_7_5_4).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_8_5_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_8_5_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_8_5_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_8_5_4).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_9_5_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_9_5_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_9_5_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_9_5_4).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_10_5_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_10_5_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_10_5_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_10_5_4).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_11_5_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_11_5_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_11_5_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_11_5_4).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});




    }

    public int Checked(View v){
        RadioButton radio1_1 = (RadioButton)findViewById(R.id.radio_1_5_1);
        RadioButton radio1_2 = (RadioButton)findViewById(R.id.radio_1_5_2);
        RadioButton radio1_3 = (RadioButton)findViewById(R.id.radio_1_5_3);
        RadioButton radio1_4 = (RadioButton)findViewById(R.id.radio_1_5_4);

        RadioButton radio2_1 = (RadioButton)findViewById(R.id.radio_2_5_1);
        RadioButton radio2_2 = (RadioButton)findViewById(R.id.radio_2_5_2);
        RadioButton radio2_3 = (RadioButton)findViewById(R.id.radio_2_5_3);
        RadioButton radio2_4 = (RadioButton)findViewById(R.id.radio_2_5_4);

        RadioButton radio3_1 = (RadioButton)findViewById(R.id.radio_3_5_1);
        RadioButton radio3_2 = (RadioButton)findViewById(R.id.radio_3_5_2);
        RadioButton radio3_3 = (RadioButton)findViewById(R.id.radio_3_5_3);
        RadioButton radio3_4 = (RadioButton)findViewById(R.id.radio_3_5_4);

        RadioButton radio4_1 = (RadioButton)findViewById(R.id.radio_4_5_1);
        RadioButton radio4_2 = (RadioButton)findViewById(R.id.radio_4_5_2);
        RadioButton radio4_3 = (RadioButton)findViewById(R.id.radio_4_5_3);
        RadioButton radio4_4 = (RadioButton)findViewById(R.id.radio_4_5_4);

        RadioButton radio5_1 = (RadioButton)findViewById(R.id.radio_5_5_1);
        RadioButton radio5_2 = (RadioButton)findViewById(R.id.radio_5_5_2);
        RadioButton radio5_3 = (RadioButton)findViewById(R.id.radio_5_5_3);
        RadioButton radio5_4 = (RadioButton)findViewById(R.id.radio_5_5_4);

        RadioButton radio6_1 = (RadioButton)findViewById(R.id.radio_6_5_1);
        RadioButton radio6_2 = (RadioButton)findViewById(R.id.radio_6_5_2);
        RadioButton radio6_3 = (RadioButton)findViewById(R.id.radio_6_5_3);
        RadioButton radio6_4 = (RadioButton)findViewById(R.id.radio_6_5_4);
        RadioButton radio7_1 = (RadioButton)findViewById(R.id.radio_7_5_1);
        RadioButton radio7_2 = (RadioButton)findViewById(R.id.radio_7_5_2);
        RadioButton radio7_3 = (RadioButton)findViewById(R.id.radio_7_5_3);
        RadioButton radio7_4 = (RadioButton)findViewById(R.id.radio_7_5_4);

        RadioButton radio8_1 = (RadioButton)findViewById(R.id.radio_8_5_1);
        RadioButton radio8_2 = (RadioButton)findViewById(R.id.radio_8_5_2);
        RadioButton radio8_3 = (RadioButton)findViewById(R.id.radio_8_5_3);
        RadioButton radio8_4 = (RadioButton)findViewById(R.id.radio_8_5_4);

        RadioButton radio9_1 = (RadioButton)findViewById(R.id.radio_9_5_1);
        RadioButton radio9_2 = (RadioButton)findViewById(R.id.radio_9_5_2);
        RadioButton radio9_3 = (RadioButton)findViewById(R.id.radio_9_5_3);
        RadioButton radio9_4 = (RadioButton)findViewById(R.id.radio_9_5_4);

        RadioButton radio10_1 = (RadioButton)findViewById(R.id.radio_10_5_1);
        RadioButton radio10_2 = (RadioButton)findViewById(R.id.radio_10_5_2);
        RadioButton radio10_3 = (RadioButton)findViewById(R.id.radio_10_5_3);
        RadioButton radio10_4 = (RadioButton)findViewById(R.id.radio_10_5_4);

        RadioButton radio11_1 = (RadioButton)findViewById(R.id.radio_11_5_1);
        RadioButton radio11_2 = (RadioButton)findViewById(R.id.radio_11_5_2);
        RadioButton radio11_3 = (RadioButton)findViewById(R.id.radio_11_5_3);
        RadioButton radio11_4 = (RadioButton)findViewById(R.id.radio_11_5_4);



        int score = 500;

        if(radio1_1.isChecked()) {}
        if(radio1_2.isChecked()) score += 4;
        if(radio1_3.isChecked()) score += 8;
        if(radio1_4.isChecked()) score += 12;

        if(radio2_1.isChecked()) {}
        if(radio2_2.isChecked()) score += 2;
        if(radio2_3.isChecked()) score += 4;
        if(radio2_4.isChecked()) score += 6;

        if(radio3_1.isChecked()) {}
        if(radio3_2.isChecked()) score += 2;
        if(radio3_3.isChecked()) score += 4;
        if(radio3_4.isChecked()) score += 6;

        if(radio4_1.isChecked()) {}
        if(radio4_2.isChecked()) score += 2;
        if(radio4_3.isChecked()) score += 4;
        if(radio4_4.isChecked()) score += 6;

        if(radio5_1.isChecked()) {}
        if(radio5_2.isChecked()) score++;
        if(radio5_3.isChecked()) score += 2;
        if(radio5_4.isChecked()) score += 3;

        if(radio6_1.isChecked()) {}
        if(radio6_2.isChecked()) score++;
        if(radio6_3.isChecked()) score += 2;
        if(radio6_4.isChecked()) score += 3;

        if(radio7_1.isChecked()) {}
        if(radio7_2.isChecked()) score++;
        if(radio7_3.isChecked()) score += 2;
        if(radio7_4.isChecked()) score += 3;

        if(radio8_1.isChecked()) {}
        if(radio8_2.isChecked()) score++;
        if(radio8_3.isChecked()) score += 2;
        if(radio8_4.isChecked()) score += 3;

        if(radio9_1.isChecked()) {}
        if(radio9_2.isChecked()) score++;
        if(radio9_3.isChecked()) score += 2;
        if(radio9_4.isChecked()) score += 3;

        if(radio10_1.isChecked()) {}
        if(radio10_2.isChecked()) score++;
        if(radio10_3.isChecked()) score += 2;
        if(radio10_4.isChecked()) score += 3;

        if(radio11_1.isChecked()) {}
        if(radio11_2.isChecked()) score++;
        if(radio11_3.isChecked()) score += 2;
        if(radio11_4.isChecked()) score += 3;


        return score;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.test5done){
            Intent intent = new Intent(Selftest5.this, SelftestResult.class);
            String[] strings = new String[2];
            strings[0] = ID;
            strings[1] = Integer.toString(Checked(v));
            intent.putExtra("score", strings);
            startActivity(intent);
        }
    }
}