package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelftestResult extends AppCompatActivity {

    // 0자궁경부암
    // 1아르거시-갱년기
    // 3다낭성 난소 증후군
    // 2자궁근종
    // 5쿠퍼만-갱년기
    // 4폐경기
    int disease_code, disease_result;
    String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selftest_result);

        Intent Intent = getIntent();
        String[] strings = Intent.getStringArrayExtra("score");
        ID = strings[0];
        disease_code = Integer.parseInt(strings[1]);

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
               // System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
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



        TextView disease = (TextView) findViewById(R.id.diseasename);
        TextView result = (TextView) findViewById(R.id.diseaseresult);
        Button back = (Button) findViewById(R.id.btn_back);


        disease_result = disease_code % 100 ;
        disease_code = disease_code / 100;
        if( disease_code == 0){
            disease.setText("자궁경부암 자가 진단 결과");
            if(disease_result > 4){
                result.setText("의사와의 상담이 필요합니다.");
            }
            else{
                result.setText("해당 사항이 없습니다 :)");
            }
        }
        else if(disease_code == 1){
            disease.setText("아르거시 갱년기 자가 진단 결과");
            if(disease_result > 3){
                result.setText("갱년기 증상이 있습니다.");
            }
            else{
                result.setText("갱년기 증상이 거의 없습니다.");
            }
        }

        else if(disease_code == 2){
            disease.setText("자궁근종 자가 진단 결과");
            if(disease_result > 4){
                result.setText("자궁근종이 의심되오니 의사와 상담해보시길 권장드립니다.");
            }
            else{
                result.setText("해당 사항이 없습니다 :)");
            }
        }

        else if(disease_code == 3){
            disease.setText("다낭성 난소 증후군 자가 진단 결과");
            if(disease_result > 4){
                result.setText("다낭성 난소 증후군이 의심되오니 의사와 상담해보시길 권장드립니다.");
            }
            else{
                result.setText("해당 사항이 없습니다 :)");
            }
        }

        else if(disease_code == 4){
            disease.setText("폐경기 자가 진단 결과");
            if(disease_result >= 30){
                result.setText("심한 폐경기 증상 입니다..");
            }
            else if(disease_result > 15){
                result.setText("중증도의 폐경기 증상 입니다.");
            }
            else{
                result.setText("경미한 폐경기 증상 입니다.");
            }
        }

        else if(disease_code == 5){
            disease.setText("쿠퍼만 갱년기 자가 진단 결과");
            if(disease_result >= 25){
                result.setText("심각한 상태로 전문가와의 상담이 필요합니다.");
            }
            else if(disease_result >= 20){
                result.setText("중증 상태로 전문가와의 상담이 필요합니다.");
            }
            else if(disease_result >= 15){
                result.setText("경증 상태로 전문가와의 상담이 필요합니다.");
            }
            else if(disease_result >= 10){
                result.setText("매우 좋은 상태는 아니며 관리가 필요한 상황입니다.");
            }
            else{
                result.setText("몸 상태는 좋지만 지금부터 관리하는 것이 좋습니다.");
            }
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SelftestResult.this, Selftest_main.class);
                intent1.putExtra("Id", ID);
                startActivity(intent1);
            }
        });

    }
}