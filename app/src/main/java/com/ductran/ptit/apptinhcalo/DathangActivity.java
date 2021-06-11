package com.ductran.ptit.apptinhcalo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.os.HardwarePropertiesManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class DathangActivity extends AppCompatActivity {

    EditText fullnameDathangEd, emailDathangEd, sdtEd;
    TextView ngaydatTv;
    Button datngayBtn;
    Toolbar dathangTb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dathang);

        //anh xa
        initView();

        //xy ly toolbar
        xuLyToolbar();

        //xu ly hien thi ngay tu dong
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dinhDangNgay = new SimpleDateFormat("dd/MM/yyyy");
        ngaydatTv.append(dinhDangNgay.format(calendar.getTime()));

        //xu ly dat hang
        datngayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = fullnameDathangEd.getText().toString().trim();
                String email = emailDathangEd.getText().toString().trim();
                String sdt = sdtEd.getText().toString().trim();
                String date = ngaydatTv.getText().toString().trim();

                if(ten.length() > 0 && sdt.length() > 0 && email.length() > 0 && date.length() > 0){
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdandonhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String madonhang) {
                            Log.d("mangdonhang", madonhang);
                            if(Integer.parseInt(madonhang) > 0){
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                StringRequest request = new StringRequest(Request.Method.POST, Server.duongdanchitietdonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if(response.equals("Success")){
                                            HomeActivity.arrayGiohang.clear();
                                            AlertDialog.Builder builder = new AlertDialog.Builder(DathangActivity.this);
                                            builder.setTitle("Thông báo");
                                            builder.setMessage("Đơn hàng đã được đặt thành công");
                                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    finish();
                                                    Toast.makeText(getApplicationContext(), "Cảm ơn quý khách!",Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                            builder.show();
                                        }
                                        else
                                        {
                                            Toast.makeText(getApplicationContext(), "Đặt không thành công!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray = new JSONArray();
                                        for (int i = 0; i < HomeActivity.arrayGiohang.size();i++)
                                        {
                                            JSONObject jsonObject = new JSONObject();
                                            try {
                                                jsonObject.put("madonhang",madonhang);
                                                jsonObject.put("masanpham", HomeActivity.arrayGiohang.get(i).getIdsp());
                                                jsonObject.put("tensanpham", HomeActivity.arrayGiohang.get(i).getTensp());
                                                jsonObject.put("giasanpham", HomeActivity.arrayGiohang.get(i).getGiasp());
                                                jsonObject.put("soluongsanpham", HomeActivity.arrayGiohang.get(i).getSoluongsp());

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String, String> hashMap = new HashMap<String, String>();
                                        hashMap.put("json", jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                queue.add(request);
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
                            hashMap.put("tenkhachhang", ten);
                            hashMap.put("sodienthoai", sdt);
                            hashMap.put("email", email);
                            hashMap.put("ngaydat", date);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Chưa nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void xuLyToolbar() {
        setSupportActionBar(dathangTb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dathangTb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        fullnameDathangEd = findViewById(R.id.fullnameDathangEd);
        emailDathangEd = findViewById(R.id.emailDathangEd);
        sdtEd = findViewById(R.id.sdtDathangEd);
        ngaydatTv = findViewById(R.id.ngayDathangTv);
        datngayBtn = findViewById(R.id.datngayBtn);
        dathangTb = findViewById(R.id.dathangTb);
    }
}