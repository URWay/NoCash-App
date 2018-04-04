package com.example.luiz1.nocash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class LinkedActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linked);
// View com site rodando no fundo
         webView = (WebView)findViewById(R.id.webviewnav);
         webView.setWebViewClient(new WebViewClient());
        String url = "http://www.google.com";
        webView.getSettings().getJavaScriptEnabled();
        webView.loadUrl(url);

// Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarnav);
        toolbar.setTitle("Cadastre-se Aqui!");
        toolbar.setNavigationIcon(R.drawable.ic_clear_white_24dp);

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

}
