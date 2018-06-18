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

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ContaListView  extends ArrayAdapter<String>  {
    private String[] nometrans;
    private String[] desctrans;
    private String data;
    private Activity context;


    public ContaListView(Activity context, String[] nometrans, String[] desctrans, String data) {
        super(context, R.layout.layoutlistatransacao, nometrans);

        this.context = context;
        this.nometrans = nometrans;
        this.desctrans = desctrans;
        this.data=data;

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



        return r;
    }

    class ViewHolder {
        TextView nometrans;
        TextView desctrans;
        ImageView imgtrans;
        String data;
        ViewHolder(View v){

            nometrans = (TextView) v.findViewById(R.id.nomeprod);
            desctrans = (TextView) v.findViewById(R.id.desctrans);

            SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
            Date todayDate = new Date();
            data = currentDate.format(todayDate);
        }

    }


}





