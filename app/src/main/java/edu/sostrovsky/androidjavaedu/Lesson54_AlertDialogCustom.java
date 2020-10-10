package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by OS1 on 15.12.2016.
 */
public class Lesson54_AlertDialogCustom extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private final int DIALOG = 1;

    private int btn;

    private LinearLayout view;

    private Button mAddBtn;
    private Button mDeleteBtn;

    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    private TextView tvCount;

    private ArrayList<TextView> textViewsArrList;

    private final static String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson54);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson54_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        textViewsArrList = new ArrayList<TextView>(10);

        mAddBtn = (Button) findViewById(R.id.Lesson54_Add_Btn);
        mAddBtn.setOnClickListener(this);

        mDeleteBtn = (Button) findViewById(R.id.Lesson54_Delete_Btn);
        mDeleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        btn = view.getId();

        switch (view.getId()) {
            case R.id.Lesson54_ArrowBackWrapLL:
                startActivity(new Intent(Lesson54_AlertDialogCustom.this, Main_Activity.class));
                break;
            case R.id.Lesson54_Add_Btn:
            case R.id.Lesson54_Delete_Btn:
                showDialog(DIALOG);
                break;
            default:
                break;
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Custom dialog");

        // создаем view из dialog.xml
        view = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_lesson54, null);

        // устанавливаем ее, как содержимое тела диалога
        adb.setView(view);

        // находим TexView для отображения кол-ва
        tvCount = (TextView) view.findViewById(R.id.Lesson54_Count_TV);
        return adb.create();
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        if (id == DIALOG) {

            // Находим TextView для отображения времени и показываем текущее время
            TextView tvTime = (TextView) dialog.getWindow().findViewById(R.id.Lesson54_Time_TV);

            tvTime.setText(sdf.format(new Date(System.currentTimeMillis())));

            // если была нажата кнопка Добавить
            if (btn == R.id.Lesson54_Add_Btn) {
                // создаем новое TextView, добавляем в диалог, указываем текст
                TextView tv = new TextView(this);

                view.addView(tv, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                tv.setText("TextView " + (textViewsArrList.size() + 1));

                // добавляем новое TextView в коллекцию
                textViewsArrList.add(tv);

              // иначе
            } else {
                // если коллекция созданных TextView непуста
                if (textViewsArrList.size() > 0) {

                    // находим в коллекции последний TextView
                    TextView tv = textViewsArrList.get(textViewsArrList.size() - 1);

                    // удаляем из диалога
                    view.removeView(tv);

                    // удаляем из коллекции
                    textViewsArrList.remove(tv);
                }
            }

            // обновляем счетчик
            tvCount.setText("Кол-во TextView = " + textViewsArrList.size());
        }
    }
}