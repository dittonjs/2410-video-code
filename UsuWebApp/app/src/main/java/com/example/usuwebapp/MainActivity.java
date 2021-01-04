package com.example.usuwebapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout mainLayout = new LinearLayout(this);
        LinearLayout topStuffLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        AppCompatTextView textView = new AppCompatTextView(this);
        textView.setText("Hello, world");
        AppCompatButton button = new AppCompatButton(this);
        button.setText("Press me!");
        WebView webView = new WebView(this);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://usu.edu");

        topStuffLayout.addView(textView);
        topStuffLayout.addView(button);

        mainLayout.addView(topStuffLayout);
        mainLayout.addView(webView);
        setContentView(mainLayout);
    }
}