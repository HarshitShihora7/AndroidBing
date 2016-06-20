package com.hshihora.androidbing;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView wView;
    private TextView startView;
    private TextView endView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wView= (WebView) findViewById(R.id.webView);
        wView.loadUrl("file:///android_asset/putPins.html");
        wView.clearCache(true);
        wView.clearHistory();
        wView.getSettings().setJavaScriptEnabled(true);
        wView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wView.addJavascriptInterface(new WebAppInterface(this),"Android");
        startView= (TextView) findViewById(R.id.StartLatLong);
        endView= (TextView) findViewById(R.id.EndLatLong);
    }

    public class WebAppInterface{
        Context mContext;

        WebAppInterface(Context c){
            mContext=c;
        }

        @JavascriptInterface
        public void showStartLatLong(final String cordi){
            //startView.setText(cordi);
            Toast.makeText(mContext,"showStartLatLong:"+cordi,Toast.LENGTH_SHORT).show();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    startView.setText(cordi);
                }
            });
        }

        @JavascriptInterface
        public void showEndLatLong(final String cordi){
            //endView.setText(cordi);
            Toast.makeText(mContext,"showEndLatLong:"+cordi,Toast.LENGTH_SHORT).show();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    endView.setText(cordi);
                }
            });
        }

    }

}
