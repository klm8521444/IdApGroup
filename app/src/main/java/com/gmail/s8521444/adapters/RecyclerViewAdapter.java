package com.gmail.s8521444.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.s8521444.Models.Cat;
import com.gmail.s8521444.R;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private List<Cat> mCatList;
    private final View.OnClickListener listener;

    public RecyclerViewAdapter(List<Cat> catList, View.OnClickListener listener) {
        this.mCatList = catList;
        this.listener = listener;
    }

    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_item, viewGroup, false);
        return new MyViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {
        Cat cat = mCatList.get(i);

        viewHolder.mNameTextView.setText(cat.getName());
        viewHolder.mSubtitleTextView.setImageResource(cat.getSubtitle());


    }

    @Override
    public int getItemCount() {
        return mCatList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mNameTextView;
        private ImageView mSubtitleTextView;

        public MyViewHolder(View itemView, View.OnClickListener listener) {
            super(itemView);
            mNameTextView = (TextView) itemView.findViewById(R.id.textViewLarge);
            mSubtitleTextView = (ImageView) itemView.findViewById(R.id.textViewSmall);

            itemView.setOnClickListener(listener);
        }
    }
}
