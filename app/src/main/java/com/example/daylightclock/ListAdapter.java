package com.example.daylightclock;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.Viewholder> {

    private LinkedList<String> localDataSet;

    public static class Viewholder extends RecyclerView.ViewHolder{
        private final TextView textView;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
        public TextView getTextView() {
            return textView;
        }
    }

    public ListAdapter(LinkedList<String> dataSet){
        localDataSet = dataSet;
    }

    public void setList(LinkedList<String> dataSet){
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_row_item, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.getTextView().setText(localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
