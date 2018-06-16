package com.example.luiz1.nocash;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.luiz1.nocash.Model.Carteira;
import com.example.luiz1.nocash.Model.Movimento;
import com.example.luiz1.nocash.Model.Pagamento;
import com.example.luiz1.nocash.service.MovimentoService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.ProgressHelper;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CompraActivity extends AppCompatActivity {
    private static final String TAG = "Compra";
    private ProgressHelper tempoload;
    SweetAlertDialog load;

    private EditText txtNr;
    private EditText txtVl;
    private EditText txtCD;
    private Button btnEfetuarPagamento;
    private char origem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        txtVl = findViewById(R.id.txtVL_COMPRA);
        txtVl.addTextChangedListener(Mask.insert("###", txtVl));

        txtNr = findViewById(R.id.txtNrDocumento);
        txtCD = findViewById(R.id.txtCOD_USER);

        btnEfetuarPagamento = findViewById(R.id.btnEfetuarPagamento);



        btnEfetuarPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                load=new SweetAlertDialog(CompraActivity.this,SweetAlertDialog.PROGRESS_TYPE);
                load.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                load.setTitleText("Aguarde...");
                load.setCancelable(true);
                load.show();

                try {

                    Session session = new Session();
                    Gson gson = new Gson();

                    String object = session.getSessionPagamento(CompraActivity.this);
                    Pagamento pagamento = gson.fromJson(object, Pagamento.class);

                    // Carteira Destino
                    String objCarteira = session.getSessionCarteira(CompraActivity.this);
                    Carteira carteiraDestino = gson.fromJson(objCarteira, Carteira.class);

                    // Parte do Movimento
                    Movimento movimento = new Movimento();
                    movimento.setCarteiraOrigem(null); /// TEM QUE COLOCAR O QUE ESTÁ NA TELA
                    movimento.setCarteiraDestino(carteiraDestino);
                    movimento.setNrDocumento(pagamento.getDesccricao());
                    movimento.setVlBruto(pagamento.getValor());
                    movimento.setVlLiquido(pagamento.getValor());
                    movimento.setVlDesc(0);

                    Date date = new Date();
                    movimento.setDtMovimento(date);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(MovimentoService.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    MovimentoService service = retrofit.create(MovimentoService.class);
                    Call<Movimento> request = service.cargaMovimento(movimento);

                    request.enqueue(new Callback<Movimento>() {
                        @Override
                        public void onResponse(Call<Movimento> call, Response<Movimento> response) {
                            if (response.isSuccessful()) {
                                load.hide();
                                Log.i(TAG, "Erro: " + response.code());

                                new SweetAlertDialog(CompraActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Sucesso!")
                                    .setContentText("Venda efetuado com sucesso!")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {

                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {

                                        sweetAlertDialog.dismissWithAnimation();
                                        Intent i = new Intent(CompraActivity.this, HomeActivity.class);

                                        if (i.resolveActivity(getPackageManager()) == null) {
                                            loading("Aguarde por favor...");
                                        }
                                        else{
                                            startActivity(i);
                                            finish();
                                        }
                                        }

                                    })
                                    .show();
                            } else {
                                load.hide();
                                Log.i(TAG, "Erro: " + response.code());
                                erro("Erro", "" +
                                        "Houve um erro, não foi possível realizar a venda!");
                            }
                        }

                        @Override
                        public void onFailure(Call<Movimento> call, Throwable t) {
                            load.hide();
                            erro("Erro", "" +
                                    "Houve um erro, não foi possível realizar a venda!");
                            Log.e(TAG, "Erro: " + t.getMessage());
                        }
                    });

                } catch (Exception e){
                    load.hide();
                    erro("Erro", "Houve um erro: " + e.getMessage());
                    Log.e(TAG, "Erro: " + e.getMessage());
                }

            }
        });

    }

    private void erro(String title, String mensagem) {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(title)
                .setContentText(mensagem)
                .show();
    }

    public void loading(String msg){
        SweetAlertDialog load = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        load.getProgressHelper().setBarColor(Color.parseColor("#6cb907"));
        tempoload = new ProgressHelper(CompraActivity.this);
        load.setTitleText(msg);
        load.setCancelable(false);
        load.show();
    }

}
