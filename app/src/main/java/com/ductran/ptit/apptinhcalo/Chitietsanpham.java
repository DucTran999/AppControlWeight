package com.ductran.ptit.apptinhcalo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import com.ductran.ptit.apptinhcalo.model.Giohang;
import com.ductran.ptit.apptinhcalo.model.Sanpham;
import com.ductran.ptit.apptinhcalo.model.User;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class Chitietsanpham extends AppCompatActivity {

    Toolbar deltailsTb;
    ImageView detailsIv;
    TextView namechitietTv,giachitietTv, motachitietspTv;
    Spinner spinner;
    Button datmuabtn;

    //bien hung gia tri
    int id = 0;
    String tenChitiet = "";
    int giaChitiet = 0;
    String haChitiet = "";
    String motaChitiet = "";
    int idSanpham = 0;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsanpham);
        //anh xa
        initView();

        //xư ly hien thi toolbar
        actionToolbar();

        //xu ly thong tin 1 san pham
        getInfomation();

        //bat su kien cho spinner
        catchEventSpinner();

        //xu ly sư kien cho san pham vao gio hang
        eventButton();

    }

    private void eventButton() {
        datmuabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xu ly khi gio hang da co
                if(HomeActivity.arrayGiohang.size() > 0) {
                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                    //bien kiem tra ton tai hay chua
                    boolean exists = false;
                    for (int i = 0; i < HomeActivity.arrayGiohang.size(); i++) {
                        //kiem tra san pham co trung id voi san pham dang xem hay khong de tang so luong san pham
                        if (HomeActivity.arrayGiohang.get(i).getIdsp() == id) {
                            HomeActivity.arrayGiohang.get(i).setSoluongsp(HomeActivity.arrayGiohang.get(i).getSoluongsp() + sl);
                            if (HomeActivity.arrayGiohang.get(i).getSoluongsp() >= 10) {
                                HomeActivity.arrayGiohang.get(i).setSoluongsp(10);
                            }
                            HomeActivity.arrayGiohang.get(i).setGiasp(giaChitiet * HomeActivity.arrayGiohang.get(i).getSoluongsp());
                            exists = true;
                        }
                    }
                    if(exists == false){
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        long giaMoi = soluong*giaChitiet;
                        HomeActivity.arrayGiohang.add(new Giohang(id,tenChitiet,giaMoi,haChitiet,soluong));
                    }
                }
                //xu ly khi gio hang chua chua san pham
                else{
                    //lay so luong trong spinner
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long giaMoi = soluong * giaChitiet;
                    HomeActivity.arrayGiohang.add(new Giohang(id,tenChitiet,giaMoi,haChitiet,soluong));
                }
                Intent i = new Intent(Chitietsanpham.this, GiohangActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void catchEventSpinner() {
        Integer[] soluong = new Integer[] {1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_dropdown_item_1line, soluong);
        spinner.setAdapter(arrayAdapter);
    }

    private void getInfomation() {

        //nhan du lieu gui ve tu tab whey dang object
        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        id = sanpham.getId();
        tenChitiet = sanpham.getTensanpham();
        giaChitiet = sanpham.getGiasanpham();
        haChitiet = sanpham.getHinhanhsanpham();
        motaChitiet = sanpham.getMotasanpham();
        idSanpham = sanpham.getIdsanpham();

        //set giaa tri de hien thi
        namechitietTv.setText(tenChitiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        giachitietTv.setText("Giá: "+decimalFormat.format(giaChitiet)+" VNĐ");
        motachitietspTv.setText(motaChitiet);

        Picasso.get().load(haChitiet)
                .placeholder(R.drawable.noimg)
                .error(R.drawable.error)
                .into(detailsIv);
    }

    private void actionToolbar() {
        setSupportActionBar(deltailsTb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //bat su kien click vào toolbar
        deltailsTb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //menu option hien thi
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_only_cart, menu);
        return true;
    }

    //bat su kien menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuCart:
                Intent i = new Intent(getApplicationContext(), GiohangActivity.class);
                startActivity(i);
                break;
        }
        return true;
    }

    private void initView() {
        deltailsTb = findViewById(R.id.detailsTb);
        detailsIv = findViewById(R.id.detailsIv);
        namechitietTv = findViewById(R.id.namechitietTv);
        giachitietTv = findViewById(R.id.giachitietTv);
        motachitietspTv = findViewById(R.id.motachitietspTv);
        spinner = findViewById(R.id.spinner);
        datmuabtn = findViewById(R.id.datmuaBtn);
    }
}