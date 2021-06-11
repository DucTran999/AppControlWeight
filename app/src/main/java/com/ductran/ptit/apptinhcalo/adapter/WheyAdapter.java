package com.ductran.ptit.apptinhcalo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.ductran.ptit.apptinhcalo.R;
import com.ductran.ptit.apptinhcalo.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class WheyAdapter extends BaseAdapter {

    Context context;
    ArrayList<Sanpham> arrayWhey;
    ArrayList<Sanpham> arrayListOld;

    public WheyAdapter(Context context, ArrayList<Sanpham> arrayWhey) {
        this.context = context;
        this.arrayWhey = arrayWhey;
    }

    @Override
    public int getCount() {
        return arrayWhey.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayWhey.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txttenwhey, txtgiawhey, txtmotawhey;
        public ImageView imgwhey;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =  null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.sanpham_card,null);

            viewHolder.txttenwhey  = (TextView) convertView.findViewById(R.id.tenspTv);
            viewHolder.txtgiawhey  = (TextView) convertView.findViewById(R.id.giaspTv);
            viewHolder.txtmotawhey = (TextView)  convertView.findViewById(R.id.motaTv);
            viewHolder.imgwhey = (ImageView) convertView.findViewById(R.id.sanphamIv);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Sanpham sanpham = (Sanpham) getItem(position);
        viewHolder.txttenwhey.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiawhey.setText("Giá: " +decimalFormat.format(sanpham.getGiasanpham())+" VND");

        //xu ly mo ta san pham
        viewHolder.txtmotawhey.setMaxLines(2);
        //dau ba cham o cuoi cau
        viewHolder.txtmotawhey.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotawhey.setText(sanpham.getMotasanpham());

        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimg)
                .error(R.drawable.error)
                .into(viewHolder.imgwhey);

        return convertView;
    }
}
