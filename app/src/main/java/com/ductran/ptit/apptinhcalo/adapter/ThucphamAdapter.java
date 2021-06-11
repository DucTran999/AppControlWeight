package com.ductran.ptit.apptinhcalo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.ductran.ptit.apptinhcalo.R;
import com.ductran.ptit.apptinhcalo.model.Thucpham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ThucphamAdapter extends BaseAdapter implements Filterable {

    //Khai bao
    ArrayList<Thucpham> arrayListTp;
    ArrayList<Thucpham> arrayListTpOld;
    Context context;

    public ThucphamAdapter(ArrayList<Thucpham> arrayListTp, Context context) {
        this.arrayListTp = arrayListTp;
        this.arrayListTpOld = arrayListTp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListTp.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListTp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()){
                    arrayListTp = arrayListTpOld;
                }
                else{
                    ArrayList<Thucpham> list = new ArrayList<>();
                    for(Thucpham thucpham: arrayListTpOld){
                        if(thucpham.getTenThucpham().toLowerCase().contains(strSearch)){
                            list.add(thucpham);
                        }
                    }
                    arrayListTp = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = arrayListTp;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                arrayListTp = (ArrayList<Thucpham>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    //load du lieu nhanh hon
    public class ViewHolder{
        TextView tenthucphamTv, proteinTv, caloriesTv;
        ImageView thucphamIv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.thucpham_card,null);

            viewHolder.tenthucphamTv = convertView.findViewById(R.id.tenthucphamTv);
            viewHolder.caloriesTv    = convertView.findViewById(R.id.proteinTv);
            viewHolder.proteinTv     = convertView.findViewById(R.id.caloriesTv);
            viewHolder.thucphamIv    = convertView.findViewById(R.id.thucphamIv);

            //anh xa vao viewholder
            convertView.setTag(viewHolder);
        }
        else{
            //lay ra nhung thu la luu trong tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //get item
        Thucpham thucpham = (Thucpham) getItem(position);
        viewHolder.tenthucphamTv.setText(thucpham.getTenThucpham() + " (100g)");
        viewHolder.proteinTv.setText("Protein: "+thucpham.getProtein()+"g");
        viewHolder.caloriesTv.setText("Calo: "+thucpham.getCalories());

        //picasso load anh
        Picasso.get().load(thucpham.getHinhAnhthucpham())
                .placeholder(R.drawable.noimg)
                .error(R.drawable.error)
                .into(viewHolder.thucphamIv);

        return convertView;
    }
}
