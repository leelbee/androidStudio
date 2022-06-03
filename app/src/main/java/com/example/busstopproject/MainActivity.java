package com.example.busstopproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private EditText et_id, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_id = findViewById(R.id.et_id);
        et_password = findViewById(R.id.et_password);
    }
    //예시
    public void onStationButtonClick(View v){
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
    }

    //회원가입 버튼 클릭시
    public void onSignUpButtonClick(View v){
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }

    //아이디/비밀번호 찾기 버튼 클릭시
    public void onFindIdButtonClick(View v){
        Intent intent = new Intent(MainActivity.this, FindIdActivity.class);
        startActivity(intent);
    }

    //로그인 버튼 클릭시
    public void  onLoginButtonClick(View v){
        String u_id = et_id.getText().toString();
        String u_password = et_password.getText().toString();

        Response.Listener<String> responseListner = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if(success){
                        Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        LoginRequest loginRequest = new LoginRequest(u_id, u_password, responseListner);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(loginRequest);
    }
}