package com.ductran.ptit.apptinhcalo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class DiachiActivity extends AppCompatActivity {

    Toolbar mapTb;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diachi);
        initView();

        actionbar();

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String html = "<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3725.3050420700015!2d105.78571131444458!3d20.980405994804197!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135accddf519ab3%3A0x3c7b9c2ae61d0a2!2zSOG7jWMgVmnhu4duIEPDtG5nIE5naOG7hyBCxrB1IENow61uaCBWaeG7hW4gVGjDtG5nIC0gVHLhuqduIFBow7ogKEjDoCDEkMO0bmcp!5e0!3m2!1svi!2s!4v1623418351936!5m2!1svi!2s\" width=\"600\" height=\"450\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\"></iframe>";
        webView.loadData(html, "text/html", null);

    }

    private void actionbar() {
        setSupportActionBar(mapTb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mapTb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        mapTb = findViewById(R.id.mapTb);
        webView = findViewById(R.id.webView);
    }

}