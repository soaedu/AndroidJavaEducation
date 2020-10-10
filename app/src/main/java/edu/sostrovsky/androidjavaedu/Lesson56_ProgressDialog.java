package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 16.12.2016.
 */
public class Lesson56_ProgressDialog    extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private Button mDefaultBtn;
    private Button mHorizontalBtn;

    private ProgressDialog pd;

    private Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson56);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson56_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        mDefaultBtn = (Button) findViewById(R.id.Lesson56_Default_Btn);
        mDefaultBtn.setOnClickListener(this);

        mHorizontalBtn = (Button) findViewById(R.id.Lesson56_Horizontal_Btn);
        mHorizontalBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson56_ArrowBackWrapLL:
                startActivity(new Intent(Lesson56_ProgressDialog.this, Main_Activity.class));
                break;
            case R.id.Lesson56_Default_Btn:
                pd = new ProgressDialog(this);
                pd.setTitle("Title");
                pd.setMessage("Message");

                // добавляем кнопку
                pd.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                pd.show();
                break;
            case R.id.Lesson56_Horizontal_Btn:
                pd = new ProgressDialog(this);
                pd.setTitle("Title");
                pd.setMessage("Message");

                // меняем стиль на индикатор
                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

                // устанавливаем максимум
                pd.setMax(2148);

                // включаем анимацию ожидания
                pd.setIndeterminate(true);

                pd.show();

                h = new Handler() {
                    public void handleMessage(Message msg) {
                        // выключаем анимацию ожидания
                        pd.setIndeterminate(false);

                        if (pd.getProgress() < pd.getMax()) {
                            // увеличиваем значения индикаторов
                            pd.incrementProgressBy(50);
                            pd.incrementSecondaryProgressBy(75);
                            h.sendEmptyMessageDelayed(0, 100);
                        } else {
                            pd.dismiss();
                        }
                    }
                };
                h.sendEmptyMessageDelayed(0, 2000);
                break;
            default:
                break;
        }
    }
}