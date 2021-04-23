package com.example.myapplication;

import android.webkit.WebViewClient;
import android.webkit.CookieManager;
import android.webkit.WebView;

public class MyWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        CookieManager.getInstance().setAcceptCookie(true);
        return true;
    }
}
