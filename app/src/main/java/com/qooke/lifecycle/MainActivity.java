package com.qooke.lifecycle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText editName;
    EditText editAge;
    TextView txtFuture;


    // 내가 실행한 액티비티로부터 데이터를 다시 받아올 필요가 있을 때 작성하는 코드
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if (o.getResultCode() == 100) {
                        int age = o.getData().getIntExtra("age", 0);
                        txtFuture.setText("10년 후의 나이는 : "+age);
                    }
                }
            });




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("AAA", "Main: onCreate 함수 실행");

        button = findViewById(R.id.button);
        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        txtFuture = findViewById(R.id.txtFuture);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editName.getText().toString().trim();
                String strAge = editAge.getText().toString().trim();

                if(name.isEmpty() || strAge.isEmpty()) {
                    Toast.makeText(MainActivity.this, "두항목을 모두 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                int age = Integer.parseInt(strAge);


                // 다른 액티비티를 실행하는 방법(second activity 실행)
                // 인텐트를 만든다. 인텐트란? 어떤 액티비티가 어떤 액티비티를 실행하겠다는 것!
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                // 인텐트에 데이터 담기
                intent.putExtra("name", name);
                intent.putExtra("age", age);
                // 인텐트 실행(단방향 처리)
                // startActivity(intent);

                // 양방향 데이터 처리시
                launcher.launch(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("AAA", "Main: onPause 함수 실행");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("AAA", "Main: onStop 함수 실행");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("AAA", "Main: onStart 함수 실행");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("AAA", "Main: onSResume 함수 실행");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("AAA", "Main: onDestroy 함수 실행");
    }
}