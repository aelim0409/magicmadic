package com.example.myapplication2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

import im.dacer.androidcharts.LineView;





public class graph extends AppCompatActivity {
   // private SQLiteDatabase db;
    private LineView lineView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph);


        lineView =(LineView)findViewById(R.id.line_view1);

       // List<AirQualityData> data= db.todayAirQualityData();

        //레이블
        ArrayList<String> day = new ArrayList<String>();

        //3 data sets
        ArrayList<Integer> dataList_1 = new ArrayList<>();
        ArrayList<Integer> dataList_2 = new ArrayList<>();
        ArrayList<Integer> dataList_3 = new ArrayList<>();

        ArrayAdapter<Integer> adapter1 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dataList_1);
        ArrayAdapter<Integer> adapter2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dataList_2);
        ArrayAdapter<Integer> adapter3 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dataList_3);



        //임의로 값 넣어봄
        dataList_1.add(3);
        dataList_1.add(4);
        dataList_1.add(5);

        dataList_2.add(0);
        dataList_2.add(2);
        dataList_2.add(1);

        dataList_3.add(10);
        dataList_3.add(8);
        dataList_3.add(2);

        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
        adapter3.notifyDataSetChanged();

        ArrayList<ArrayList<Integer>> dataLists= new ArrayList<>();
        ArrayAdapter<ArrayList<Integer>> adapter4 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dataLists);
        dataLists.add(dataList_1);
        dataLists.add(dataList_2);
        dataLists.add(dataList_3);
        adapter4.notifyDataSetChanged();

        //꺾은선 그래프 그리기
        lineView.setDrawDotLine(true);
        lineView.setShowPopup(LineView.SHOW_POPUPS_All);
        lineView.setColorArray(new int []{Color.BLACK, Color.YELLOW,Color.BLUE});

        lineView.setBottomTextList(day);
        lineView.setDataList(dataLists);



        //버튼 연결//
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

        Button graph_btn = findViewById(R.id.graph_btn);
        graph_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),graph.class);
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
