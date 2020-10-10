package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 19.11.2016.
 */
public class Lesson14_SimpleCalculator  extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private EditText num1ET;
    private EditText num2ET;

    private Button addBTN;
    private Button subBTN;
    private Button multBTN;
    private Button divBTN;

    private TextView resultTV;

    private String oper = "";

    private final int MENU_RESET_ID = 1;
    private final int MENU_QUIT_ID  = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson14);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson14_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Lesson14_SimpleCalculator.this, Main_Activity.class));
            }
        });

        num1ET = (EditText) findViewById(R.id.Lesson14_Num1_ET);
        num2ET = (EditText) findViewById(R.id.Lesson14_Num2_ET);

        addBTN  = (Button) findViewById(R.id.Lesson14_Add_BTN);
        addBTN.setOnClickListener(this);

        subBTN  = (Button) findViewById(R.id.Lesson14_Sub_BTN);
        subBTN.setOnClickListener(this);

        multBTN = (Button) findViewById(R.id.Lesson14_Mult_BTN);
        multBTN.setOnClickListener(this);

        divBTN  = (Button) findViewById(R.id.Lesson14_Div_BTN);
        divBTN.setOnClickListener(this);

        resultTV = (TextView) findViewById(R.id.Lesson14_Result_TV);
    }

    @Override
    public void onClick(View view) {

        float num1   = 0;
        float num2   = 0;
        float result = 0;

        // Проверяем поля на пустоту
        if (TextUtils.isEmpty(num1ET.getText().toString()) || TextUtils.isEmpty(num2ET.getText().toString())) {
            return;
        }

        // читаем EditText и заполняем переменные числами
        num1 = Float.parseFloat(num1ET.getText().toString());
        num2 = Float.parseFloat(num2ET.getText().toString());

        // определяем нажатую кнопку и выполняем соответствующую операцию
        // в oper пишем операцию, потом будем использовать в выводе
        switch (view.getId()) {

            case R.id.Lesson14_Add_BTN:
                                        oper = "+";
                                        result = num1 + num2;
                                        break;
            case R.id.Lesson14_Sub_BTN:
                                        oper = "-";
                                        result = num1 - num2;
                                        break;
            case R.id.Lesson14_Mult_BTN:
                                        oper = "*";
                                        result = num1 * num2;
                                        break;
            case R.id.Lesson14_Div_BTN:
                                        oper = "/";
                                        result = num1 / num2;
                                        break;
            default:
                                        break;
        }

        // формируем строку вывода
        resultTV.setText(num1 + " " + oper + " " + num2 + " = " + result);
    }

    // создание меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, MENU_RESET_ID, 0, "Reset");
        menu.add(0, MENU_QUIT_ID, 0, "Quit");

        return super.onCreateOptionsMenu(menu);
    }

    // обработка нажатий на пункты меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case MENU_RESET_ID:
                                // очищаем поля
                                num1ET.setText("");
                                num2ET.setText("");
                                resultTV.setText("");
                                break;
            case MENU_QUIT_ID:
                                // выход из приложения
                                finish();
                                break;
        }

        return super.onOptionsItemSelected(item);
    }
}