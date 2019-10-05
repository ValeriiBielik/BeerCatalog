package com.my.bielik.beercatalog.beer.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.my.bielik.beercatalog.R;
import com.my.bielik.beercatalog.api.responce.beer.BeerData;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.BeerViewHolder> {

    private List<BeerData> beerData = new ArrayList<>();
    private OnBeerClickListener onBeerClickListener;

    public BeerAdapter(OnBeerClickListener onBeerClickListener) {
        this.onBeerClickListener = onBeerClickListener;
    }

    public void addData(List<BeerData> data) {
        beerData.addAll(data);
        notifyDataSetChanged();
    }

    void clearData() {
        beerData.clear();
        notifyDataSetChanged();
    }

    public List<BeerData> getBeerData() {
        return beerData;
    }

    @NonNull
    @Override
    public BeerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.beer_item, parent, false);
        final BeerViewHolder beerViewHolder = new BeerViewHolder(view);
        beerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBeerClickListener != null) {
                    int position = beerViewHolder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onBeerClickListener.onItemClick(position);
                    }
                }
            }
        });
        return beerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BeerViewHolder holder, int position) {
        holder.tvBeer.setText(beerData.get(position).getName());
        if (beerData.get(position).getLabels() != null && beerData.get(position).getLabels().getIcon() != null) {
            Glide.with(holder.itemView.getContext())
                    .load(beerData.get(position).getLabels().getIcon())
                    .into(holder.ivBeer);
        } else {
            Glide.with(holder.itemView.getContext())
                    .load(R.drawable.ic_not_found)
                    .into(holder.ivBeer);
        }

    }

    @Override
    public int getItemCount() {
        return beerData.size();
    }

    static class BeerViewHolder extends RecyclerView.ViewHolder {

        private TextView tvBeer;
        private ImageView ivBeer;

        BeerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBeer = itemView.findViewById(R.id.tv_beer);
            ivBeer = itemView.findViewById(R.id.iv_beer);
        }
    }

    public interface OnBeerClickListener {
        void onItemClick(int position);
    }
}
