package com.example.luiz1.nocash;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.luiz1.nocash.Adapter.CustomListview;


/**
 * A simple {@link Fragment} subclass.
 */

public class HomeFragment extends Fragment {



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        String[] nomeprod={"Prod1","Prod2", "Prod3", "Prod4"};
        String[] proddesc={"Desc1", "Desc2", "Desc3", "Desc4"};
        Integer[] imgid={R.drawable.bglogin, R.drawable.bglogin,R.drawable.bglogin,R.drawable.bglogin};


        ListView lista =  view.findViewById(R.id.listahome);
            CustomListview customListview = new CustomListview(getActivity(), nomeprod, proddesc, imgid);
            lista.setAdapter(customListview);

    return view;

    }

}
