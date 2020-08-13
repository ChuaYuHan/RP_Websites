package sg.edu.rp.c346.id19020844.rpwebsites;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {

    WebView wvPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_activity);

        wvPage = findViewById(R.id.webViewMyPage);

        Intent receiveIntent = getIntent();
        String url = receiveIntent.getStringExtra("url");

        wvPage.setWebViewClient(new WebViewClient());

        WebSettings wsPage = wvPage.getSettings();
        wsPage.setJavaScriptEnabled(true);
        wsPage.setBuiltInZoomControls(true);

        wvPage.loadUrl(url);

    }

}
