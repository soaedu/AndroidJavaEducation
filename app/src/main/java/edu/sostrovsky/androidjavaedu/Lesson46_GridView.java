package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 14.12.2016.
 */
public class Lesson46_GridView  extends     Activity
                                implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private String[] data = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};

    private GridView mMainGV;

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson46);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson46_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        adapter = new ArrayAdapter<String>(this, R.layout.grid_item_lesson46, R.id.Lesson46_Text_TV, data);

        mMainGV = (GridView) findViewById(R.id.Lesson46_Main_GV);
        mMainGV.setAdapter(adapter);

        adjustGridView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.Lesson46_ArrowBackWrapLL:
                startActivity(new Intent(Lesson46_GridView.this, Main_Activity.class));
                break;

        }
    }

    private void adjustGridView() {

        // mMainGV.setNumColumns(3);

        mMainGV.setNumColumns(GridView.AUTO_FIT);
        // mMainGV.setColumnWidth(50);

        mMainGV.setColumnWidth(80);
        mMainGV.setVerticalSpacing(5);
        mMainGV.setHorizontalSpacing(5);

        // mMainGV.setStretchMode(GridView.NO_STRETCH);
        // mMainGV.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        // mMainGV.setStretchMode(GridView.STRETCH_SPACING);
        mMainGV.setStretchMode(GridView.STRETCH_SPACING_UNIFORM);
    }
}