package com.fabricai.market;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends Activity {

    private static final String TAG = "main activity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                           @Override
                                           public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                               if (!task.isSuccessful()) {
                                                   Log.w(TAG, "getInstanceId failed", task.getException());
                                                   return;
                                               }
                                               String token = task.getResult().getToken();

                                               // Log and toast
                                               String msg = getString(R.string.msg_token_fmt, token);
                                               Log.d(TAG, msg);
                                           }

                                       });


        WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings= myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        myWebView.setWebViewClient(new WebViewClient()
        {
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return false;
            }
        });

        myWebView.loadUrl("http://35.231.173.150/market");
    }
}
