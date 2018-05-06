package com.example.luiz1.nocash;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.pedant.SweetAlert.SweetAlertDialog;



    public class CadastroActivity extends AppCompatActivity {

        private EditText txtnome;
        private EditText txtmail;
        private EditText txtcpf;
        private EditText txtrg;
        private EditText txtcep;
        private Button btncep;
        private EditText txtendereco;
        private EditText txtnum;
        private EditText compcad;
        private EditText txtlogin;
        private EditText txtsenha;
        private Button btnok;
        private Toolbar toolbar;
        private WebView webView;
        private Functions f = new Functions();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_cadastro);

            txtnome = findViewById(R.id.txtnome);
            txtmail = findViewById(R.id.txtmail);
            txtcpf = findViewById(R.id.txtcpf);
            txtrg = findViewById(R.id.txtrg);
            txtcep = findViewById(R.id.txtcep);
            btncep = findViewById(R.id.btncep);
            txtendereco = findViewById(R.id.txtendereco);
            txtnum = findViewById(R.id.txtnumero);
            compcad = findViewById(R.id.txtcompcad);
            txtlogin = findViewById(R.id.txtlogincad);
            txtsenha = findViewById(R.id.txtsenhacad);
            btnok = findViewById(R.id.btnsubmit);

            txtcpf.addTextChangedListener(Mask.insert("###.###.###-##", txtcpf));
            txtrg.addTextChangedListener(Mask.insert("##.###.###-#", txtrg));
            txtcep.addTextChangedListener(Mask.insert("#####-###", txtcep));


            // Toolbar
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarcad);
            toolbar.setTitle("Cadastro de Usuário");
            toolbar.setNavigationIcon(R.drawable.ic_clear_white_24dp);

            setSupportActionBar(toolbar);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(CadastroActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            });




            btnok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (txtnome.getText().toString().isEmpty() ||
                            txtmail.getText().toString().isEmpty() ||
                            txtcpf.getText().toString().isEmpty() ||
                            txtrg.getText().toString().isEmpty() ||
                            txtcep.getText().toString().isEmpty() ||
                            txtendereco.getText().toString().isEmpty() ||
                            txtnum.getText().toString().isEmpty() ||
                            txtlogin.getText().toString().isEmpty() ||
                            txtsenha.getText().toString().isEmpty()
                            ) {
                        errologin();
                    }
                /*else if(f.isValidEmail(txtmail.toString()) == false){
                    erromail();

                }*/
                    else if (f.isCPF(txtcpf.toString()) == false) {
                        errocpf();
                    } else {
                        loginok();
                    }
                }
            });


            btncep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String vcep = txtcep.getText().toString();

                    if (vcep.isEmpty() || vcep.equals("")) {
                        errocep();
                    } else {
                        EnderecoNetworkCall myCallEndereco = new EnderecoNetworkCall();
                        myCallEndereco.execute("https://viacep.com.br/ws/" + vcep + "/json/");
                    }
                }
            });


        }


        private void errocep() {
            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Opa, houve um erro!")
                    .setContentText("O CEP informado é inválido, verifique o CEP informado e tente novamente. ")
                    .show();
        }

        private void erromail() {

            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Opa, houve um erro!")
                    .setContentText("O e-mail informado é inválido, verifique o e-mail informado e tente novamente.")
                    .show();


        }



        // WEBSERVICE CEP
        public class EnderecoNetworkCall extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                try {
                    HttpURLConnection urlConnection = (HttpURLConnection) new URL(params[0]).openConnection();
                    InputStream in = urlConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

                    StringBuilder resultado = new StringBuilder();
                    String linha = bufferedReader.readLine();

                    while (linha != null) {
                        resultado.append(linha);
                        linha = bufferedReader.readLine();
                    }

                    String respostaCompleta = resultado.toString();
                    return respostaCompleta;

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }



        private void errocpf() {

            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Opa, houve um erro!")
                    .setContentText("O CPF informado é inválido, verifique o CPF informado e tente novamente.")
                    .show();


        }

        private void errologin() {

            new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Opa, houve um erro!")
                    .setContentText("Os campos com * são de preenchimento obrigatórios.")
                    .show();


        }

        private void loginok() {
            new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Bem vindo!")
                    .setContentText("Cadastro realizado com sucesso!")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {

                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {

                            sweetAlertDialog.dismissWithAnimation();
                            Intent i = new Intent(CadastroActivity.this, LoginActivity.class);
                            startActivity(i);
                        }

                    })
                    .show();

        }

    }


