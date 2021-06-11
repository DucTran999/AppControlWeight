package com.ductran.ptit.apptinhcalo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ductran.ptit.apptinhcalo.R;
import com.ductran.ptit.apptinhcalo.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.SanphamViewHolder> implements Filterable {

    private List<Sanpham> sanphamList;
    private List<Sanpham> sanphamListOld;
    private LayoutInflater inflater;

    public SanphamAdapter(Context context, List<Sanpham> sanphamList) {
        this.sanphamList = sanphamList;
        this.sanphamListOld = sanphamList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public SanphamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.sanpham_card, parent, false);
        return new SanphamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanphamAdapter.SanphamViewHolder holder, int position) {
        Sanpham sanpham = sanphamList.get(position);
        if(sanpham == null){
            return;
        }
        holder.tenspTv.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.giaspTv.setText("Giá: " +decimalFormat.format(sanpham.getGiasanpham())+" VND");

        //xu ly mo ta san pham
        holder.motaTv.setMaxLines(2);
        //dau ba cham o cuoi cau
        holder.motaTv.setEllipsize(TextUtils.TruncateAt.END);
        holder.motaTv.setText(sanpham.getMotasanpham());

        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimg)
                .error(R.drawable.error)
                .into(holder.sanphamIv);

    }

    @Override
    public int getItemCount() {
        if(sanphamList != null){
            return sanphamList.size();
        }
         return 0;
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                //keyword search
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()){
                    sanphamList = sanphamListOld;
                }
                else {
                    List<Sanpham> list = new ArrayList<>();
                    for(Sanpham sanpham : sanphamListOld){
                        if(sanpham.getTensanpham().toLowerCase().contains(strSearch)){
                            list.add(sanpham);
                        }
                    }
                    sanphamList = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = sanphamList;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                sanphamList = (List<Sanpham>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class SanphamViewHolder extends RecyclerView.ViewHolder{

        private TextView tenspTv, giaspTv, motaTv;
        private ImageView sanphamIv;

        public SanphamViewHolder(@NonNull View itemView) {
            super(itemView);
            tenspTv = itemView.findViewById(R.id.tenspTv);
            giaspTv = itemView.findViewById(R.id.giaspTv);
            motaTv = itemView.findViewById(R.id.motaTv);
            sanphamIv = itemView.findViewById(R.id.sanphamIv);
        }
    }

}
