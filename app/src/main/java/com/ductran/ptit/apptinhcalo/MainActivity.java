package com.ductran.ptit.apptinhcalo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ductran.ptit.apptinhcalo.ultil.Server;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText fullnameEd, usernameEd, passwordEd, emailEd;
    Button signupBtn;
    TextView signinTv, statusTV;
    ProgressBar progress;
    String fullname, email, username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //anh xa
        initView();

        //xu ly dang nhap
        fullname = email = username = password ="";

        //xu ly dang ki
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xyLyDangKi();
            }
        });

        //xu ly chuyen đến màn hình đăng nhập
        signinTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }


    private void xyLyDangKi() {
        fullname = fullnameEd.getText().toString().trim();
        username = usernameEd.getText().toString().trim();
        email    = emailEd.getText().toString().trim();
        password = passwordEd.getText().toString().trim();

        //xu ly nhap
        if(!username.equals("") && !password.equals("") && !fullname.equals("") && !email.equals("")){
            StringRequest stringRequest =  new StringRequest(Request.Method.POST, Server.duongdanregister, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        statusTV.setText("Đăng kí thành công!");

                    } else if (response.equals("failure")) {
                        statusTV.setText("Đăng kí không thành công!");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("fullname", fullname);
                    data.put("email", email);
                    data.put("username", username);
                    data.put("password", password);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }

    private void initView() {
        fullnameEd = findViewById(R.id.fullnameEd);
        usernameEd = findViewById(R.id.usernameEd);
        passwordEd = findViewById(R.id.passwordEd);
        emailEd    = findViewById(R.id.emailEd);
        signupBtn  = findViewById(R.id.signupBtn);
        signinTv   = findViewById(R.id.signinTv);
        progress   = findViewById(R.id.progress);
        statusTV   = findViewById(R.id.statusTv);
    }
}