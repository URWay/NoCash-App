package com.example.luiz1.nocash;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import android.view.View.OnClickListener;
/**
 * A simple {@link Fragment} subclass.
 */
public class RecargaFragment extends Fragment {

private Button btn10;
private Button btn20;
private Button btn50;
private Button btn100;
private Button btnconfrecarga;
private TextView txtval;
    public RecargaFragment() {
        // Required empty public constructor
    }
    private void  AddDez(){
    double soma = 10;
    double valor = Double.parseDouble(txtval.getText().toString());
    double total = valor+soma;
    String totalmax = Double.toString(total);

    txtval.setText(totalmax);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_recarga, container, false);

        btn10=(Button) view.findViewById(R.id.btn1);
        btn20=(Button) view.findViewById(R.id.btn3);
        btn50=(Button) view.findViewById(R.id.button4);
        btn100=(Button)view.findViewById(R.id.button5);
        btnconfrecarga = (Button) view.findViewById(R.id.btnconfrecarga);

        txtval = (TextView)view.findViewById(R.id.textView2);
        btn10.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                    AddDez();
            }
        });
        btn20.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AddDez();
            }
        });
        btn50.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AddDez();
            }
        });
        btn100.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnconfrecarga.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;

    }




}
