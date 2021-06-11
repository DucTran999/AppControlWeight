package com.ductran.ptit.apptinhcalo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ductran.ptit.apptinhcalo.R;
import com.ductran.ptit.apptinhcalo.adapter.ThucphamAdapter;
import com.ductran.ptit.apptinhcalo.model.Thucpham;
import com.ductran.ptit.apptinhcalo.ultil.CheckConnection;
import com.ductran.ptit.apptinhcalo.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsFragment extends Fragment {


    //Khai bao
    public ViewFlipper slideVf;
    public ListView newLv;

    //phan cho Listview
    ArrayList<Thucpham> mangThucpham;
    ThucphamAdapter adapter;

    //khai bao cac bien de hung du lieu
    private int id = 0;
    private String tenthucpham = ""; //phai la chuoi rong khong se bao null
    private String protein  = "";
    private String calories = "";
    private String hinhanhthucpham = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_news, container, false);

        //Anh xa
        slideVf = (ViewFlipper) v.findViewById(R.id.slideVf);
        newLv =  v.findViewById(R.id.newLv);

        //kiem tra ket noi mang
        if(CheckConnection.haveNetworkConnection(getActivity().getApplicationContext())){
            //Xu ly viewFlipper
            ActionViewFlipper();

            //hien thi list thuc pham
            mangThucpham = new ArrayList<>();
            adapter = new ThucphamAdapter(mangThucpham,getActivity().getApplicationContext());
            newLv.setAdapter(adapter);

            //Xu ly lay du lieu tu tren database ve
            GetDuLieutp();

        }
        else{
            CheckConnection.showToast_Short(getActivity().getApplicationContext(), "Có biến rồi đại vương ơi!");
            getActivity().finish();
        }

        return v;
    }

    //Mang chua gia tri thuc pham lay ve
    private void GetDuLieutp() {
        //su dung volley
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        //function ho tro doc mang json
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdanthucpham, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    for (int i =0; i < response.length(); i++){
                        try {
                            //phai lay theo cac doi tuong
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tenthucpham = jsonObject.getString("tentp");
                            protein = jsonObject.getString("protein");
                            calories = jsonObject.getString("caloris");
                            hinhanhthucpham = jsonObject.getString("hinhanhtp");

                            //tao mang
                            mangThucpham.add(new Thucpham(id,tenthucpham,protein,calories,hinhanhthucpham));

                            //Update lv
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            CheckConnection.showToast_Short(getContext().getApplicationContext(), e.toString());
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.showToast_Short(getActivity().getApplicationContext(),error.toString());
            }
        });
        //thuc hien gửi request
        requestQueue.add(jsonArrayRequest);
    }

    //xu ly slide quang cao
    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        //lay anh tu tren mạng qua duong dan
        mangquangcao.add("https://www.wheystore.vn/upload/news_optimize/main/upl_top_50_thuc_pham_giau_protein_cai_thien_thuc_don_cua_gymer_1600745220_image_1600745220.png");
        mangquangcao.add("https://cdn.eva.vn/upload/3-2020/images/2020-07-07/20-thuc-pham-giau-vitamin-c-1-1594097405-39-width640height411.jpg");
        mangquangcao.add("https://login.medlatec.vn//ImagePath/images/20200529/20200529_vitaminD-1.jpg");
        mangquangcao.add("https://s3-ap-southeast-1.amazonaws.com/img.ecomedic.vn/wp-content/uploads/2017/10/06124507/kho%C3%A1ng-ch%E1%BA%A5t-750x430.jpg");
        mangquangcao.add("https://vnn-imgs-f.vgcloud.vn/2020/07/22/14/nuoc-ion-kiem-3a-nuoc-chuc-nang-thuc-pham-dac-biet-bao-ve-suc-khoe-2.jpg");

        //gan vao ImageView
        for (int i = 0;i<mangquangcao.size();i++)
        {
            ImageView imageView = new ImageView(getActivity().getApplicationContext());
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            //tu dong fix kich thuoc anh
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            slideVf.addView(imageView);
        }

        //gan thoi gian chay cho tung slide
        slideVf.setFlipInterval(5000);
        //slide tu dong chay
        slideVf.setAutoStart(true);

    }
}
