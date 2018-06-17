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
import android.widget.Toast;

import com.example.luiz1.nocash.Adapter.PromoListview;


public class FragmentPromocao extends Fragment {

    private ImageView imgitem;
    private TextView nometrans;
    private TextView descpromo;

    public FragmentPromocao() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_promocao, container, false);


        imgitem = view.findViewById(R.id.imgitem);
        nometrans = view.findViewById(R.id.nometrans);
        descpromo = view.findViewById(R.id.descpromo);

// Definindo o que será passado: nome da promoção, descrição, e imagem nessa ordem:
        final String[] nomepromoprod={"ProdPromo1","ProdPromo2", "ProdPromo3", "ProdPromo4"};
        final String[] prodpromodesc={"DescPromo1", "DescPromo2", "DescPromo3", "DescPromo4"};
        final Integer[] imgpromoid={R.drawable.logomenu, R.drawable.bglogin,R.drawable.bglogin,R.drawable.bglogin};

        final Double[] antigosprecos = {30.00, 80.00, 150.00, 250.00};
        final Double[] precos={20.00 ,50.00 ,100.00 ,200.00};

        // Pegando os dados dos produtos (Fragment Home Produto)
        final Bundle bundle2 = this.getArguments();
        if(bundle2 !=null){
            String nomedoproduto = bundle2.getString("nomedoproduto");
            String descricaoprod = bundle2.getString("descricaoprod");
            Integer imagemprod = bundle2.getInt("Imagem");

            imgitem.setImageResource(imagemprod); // Imagem do Produto
            nometrans.setText(nomedoproduto); // Nome do Produto
            descpromo.setText(descricaoprod);


        }


// Listener de cada item da lista
        final ListView lista =  view.findViewById(R.id.listapromo);
        PromoListview promoListview = new PromoListview(getActivity(), nomepromoprod, prodpromodesc, imgpromoid);
        lista.setAdapter(promoListview);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                String s = lista.getItemAtPosition(i).toString();
                // Toast só pra teste
                //  Toast.makeText(getActivity().getApplicationContext(), s, Toast.LENGTH_LONG).show();

                // Passando os dados dos produtos em promoção via Bundle para FragmentHomeProduto
                Bundle bundle2 = new Bundle();

                if(s == nomepromoprod[0]){


                    bundle2.putString("nomedoproduto", s);
                    bundle2.putString("descricaoprod", prodpromodesc[0]);
                    bundle2.putInt("Imagem", imgpromoid[0]);

                    bundle2.putDouble("precoproduto", precos[0]);
                    bundle2.putDouble("antigosprecos", antigosprecos[0]);
                }else if(s == nomepromoprod[1]){

                    bundle2.putString("nomedoproduto", s);
                    bundle2.putString("descricaoprod", prodpromodesc[1]);
                    bundle2.putInt("Imagem", imgpromoid[1]);

                    bundle2.putDouble("precoproduto", precos[1]);
                    bundle2.putDouble("antigosprecos", antigosprecos[1]);

                }else if(s == nomepromoprod[2]){

                    bundle2.putString("nomedoproduto", s);
                    bundle2.putString("descricaoprod", prodpromodesc[2]);
                    bundle2.putInt("Imagem", imgpromoid[2]);


                    bundle2.putDouble("precoproduto", precos[2]);
                    bundle2.putDouble("antigosprecos", antigosprecos[2]);

                }else if(s == nomepromoprod[3]){
                    bundle2.putString("nomedoproduto", s);
                    bundle2.putString("descricaoprod", prodpromodesc[3]);
                    bundle2.putInt("Imagem", imgpromoid[3]);


                    bundle2.putDouble("precoproduto", precos[3]);
                    bundle2.putDouble("antigosprecos", antigosprecos[3]);

                }



                FragmentHomeProduto frag1 = new FragmentHomeProduto();
                frag1.setArguments(bundle2);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.corpo, frag1);
                fragmentTransaction.commit();
            }
        });



        return view;

    }

}
