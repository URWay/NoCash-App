package com.example.luiz1.nocash;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.luiz1.nocash.Adapter.PromoListview;


/**
 * A simple {@link Fragment} subclass.
 */
public class PromocaoFragment extends Fragment {


    public PromocaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_promocao, container, false);

// Definindo o que será passado: nome da promoção, descrição, e imagem nessa ordem:
        String[] nomepromoprod={"ProdPromo1","ProdPromo2", "ProdPromo3", "ProdPromo4"};
        String[] prodpromodesc={"DescPromo1", "DescPromo2", "DescPromo3", "DescPromo4"};
        Integer[] imgpromoid={R.drawable.logomenu, R.drawable.bglogin,R.drawable.bglogin,R.drawable.bglogin};

// Listener de cada item da lista
        ListView lista =  view.findViewById(R.id.listapromo);
        PromoListview promoListview = new PromoListview(getActivity(), nomepromoprod, prodpromodesc, imgpromoid);
        lista.setAdapter(promoListview);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(),"WORKS", Toast.LENGTH_SHORT ).show();
            }
        });



        return view;

    }

}
