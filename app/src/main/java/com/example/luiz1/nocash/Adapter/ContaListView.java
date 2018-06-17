package com.example.luiz1.nocash.Adapter;

import android.app.Activity;

import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.example.luiz1.nocash.R;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ContaListView  extends ArrayAdapter<String>  {
    private String[] nometrans;
    private String[] desctrans;
    private Integer[] imgtrans;
    private Activity context;


    public ContaListView(Activity context, String[] nometrans, String[] desctrans, Integer[] imgtrans) {
        super(context, R.layout.layoutlistatransacao, nometrans);

        this.context = context;
        this.nometrans = nometrans;
        this.desctrans = desctrans;
        this.imgtrans = imgtrans;

    }


    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        ContaListView.ViewHolder viewHolder = null;
        if (r == null){

            LayoutInflater layoutInflater = context.getLayoutInflater();
            r= layoutInflater.inflate(R.layout.layoutlistatransacao, null , true);
            viewHolder = new ContaListView.ViewHolder(r);
            r.setTag(viewHolder);

        }else{
            viewHolder = (ContaListView.ViewHolder) r.getTag();
        }

        viewHolder.nometrans.setText(nometrans[position]);
        viewHolder.desctrans.setText(desctrans[position]);
        viewHolder.imgtrans.setImageResource(imgtrans[position]);


        return r;
    }

    class ViewHolder {
        TextView nometrans;
        TextView desctrans;
        ImageView imgtrans;
        ViewHolder(View v){

            nometrans = (TextView) v.findViewById(R.id.nomeparca);
            desctrans = (TextView) v.findViewById(R.id.desctrans);
            imgtrans = (ImageView) v.findViewById(R.id.imgparca);
        }

    }


}





