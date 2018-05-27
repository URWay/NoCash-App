package com.example.luiz1.nocash.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luiz1.nocash.R;

public class PromoListview extends ArrayAdapter<String> {


    private String[] nomepromoprod;
    private String[] prodpromodesc;
    private Integer[] imgpromoid;
    private Activity context;


    public PromoListview(Activity context, String[] nomeprod, String[] proddesc, Integer[] imgid) {
        super(context, R.layout.layoutlistapromo,nomeprod);

        this.context = context;
        this.nomepromoprod = nomeprod;
        this.prodpromodesc = proddesc;
        this.imgpromoid = imgid;
    }


    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        PromoListview.ViewHolder viewHolder = null;
        if (r == null){

            LayoutInflater layoutInflater = context.getLayoutInflater();
            r= layoutInflater.inflate(R.layout.layoutlistapromo, null , true);
            viewHolder = new PromoListview.ViewHolder(r);
            r.setTag(viewHolder);

        }else{
            viewHolder = (PromoListview.ViewHolder) r.getTag();
        }

        viewHolder.txtprod.setText(nomepromoprod[position]);
        viewHolder.txtdesc.setText(prodpromodesc[position]);
        viewHolder.imgprod.setImageResource(imgpromoid[position]);


        return r;
    }

    class ViewHolder {
        TextView txtprod;
        TextView txtdesc;
        ImageView imgprod;
        ViewHolder(View v){

            txtprod = (TextView) v.findViewById(R.id.nomepromo);
            txtdesc = (TextView) v.findViewById(R.id.descpromo);
            imgprod = (ImageView) v.findViewById(R.id.imgitem);
        }

    }

}
