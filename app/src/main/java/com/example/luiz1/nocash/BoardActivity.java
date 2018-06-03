package com.example.luiz1.nocash;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public class BoardActivity extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setPrevText("Anterior"); // Previous button text
        setNextText("Próximo"); // Next button text
        setFinishText("Usar o aplicativo"); // Finish button text
        setCancelText("Cancelar"); // Cancel button text



        addFragment(new Step.Builder().setTitle("Bem vindo ao NoCash!")
                .setContent("This is content")
                .setBackgroundColor(Color.parseColor("#121212")) // int background color
                .setDrawable(R.drawable.logomenu) // int top drawable
                .setSummary("This is summary")
                .build());


        addFragment(new Step.Builder().setTitle("Facilidade nas suas compras!")
                .setContent("This is content")
                .setBackgroundColor(Color.parseColor("#121212")) // int background color
                .setDrawable(R.drawable.ic_action_home) // int top drawable
                .setSummary("This is summary")
                .build());


        addFragment(new Step.Builder().setTitle("Realize o login")
                .setContent("Com o login realizado, poderá ter acesso total à plataforma!")
                .setBackgroundColor(Color.parseColor("#121212")) // int background color
                .setDrawable(R.drawable.ic_login) // int top drawable
                .setSummary("Obtendo descontos, promoções e muito mais!")
                .build());




    }

    @Override
    public void finishTutorial() {
        Intent i = new Intent(BoardActivity.this, LoginActivity.class);
        startActivity(i);
        finish();

    }
}
