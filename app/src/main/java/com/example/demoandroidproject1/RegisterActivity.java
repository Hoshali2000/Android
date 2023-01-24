package com.example.demoandroidproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void redirectToLogin(View view)
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        View v1=findViewById(R.id.ev_fs);
        EditText firstN=(EditText)v1;
        String firstName=firstN.getText().toString();

        String lastName=((EditText)findViewById(R.id.ev_ls)).getText().toString();
        String email=((EditText)findViewById(R.id.ev_em)).getText().toString();
        String password=((EditText)findViewById(R.id.ev_ps)).getText().toString();
        String city=((EditText)findViewById(R.id.ev_city)).getText().toString();
        String state=((EditText)findViewById(R.id.ev_state)).getText().toString();
        String phone=((EditText)findViewById(R.id.ev_ph)).getText().toString();

        String url = "http://10.20.3.153:8769/user/signup";
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("firstName", firstName);
            jsonObject.put("lastName", lastName);
            jsonObject.put("email", email);
            jsonObject.put("password", password);
            jsonObject.put("city", city);
            jsonObject.put("state", state);
            jsonObject.put("phoneNumber", phone);
        }catch (JSONException e){
            e.printStackTrace();
        }


        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest( Request.Method.POST, url,jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("response", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("error",error.getMessage());
                    }
                });

        queue.add(jsonObjectRequest);
        Intent intent=new Intent(this,UserLoginActivity.class);
        startActivity(intent);
    }
}