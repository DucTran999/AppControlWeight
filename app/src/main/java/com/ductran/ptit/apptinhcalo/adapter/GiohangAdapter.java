package com.ductran.ptit.apptinhcalo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ductran.ptit.apptinhcalo.GiohangActivity;
import com.ductran.ptit.apptinhcalo.HomeActivity;
import com.ductran.ptit.apptinhcalo.R;
import com.ductran.ptit.apptinhcalo.model.Giohang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GiohangAdapter extends BaseAdapter {
    Context context;
    ArrayList<Giohang> arrayGiohang;

    public GiohangAdapter(Context context, ArrayList<Giohang> arrayGiohang) {
        this.context = context;
        this.arrayGiohang = arrayGiohang;
    }

    @Override
    public int getCount() {
        return arrayGiohang.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayGiohang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        public TextView tengiohangTv, giagiohangTv;
        public ImageView anhgiohangIv;
        public Button tangBtn,giamBtn, soluongBtn;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.giohang_card,null);

            //gan anh xa de lay giu lieu
            viewHolder.tengiohangTv = convertView.findViewById(R.id.tengiohangTv);
            viewHolder.giagiohangTv = convertView.findViewById(R.id.giagiohangTv);
            viewHolder.anhgiohangIv = convertView.findViewById(R.id.anhgiohangIv);
            viewHolder.tangBtn = convertView.findViewById(R.id.tangBtn);
            viewHolder.giamBtn = convertView.findViewById(R.id.giamBtn);
            viewHolder.soluongBtn = convertView.findViewById(R.id.soluongBtn);

            //set Tag
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //gan gia tri de hien thi du lieu ra
        Giohang giohang = (Giohang) getItem(position);
        viewHolder.tengiohangTv.setText(giohang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.giagiohangTv.setText("Giá: " +decimalFormat.format(giohang.getGiasp())+ " VNĐ");

        //xu ly anh
        Picasso.get().load(giohang.getHinhsp())
                .placeholder(R.drawable.noimg)
                .error(R.drawable.error)
                .into(viewHolder.anhgiohangIv);

        viewHolder.soluongBtn.setText(giohang.getSoluongsp()+"");

        //xu ly hien thi nut
        int sl = Integer.parseInt(viewHolder.soluongBtn.getText().toString());

        if (sl >= 10) {
            viewHolder.tangBtn.setVisibility(convertView.INVISIBLE);
            viewHolder.giamBtn.setVisibility(convertView.VISIBLE);
        }
        else if(sl <= 1){
            viewHolder.giamBtn.setVisibility(convertView.INVISIBLE);
        }
        else if(sl >= 1){
            viewHolder.giamBtn.setVisibility(convertView.VISIBLE);
            viewHolder.tangBtn.setVisibility(convertView.VISIBLE);
        }

        //xu ly bam vao nut tang giam
        viewHolder.tangBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat = Integer.parseInt(viewHolder.soluongBtn.getText().toString()) + 1;
                int slht = HomeActivity.arrayGiohang.get(position).getSoluongsp();
                long giaht = HomeActivity.arrayGiohang.get(position).getGiasp();

                HomeActivity.arrayGiohang.get(position).setSoluongsp(slmoinhat);
                //tinh gia moi nhat
                long giamoinhat = (giaht * slmoinhat) /slht;
                HomeActivity.arrayGiohang.get(position).setGiasp(giamoinhat);

                //gan ket qua cho san pham sau thi tang
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                viewHolder.giagiohangTv.setText("Giá: " +decimalFormat.format(giamoinhat)+ " VNĐ");

                //xu ly tong tien
                GiohangActivity.XulyGiohang();
                if (slmoinhat > 10 ){
                    viewHolder.tangBtn.setVisibility(View.INVISIBLE);
                    viewHolder.giamBtn.setVisibility(View.VISIBLE);

                    //set gia tri so luong sau khi bam nut
                    viewHolder.soluongBtn.setText(String.valueOf(slmoinhat));
                }
                else{
                    viewHolder.tangBtn.setVisibility(View.VISIBLE);
                    viewHolder.giamBtn.setVisibility(View.VISIBLE);
                    viewHolder.soluongBtn.setText(String.valueOf(slmoinhat));
                }
            }
        });

        viewHolder.giamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat = Integer.parseInt(viewHolder.soluongBtn.getText().toString()) -1;
                int slht = HomeActivity.arrayGiohang.get(position).getSoluongsp();
                long giaht = HomeActivity.arrayGiohang.get(position).getGiasp();

                HomeActivity.arrayGiohang.get(position).setSoluongsp(slmoinhat);
                //tinh gia moi nhat
                long giamoinhat = (giaht * slmoinhat) /slht;
                HomeActivity.arrayGiohang.get(position).setGiasp(giamoinhat);

                //gan ket qua cho san pham sau thi tang
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                viewHolder.giagiohangTv.setText("Giá: " +decimalFormat.format(giamoinhat)+ " VNĐ");

                //xu ly tong tien
                GiohangActivity.XulyGiohang();
                if (slmoinhat < 2 ){
                    viewHolder.giamBtn.setVisibility(View.INVISIBLE);
                    viewHolder.tangBtn.setVisibility(View.VISIBLE);

                    //set gia tri so luong sau khi bam nut
                    viewHolder.soluongBtn.setText(String.valueOf(slmoinhat));
                }
                else{
                    viewHolder.tangBtn.setVisibility(View.VISIBLE);
                    viewHolder.giamBtn.setVisibility(View.VISIBLE);
                    viewHolder.soluongBtn.setText(String.valueOf(slmoinhat));
                }
            }
        });

        return convertView;
    }
}
