package com.example.busstopproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class FindIdActivity extends AppCompatActivity {
    private EditText et_name, et_phone;
    private TextView tv_id, tv_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_id);

        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        tv_id = findViewById(R.id.tv_id);
        tv_password = findViewById(R.id.tv_password);
    }

    public void onSearchButtonClick(View v){
        String u_name = et_name.getText().toString();
        String u_phone = et_phone.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    String u_id = jsonObject.getString("u_id");
                    String u_password = jsonObject.getString("u_password");
                    if(success){
                        Toast.makeText(getApplicationContext(), "조회 성공", Toast.LENGTH_SHORT).show();
                        tv_id.setText("아이디: " + u_id);
                        tv_password.setText("비밀번호: " + u_password);
                    }else{
                        Toast.makeText(getApplicationContext(), "조회 실패", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        FindIdRequest findIdRequest = new FindIdRequest(u_name, u_phone, responseListener);
        RequestQueue queue = Volley.newRequestQueue(FindIdActivity.this);
        queue.add(findIdRequest);
    }

    public void onLoginButtonClick(View v){
        Intent intent = new Intent(FindIdActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
