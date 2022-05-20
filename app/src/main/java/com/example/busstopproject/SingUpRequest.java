package com.example.busstopproject;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SingUpRequest extends StringRequest{

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://mobilepro7.dothome.co.kr/Register.php";
    private Map<String, String> map;

    public SingUpRequest(String u_name, String u_id, String u_password, String u_phone, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("u_name", u_name);
        map.put("u_id", u_id);
        map.put("u_password", u_password);
        map.put("u_phone", u_phone);
    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
