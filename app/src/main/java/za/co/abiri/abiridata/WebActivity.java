package za.co.abiri.abiridata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Shows data portal from website
 */
public class WebActivity extends AppCompatActivity {

    public WebView dataWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        // Initialise webview from xml file
        WebView myWebView = (WebView) findViewById(R.id.webdataview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl("https://abiri.sturtium.com/");


    }
}