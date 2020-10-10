package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HeaderViewListAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 13.12.2016.
 */
public class Lesson44_HeaderFooter  extends     Activity
                                    implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private final String LOG_TAG = "myLogs";

    private String[] data = {"one", "two", "three", "four", "five"};

    private  ListView lvMain;

    private ArrayAdapter<String> adapter;

    private View header1;
    private View header2;

    private View footer1;
    private View footer2;

    private Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson44);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson44_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        lvMain = (ListView) findViewById(R.id.Lesson44_Main_LV);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);

        btnTest = (Button) findViewById(R.id.Lesson44_BTN);
        btnTest.setOnClickListener(this);

        // создаем Header и Footer
        header1 = createHeader("header 1");
        header2 = createHeader("header 2");
        footer1 = createFooter("footer 1");
        footer2 = createFooter("footer 2");

        fillList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.Lesson44_ArrowBackWrapLL:
                startActivity(new Intent(Lesson44_HeaderFooter.this, Main_Activity.class));
                break;
            case R.id.Lesson44_BTN:
//                lvMain.removeHeaderView(header2);
//                lvMain.removeFooterView(footer2);

                Object obj;
                HeaderViewListAdapter hvlAdapter = (HeaderViewListAdapter) lvMain.getAdapter();
                obj = hvlAdapter.getItem(1);
                Log.d(LOG_TAG, "hvlAdapter.getItem(1) = " + obj.toString());
                obj = hvlAdapter.getItem(4);
                Log.d(LOG_TAG, "hvlAdapter.getItem(4) = " + obj.toString());

                ArrayAdapter<String> alAdapter = (ArrayAdapter<String>) hvlAdapter.getWrappedAdapter();
                obj = alAdapter.getItem(1);
                Log.d(LOG_TAG, "alAdapter.getItem(1) = " + obj.toString());
                obj = alAdapter.getItem(4);
                Log.d(LOG_TAG, "alAdapter.getItem(4) = " + obj.toString());

                break;
        }
    }

    // формирование списка
//    void fillList() {
//        try {
//            lvMain.setAdapter(adapter);
//            lvMain.addHeaderView(header1);
//        } catch (Exception ex) {
//            Log.e(LOG_TAG, ex.getMessage());
//        }
//    }

    // формирование списка
    void fillList() {
        lvMain.addHeaderView(header1);
        lvMain.addHeaderView(header2, "some text for header 2", false);
        lvMain.addFooterView(footer1);
        lvMain.addFooterView(footer2, "some text for footer 2", false);
        lvMain.setAdapter(adapter);
    }

    // создание Header
    View createHeader(String text) {
        View v = getLayoutInflater().inflate(R.layout.header_lesson44, null);
        ((TextView)v.findViewById(R.id.Lesson44_HeaderText_TV)).setText(text);
        return v;
    }

    // создание Footer
    View createFooter(String text) {
        View v = getLayoutInflater().inflate(R.layout.footer_lesson44, null);
        ((TextView)v.findViewById(R.id.Lesson44_FooterText_TV)).setText(text);
        return v;
    }
}