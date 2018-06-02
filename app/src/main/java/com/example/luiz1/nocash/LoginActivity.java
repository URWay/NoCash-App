package com.example.luiz1.nocash;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.luiz1.nocash.Model.Cliente;
import com.example.luiz1.nocash.service.ClienteService;

import java.text.Normalizer;

import cn.pedant.SweetAlert.ProgressHelper;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "Cliente";

    private EditText txtuser;
    private EditText txtsenha;
    private Button btnsubmit;
    private TextView txtcadlogin;
    private ProgressHelper tempoload;

    private ProgressBar loading;
    SweetAlertDialog load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtuser = (EditText)findViewById(R.id.txtuser);
        txtsenha = (EditText)findViewById(R.id.txtsenha);
        btnsubmit = (Button)findViewById(R.id.btnsubmit);
        txtcadlogin = (TextView) findViewById(R.id.txtcadlogin);

        loading = (ProgressBar) findViewById(R.id.loading);


        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = txtuser.getText().toString().trim();
                String senha = txtsenha.getText().toString().trim();

                if (email.isEmpty() || senha.isEmpty()){
                    erro("Opa, houve um erro!",
                            "Os campos com * são de preenchimento obrigatórios.");
                } else {

                    // Colocar o loading aqui
                    load=new SweetAlertDialog(LoginActivity.this,SweetAlertDialog.PROGRESS_TYPE);
                    load.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    load.setTitleText("Aguarde...");
                    load.setCancelable(true);
                    load.show();


                    final Cliente cliente = new Cliente();
                    cliente.setEmail(email);
                    cliente.setSenha(senha);

                    try {
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(ClienteService.BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        ClienteService service = retrofit.create(ClienteService.class);
                        Call<Cliente> requestLogin = service.Login(cliente);

                        requestLogin.enqueue(new Callback<Cliente>() {
                            @Override
                            public void onResponse(Call<Cliente> call, Response<Cliente> response) {

                                if (!response.isSuccessful()) {
                                    Log.i(TAG, "Erro: " + response.code());
                                    erro("Erro", "E-mail ou senha inválidos!");
                                    // Terminar o loading aqui
                                    load.hide();

                                } else {
                                    // Requisição retornou com sucesso
                                    Cliente cliente = response.body();
                                    if(cliente.getId() > 0){
                                        // Terminar o loading aqui
                                    load.hide();


                                        new Session().SessaoLogin(LoginActivity.this, cliente.getId());
                                        loginok();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<Cliente> call, Throwable t) {
                                erro("Erro", "" +
                                        "Não foi possível realizar o login, verifique o sinal da internet e tente novamente!");
                                Log.e(TAG, "Erro: " + t.getMessage());
                                // Terminar o loading aqui
                                load.hide();


                            }
                        });
                    }catch(Exception e){
                        erro("Erro", "Houve um erro: " + e.getMessage());
                        Log.e(TAG, "Erro: " + e.getMessage());
                        // Terminar o loading aqui
                        load.hide();

                    }
                }
            }
        });

        txtcadlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    public void loading(String msg){
        SweetAlertDialog load = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        load.getProgressHelper().setBarColor(Color.parseColor("#6cb907"));
        tempoload = new ProgressHelper(LoginActivity.this);
        load.setTitleText(msg);
        load.setCancelable(false);
        load.show();
    }

    private void loginok(){
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Bem vindo!")
                .setContentText("Login efetuado com sucesso!")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {

                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                        sweetAlertDialog.dismissWithAnimation();
                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);

                        if (i.resolveActivity(getPackageManager()) == null) {
                            loading("Aguarde por favor...");
                        }
                        else{
                            // There is an activity which can handle this intent.
                            startActivity(i);
                            finish();
                        }
                    }

                })
                .show();

    }

    private void erro(String title, String mensagem) {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(title)
                .setContentText(mensagem)
                .show();
        removeAccent(txtuser.toString());
    }

    public String removeAccent(final String str) {
        String strNoAccent = Normalizer.normalize(str, Normalizer.Form.NFD);
        strNoAccent = strNoAccent.replaceAll("[^\\p{ASCII}]", "");
        return strNoAccent;
    }

}
