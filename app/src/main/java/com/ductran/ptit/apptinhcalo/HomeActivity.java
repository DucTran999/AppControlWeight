package com.ductran.ptit.apptinhcalo;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.ductran.ptit.apptinhcalo.Toolbar.ItemMenu;
import com.ductran.ptit.apptinhcalo.Toolbar.ToolbarAdapter;
import com.ductran.ptit.apptinhcalo.adapter.ThucphamAdapter;
import com.ductran.ptit.apptinhcalo.fragment.ViewPagerAdapter;
import com.ductran.ptit.apptinhcalo.model.Giohang;
import com.ductran.ptit.apptinhcalo.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 88;
    //thanh phan gom co toolbar, viewpager,bottomnavigation, Drawer navigation.
    Toolbar homeTb;
    NavigationView homeNavi;
    ListView homeLv;
    DrawerLayout homeDrawer;
    ArrayList<ItemMenu> arrayList;
    ToolbarAdapter adapter;

    LinearLayout menuContact, menuLogout;
    ViewPager viewPager;
    BottomNavigationView bottomNavigationView;
    //khai bao cho viec lay doi tuong user

    //khai bao gio hang
    public static ArrayList<Giohang> arrayGiohang;
    ThucphamAdapter thucphamAdapter;
    //khai bao User
    private User user;
    TextView helloTv,emai_login_Tv;

    //khai bao searchview
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //anh xa
        initView();

        //xu ly hien thi toolbar
           actionToolbar();

           //bat su kien click vao toolbar thi hien thi danh sach cac item
        actionClick();

        //tao adapter
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);

        //xu ly bat su kien click vao item trong toolbar
        catchOnItemToobar();
        
        //xy ly hien thi thong tin tai khoan
        xyLyThongTinTaiKhoan();
        
        //xu ly bottomnavigation
        xulyBottomnnavi();

        //xu ly contact va logout
        xuLyContactVaLogout();

    }

    private void xyLyThongTinTaiKhoan() {
        user = (User) getIntent().getExtras().get("thongtinuser");
        helloTv.setText(user.getFullname());
        emai_login_Tv.setText(user.getEmail());
    }

    private void xuLyContactVaLogout() {
        menuContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, ContactActivity.class);
                homeDrawer.closeDrawer(GravityCompat.START);
                startActivity(i);
            }
        });
        menuLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void xulyBottomnnavi() {
        //xu ly bottom navigation
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            //xuly vuot
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.menu_info).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.menu_news).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.menu_alarm).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.menu_shopping).setChecked(true);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //xu ly su kien click vao item
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_info:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.menu_news:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.menu_alarm:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.menu_shopping:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }

    //menu option hien thi
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
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

            case R.id.menuSetting:
                Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("thongtinuser", user);
                intent.putExtras(bundle);
                startActivityForResult(intent, MY_REQUEST_CODE);
                break;

            case R.id.menuSearch:
                Intent in = new Intent(getApplicationContext(), SearchSPActivity.class);
                startActivity(in);
        }
        return true;
    }

    //xu ly su kien click vao tung item trong toolbar
    private void catchOnItemToobar() {
        homeLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        viewPager.setCurrentItem(0);
                        homeDrawer.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        homeDrawer.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        viewPager.setCurrentItem(2);
                        homeDrawer.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        viewPager.setCurrentItem(3);
                        homeDrawer.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    //xu ly hien thi danh sach cac item trong toolbar
    private void actionClick() {
        arrayList = new ArrayList<>();
        arrayList.add(new ItemMenu("Infomations", R.drawable.ic_info));
        arrayList.add(new ItemMenu("News", R.drawable.ic_news));
        arrayList.add(new ItemMenu("Alarm", R.drawable.ic_alarm));
        arrayList.add(new ItemMenu("Shopping", R.drawable.ic_shopping));
        adapter = new ToolbarAdapter(this, R.layout.toolbar_card, arrayList);
        homeLv.setAdapter(adapter);
    }

    //xu ly toolbar
    private void actionToolbar() {
        setSupportActionBar(homeTb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //phai set thi moi hien thi dung icon menu
        homeTb.setNavigationIcon(R.drawable.ic_action_toolbar);

        //bat su kien click vào toolbar
        homeTb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeDrawer.openDrawer(GravityCompat.START);
            }
        });
    }

    private void initView() {
        homeTb = findViewById(R.id.homeTb);
        homeNavi = findViewById(R.id.homeNavi);
        homeLv = findViewById(R.id.homeLv);
        homeDrawer = findViewById(R.id.homeDrawer);
        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        menuContact = findViewById(R.id.menuContact);
        menuLogout = findViewById(R.id.menuLogout);
        helloTv = findViewById(R.id.helloTv);
        emai_login_Tv = findViewById(R.id.email_login_Tv);
        searchView = findViewById(R.id.menuSearch);

        //xu ly gio hang
        if(arrayGiohang != null){

        }
        else{
            arrayGiohang = new ArrayList<>();
        }
    }

}