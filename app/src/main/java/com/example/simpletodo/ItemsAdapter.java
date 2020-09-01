package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//import androidx.annotation.NonNull;



public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{
    // container to provide easy access to views that represent each row of the list
    public interface OnLongClickListener {
        void onItemLongClicked(int position);
    }
    List<String> items;
    OnClickListener longClickListener;
    public ItemsAdapter(List<String> items, OnClickListener longClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull


    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(todoView);

    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        String item = items.get(position);
        holder.bind(item);
    }



    //tells how many items are in the list

    public int getItemCount(){
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvitem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvitem = itemView.findViewById(android.R.id.text1);

        }
        public void bind(String item){
        tvitem.setText(item);
        tvitem.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                longClickListener.onItemLongClicked(getAdapterPosition());
                //notify the listener which postion
                return true;
            }
        });
        }
    }
}
