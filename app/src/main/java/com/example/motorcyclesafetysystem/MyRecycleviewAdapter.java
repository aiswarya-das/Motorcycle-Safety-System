package com.example.motorcyclesafetysystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecycleviewAdapter extends RecyclerView.Adapter<MyRecycleviewAdapter.MyViewHolder> {

    Context context;
    private ItemListener  itemListener;
    ArrayList<recycleData> list;

    public MyRecycleviewAdapter(Context context, ArrayList<recycleData> list,ItemListener itemListener) {
        this.context = context;
        this.itemListener=itemListener;
        this.list = list;
    }
    public MyRecycleviewAdapter(){}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        recycleData recycleData = list.get(position);
        holder.date.setText(recycleData.getDate());
        holder.location.setImageResource(R.drawable.newmap);
        holder.itemView.setOnClickListener(view -> { itemListener.onclick(recycleData.location);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public interface ItemListener{
        void onclick(String s);
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView date;
        ImageView location;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.Date);
            location = itemView.findViewById(R.id.imageView);
        }
    }
}
