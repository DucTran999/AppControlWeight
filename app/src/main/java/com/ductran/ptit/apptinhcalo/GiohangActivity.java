package com.ductran.ptit.apptinhcalo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ductran.ptit.apptinhcalo.adapter.GiohangAdapter;
import com.ductran.ptit.apptinhcalo.fragment.ShoppingFragment;
import com.ductran.ptit.apptinhcalo.model.User;

import java.text.DecimalFormat;

public class GiohangActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 88 ;
    private ListView giohanglv;
    private TextView thongbaoTv;
    static TextView tongtienTv;
    private Button dathangbtn, tieptucbtn;
    private Toolbar giohangTb;
    private GiohangAdapter giohangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);

        //anh xa
        initView();

        //xu ly toolbar
        actionToolbar();

        //kiem tra xem gio hang co du lieu chua
        checkData();

        //xu ly gio hang
        XulyGiohang();

        //xu ly xoa san pham
        deleteSP();

        //xu ly nut tiep tuc mua hang
        muaTiep();

        //xu ly nut thanh toan
        thanhToan();

    }

    private void thanhToan() {
            dathangbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!HomeActivity.arrayGiohang.isEmpty())
                    {
                        Intent i = new Intent(GiohangActivity.this, DathangActivity.class);
                        startActivity(i);
                        finish();
                        }
                    else{
                        Toast.makeText(getApplicationContext(), "Giỏ hàng đang trống!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    }

    private void muaTiep() {
        tieptucbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //xoa san pham trong gio hang
    private void deleteSP() {
        giohanglv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(GiohangActivity.this);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn muốn xóa sản phầm này");
                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(HomeActivity.arrayGiohang.size() <= 0 ){
                            thongbaoTv.setVisibility(View.VISIBLE);
                        }
                        else{
                            HomeActivity.arrayGiohang.remove(position);
                            giohangAdapter.notifyDataSetChanged();
                            XulyGiohang();

                            if(HomeActivity.arrayGiohang.size() <= 0){
                                thongbaoTv.setVisibility(View.VISIBLE);
                            }
                            else{
                                thongbaoTv.setVisibility(View.INVISIBLE);
                                giohangAdapter.notifyDataSetChanged();
                                XulyGiohang();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        giohangAdapter.notifyDataSetChanged();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void XulyGiohang() {
        long tongtien = 0;
        for (int  i = 0; i < HomeActivity.arrayGiohang.size();i++){
            tongtien += HomeActivity.arrayGiohang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tongtienTv.setText(decimalFormat.format(tongtien) +" VNĐ");
    }

    private void checkData() {
        if(HomeActivity.arrayGiohang.size() <= 0)
        {
            giohangAdapter.notifyDataSetChanged();
            thongbaoTv.setVisibility(View.VISIBLE);
            giohanglv.setVisibility(View.INVISIBLE);
        }
        else{
            giohangAdapter.notifyDataSetChanged();
            thongbaoTv.setVisibility(View.INVISIBLE);
            giohanglv.setVisibility(View.VISIBLE);
        }
    }

    private void actionToolbar() {
        setSupportActionBar(giohangTb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //xuly nut back
        giohangTb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }

    private void initView() {
        giohanglv = findViewById(R.id.giohanglv);
        thongbaoTv = findViewById(R.id.thongbaoTv);
        tongtienTv = findViewById(R.id.tongtienTv);
        dathangbtn = findViewById(R.id.dathangBtn);
        tieptucbtn = findViewById(R.id.tieptucmuabtn);
        giohangTb = findViewById(R.id.giohangTb);
        giohangAdapter = new GiohangAdapter(GiohangActivity.this, HomeActivity.arrayGiohang);
        giohanglv.setAdapter(giohangAdapter);
    }
}