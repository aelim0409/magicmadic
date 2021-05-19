package com.example.myapplication2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class Selftest4 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selftest4);
        Button btn_back = (Button) findViewById(R.id.test4back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Selftest4.this, Selftest_main.class);
                startActivity(intent);
            }
        });

        Button btn_done = (Button) findViewById(R.id.test4done);
        btn_done.setOnClickListener(this);

        Intent intent1 = getIntent();

        findViewById(R.id.radio_1_4_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_1_4_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_1_4_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_2_4_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_2_4_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_2_4_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_3_4_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_3_4_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_3_4_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_4_4_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_4_4_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_4_4_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_5_4_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_5_4_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_5_4_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_6_4_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_6_4_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_6_4_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_7_4_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_7_4_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_7_4_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_8_4_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_8_4_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_8_4_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_9_4_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_9_4_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_9_4_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_10_4_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_10_4_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_10_4_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_11_4_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_11_4_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_11_4_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_12_4_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_12_4_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_12_4_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_13_4_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_13_4_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_13_4_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_14_4_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_14_4_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_14_4_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_15_4_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_15_4_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_15_4_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_16_4_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_16_4_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_16_4_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_17_4_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_17_4_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_17_4_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_18_4_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_18_4_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_18_4_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_19_4_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_19_4_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_19_4_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

        findViewById(R.id.radio_20_4_1).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_20_4_2).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});
        findViewById(R.id.radio_20_4_3).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { Checked(v); }});

    }

    public int Checked(View v){
        RadioButton radio1_1 = (RadioButton)findViewById(R.id.radio_1_4_1);
        RadioButton radio1_2 = (RadioButton)findViewById(R.id.radio_1_4_2);
        RadioButton radio1_3 = (RadioButton)findViewById(R.id.radio_1_4_3);

        RadioButton radio2_1 = (RadioButton)findViewById(R.id.radio_2_4_1);
        RadioButton radio2_2 = (RadioButton)findViewById(R.id.radio_2_4_2);
        RadioButton radio2_3 = (RadioButton)findViewById(R.id.radio_2_4_3);

        RadioButton radio3_1 = (RadioButton)findViewById(R.id.radio_3_4_1);
        RadioButton radio3_2 = (RadioButton)findViewById(R.id.radio_3_4_2);
        RadioButton radio3_3 = (RadioButton)findViewById(R.id.radio_3_4_3);

        RadioButton radio4_1 = (RadioButton)findViewById(R.id.radio_4_4_1);
        RadioButton radio4_2 = (RadioButton)findViewById(R.id.radio_4_4_2);
        RadioButton radio4_3 = (RadioButton)findViewById(R.id.radio_4_4_3);

        RadioButton radio5_1 = (RadioButton)findViewById(R.id.radio_5_4_1);
        RadioButton radio5_2 = (RadioButton)findViewById(R.id.radio_5_4_2);
        RadioButton radio5_3 = (RadioButton)findViewById(R.id.radio_5_4_3);

        RadioButton radio6_1 = (RadioButton)findViewById(R.id.radio_6_4_1);
        RadioButton radio6_2 = (RadioButton)findViewById(R.id.radio_6_4_2);
        RadioButton radio6_3 = (RadioButton)findViewById(R.id.radio_6_4_3);

        RadioButton radio7_1 = (RadioButton)findViewById(R.id.radio_7_4_1);
        RadioButton radio7_2 = (RadioButton)findViewById(R.id.radio_7_4_2);
        RadioButton radio7_3 = (RadioButton)findViewById(R.id.radio_7_4_3);

        RadioButton radio8_1 = (RadioButton)findViewById(R.id.radio_8_4_1);
        RadioButton radio8_2 = (RadioButton)findViewById(R.id.radio_8_4_2);
        RadioButton radio8_3 = (RadioButton)findViewById(R.id.radio_8_4_3);

        RadioButton radio9_1 = (RadioButton)findViewById(R.id.radio_9_4_1);
        RadioButton radio9_2 = (RadioButton)findViewById(R.id.radio_9_4_2);
        RadioButton radio9_3 = (RadioButton)findViewById(R.id.radio_9_4_3);

        RadioButton radio10_1 = (RadioButton)findViewById(R.id.radio_10_4_1);
        RadioButton radio10_2 = (RadioButton)findViewById(R.id.radio_10_4_2);
        RadioButton radio10_3 = (RadioButton)findViewById(R.id.radio_10_4_3);

        RadioButton radio11_1 = (RadioButton)findViewById(R.id.radio_11_4_1);
        RadioButton radio11_2 = (RadioButton)findViewById(R.id.radio_11_4_2);
        RadioButton radio11_3 = (RadioButton)findViewById(R.id.radio_11_4_3);

        RadioButton radio12_1 = (RadioButton)findViewById(R.id.radio_12_4_1);
        RadioButton radio12_2 = (RadioButton)findViewById(R.id.radio_12_4_2);
        RadioButton radio12_3 = (RadioButton)findViewById(R.id.radio_12_4_3);

        RadioButton radio13_1 = (RadioButton)findViewById(R.id.radio_13_4_1);
        RadioButton radio13_2 = (RadioButton)findViewById(R.id.radio_13_4_2);
        RadioButton radio13_3 = (RadioButton)findViewById(R.id.radio_13_4_3);

        RadioButton radio14_1 = (RadioButton)findViewById(R.id.radio_14_4_1);
        RadioButton radio14_2 = (RadioButton)findViewById(R.id.radio_14_4_2);
        RadioButton radio14_3 = (RadioButton)findViewById(R.id.radio_14_4_3);

        RadioButton radio15_1 = (RadioButton)findViewById(R.id.radio_15_4_1);
        RadioButton radio15_2 = (RadioButton)findViewById(R.id.radio_15_4_2);
        RadioButton radio15_3 = (RadioButton)findViewById(R.id.radio_15_4_3);

        RadioButton radio16_1 = (RadioButton)findViewById(R.id.radio_16_4_1);
        RadioButton radio16_2 = (RadioButton)findViewById(R.id.radio_16_4_2);
        RadioButton radio16_3 = (RadioButton)findViewById(R.id.radio_16_4_3);

        RadioButton radio17_1 = (RadioButton)findViewById(R.id.radio_17_4_1);
        RadioButton radio17_2 = (RadioButton)findViewById(R.id.radio_17_4_2);
        RadioButton radio17_3 = (RadioButton)findViewById(R.id.radio_17_4_3);

        RadioButton radio18_1 = (RadioButton)findViewById(R.id.radio_18_4_1);
        RadioButton radio18_2 = (RadioButton)findViewById(R.id.radio_18_4_2);
        RadioButton radio18_3 = (RadioButton)findViewById(R.id.radio_18_4_3);

        RadioButton radio19_1 = (RadioButton)findViewById(R.id.radio_19_4_1);
        RadioButton radio19_2 = (RadioButton)findViewById(R.id.radio_19_4_2);
        RadioButton radio19_3 = (RadioButton)findViewById(R.id.radio_19_4_3);

        RadioButton radio20_1 = (RadioButton)findViewById(R.id.radio_20_4_1);
        RadioButton radio20_2 = (RadioButton)findViewById(R.id.radio_20_4_2);
        RadioButton radio20_3 = (RadioButton)findViewById(R.id.radio_20_4_3);

        int score = 400;

        if(radio1_1.isChecked()) {}
        if(radio1_2.isChecked()) score++;
        if(radio1_3.isChecked()) score += 2;

        if(radio2_1.isChecked()) {}
        if(radio2_2.isChecked()) score++;
        if(radio2_3.isChecked()) score += 2;

        if(radio3_1.isChecked()) {}
        if(radio3_2.isChecked()) score++;
        if(radio3_3.isChecked()) score += 2;

        if(radio4_1.isChecked()) {}
        if(radio4_2.isChecked()) score++;
        if(radio4_3.isChecked()) score += 2;
        if(radio5_1.isChecked()) {}
        if(radio5_2.isChecked()) score++;
        if(radio5_3.isChecked()) score += 2;

        if(radio6_1.isChecked()) {}
        if(radio6_2.isChecked()) score++;
        if(radio6_3.isChecked()) score += 2;

        if(radio7_1.isChecked()) {}
        if(radio7_2.isChecked()) score++;
        if(radio7_3.isChecked()) score += 2;
        if(radio8_1.isChecked()) {}
        if(radio8_2.isChecked()) score++;
        if(radio8_3.isChecked()) score += 2;

        if(radio9_1.isChecked()) {}
        if(radio9_2.isChecked()) score++;
        if(radio9_3.isChecked()) score += 2;
        if(radio10_1.isChecked()) {}
        if(radio10_2.isChecked()) score++;
        if(radio10_3.isChecked()) score += 2;
        if(radio11_1.isChecked()) {}
        if(radio11_2.isChecked()) score++;
        if(radio11_3.isChecked()) score += 2;

        if(radio12_1.isChecked()) {}
        if(radio12_2.isChecked()) score++;
        if(radio12_3.isChecked()) score += 2;
        if(radio13_1.isChecked()) {}
        if(radio13_2.isChecked()) score++;
        if(radio13_3.isChecked()) score += 2;

        if(radio14_1.isChecked()) {}
        if(radio14_2.isChecked()) score++;
        if(radio14_3.isChecked()) score += 2;

        if(radio15_1.isChecked()) {}
        if(radio15_2.isChecked()) score++;
        if(radio15_3.isChecked()) score += 2;
        if(radio16_1.isChecked()) {}
        if(radio16_2.isChecked()) score++;
        if(radio16_3.isChecked()) score += 2;
        if(radio17_1.isChecked()) {}
        if(radio17_2.isChecked()) score++;
        if(radio17_3.isChecked()) score += 2;
        if(radio18_1.isChecked()) {}
        if(radio18_2.isChecked()) score++;
        if(radio18_3.isChecked()) score += 2;
        if(radio19_1.isChecked()) {}
        if(radio19_2.isChecked()) score++;
        if(radio19_3.isChecked()) score += 2;

        if(radio20_1.isChecked()) {}
        if(radio20_2.isChecked()) score++;
        if(radio20_3.isChecked()) score += 2;

        return score;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.test4done){
            Intent intent = new Intent(Selftest4.this, SelftestResult.class);
            intent.putExtra("score", Checked(v));
            startActivity(intent);
        }
    }
}