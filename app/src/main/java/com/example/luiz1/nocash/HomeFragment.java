package com.example.luiz1.nocash;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.luiz1.nocash.Adapter.HomeListview;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

// Definindo o que será passado: nome da promoção, descrição, e imagem nessa ordem:
        String[] nomeprod={"Prod1","Prod2", "Prod3", "Prod4"};
        String[] proddesc={"Desc1", "Desc2", "Desc3", "Desc4"};
        Integer[] imgid={R.drawable.logomenu, R.drawable.bglogin,R.drawable.bglogin,R.drawable.bglogin};

// Listener de cada item da lista
        ListView lista =  view.findViewById(R.id.listahome);
            HomeListview homeListview = new HomeListview(getActivity(), nomeprod, proddesc, imgid);
            lista.setAdapter(homeListview);
                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        FragmentHomeProduto frag1 = new FragmentHomeProduto();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.corpo, frag1);
                        fragmentTransaction.commit();


                    }
                });



    return view;


    }

}
