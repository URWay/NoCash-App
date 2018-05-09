package com.example.luiz1.nocash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class PagamentoActivity extends AppCompatActivity {
    private EditText txtcard;
    private Spinner spinmes;
    private Spinner spinano;
    private EditText txtcod;
    private Functions f = new Functions();

    private List<String> mes = new ArrayList<String>();
    private List<String> anos = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarnav);
        toolbar.setTitle("Adicionar Créditos");
        toolbar.setNavigationIcon(R.drawable.ic_clear_white_24dp);

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        txtcard = findViewById(R.id.txtcard);
        txtcard.addTextChangedListener(Mask.insert("####.####.####.####", txtcard));
        txtcod = findViewById(R.id.txtcod);
        txtcod.addTextChangedListener(Mask.insert("###", txtcod));

        spinmes=findViewById(R.id.spinmes);
        spinano= findViewById(R.id.spinano);

        mes.add("Janeiro");
        mes.add("Fevereiro");
        mes.add("Março");
        mes.add("Abril");
        mes.add("Maio");
        mes.add("Junho");
        mes.add("Julho");
        mes.add("Agosto");
        mes.add("Setembro");
        mes.add("Outubro");
        mes.add("Novembro");
        mes.add("Dezembro");


        for(int ano = 2015; ano <= 2100; ano++){

            anos.add(Integer.toString(ano));
        }

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(PagamentoActivity.this, android.R.layout.simple_list_item_1, mes);

        ArrayAdapter<String> adapterSpinner2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, anos);
        spinmes.setAdapter(adapterSpinner);
        spinano.setAdapter(adapterSpinner2);
    }
}
