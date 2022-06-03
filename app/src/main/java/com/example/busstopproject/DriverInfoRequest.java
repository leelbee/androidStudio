package com.example.busstopproject;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DriverInfoRequest extends StringRequest{

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://mobilepro7.dothome.co.kr/DriverInfo.php";
    private Map<String, String> map;

    public DriverInfoRequest(String d_bus_num, String d_way, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("d_bus_num", d_bus_num);
        map.put("d_way", d_way);
    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}