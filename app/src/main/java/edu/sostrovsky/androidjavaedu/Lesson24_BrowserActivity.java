package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 19.11.2016.
 */
public class Lesson24_BrowserActivity   extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson24_browser_activity);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson24_BrowserActivity_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        StringBuilder sb = new StringBuilder("");
        sb.append(getResources().getString(R.string.lesson24_simple_browser_title));
        sb.append(" -> ");
        sb.append("Browser Activity");

        TextView title = (TextView) findViewById(R.id.Lesson24_BrowserActivityTitle_TV);
        title.setText(sb.toString());

        /////////////////////////////////////////////////////////////////////////////////////

        WebView webView = (WebView) findViewById(R.id.Lesson24_WebView);

        Uri data = getIntent().getData();
        webView.loadUrl(data.toString());
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson24_BrowserActivity_ArrowBackWrapLL:     startActivity(new Intent(Lesson24_BrowserActivity.this, Lesson24_SimpleBrowser.class));
                                                                    break;
        }
    }
}