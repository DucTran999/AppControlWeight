package com.ductran.ptit.apptinhcalo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;

public class ContactActivity extends AppCompatActivity {

    Toolbar lienheTb;
    LinearLayout phoneContact,contactEmail, diachiContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        //anh xa
        initView();

        //hien thi toolbar
        xuLyToolbar();

        //xuly lien he qua so dien thoai
        phoneContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:" + "0123456789"));
                startActivity(i);
            }
        });

        //xu ly lien he qua mail
        contactEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:"+"tranaduc9x@gmail.com"));
                startActivity(intent);
            }
        });

        //xuly hien thi dia chi cua hang qua gg map
        diachiContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactActivity.this, DiachiActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        lienheTb = findViewById(R.id.lienheTb);
        phoneContact = findViewById(R.id.phoneContact);
        contactEmail = findViewById(R.id.contactEmail);
        diachiContact = findViewById(R.id.diachiContact);
    }

    private void xuLyToolbar() {
        setSupportActionBar(lienheTb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //xuly nut back
            lienheTb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}