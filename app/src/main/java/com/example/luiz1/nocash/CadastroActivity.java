package com.example.luiz1.nocash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.luiz1.nocash.Model.Cliente;
import com.example.luiz1.nocash.service.ClienteService;

import java.util.Timer;
import java.util.TimerTask;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CadastroActivity extends AppCompatActivity {

    private static final String TAG = "Cadastro";

    private EditText txtnome;
    private EditText txtmail;
    private EditText txtcpf;
    private EditText txtrg;
    private EditText txtsenha;
    private Button btnok;
    private Toolbar toolbar;
    private WebView webView;
    private Functions functions = new Functions();

    private ProgressBar loading;
    Handler handler;
    Runnable runnable;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        txtnome = findViewById(R.id.txtnome);
        txtmail = findViewById(R.id.txtmail);
        txtcpf = findViewById(R.id.txtcpf);
        txtrg = findViewById(R.id.txtrg);
        txtsenha = findViewById(R.id.txtsenhacad);
        btnok = findViewById(R.id.btnsubmit);

        txtcpf.addTextChangedListener(Mask.insert("###.###.###-##", txtcpf));
        txtrg.addTextChangedListener(Mask.insert("##.###.###-#", txtrg));

        loading = (ProgressBar) findViewById(R.id.loading);
        // Testar esse loading
        loading.setVisibility(View.GONE);


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


                // Declaração das variáveis
                String nome = txtnome.getText().toString().trim();
                String email = txtmail.getText().toString().trim();
                String cpf = txtcpf.getText().toString().trim();
                String rg = txtrg.getText().toString().trim();
                String senha = txtsenha.getText().toString().trim();

                if (nome.isEmpty() || email.isEmpty() || cpf.isEmpty() ||
                        rg.isEmpty() || senha.isEmpty() )
                {
                    erro("Os campos com * são de preenchimento obrigatórios.");
                }
                else if (functions.isCPF(cpf) == false) {
                    erro("O CPF informado é inválido, verifique o CPF informado e tente novamente.");
                } else {

                    // Colocar o loading aqui

                    // Testar esse loading
                loading.setVisibility(View.VISIBLE);

                    Cliente cliente = new Cliente();

                    cliente.setNome(nome);
                    cliente.setEmail(email);
                    cliente.setSenha(senha);
                    cliente.setRg(rg);
                    cliente.setCpf(cpf);

                    // Efetua o cadastro
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(ClienteService.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    ClienteService service = retrofit.create(ClienteService.class);
                    Call<Cliente> response = service.Cadastro(cliente);

                    response.enqueue(new Callback<Cliente>() {
                        @Override
                        public void onResponse(Call<Cliente> call, Response<Cliente> response) {

                            if (!response.isSuccessful()) {
                                Log.i(TAG, "Erro: " + response.code());
                                erro("Não foi possível realizar o cadastro, fale com " +
                                        "os desenvolvedores do aplicativo para resolver o problema!");
                                // Terminar o loading aqui

                                loading.setVisibility(View.GONE);

                            } else {
                                if (response.code() == 200){
                                    // Terminar o loading aqui
                                    // Testar esse loading
                                    loading.setVisibility(View.GONE);


                                    loginok();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Cliente> call, Throwable t) {
                            Log.e(TAG, "Erro: " + t.getMessage());
                            erro("Não foi possível realizar o cadastro, verifique o " +
                                    "sinal da internet e tente novamente!");
                            // Terminar o loading aqui
                            // Testar esse loading
                            loading.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });

    }

    // Mensagens de aviso
    private void erro(String mensagem) {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Opa, houve um erro!")
                .setContentText(mensagem)
                .show();
    }

    // Caso o cadastro seja realizado com sucesso, cadastra o cliente e
    // redireciona para realizar o Login
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