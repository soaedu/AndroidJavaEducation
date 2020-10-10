package edu.sostrovsky.androidjavaedu;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;


/**
 * Created by OS1 on 18.12.2016.
 */
public class Lesson67_TabContentFactory extends     TabActivity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private final String TABS_TAG_1 = "Tag 1";
    private final String TABS_TAG_2 = "Tag 2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson67);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson67_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        TabHost tabHost = getTabHost();

        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec(TABS_TAG_1);
        tabSpec.setContent(TabFactory);
        tabSpec.setIndicator(getResources().getString(R.string.tab1_text));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec(TABS_TAG_2);
        tabSpec.setContent(TabFactory);
        tabSpec.setIndicator(getResources().getString(R.string.tab2_text));
        tabHost.addTab(tabSpec);
    }

    TabHost.TabContentFactory TabFactory = new TabHost.TabContentFactory() {

        @Override
        public View createTabContent(String tag) {
            if (tag == TABS_TAG_1) {
                return getLayoutInflater().inflate(R.layout.tab_lesson67, null);
            } else if (tag == TABS_TAG_2) {
                TextView tv = new TextView(Lesson67_TabContentFactory.this);
                tv.setText(getResources().getString(R.string.lesson67_made_programmatically_text));
                return tv;
            }
            return null;
        }
    };

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson67_ArrowBackWrapLL:
                startActivity(new Intent(Lesson67_TabContentFactory.this, Main_Activity.class));
                break;
            default:
                break;
        }
    }
}