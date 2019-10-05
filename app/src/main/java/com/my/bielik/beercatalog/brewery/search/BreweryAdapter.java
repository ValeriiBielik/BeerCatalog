package com.my.bielik.beercatalog.brewery.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.my.bielik.beercatalog.R;
import com.my.bielik.beercatalog.api.responce.brewery.BreweryData;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BreweryAdapter extends RecyclerView.Adapter<BreweryAdapter.BreweryViewHolder> {

    private List<BreweryData> breweryData = new ArrayList<>();
    private OnBreweryClickListener onBreweryClickListener;

    BreweryAdapter(OnBreweryClickListener onBreweryClickListener) {
        this.onBreweryClickListener = onBreweryClickListener;
    }

    List<BreweryData> getBreweryData() {
        return breweryData;
    }

    void updateBreweryData(List<BreweryData> breweryData) {
        this.breweryData.addAll(breweryData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BreweryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brewery_item, parent, false);
        final BreweryViewHolder breweryViewHolder = new BreweryViewHolder(view);
        breweryViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBreweryClickListener != null) {
                    int position = breweryViewHolder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onBreweryClickListener.onItemCLick(position);
                    }
                }
            }
        });
        return breweryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BreweryViewHolder holder, int position) {
        holder.tvBrewery.setText(breweryData.get(position).getBrewery().getName());
    }

    @Override
    public int getItemCount() {
        return breweryData.size();
    }


    static class BreweryViewHolder extends RecyclerView.ViewHolder {

        private TextView tvBrewery;

        BreweryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBrewery = itemView.findViewById(R.id.tv_brewery);
        }
    }

    interface OnBreweryClickListener {
        void onItemCLick(int position);
    }
}
