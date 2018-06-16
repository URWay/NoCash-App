package com.example.luiz1.nocash;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import java.text.DecimalFormat;

public class FragmentHomeProduto extends Fragment {
private TextView txtpreco1;
private TextView txtproduto;
private Button btnsubmit;
    public FragmentHomeProduto() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment_home_produto, container, false);



        txtpreco1 = v.findViewById(R.id.precoantigo);
        txtpreco1.setPaintFlags(txtpreco1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        txtproduto = v.findViewById(R.id.txtprecoprod);


        txtpreco1.setText(new DecimalFormat("#,##0.00").format(0));
        txtproduto.setText(new DecimalFormat("#,##0.00").format(0));

        btnsubmit = v.findViewById(R.id.btnsubmit);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), CompraActivity.class);

                startActivity(intent);


            }
        });



        // Inflate the layout for this fragment
        return v;
    }


}
