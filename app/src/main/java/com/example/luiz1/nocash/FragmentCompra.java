package com.example.luiz1.nocash;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.luiz1.nocash.Adapter.ContaListView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCompra extends Fragment {


    public FragmentCompra() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_compra, container, false);

        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        //String data = currentDate.format(todayDate);

        // Definindo o que será passado: nome da promoção, descrição, e imagem nessa ordem:
        String[] vtrans={"50","100", "200", "300.00"};
        String[] desctrans={"Desc1", "Desc2", "Desc3", "Desc4"};
        String[] data={""};


        // Listener de cada item da lista
        ListView lista =  v.findViewById(R.id.listatrans);
        ContaListView contaListview = new ContaListView(getActivity(), vtrans, desctrans, data);
        lista.setAdapter(contaListview);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                FragmentCompra frag1 = new FragmentCompra();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.corpo, frag1);
                fragmentTransaction.commit();


            }
        });


        return v;
    }


}
