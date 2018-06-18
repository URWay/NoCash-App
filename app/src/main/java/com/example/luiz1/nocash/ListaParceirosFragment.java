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
import com.example.luiz1.nocash.Adapter.ParcasListview;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListaParceirosFragment extends Fragment {


    public ListaParceirosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
               // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_lista_parceiros, container, false);

        // Parceiros Fakes (Mais originais que Modern Warfare 4)
        final String parca1 = "Parceiro 1";
        final String parca2 = "Parceiro 2";
        final String parca3 = "Parceiro 3";
        final String parca4 = "Parceiro 4";

        final String[] nomeparca={parca1,parca2,parca3, parca4};
        final String[] descdoparca={"Descrição 1","Descrição 2", "Descrição 3","Descrição 4"};
        final Integer[] imgparca={R.drawable.logomenu, R.drawable.bglogin,R.drawable.bglogin,R.drawable.bglogin}; // Imagens, se for adicionar, use PNG

        // Listener de cada item da lista
        final ListView lista =  v.findViewById(R.id.listaparcas);
        final ParcasListview parcasListview = new ParcasListview(getActivity(), nomeparca, descdoparca, imgparca);
        lista.setAdapter(parcasListview);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String s = lista.getItemAtPosition(i).toString();
                // Toast só pra teste
                //  Toast.makeText(getActivity().getApplicationContext(), s, Toast.LENGTH_LONG).show();


                // Passando os dados dos produtos via Bundle para FragmentParceiroProduto
                Bundle bundle = new Bundle();

                if(s == parca1){

                    bundle.putString("nomeparca", s);
                    bundle.putString("descdoparca", descdoparca[0]);
                    bundle.putInt("imgparca", imgparca[0]);

                }else if(s == parca2){

                    bundle.putString("nomeparca", s);
                    bundle.putString("descdoparca", descdoparca[1]);
                    bundle.putInt("imgparca", imgparca[1]);


                }else if(s == parca3){

                    bundle.putString("nomeparca", s);
                    bundle.putString("descdoparca", descdoparca[2]);
                    bundle.putInt("imgparca", imgparca[2]);


                }else if(s == parca4){
                    bundle.putString("nomeparca", s);
                    bundle.putString("descdoparca", descdoparca[3]);
                    bundle.putInt("imgparca", imgparca[3]);

                }

                FragmentParceiroProduto frag1 = new FragmentParceiroProduto();
                frag1.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.corpo, frag1);
                fragmentTransaction.commit();

            }
        });


        return v;
    }

}
