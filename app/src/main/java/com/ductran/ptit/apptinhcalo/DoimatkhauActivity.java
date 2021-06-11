package com.ductran.ptit.apptinhcalo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ductran.ptit.apptinhcalo.ultil.CheckConnection;
import com.ductran.ptit.apptinhcalo.ultil.Server;

import java.util.HashMap;
import java.util.Map;

public class DoimatkhauActivity extends AppCompatActivity {

    private TextView Tv_dmk_username;
    private EditText Et_mkc,Et_mkm;
    private Button dmkBtn;
    private Toolbar dmkTb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doimatkhau);
        
        //anh xa
        initView();

        //hien thi toolbar
        dmkToolbar();

        //xu ly doi mat khau
        xuLyDoiMatKhau();
    }

    private void dmkToolbar() {
        setSupportActionBar(dmkTb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dmkTb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void xuLyDoiMatKhau() {
        dmkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Tv_dmk_username.getText().toString();
                String password = Et_mkc.getText().toString();
                String newpass  = Et_mkm.getText().toString();

                if(username.length() > 0 && password.length() > 0 && newpass.length() > 0 ){
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdandoimatkhau, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.equals("Thành công")){
                                AlertDialog.Builder builder = new AlertDialog.Builder(DoimatkhauActivity.this);
                                builder.setTitle("Thông báo");
                                builder.setMessage("Đôi mật khẩu thành công");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                });
                                builder.show();
                            }
                            else if(response.equals("Không thành công")){
                                CheckConnection.showToast_Short(getApplicationContext(), "Xem lại thông tin nhập vào");
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Lỗi ở đây", Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap = new HashMap<String, String>();
                            hashMap.put("username", username);
                            hashMap.put("password", password);
                            hashMap.put("newpass", newpass);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
                else{
                    CheckConnection.showToast_Short(getApplicationContext(), "Thông tin nhập không chính xác");
                }
            }
        });
    }

    private void initView() {
        Tv_dmk_username = findViewById(R.id.Tv_dmk_username);
        Et_mkc = findViewById(R.id.Et_mkc);
        Et_mkm = findViewById(R.id.Et_mkm);
        dmkBtn = findViewById(R.id.dmkBtn);
        dmkTb = findViewById(R.id.dmkTb);
    }
}