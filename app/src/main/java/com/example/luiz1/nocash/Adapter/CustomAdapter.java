package com.example.luiz1.nocash.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.battleent.ribbonviews.RibbonLayout;
import com.example.luiz1.nocash.HomeActivity;
import com.example.luiz1.nocash.IntroActivity;
import com.example.luiz1.nocash.Model.Item;
import com.example.luiz1.nocash.R;
import com.squareup.picasso.Picasso;

import java.util.List;

class CustomViewHolder extends RecyclerView.ViewHolder  {

    RibbonLayout ribbonLayout;
    ImageView imageView;
    private View.OnClickListener listener;
    public CustomViewHolder(View itemView) {
        super(itemView);

        ribbonLayout = (RibbonLayout)itemView.findViewById(R.id.ribbonLayout);
        imageView = (ImageView)itemView.findViewById(R.id.imageView);
    }

}
public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> implements View.OnClickListener{
    Context context;
    List<Item> itemList;
    private View.OnClickListener listener;
    public void onClick(View view) {
        if(listener!= null){
            listener.onClick(view);
        }
    }

    public CustomAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    public void addItemnaLista(List<Item> list) {
        itemList.addAll(list);
    this.notifyDataSetChanged();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType)  {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        itemView.setOnClickListener(this);

        return new CustomViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        Item item = itemList.get(position);
        if(item.type == 0){
            holder.ribbonLayout.setShowBottom(false);


            holder.ribbonLayout.setHeaderRibbonColor(Color.parseColor("#f44242"));
            holder.ribbonLayout.setHeaderTextColor(Color.parseColor("#FFFFFF"));
            holder.ribbonLayout.setHeaderText(item.headerText);


            Picasso.with(context).load(item.imageURL).into(holder.imageView);

        }else if(item.type == 1){

            holder.ribbonLayout.setShowBottom(false);


            holder.ribbonLayout.setHeaderRibbonColor(Color.parseColor("#618c4e"));
            holder.ribbonLayout.setHeaderTextColor(Color.parseColor("#FFFFFF"));

            holder.ribbonLayout.setHeaderText(item.headerText);


            Picasso.with(context).load(item.imageURL).into(holder.imageView);


        }else{
            holder.ribbonLayout.setShowBottom(false);
            holder.ribbonLayout.setShowBottom(false);


            Picasso.with(context).load(item.imageURL).into(holder.imageView);

        }

    }
    public void setOnClickListener(View.OnClickListener listener){
            this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }



}

