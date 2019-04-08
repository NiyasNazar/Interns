package remind.edu.myapplication.Sub_details;

import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.joanzapata.pdfview.PDFView;

import java.util.ArrayList;

import es.voghdev.pdfviewpager.library.PDFViewPager;
import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.BasePDFPagerAdapter;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;
import remind.edu.myapplication.R;
import remind.edu.mylibrary.VigerPDF;
import remind.edu.mylibrary.adapter.VigerAdapter;
import remind.edu.mylibrary.manage.OnResultListener;

public class View_DOCS extends AppCompatActivity {
    LinearLayout root;
    RemotePDFViewPager remotePDFViewPager;
PDFView pdfView;
    BasePDFPagerAdapter adapter;
    WebView webview;
    String pdfurl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__docs);
        pdfurl=getIntent().getStringExtra("urlpdf");
        Log.i("url",pdfurl);

        webview = (WebView) findViewById(R.id.webView1);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                webview.loadUrl("javascript:(function() { " +
                        "document.querySelector('[role=\"toolbar\"]').remove();})()");
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webview.loadUrl("javascript:(function() { " +
                        "document.querySelector('[role=\"toolbar\"]').remove();})()");
            }
        });
        String your_pdf_link="https://www.antennahouse.com/XSLsample/pdf/sample-link_1.pdf";
        webview.loadUrl("https://docs.google.com/viewer?embedded=true&url=" + pdfurl);







      /*  webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("https://docs.google.com/viewer?embedded=true&url=http://www.runfreeordie.com/right_to_know.pdf");
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });*/



        final String url = "http://www.cals.uidaho.edu/edComm/curricula/CustRel_curriculum/content/sample.pdf";


    }



}
