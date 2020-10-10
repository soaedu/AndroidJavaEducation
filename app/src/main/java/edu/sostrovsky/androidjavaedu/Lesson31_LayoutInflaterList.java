package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 03.12.2016.
 */
public class Lesson31_LayoutInflaterList    extends     Activity
                                            implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private String[] name = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис", "Костя", "Игорь" };
    private String[] position = { "Программер", "Бухгалтер", "Программер", "Программер", "Бухгалтер", "Директор", "Программер", "Охранник" };

    private final String LOG_TAG = "myLogs";

    private int salary[] = { 13000, 10000, 13000, 13000, 10000, 15000, 13000, 8000 };
    private int[] colors = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson31);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson31_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        colors[0] = Color.parseColor("#559966CC");
        colors[1] = Color.parseColor("#55336699");

        LinearLayout linLayout = (LinearLayout) findViewById(R.id.Lesson31_Layout_LL);

        LayoutInflater ltInflater = getLayoutInflater();

        for (int i = 0; i < name.length; i++) {

            Log.d(LOG_TAG, "i = " + i);

            View item = ltInflater.inflate(R.layout.list_item_lesson31, linLayout, false);

            TextView tvName = (TextView) item.findViewById(R.id.Lesson31_Name_TV);
            tvName.setText(name[i]);

            TextView tvPosition = (TextView) item.findViewById(R.id.Lesson31_Position_TV);
            tvPosition.setText("Должность: " + position[i]);

            TextView tvSalary = (TextView) item.findViewById(R.id.Lesson31_Salary_TV);
            tvSalary.setText("Оклад: " + String.valueOf(salary[i]));

            item.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            item.setBackgroundColor(colors[i % 2]);

            linLayout.addView(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.Lesson31_ArrowBackWrapLL:
                startActivity(new Intent(Lesson31_LayoutInflaterList.this, Main_Activity.class));
                break;
        }
    }
}