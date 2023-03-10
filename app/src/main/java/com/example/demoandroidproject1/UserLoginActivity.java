package com.example.demoandroidproject1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class UserLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
    }
    public void hideHorizontalDivider(View view)
    {
        EditText editText=(EditText)view;
        String s= editText.getHint().toString();
        Log.i("hi",(""+s.equals(R.string.email_hint)));
        View v1 = findViewById(R.id.dv_email);
        if(s.equals(R.string.email_hint)) {
            v1.setBackgroundColor(ContextCompat.getColor(this, R.color.grey_dark_2));
        }
      v1.setFocusable(true);
    }
    public void userLogin(View view)
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String email=((EditText)findViewById(R.id.ev_log_em)).getText().toString();
        String password=((EditText)findViewById(R.id.ev_log_ps)).getText().toString();
        String url = "http://10.20.3.153:8769/user/signIn";
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("password", password);
        }catch (JSONException e){
            e.printStackTrace();
        }


        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest( Request.Method.POST, url,jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("response", response.toString());
                        try {
                            String token = response.getString("token");
                            SharedPreferences sharedPreferences=getSharedPreferences("TOKEN", MODE_PRIVATE);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putString("token",token);
                            editor.commit();
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("error",error.getMessage());
                    }
                });

        queue.add(jsonObjectRequest);
    }
    public void userSignUp(View view)
    {

        Intent intent=new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}