package com.example.luiz1.nocash;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import org.w3c.dom.Text;


public class ParceirosFragment extends Fragment {
private TextView txtnomeparca;
private TextView descparca;
private ImageView imgparca;

    public ParceirosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lista_parceiros, container, false);

      //  txtnomeparca = v.findViewById(R.id.txtnomeparca);
      //  descparca = v.findViewById(R.id.descparca);
        imgparca = v.findViewById(R.id.imgparca);



        // Pegando os dados dos produtos (ListaParceiros)
        Bundle bundle = this.getArguments();
        if(bundle !=null){
            String nomedoparca = bundle.getString("nomeparca");
            String descdoparca = bundle.getString("descricaoparca");
            Integer imagemparca = bundle.getInt("imglogo");


            txtnomeparca.setText(nomedoparca); // Nome do Produto
            descparca.setText(descdoparca);
            imgparca.setImageResource(imagemparca); // Imagem do Produto


        }



        // Inflate the layout for this fragment
        return v;
    }

}
