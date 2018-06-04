package com.example.luiz1.nocash;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.luiz1.nocash.Model.Cliente;
import com.example.luiz1.nocash.service.ClienteService;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Session {

    private static final String TAG = "Session";
    private static boolean RETORNO = false;
    private SharedPreferences prefs = null;

    // Retorno do ID do cliente
    public String getSession(Context context){
        prefs = context.getSharedPreferences("SessionLogin", context.MODE_PRIVATE);
        return prefs.getString("Session", "");
    }

    // Armazenando sessão
    public void SessaoLogin(Context context, Cliente cliente){
        Gson gson = new Gson();
        String json = gson.toJson(cliente);

        prefs = context.getSharedPreferences("SessionLogin", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Session", json);
        editor.apply();
    }

    // Deleta sessão
    public void Logoff(Context context){
        prefs = context.getSharedPreferences("SessionLogin", context.MODE_PRIVATE);
        prefs.edit().remove("Session").commit();
    }

    // Verifica se já existe o e-mail cadastradi
    public boolean verificaEmail(String email){

        try {
            // verificar pq não está validando
            String emailUnico = "\"email\":" + "\""+email+"\"";

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ClienteService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ClienteService service = retrofit.create(ClienteService.class);
            Call<Boolean> response = service.verificaEmail(emailUnico);

            response.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    if (response.isSuccessful()) {
                        RETORNO = response.body();
                    }
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    Log.e(TAG, "Erro: " + t.getMessage());
                }
            });

        } catch (Exception e){
            Log.e(TAG, "Erro: " + e.getMessage());
        }

        return RETORNO;
    }

}
