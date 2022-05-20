package com.example.busstopproject;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest{

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://mobilepro7.dothome.co.kr/Login.php";
    private Map<String, String> map;

    public LoginRequest(String u_id, String u_password, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("u_id", u_id);
        map.put("u_password", u_password);
    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
