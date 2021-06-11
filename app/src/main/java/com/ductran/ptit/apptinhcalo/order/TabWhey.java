package com.ductran.ptit.apptinhcalo.order;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ductran.ptit.apptinhcalo.Chitietsanpham;
import com.ductran.ptit.apptinhcalo.R;
import com.ductran.ptit.apptinhcalo.adapter.WheyAdapter;
import com.ductran.ptit.apptinhcalo.model.Sanpham;
import com.ductran.ptit.apptinhcalo.ultil.CheckConnection;
import com.ductran.ptit.apptinhcalo.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TabWhey extends Fragment {

    //khai bao
    ListView lvwhey;
    WheyAdapter wheyAdapter;
    ArrayList<Sanpham> arraywhey;

    //tao bien de request
    int page = 1;
    int idWhey = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tab_whey, container, false);
        //anh xa
        lvwhey = (ListView) v.findViewById(R.id.wheyLv);

        //tao mang chua danh sach whey
        arraywhey = new ArrayList<>();
        wheyAdapter = new WheyAdapter(getActivity().getApplicationContext(), arraywhey);
        lvwhey.setAdapter(wheyAdapter);

        //kiem tra ket noi
        if(CheckConnection.haveNetworkConnection(getActivity().getApplicationContext())){
            //xu ly lay du lieu
            GetData(page);
            XulyClickItem();
        }
        else{
            CheckConnection.showToast_Short(getActivity().getApplicationContext(),"Có biến rồi đại vương ơi!");
            getActivity().finish();
        }
        return v;
    }

    private void XulyClickItem() {
        lvwhey.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //xu ly chuyen man
                Intent i = new Intent(getActivity().getApplicationContext(), Chitietsanpham.class);
                i.putExtra("thongtinsanpham", arraywhey.get(position));
                startActivity(i);
            }
        });
    }

    private void GetData(int Page) {
        //bien gui request
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = Server.duongdanwhey+String.valueOf(Page);
        //post
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //tao bien de hung gia tri
                int id = 0;
                String tensp = "";
                int gia = 0;
                String hinhanhsp = "";
                String mota = "";
                int idspwhey = 0;

                if(response != null){
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tensp = jsonObject.getString ("tensp");
                            gia = jsonObject.getInt("giasp");
                            hinhanhsp = jsonObject.getString("hinhanhsp");
                            mota = jsonObject.getString("motasp");
                            idspwhey = jsonObject.getInt("idsanpham");

                            arraywhey.add(new Sanpham(id, tensp, gia, hinhanhsp, mota, idspwhey));
                            wheyAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        CheckConnection.showToast_Short(getActivity().getApplicationContext(), e.toString());
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.showToast_Short(getActivity().getApplicationContext(),"Lỗi ở đây");
            }
        }){ //xu ly id san pham
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String,String>();
                param.put("idsanpham",String.valueOf(idWhey));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
}