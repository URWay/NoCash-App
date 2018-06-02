package com.example.luiz1.nocash;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {

    private SharedPreferences prefs = null;

    // Retorno do ID do cliente
    public int getId(Context context){
        prefs = context.getSharedPreferences("SessionLogin", context.MODE_PRIVATE);
        return prefs.getInt("Session", 0);
    }

    // Armazenando sessão
    public void SessaoLogin(Context context, int id){
        prefs = context.getSharedPreferences("SessionLogin", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("Session", id);
        editor.apply();
    }

    // Deleta sessão
    public void Logoff(Context context){
        prefs = context.getSharedPreferences("SessionLogin", context.MODE_PRIVATE);
        prefs.edit().remove("Session").commit();
    }

}
