package com.example.luiz1.nocash;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pnikosis.materialishprogress.ProgressWheel;

import cn.pedant.SweetAlert.ProgressHelper;
import cn.pedant.SweetAlert.SweetAlertDialog;


public class LoginActivity extends AppCompatActivity {
    private EditText txtuser;
    private EditText txtsenha;
    private Button btnsubmit;
    private TextView txtcadlogin;
     private ProgressHelper tempoload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    txtuser = (EditText)findViewById(R.id.txtuser);
    txtsenha = (EditText)findViewById(R.id.txtsenha);
    btnsubmit = (Button)findViewById(R.id.btnsubmit);
    txtcadlogin = (TextView) findViewById(R.id.txtcadlogin);



    btnsubmit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           if (txtuser.getText().toString().isEmpty() || txtsenha.getText().toString().isEmpty()){
            errologin();
           }else{

               loginok();


           }
        }
    });


        txtcadlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webcadastrologin();
            }
        });

    }


    private void webcadastrologin(){

        WebView webView = new WebView(this);
        setContentView(webView);
        webView.loadUrl("https://developer.android.com/training/app-links/index.html");


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

    private void errologin() {

        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Opa, houve um erro!")
                .setContentText("O login e senha informados são inválidos, verifique-os e tente novamente.")
                .show();
    }



}
