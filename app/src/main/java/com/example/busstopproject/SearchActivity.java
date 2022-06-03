package com.example.busstopproject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SearchActivity extends AppCompatActivity {
    EditText et_search;
    ListView lv_result;
    List<String> list;
    List<String[]> s_position;
    List<String[]> d_position;
    int way;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        et_search = (EditText) findViewById(R.id.et_search);
        lv_result = (ListView) findViewById(R.id.lv_result);
        list = new ArrayList<>();
        lv_result.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String d_bus_num = list.get(position);
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            String d_bus_num = jsonObject.getString("d_bus_num");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
            }
        });
    }

    public void onSearchBusButtonClick(View v){
        String name = et_search.getText().toString();
        Response.Listener<String> responseListener  = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    String s_bus_num = jsonObject.getString("s_bus_num");
                    way = jsonObject.getInt("s_way");
                    String p = jsonObject.getString("s_position");
                    s_position.add(p.split(","));

                    if(success){
                        if(list != null && list.size() != 0){
                            list.clear();
                        }
                        list.addAll(Arrays.asList(s_bus_num.split(",")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        SearchRequest searchRequest = new SearchRequest(name, responseListener);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        RequestQueue queue = Volley.newRequestQueue(SearchActivity.this);
        queue.add(searchRequest);
        lv_result.setAdapter(adapter);

    }



}
