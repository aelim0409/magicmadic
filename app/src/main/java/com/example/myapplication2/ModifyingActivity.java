package com.example.myapplication2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ModifyingActivity extends Activity {
    boolean deleting, editing, adding, moving;
    int totalNum;
    EditText focusingText;
    String fileName;
    LinearLayout memo_container;
    LinearLayout.LayoutParams params;
    LinearLayout.LayoutParams zeroParams;
    LayoutInflater layout_inflater;
    SharedPreferences fileGetter;
    SharedPreferences.Editor editor;
    String dateText; // (2019/8/31)
    TextView date;
    ImageButton menuButton;
    int state; // 보기 방식
    String state_str; // 전체 목록 - 완료한 일 - 해야할 일
    int year, month, day;
    Button add_button, remove_button, save_button, move_button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        deleting = false;
        editing = false;
        adding = false;
        moving = false;
        fileName = getIntent().getExtras().getString("fileName");

        date = findViewById(R.id.sub_date_view);

        // MainActivity에서 날짜를 전달 받아, 상단의 TextView인 date를 초기화한다.

        year = getIntent().getExtras().getInt("year");
        month = getIntent().getExtras().getInt("month");
        day = getIntent().getExtras().getInt("day");

        dateText = "("+year+"/"+month+"/"+day+")";

        memo_container = findViewById(R.id.sub_content); // 동적으로 추가할 메모지들의 부모 Layout

        layout_inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE); // layout inflater 생성

        // 파라미터 설정
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(0, 0, 0, 40);
        zeroParams = new LinearLayout.LayoutParams(0,0);


        fileGetter = getSharedPreferences("StateStorage", 0);
        state = fileGetter.getInt("state",0);
        state_str = getStateString(state);
        date.setText(state_str+" "+dateText);
        loadDBMemo(); // 메모지 초기화

        add_button = findViewById(R.id.add_button); // 추가 버튼
        add_button.setOnClickListener(new AddButtonListener());
        if(state==2) add_button.setLayoutParams(zeroParams);

        remove_button = findViewById(R.id.remove_button); // 삭제 버튼
        remove_button.setOnClickListener(new RemoveButtonListener());

        save_button = findViewById(R.id.save_button); // 저장 버튼
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ImageButton menuButton = findViewById(R.id.sub_menu_button);
        menuButton.setOnClickListener(new MenuListener());

        if(getIntent().getExtras().getBoolean("auto")==true) dateChange();
    }

    @Override
    public void onBackPressed() {
        if(totalNum==0) super.onBackPressed();
        if(deleting==true){
            deleting = false;
            recoverGreen();
            saveDBMemo();
            recoverEditingMode();
            showCheckBox();
            return;
        }
        if(editing==true){
            editing = false;
            adding = false;
            recoverGreen();
            saveDBMemo();
            memo_container.clearFocus();
            hideKeyBoard();
            super.onBackPressed();
        }
        if(adding==true){
            adding = false;
            recoverGreen();
            saveDBMemo();
            memo_container.clearFocus();
            return;
        }
        if(moving==true){
            moving = false;
            recoverGreen();
            saveDBMemo();
            recoverEditingMode();
            showCheckBox();
            return;
        }
        saveDBMemo();
        super.onBackPressed();
    }


    private void loadDBMemo(){ // 보기 방식(state)에 따라 다르게 보여줌 : 0 - 전체, 1 - 미완료, 2 - 완료
        memo_container.removeAllViews();
        fileGetter = getSharedPreferences(fileName, 0); // 클릭한 날짜에 해당되는 File의 DB 객체 생성
        totalNum = fileGetter.getInt("num",0); // 메모지의 개수를 불러옴

        if(totalNum == 0){ // 메모지가 하나도 없는 경우, 빈 메모지 1개를 자동으로 추가
            EditText editText = addNewMemoPaper();
            focusingText = editText;
            showKeyBoard(focusingText);
            totalNum++;
        }
        else {
            // DB로부터 데이터를 불러와서 기존에 있던 메모들을 배치시킴
            for (int i = 0; i < totalNum; i++) {
                String str = fileGetter.getString("memo" + i, "해당 key값을 가진 entry가 DB 상에 없습니다.");
                EditText editText = addNewMemoPaper();

                // 체크박스 초기화
                RelativeLayout memo = (RelativeLayout) editText.getParent();
                CheckBox checkBox = (CheckBox) memo.getChildAt(1);
                checkBox.setChecked(loadDBCheckBox(i));

                editText.setText(str); // 메모지에 글자 추가
                focusingText = editText;

                zeroSizeControl(memo, i); // 보기 방식에 따른 설정
            }
            recoverGreen();
        }
    }

    private void zeroSizeControl(RelativeLayout memo, int i){ // 보기 방식에 따른 설정
        switch (state) {
            case 0:
                memo.setLayoutParams(params);
                break;
            case 1:
                if (getCheckBoxAt(i).isChecked()) memo.setLayoutParams(zeroParams);
                else memo.setLayoutParams(params);
                break;
            case 2:
                if(!getCheckBoxAt(i).isChecked()) memo.setLayoutParams(zeroParams);
                else memo.setLayoutParams(params);
                break;
            default:
                break;
        }
    }

    private String getStateString(int state){
        switch (state){
            case 0:
                return "전체 목록";
            case 1:
                return "해야할 일";
            case 2:
                return "완료한 일";
            default:
                return null;
        }
    }

    private void saveDBMemo(){ // 빈 메모를 제외한 모든 메모지를 저장
        editor = getSharedPreferences(fileName, 0).edit();
        for(int i = 0; i<totalNum; i++){ // 모든 메모지를 저장
            String temp = getEditTextAt(i).getText().toString();
            editor.putString("memo"+i,temp); // putString은 entry값이 있어야 유효함
        }
        editor.putInt("num", totalNum).commit();

        for(int i=0; i<totalNum; i++){ // 빈 메모지를 제거
            if(getEditTextAt(i).getText().toString().length()==0){
                editor.remove("check"+i).commit();
                memo_container.removeViewAt(i); // 따라서, DB는 건들 필요 없이 view만 삭제
                totalNum--;
                i--;
            }
        }
    }

    private void deleteDBMemo(int index){
        editor = getSharedPreferences(fileName, 0).edit();
        editor.remove("memo"+index);
        memo_container.removeViewAt(index);

        //ArrayList처럼 미는 처리
        fileGetter = getSharedPreferences(fileName, 0);
        for(int i = index; i< totalNum-1; i++){
            String temp = fileGetter.getString("memo"+(i+1),"오류 발생!");
            editor.putString("memo"+i, temp);
        }
        editor.remove("memo"+ (totalNum-1)).commit(); // 다 밀고 맨 뒤에 있는 요소 제거
    }

    private boolean loadDBCheckBox(int index){
        SharedPreferences fileGetter = getSharedPreferences(fileName, 0);
        return fileGetter.getBoolean("check"+index,false);
    }

    private void saveDBCheckBox(int index, boolean checked){
        editor = getSharedPreferences(fileName, 0).edit();
        editor.putBoolean("check"+index,checked).commit();
    }

    private void deleteDBCheckBox(int index){
        editor = getSharedPreferences(fileName, 0).edit();
        editor.remove("check"+index);

        //ArrayList처럼 미는 처리
        fileGetter = getSharedPreferences(fileName, 0);
        for(int i = index; i< totalNum-1; i++){
            boolean temp = fileGetter.getBoolean("check"+(i+1),false);
            editor.putBoolean("check"+i, temp);
        }
        editor.remove("check"+ (totalNum-1)).commit(); // 다 밀고 맨 뒤에 있는 요소 제거
    }

    private EditText addNewMemoPaper(){ // 새 메모지를 생성하여 container 상에 추가
        RelativeLayout newMemoPaper = (RelativeLayout) layout_inflater.inflate(R.layout.layout_memo, null);
        newMemoPaper.setLayoutParams(params);
        newMemoPaper.getChildAt(0).setOnFocusChangeListener(new EditListener());
        ((CheckBox)newMemoPaper.getChildAt(1)).setOnCheckedChangeListener(new CheckBoxListener());
        ((CheckBox)newMemoPaper.getChildAt(1)).setOnClickListener(new HidePaperByStateListener());
        memo_container.addView(newMemoPaper); // ScrollView에 메모지 추가
        return (EditText) newMemoPaper.getChildAt(0); // 새로 생성된 메모지의 EditText를 반환
    }

    private void recoverGreen(){
        for (int i = 0; i< totalNum; i++) {
            RelativeLayout memo = (RelativeLayout) memo_container.getChildAt(i);
            if(memo!=null) ((GradientDrawable) memo.getBackground()).setColor(getResources().getColor(R.color.memoPaper));
        }
    }

    private void recoverEditingMode(){
        for (int i = 0; i< totalNum; i++) {
            RelativeLayout memo = (RelativeLayout) memo_container.getChildAt(i);
            ((EditText) memo.getChildAt(0)).setFocusableInTouchMode(true);
        }
    }

    private void showKeyBoard(View view){
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); // 키보드
        imm.showSoftInput(view, 0);
    }

    private void hideKeyBoard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(focusingText.getWindowToken(), 0); // 키보드 숨기기
    }

    private EditText getEditTextAt(int index){
        return ((EditText)((RelativeLayout) memo_container.getChildAt(index)).getChildAt(0));
    }

    private CheckBox getCheckBoxAt(int index){
        return (CheckBox)((RelativeLayout)memo_container.getChildAt(index)).getChildAt(1);
    }

    private class AddButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) { // 메모 추가하기 버튼을 눌렀을 때
            adding = true;
            deleting = false;
            recoverGreen();
            EditText editText = addNewMemoPaper();
            showKeyBoard(editText);
            recoverEditingMode();
            totalNum++;
            focusingText = editText;
            hideCheckBox();
        }
    }

    private class RemoveButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) { // 메모 삭제하기 버튼을 눌렀을 때
            deleting = true;
            for (int i = 0; i< totalNum; i++) {
                RelativeLayout memoPaper = (RelativeLayout) memo_container.getChildAt(i); // i번재 메모지
                ((GradientDrawable) memoPaper.getBackground()).setColor(getResources().getColor(R.color.deletingPaper));
                ((EditText) memoPaper.getChildAt(0)).setFocusableInTouchMode(false); // EditView 편집 못하게 하기
                ((EditText) memoPaper.getChildAt(0)).setFocusable(false); // 커서 없애기
                memoPaper.setOnClickListener(new DeleteDBListener()); // 메모지
                memoPaper.getChildAt(0).setOnClickListener(new DeleteDBListener()); // EditView
            }
            hideKeyBoard();
            hideCheckBox();
        }
    }

    private void moveMode(){
        moving = true;
        for (int i = 0; i< totalNum; i++) {
            RelativeLayout memoPaper = (RelativeLayout) memo_container.getChildAt(i); // i번재 메모지
            ((GradientDrawable) memoPaper.getBackground()).setColor(getResources().getColor(R.color.colorPink));
            ((EditText) memoPaper.getChildAt(0)).setFocusableInTouchMode(false); // EditView 편집 못하게 하기
            ((EditText) memoPaper.getChildAt(0)).setFocusable(false); // 커서 없애기
            memoPaper.setOnClickListener(new ChooseListener()); // 메모지
            memoPaper.getChildAt(0).setOnClickListener(new ChooseListener()); // EditView
        }
        hideKeyBoard();
        hideCheckBox();
    }

    private class ChooseListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(moving == false) return;
            if(view instanceof EditText) view = (RelativeLayout) view.getParent();

            int index = memo_container.indexOfChild(view); // 몇 번째 메모지인지 index를 구한다

            Intent intent = new Intent(ModifyingActivity.this, ChooseDateActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); // 빠른 연타 시 액티비티가 2개 생성 되는 것을 방지
            Bundle bundle = new Bundle();
            bundle.putInt("indexToMove",index);
            bundle.putString("fileName",fileName);
            intent.putExtras(bundle);
            startActivityForResult(intent,1);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        moving = false;
        recoverEditingMode();
        recoverGreen();

        if(resultCode==0) return;

        Bundle bundle = data.getExtras();
        int y = bundle.getInt("yearMoved");
        int m = bundle.getInt("monthMoved");
        int d = bundle.getInt("dayMoved");
        int index = bundle.getInt("indexToMove");
        String name = bundle.getString("fileName");

        SharedPreferences sp = getSharedPreferences(name,0);
        SharedPreferences.Editor editor = sp.edit();

        // 이전 날짜 백업
        String memo_str = sp.getString("memo"+index,"get 실패");
        boolean checkBox = loadDBCheckBox(index);

        // 이전 날짜 삭제
        deleteDBMemo(index);
        deleteDBCheckBox(index);

        // 이전 날짜의 totalNum를 하나 빼 줌
        int num = sp.getInt("num",0);
        editor.putInt("num",num-1);
        totalNum--;

        editor.commit();

        //-----------------------------------------------------------------------------

        // 새 날짜 저장
        SharedPreferences sp2 = getSharedPreferences(y+"."+(m+1)+"."+d,0);
        SharedPreferences.Editor editor2 = sp2.edit();
        int num2 = sp2.getInt("num",0);
        editor2.putString("memo"+num2, memo_str);
        editor2.putBoolean("check"+num2, checkBox);

        // 새 날짜의 totalNum를 하나 늘려줌
        editor2.putInt("num",num2+1);

        editor2.commit();

        Toast.makeText(getBaseContext(),y+"/"+m+"/"+d+"로 이동하였습니다", Toast.LENGTH_SHORT).show();

        Intent intent = getIntent();
        intent.putExtra("y",y);
        intent.putExtra("m",m);
        intent.putExtra("d",d);
        setResult(1, intent);
        finish();
    }

    @Override
    protected void onPause() {
        saveDBMemo();
        super.onPause();
    }

    private class DeleteDBListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(deleting == false) return;
            if(view instanceof EditText) view = (RelativeLayout) view.getParent();

            int index = memo_container.indexOfChild(view); // 몇 번째 메모지인지 index를 구한다

            deleteDBMemo(index); // 메모 내용 DB 삭제
            deleteDBCheckBox(index);// 체크박스 DB 삭제

            totalNum--;
        }
    }


    private class EditListener implements View.OnFocusChangeListener{ // Edit시 패딩 조절
        @Override
        public void onFocusChange(View view, boolean focused) {
            if(focused==true){
                editing = true;

                view.setPadding(10,10,10,80);
                recoverGreen();
                ((GradientDrawable) ((RelativeLayout)view.getParent()).getBackground()).setColor(getResources().getColor(R.color.modifyingPaper));

                view.requestFocus();
                showKeyBoard(view);

                hideCheckBox();
            }
            else{
                view.setPadding(10,10,10,10);
                showCheckBox();
            }
        }
    }

    private void hideCheckBox(){ // 체크박스를 가림
        int i=0;
        while(memo_container.getChildAt(i)!=null) {
            ((RelativeLayout)memo_container.getChildAt(i)).getChildAt(1).setVisibility(View.INVISIBLE);
            i++;
        }
    }

    private void showCheckBox(){ // 체크박스를 보임
        int i=0;
        while(memo_container.getChildAt(i)!=null) {
            ((RelativeLayout)memo_container.getChildAt(i)).getChildAt(1).setVisibility(View.VISIBLE);
            i++;
        }
    }

    private class CheckBoxListener implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton view, boolean checked) { // 체크박스를 누를 때 일어날 일
            checked = ((CheckBox) view).isChecked();
            EditText text = (EditText)((RelativeLayout)view.getParent()).getChildAt(0);
            int index = ((LinearLayout)view.getParent().getParent()).indexOfChild((RelativeLayout)view.getParent());
            if(checked==true){ // 체크 ON
                text.setPaintFlags(text.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG); // 취소선
                saveDBCheckBox(index, true);
            }
            else{ //체크 OFF
                text.setPaintFlags(0);
                saveDBCheckBox(index, false);
            }
        }
    }

    private class HidePaperByStateListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            int index = memo_container.indexOfChild((RelativeLayout)view.getParent());
            hidePaperByState(index);
            if(((CheckBox)view).isChecked())
                Toast.makeText(getBaseContext(),"완료한 일 목록으로 이동", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getBaseContext(),"해야할 일 목록으로 이동", Toast.LENGTH_SHORT).show();
        }
    }

    private void hidePaperByState(int index){ // 0~6 중 2번째 삭제
        if(state==0) return;

        // remove와 add를 통해 위로 한 칸씩 당겨주는 작업
        memo_container.getChildAt(index).setLayoutParams(zeroParams);
        for(; index<totalNum-1; index++){ // 0~5 중 2~5를 작업
            RelativeLayout temp = (RelativeLayout)memo_container.getChildAt(index);
            memo_container.removeViewAt(index);
            memo_container.addView(temp,index);
        }
    }

    private class MenuListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            PopupMenu menu = new PopupMenu(getBaseContext(), view);
            getMenuInflater().inflate(R.menu.sub_menu, menu.getMenu());
            menu.setOnMenuItemClickListener(new MenuItemListener());
            menu.getMenu().getItem(0).getSubMenu().getItem(state).setChecked(true);
            menu.show();
        }
    }

    private void saveState(){
        fileGetter = getSharedPreferences("StateStorage", 0);
        editor = fileGetter.edit();
        editor.putInt("state", state).commit();
    }

    private class MenuItemListener implements PopupMenu.OnMenuItemClickListener{
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            LinearLayout.LayoutParams buttonParams = (LinearLayout.LayoutParams)remove_button.getLayoutParams();
            switch(menuItem.getItemId()){
                case R.id.show_all: // '전체 목록'을 눌렀을 때
                    state = 0;
                    add_button.setLayoutParams(buttonParams);
                    break;
                case R.id.show_not_done: // '해야할 일'을 눌렀을 때
                    state = 1;
                    add_button.setLayoutParams(buttonParams);
                    break;
                case R.id.show_completed: // '완료한 일'을 눌렀을 때
                    state = 2;
                    add_button.setLayoutParams(zeroParams);
                    break;
                case R.id.move_date: // '날짜 변경'을 눌렀을 때
                    dateChange();
                    return true;
                default: // 그밖의 모든 메뉴를 눌렀을 때
                    saveDBMemo();
                    return true;
            }
            saveState();
            state_str = getStateString(state);
            date.setText(state_str+" "+dateText);
            saveDBMemo();
            loadDBMemo();

            deleting = false;
            adding = false;
            editing = false;
            moving = false;

            // 보기 방식 변경 시, 첫번째 빈 메모지를 자동 삭제
            if(totalNum==1 && focusingText.getText().toString().length()==0){
                memo_container.removeViewAt(0); // 따라서, DB는 건들 필요 없이 view만 삭제
            }

            menuItem.setChecked(!menuItem.isChecked());
            hideKeyBoard();
            return true;
        }
    }

    private void dateChange(){
        moveMode();
        saveDBMemo();
        Toast.makeText(getBaseContext(),"날짜를 변경할 메모를 클릭하세요", Toast.LENGTH_SHORT).show();
    }
}