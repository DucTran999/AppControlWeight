package com.ductran.ptit.apptinhcalo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ductran.ptit.apptinhcalo.model.Sanpham;
import com.ductran.ptit.apptinhcalo.model.User;
import com.ductran.ptit.apptinhcalo.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 88;
    EditText username_login_Ed, password_login_Ed;
    Button signinBtn;
    TextView signupTv;
    ProgressBar progress_login;

    //Thong tin user
    int id = 0;
    String fullname = "";
    String username = "";
    String password = "";
    String email = "";
    //khai bao user
     private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //anh xa
        initView();

        //bat su kien nut dang ki
        signupTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        //bat su kien nut dang nhap
        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginXuLy();
            }
        });
    }

    private void loginXuLy() {
        username = username_login_Ed.getText().toString().trim();
        password = password_login_Ed.getText().toString().trim();

        if(!username.equals("") && !password.equals("")){
            StringRequest stringRequest =  new StringRequest(Request.Method.POST, Server.duongdanlogin, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                        if (response != "Empty") {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0; i < jsonArray.length();i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    id = jsonObject.getInt("id");
                                    fullname = jsonObject.getString ("ten");
                                    username = jsonObject.getString("username");
                                    password = jsonObject.getString("password");
                                    email = jsonObject.getString("email");

                                    user = new User(id,fullname,username,password,email);
                                }
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("thongtinuser", user);
                                intent.putExtras(bundle);
                                startActivityForResult(intent, MY_REQUEST_CODE);
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(),"Tài khoản hoặc mật khẩu không đúng",Toast.LENGTH_SHORT).show();
                            }
                        } else{
                            Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không đúng!",Toast.LENGTH_SHORT).show();
                        }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("username", username);
                    data.put("password", password);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
        else{
            Toast.makeText(this, "Điền vào đi bạn",Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        username_login_Ed = findViewById(R.id.username_login_Ed);
        password_login_Ed = findViewById(R.id.password_login_Ed);
        signinBtn         = findViewById(R.id.signinBtn);
        signupTv          = findViewById(R.id.signupTv);
        progress_login    = findViewById(R.id.progress);
    }
}