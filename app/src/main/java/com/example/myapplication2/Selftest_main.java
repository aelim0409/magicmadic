package com.example.myapplication2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Selftest_main extends AppCompatActivity {

    private ListView list;
    private Button btn; // navi bar 버튼 연결 해야함.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selftest_main);

        Intent Intent = getIntent();
        String ID = Intent.getStringExtra("Id");

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

        list = (ListView)findViewById(R.id.selftestlist);

        List<String> data = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);
        list.setAdapter(adapter);

        data.add("자궁경부암");
        data.add("쿠퍼만-갱년기");
        data.add("아르거시-갱년기");
        data.add("다낭성 난소 증후군");
        data.add("자궁근종");
        data.add("폐경기");
        adapter.notifyDataSetChanged();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position);

                if( strText.equals("자궁경부암")){
                    Intent intent1 = new Intent(Selftest_main.this, Selftest0.class);
                    intent1.putExtra("Id", ID);
                    startActivity(intent1);
                }
                else if( strText.equals("아르거시-갱년기")){
                    Intent intent2 = new Intent(Selftest_main.this, Selftest1.class);
                    intent2.putExtra("Id", ID);
                    startActivity(intent2);
                }
                else if( strText.equals("자궁근종")){
                    Intent intent2 = new Intent(Selftest_main.this, Selftest2.class);
                    intent2.putExtra("Id", ID);
                    startActivity(intent2);
                }
                else if( strText.equals("다낭성 난소 증후군")){
                    Intent intent2 = new Intent(Selftest_main.this, Selftest3.class);
                    intent2.putExtra("Id", ID);
                    startActivity(intent2);
                }
                else if( strText.equals("폐경기")){
                    Intent intent2 = new Intent(Selftest_main.this, Selftest4.class);
                    intent2.putExtra("Id", ID);
                    startActivity(intent2);
                }
                else if( strText.equals("쿠퍼만-갱년기")){
                    Intent intent2 = new Intent(Selftest_main.this, Selftest5.class);
                    intent2.putExtra("Id", ID);
                    startActivity(intent2);
                }
            }
        });
    }
}