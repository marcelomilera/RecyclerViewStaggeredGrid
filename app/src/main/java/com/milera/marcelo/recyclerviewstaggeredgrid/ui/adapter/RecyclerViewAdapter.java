package com.milera.marcelo.recyclerviewstaggeredgrid.ui.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.milera.marcelo.recyclerviewstaggeredgrid.model.Pokemon;
import com.milera.marcelo.recyclerviewstaggeredgrid.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by MARCELO on 19/10/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Pokemon> itemList;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public RecyclerViewAdapter(Context context, List<Pokemon> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.staggered_list, null);
        RecyclerViewAdapter.ViewHolder rcv = new RecyclerViewAdapter.ViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, final int position) {
        holder.name.setText(itemList.get(position).getName());
        holder.photo.setImageBitmap(getBitmapFromAssets(itemList.get(position).getPhoto()));

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener!=null){
                    Log.v("ADAPTER", "iviPhoto "+holder.photo);
                    ViewCompat.setTransitionName(holder.photo, "iviPhoto");
                    onItemClickListener.onClikListener(position,view,holder.photo);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public Bitmap getBitmapFromAssets(String fileName) {
        AssetManager assetManager = context.getAssets();

        InputStream istr = null;
        try {
            istr = assetManager.open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(istr);

        return bitmap;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView photo;
        public View view;

        public ViewHolder(View v) {
            super(v);
            this.view = v;
            name = (TextView) itemView.findViewById(R.id.country_name);
            photo = (ImageView) itemView.findViewById(R.id.country_photo);
        }

    }
}
