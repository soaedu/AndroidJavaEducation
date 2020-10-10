package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 19.11.2016.
 */
public class Lesson10_ContextMenu   extends     Activity
                                    implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private TextView mTextColorTV;
    private TextView mTextSizeTV;

    private final int MENU_COLOR_RED    = 1;
    private final int MENU_COLOR_GREEN  = 2;
    private final int MENU_COLOR_BLUE   = 3;

    private final int MENU_SIZE_22      = 4;
    private final int MENU_SIZE_26      = 5;
    private final int MENU_SIZE_30      = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson10);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson10_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        mTextColorTV    = (TextView) findViewById(R.id.Lesson10_Color_TV);
        registerForContextMenu(mTextColorTV);

        mTextSizeTV     = (TextView) findViewById(R.id.Lesson10_Size_TV);
        registerForContextMenu(mTextSizeTV);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson10_ArrowBackWrapLL:  startActivity(new Intent(Lesson10_ContextMenu.this, Main_Activity.class));
                                                break;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        switch(v.getId()) {

            case R.id.Lesson10_Color_TV:
                                        menu.add(0, MENU_COLOR_RED,     0, "Red");
                                        menu.add(0, MENU_COLOR_GREEN,   0, "Green");
                                        menu.add(0, MENU_COLOR_BLUE,    0, "Blue");
                                        break;
            case R.id.Lesson10_Size_TV:
                                        menu.add(0, MENU_SIZE_22,       0,  "22");
                                        menu.add(0, MENU_SIZE_26,       0,  "26");
                                        menu.add(0, MENU_SIZE_30,       0,  "30");
                                        break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch(item.getItemId()) {

            case MENU_COLOR_RED:
                                    mTextColorTV.setTextColor(Color.RED);
                                    mTextColorTV.setText("Text color = red");
                                    break;
            case MENU_COLOR_GREEN:
                                    mTextColorTV.setTextColor(Color.GREEN);
                                    mTextColorTV.setText("Text color = green");
                                    break;
            case MENU_COLOR_BLUE:
                                    mTextColorTV.setTextColor(Color.BLUE);
                                    mTextColorTV.setText("Text color = blue");
                                    break;
            case MENU_SIZE_22:
                                    mTextSizeTV.setTextSize(22);
                                    mTextSizeTV.setText("Text size = 22");
                                    break;
            case MENU_SIZE_26:
                                    mTextSizeTV.setTextSize(26);
                                    mTextSizeTV.setText("Text size = 26");
                                    break;
            case MENU_SIZE_30:
                                    mTextSizeTV.setTextSize(30);
                                    mTextSizeTV.setText("Text size = 30");
                                    break;
        }

        return super.onContextItemSelected(item);
    }
}