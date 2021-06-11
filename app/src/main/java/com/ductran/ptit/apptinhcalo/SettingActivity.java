package com.ductran.ptit.apptinhcalo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ductran.ptit.apptinhcalo.model.User;

public class SettingActivity extends AppCompatActivity {
    Toolbar settingTb;
    TextView fullnameTv, usernameTv, emailTv;
    Button thoatSetting,dmkBtn;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //anh xa
        initView();

        //xu ly toolbar
        xuLyTooobar();

        //xu ly hien thi thong tin
        xuLyLayThongTin();

        //xu ly nut thoat
        xuLyNutThoat();

        //xu ly nut doi mat khau
        xulyDoimatkhau();

    }

    private void xulyDoimatkhau() {
        dmkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DoimatkhauActivity.class);
                startActivity(i);
            }
        });
    }

    private void xuLyNutThoat() {
        thoatSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void xuLyLayThongTin() {
        user = (User) getIntent().getExtras().get("thongtinuser");
        fullnameTv.setText(user.getFullname());
        usernameTv.setText(user.getUsername());
        emailTv.setText(user.getEmail());
    }

    private void xuLyTooobar() {
        setSupportActionBar(settingTb);
        //hien thi toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //xuly click toolbar
        settingTb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        settingTb = findViewById(R.id.settingTb);
        fullnameTv = findViewById(R.id.fullnameSettingTv);
        usernameTv = findViewById(R.id.usernameSettingTv);
        emailTv = findViewById(R.id.emailSettingTv);
        thoatSetting = findViewById(R.id.thoatSetting);
        dmkBtn = findViewById(R.id.dmkBtn);
    }
}