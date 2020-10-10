package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 03.12.2016.
 */
public class Lesson36_ExpandableListEvents  extends     Activity
                                            implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private SimpleExpandableListAdapter adapter;
    private Lesson36_AdapterHelper ah;

    private ExpandableListView mMainELV;
    private TextView mInfoTV;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson36);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson36_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        mInfoTV = (TextView) findViewById(R.id.Lesson36_Info_TV);

        // создаем адаптер
        ah = new Lesson36_AdapterHelper(this);
        adapter = ah.getAdapter();

        mMainELV = (ExpandableListView) findViewById(R.id.Lesson35_Main_ELV);
        mMainELV.setAdapter(adapter);

        // нажатие на элемент
        mMainELV.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,   int childPosition, long id) {
                Log.d(LOG_TAG, "onChildClick groupPosition = " + groupPosition + " childPosition = " + childPosition + " id = " + id);

                mInfoTV.setText(ah.getGroupChildText(groupPosition, childPosition));
                return false;
            }
        });

        // нажатие на группу
        mMainELV.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Log.d(LOG_TAG, "onGroupClick groupPosition = " + groupPosition + " id = " + id);

                // блокируем дальнейшую обработку события для группы с позицией 1
                if (groupPosition == 1) return true;

                return false;
            }
        });

        // сворачивание группы
        mMainELV.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            public void onGroupCollapse(int groupPosition) {
                Log.d(LOG_TAG, "onGroupCollapse groupPosition = " + groupPosition);

                mInfoTV.setText("Свернули " + ah.getGroupText(groupPosition));
            }
        });

        // разворачивание группы
        mMainELV.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            public void onGroupExpand(int groupPosition) {
                Log.d(LOG_TAG, "onGroupExpand groupPosition = " + groupPosition);

                mInfoTV.setText("Развернули " + ah.getGroupText(groupPosition));
            }
        });

        // разворачиваем группу с позицией 2
        mMainELV.expandGroup(2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.Lesson36_ArrowBackWrapLL:
                startActivity(new Intent(Lesson36_ExpandableListEvents.this, Main_Activity.class));
                break;
        }
    }
}