package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 19.11.2016.
 */
public class Lesson12_DynamicLayout2    extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private LinearLayout mainLL;

    private RadioGroup gravityRG;

    private EditText nameET;
    private Button createBTN;
    private Button clearBTN;

    private int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson12);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson12_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        mainLL      = (LinearLayout) findViewById(R.id.Lesson12_Main_LL);
        gravityRG   = (RadioGroup) findViewById(R.id.Lesson12_Gravity_RG);
        nameET      = (EditText) findViewById(R.id.Lesson12_Name_ET);

        createBTN = (Button) findViewById(R.id.Lesson12_Create_BTN);
        createBTN.setOnClickListener(this);

        clearBTN = (Button) findViewById(R.id.Lesson12_Clear_BTN);
        clearBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson12_ArrowBackWrapLL: startActivity(new Intent(Lesson12_DynamicLayout2.this, Main_Activity.class));
                                                break;
            case R.id.Lesson12_Create_BTN:
                                            // Создание LayoutParams c шириной и высотой по содержимому
                                            LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(wrapContent, wrapContent);

                                            // переменная для хранения значения выравнивания
                                            // по умолчанию пусть будет LEFT
                                            int btnGravity = Gravity.LEFT;

                                            // определяем, какой RadioButton "чекнут" и
                                            // соответственно заполняем btnGravity
                                            switch (gravityRG.getCheckedRadioButtonId()) {

                                                case R.id.Lesson12_Left_RB:
                                                                                btnGravity = Gravity.LEFT;
                                                                                break;
                                                case R.id.Lesson12_Center_RB:
                                                                                btnGravity = Gravity.CENTER_HORIZONTAL;
                                                                                break;
                                                case R.id.Lesson12_Right_RB:
                                                                                btnGravity = Gravity.RIGHT;
                                                                                break;
                                            }

                                            // переносим полученное значение выравнивания в LayoutParams
                                            lParams.gravity = btnGravity;

                                            // создаем Button, пишем текст и добавляем в LinearLayout
                                            Button btnNew = new Button(this);
                                            btnNew.setText(nameET.getText().toString());

                                            mainLL.addView(btnNew, lParams);

                                            break;

            case R.id.Lesson12_Clear_BTN:
                                            mainLL.removeAllViews();
                                            Toast.makeText(this, "Удалено", Toast.LENGTH_SHORT).show();
                                            break;
        }
    }


}