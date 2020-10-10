package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.Toast;

import com.sostrovsky.androidjavaedu.R;


/**
 * Created by OS1 on 18.12.2016.
 */
public class Lesson65_Tab   extends     Activity
                            implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson65);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson65_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);

        // инициализация
        tabHost.setup();

        TabHost.TabSpec tabSpec;

        ///////////////////////////////////////////////////////////////////////////////////////

        // создаем вкладку и указываем тег
        tabSpec = tabHost.newTabSpec("tag1");

        // название вкладки
        tabSpec.setIndicator(getResources().getString(R.string.tab1_text));

        // указываем id компонента из FrameLayout, он и станет содержимым
        tabSpec.setContent(R.id.Lesson65_Tab1_TV);

        // добавляем в корневой элемент
        tabHost.addTab(tabSpec);

        //////////////////////////////////////////////////////////////////////////////////////

        tabSpec = tabHost.newTabSpec("tag2");

        // указываем название и картинку
        // в нашем случае вместо картинки идет xml-файл,
        // который определяет картинку по состоянию вкладки
        tabSpec.setIndicator(getResources().getString(R.string.tab2_text), getResources().getDrawable(R.drawable.tab_icon_selector));
        tabSpec.setContent(R.id.Lesson65_Tab2_TV);
        tabHost.addTab(tabSpec);

        //////////////////////////////////////////////////////////////////////////////////////

        tabSpec = tabHost.newTabSpec("tag3");

        // создаем View из layout-файла
        View v = getLayoutInflater().inflate(R.layout.tab_header_lesson65, null);

        // и устанавливаем его, как заголовок
        tabSpec.setIndicator(v);
        tabSpec.setContent(R.id.Lesson65_Tab3_TV);
        tabHost.addTab(tabSpec);

        //////////////////////////////////////////////////////////////////////////////////////

        // вторая вкладка будет выбрана по умолчанию
        tabHost.setCurrentTabByTag("tag2");

        // обработчик переключения вкладок
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String tabId) {
                Toast.makeText(getBaseContext(), "tabId = " + tabId, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson65_ArrowBackWrapLL:
                startActivity(new Intent(Lesson65_Tab.this, Main_Activity.class));
                break;
            default:
                break;
        }
    }
}