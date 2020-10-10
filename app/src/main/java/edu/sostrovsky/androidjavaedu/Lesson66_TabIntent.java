package edu.sostrovsky.androidjavaedu;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;

import com.sostrovsky.androidjavaedu.R;


/**
 * Created by OS1 on 18.12.2016.
 */
public class Lesson66_TabIntent extends     TabActivity
                                implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson66);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson66_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        // получаем TabHost
        TabHost tabHost = getTabHost();

        // инициализация была выполнена в getTabHost
        // метод setup вызывать не нужно

        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setIndicator(getResources().getString(R.string.tab1_text));
        tabSpec.setContent(new Intent(this, Lesson66_Activity1.class));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setIndicator(getResources().getString(R.string.tab2_text));
        tabSpec.setContent(new Intent(this, Lesson66_Activity2.class));
        tabHost.addTab(tabSpec);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson66_ArrowBackWrapLL:
                startActivity(new Intent(Lesson66_TabIntent.this, Main_Activity.class));
                break;
            default:
                break;
        }
    }
}