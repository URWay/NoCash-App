package com.example.luiz1.nocash;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.luiz1.nocash.SQL.DatabaseTransacao;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.TimeZone;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Bundle bundle;
    private TextView txtsaldomenu;
    private String TAG = "HOME";

    SweetAlertDialog load;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtsaldomenu = (TextView) findViewById(R.id.txtsaldo);

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarnav2);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //CARREGA HOME



        Fragment fragment = null;
        Class fragmentClass = null;

        fragmentClass = HomeFragment.class;


        getSupportActionBar().setTitle("Home");


        try {
            fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(bundle);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.corpo, fragment).commit();


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @Override
    protected void onStart() {
        super.onStart();

        try {

            // Verifica se o cliente não possuí uma carteira e cria
            Session session = new Session();
            session.verificaCarteira(HomeActivity.this);

            // Atualizar o valor da carteira
            Functions functions = new Functions();
            double saldo = functions.vSaldo(HomeActivity.this);

            NavigationView navigationView = findViewById(R.id.nav_view);
            View headerView = navigationView.getHeaderView(0);
            txtsaldomenu = headerView.findViewById(R.id.txtsaldo);
            txtsaldomenu.setText(new DecimalFormat("R$ ###,###,##0.00").format(saldo));

        } catch (Exception e){
            Log.e(TAG, "Erro na carteira:" + e.getMessage());
        }

        try {

            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Brazil/East"));
            int minuto = calendar.get(Calendar.SECOND);

            if(new Functions().atualizaMovimentos(HomeActivity.this, minuto)){
                // Carrega as transações
                Transacoes transacoes = new Transacoes();
                transacoes.Lista(this);
            }

        } catch (Exception e){
            Log.e(TAG, "Erro ao carregar a lista:" + e.getMessage());
        }

    }

    private void mostraHome(){
        fragment = new HomeFragment();

        if(fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.corpo, fragment).commit();

        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {

            if(fragment instanceof HomeFragment){
               super.onBackPressed(); // fecha o app

            }else{
                mostraHome();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    public void logout(){
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Logoff")
                .setContentText("Deseja fazer logoff?")
                .setConfirmText("Sim")
                .setCancelText("Não")

                // Função para confirmar
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {

                            // Logout - mata a sessão
                            Session session = new Session();
                            session.Logoff(HomeActivity.this);
                            session.SessionCarteiraDelete(HomeActivity.this);

                            //
                            DatabaseTransacao myDb = new DatabaseTransacao(HomeActivity.this);
                            myDb.deleteAllData();

                            sDialog.dismissWithAnimation();
                            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(i);
                            finish();

                        }
                    }
                )
                // Função para cancelar
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        getApplicationContext();
                        sDialog.cancel();
                        sDialog.dismissWithAnimation();
                    }
                })

                .show();

    }


    Fragment fragment = null;
    Class fragmentClass = null;
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        if(fragmentClass == FragmentHomeProduto.class){

            getSupportActionBar().setTitle("Produto");
        }

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            getSupportActionBar().setTitle("Home");

            fragmentClass = HomeFragment.class;

        } else if(id == R.id.nav_parcas){
            getSupportActionBar().setTitle("Parceiros");

            fragmentClass = ListaParceirosFragment.class;

        } else if (id == R.id.nav_promo) {

          getSupportActionBar().setTitle("Promoções");
        fragmentClass = FragmentPromocao.class;
        } else if (id == R.id.nav_recarga) {

            getSupportActionBar().setTitle("Recarga");
            fragmentClass = FragmentRecarga.class;

        } else if (id == R.id.nav_compra) {

            getSupportActionBar().setTitle("Comprar");
            fragmentClass = FragmentCompra.class;

        } else if (id == R.id.nav_transac) {

            getSupportActionBar().setTitle("Minhas Transações");
            fragmentClass = FragmentTransacao.class;
        } else if (id == R.id.nav_login) {
            getSupportActionBar().setTitle("Login");
            fragmentClass = HomeFragment.class;
            logout();
        }


        try {
            fragment = (Fragment) fragmentClass.newInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.corpo, fragment).commit();


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}