package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.sostrovsky.androidjavaedu.R;

import java.util.ArrayList;

/**
 * Created by OS1 on 13.12.2016.
 */
public class Lesson43_CustomAdapter extends     Activity
                                    implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private ArrayList<Lesson43_Product> products = new ArrayList<Lesson43_Product>();
    private Lesson43_BoxAdapter boxAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson43);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson43_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        // создаем адаптер
        fillData();
        boxAdapter = new Lesson43_BoxAdapter(this, products);

        // настраиваем список
        ListView lvMain = (ListView) findViewById(R.id.Lesson43_Main_LV);
        lvMain.setAdapter(boxAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.Lesson43_ArrowBackWrapLL:
                startActivity(new Intent(Lesson43_CustomAdapter.this, Main_Activity.class));
                break;
        }
    }

    // генерируем данные для адаптера
    void fillData() {
        for (int i = 1; i <= 20; i++) {
            products.add(new Lesson43_Product("Product " + i, i * 1000, R.mipmap.ic_launcher, false));
        }
    }

    // выводим информацию о корзине
    public void showResult(View v) {
        String result = getResources().getString(R.string.lesson43_products_in_box_text);

        for (Lesson43_Product p : boxAdapter.getBox()) {
            if (p.isBox())
                result += "\n" + p.getName();
        }

        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }
}