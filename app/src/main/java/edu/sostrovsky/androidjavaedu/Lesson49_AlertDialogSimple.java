package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 14.12.2016.
 */
public class Lesson49_AlertDialogSimple extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private Button mExitBtn;

    private final int DIALOG_EXIT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson49);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson49_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        mExitBtn = (Button) findViewById(R.id.Lesson49_Exit_BTN);
        mExitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.Lesson49_ArrowBackWrapLL:
                startActivity(new Intent(Lesson49_AlertDialogSimple.this, Main_Activity.class));
                break;
            case R.id.Lesson49_Exit_BTN:
                // вызываем диалог
                showDialog(DIALOG_EXIT);
                break;
        }
    }

    public void onBackPressed() {
        // вызываем диалог
        showDialog(DIALOG_EXIT);
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_EXIT) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            // заголовок
            adb.setTitle(R.string.exit_text);
            // сообщение
            adb.setMessage(R.string.lesson49_save_data_text);
            // иконка
            adb.setIcon(android.R.drawable.ic_dialog_info);
            // кнопка положительного ответа
            adb.setPositiveButton(R.string.yes_text, myClickListener);
            // кнопка отрицательного ответа
            adb.setNegativeButton(R.string.no_text, myClickListener);
            // кнопка нейтрального ответа
            adb.setNeutralButton(R.string.cancel_text, myClickListener);
            // не будет закрываться при нажатии на кнопку "Назад"
            adb.setCancelable(false);
            // создаем диалог
            return adb.create();
        }
        return super.onCreateDialog(id);
    }

    DialogInterface.OnClickListener myClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                // положительная кнопка
                case Dialog.BUTTON_POSITIVE:
                    saveData();
                    finish();
                    break;
                // негативная кнопка
                case Dialog.BUTTON_NEGATIVE:
                    finish();
                    break;
                // нейтральная кнопка
                case Dialog.BUTTON_NEUTRAL:
                    break;
            }
        }
    };

    void saveData() {
        Toast.makeText(this, R.string.lesson49_saved_text, Toast.LENGTH_SHORT).show();
    }
}