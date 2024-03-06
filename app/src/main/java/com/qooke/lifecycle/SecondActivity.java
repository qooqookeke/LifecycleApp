package com.qooke.lifecycle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView txtName;
    TextView txtAge;

    int age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Log.i("AAA", "Second: onCreate 함수 실행");

        txtName = findViewById(R.id.txtName);
        txtAge = findViewById(R.id.txtAge);

        // 만약에 다른 액티비티가 이 액티비티로 데이터를 전달하면 데이터를 받아준다.
        String name = getIntent().getStringExtra("name");
        age = getIntent().getIntExtra("age", 0);

        txtName.setText(name);
        txtAge. setText(""+age);


        // back 버튼 눌렀을 때 실행되게끔 셋팅
        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // 백버튼 눌렀을때 하고 싶은 코드를 작성
                // 메인 액티비티로 나이 +10 한 수를 보내고 싶다.
                age = age + 10;

                Intent intent = new Intent();
                intent.putExtra("age",age);
                setResult(100, intent);

                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("AAA", "Second: onStart 함수 실행");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("AAA", "Second: onResume 함수 실행");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("AAA", "Second: onPause 함수 실행");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("AAA", "Second: onStop 함수 실행");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("AAA", "Second: onDestroy 함수 실행");
    }


}