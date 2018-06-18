package com.example.luiz1.nocash;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.luiz1.nocash.Adapter.HomeListview;
import com.example.luiz1.nocash.Adapter.ProdParcasListview;

public class FragmentParceiroProduto extends Fragment {

    private ImageView logoparca;
    private TextView nomeparca;
    private TextView descparca;


    public FragmentParceiroProduto() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_parceiro_produto, container, false);

        logoparca = v.findViewById(R.id.logoparca);
        nomeparca = v.findViewById(R.id.nomeprod);
        descparca = v.findViewById(R.id.descparca);

        // Pegando os dados dos produtos (ListaParceirosFragment)
        Bundle bundle = this.getArguments();
        if(bundle !=null){
            String nomedoparca = bundle.getString("nomeparca");
            String descdoparca = bundle.getString("descdoparca");
            Integer logodoparca = bundle.getInt("imgparca");


            nomeparca.setText(nomedoparca); // Nome do Parceiro
            descparca.setText(descdoparca); // Descrição do Parça
            logoparca.setImageResource(logodoparca); // Imagem do Parceiro


         // Preencher lista dos produtos
            // Produtos Fakes (Mais originais que Modern Warfare 4)
            final String nomeprod1 = "Produto 1";
            final String nomeprod2 = "Produto 2";
            final String nomeprod3 = "Produto 3";
            final String nomeprod4 = "Produto 4";


         // Definindo o que será passado: nome da promoção, descrição, e imagem nessa ordem:
            final String[] nomeprod={nomeprod1,nomeprod2,nomeprod3, nomeprod4};
            final String[] proddesc={"Desc1", "Desc2", "Desc3", "Desc4"};
            final Integer[] imgid={R.drawable.logomenu, R.drawable.bglogin,R.drawable.bglogin,R.drawable.bglogin}; // Imagens, se for adicionar, use PNG

            final Double[] antigosprecos = {30.00, 80.00, 150.00, 250.00};
            final Double[] precos={20.00 ,50.00 ,100.00 ,200.00};



            // Listener de cada item da lista
            final ListView lista =  v.findViewById(R.id.listaprodparceiro);
            final ProdParcasListview prodParcasListview = new ProdParcasListview(getActivity(), nomeprod, imgid);
            lista.setAdapter(prodParcasListview);


            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    String s = lista.getItemAtPosition(i).toString();
                    // Toast só pra teste
                    //  Toast.makeText(getActivity().getApplicationContext(), s, Toast.LENGTH_LONG).show();

                    // Passando os dados dos produtos via Bundle para FragmentHomeProduto
                    Bundle bundle = new Bundle();

                    if(s == nomeprod1){


                        bundle.putString("nomedoproduto", s);
                        bundle.putDouble("precoproduto", precos[0]);
                        bundle.putDouble("antigosprecos", antigosprecos[0]);
                        bundle.putInt("Imagem", imgid[0]);

                    }else if(s == nomeprod2){

                        bundle.putString("nomedoproduto", s);
                        bundle.putDouble("precoproduto", precos[1]);
                        bundle.putDouble("antigosprecos", antigosprecos[1]);
                        bundle.putInt("Imagem", imgid[1]);


                    }else if(s == nomeprod3){

                        bundle.putString("nomedoproduto", s);
                        bundle.putDouble("precoproduto", precos[2]);
                        bundle.putDouble("antigosprecos", antigosprecos[2]);
                        bundle.putInt("Imagem", imgid[2]);


                    }else if(s == nomeprod4){
                        bundle.putString("nomedoproduto", s);
                        bundle.putDouble("precoproduto", precos[3]);
                        bundle.putDouble("antigosprecos", antigosprecos[3]);
                        bundle.putInt("Imagem", imgid[3]);

                    }

                    FragmentHomeProduto frag1 = new FragmentHomeProduto();
                    frag1.setArguments(bundle);
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.corpo, frag1);
                    fragmentTransaction.commit();





                }
            });


        }


        return v;
    }

}
