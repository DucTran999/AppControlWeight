package com.ductran.ptit.apptinhcalo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ductran.ptit.apptinhcalo.adapter.SanphamAdapter;
import com.ductran.ptit.apptinhcalo.model.Sanpham;
import com.ductran.ptit.apptinhcalo.ultil.CheckConnection;
import com.ductran.ptit.apptinhcalo.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchSPActivity extends AppCompatActivity {

    private RecyclerView rcl_sp;
    private SanphamAdapter sanphamAdapter;
    private Toolbar timkiemTb;
    private List<Sanpham> list;
    SearchView searchView;

    //khai bao cac bien de hung du lieu
    private int id = 0;
    private String tensanpham = ""; //phai la chuoi rong khong se bao null
    private int giasanpham = 0;
    private String hinhanhsanpham = "";
    private String motasanpham = "";
    private int idsanpham = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_spactivity);

        initView();

        //xuly Toolbar
        xulyToolbar();

        //khoi tao mang san pham
        list = new ArrayList<>();

        //xu ly hien thi recycler
        trichXuatDanhsach();

    }

    private void trichXuatDanhsach() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //function ho tro doc mang json
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdanallsp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    for (int i =0; i < response.length(); i++){
                        try {
                            //phai lay theo cac doi tuong
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tensanpham = jsonObject.getString("tensp");
                            giasanpham = jsonObject.getInt("giasp");
                            hinhanhsanpham = jsonObject.getString("hinhanhsp");
                            motasanpham = jsonObject.getString("motasp");
                            idsanpham = jsonObject.getInt("idsanpham");
                            //tao mang
                            list.add(new Sanpham(id,tensanpham,giasanpham,hinhanhsanpham,motasanpham,idsanpham));

                        } catch (JSONException e) {;
                            e.printStackTrace();
                            CheckConnection.showToast_Short(getApplicationContext(), e.toString());
                        }
                    }
                    rcl_sp.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    sanphamAdapter = new SanphamAdapter(getApplicationContext(), list);
                    rcl_sp.setAdapter(sanphamAdapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.showToast_Short(getApplicationContext(),error.toString());
            }
        });
        //thuc hien gửi request
        requestQueue.add(jsonArrayRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_only_search,menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.searchsp).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Nhập tên sản phẩm");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                sanphamAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sanphamAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    private void xulyToolbar() {
        setSupportActionBar(timkiemTb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        timkiemTb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    //xu ly nut back cua may'

    @Override
    public void onBackPressed() {
        if(!searchView.isIconified()){
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    private void initView() {
        rcl_sp =  findViewById(R.id.rcl_sp);
        timkiemTb = findViewById(R.id.timkiemTb);
    }
}