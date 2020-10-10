package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 19.11.2016.
 */
public class Lesson15_SimpleAnimation   extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    // константы для ID пунктов меню
    private final int MENU_ALPHA_ID = 1;
    private final int MENU_SCALE_ID = 2;
    private final int MENU_TRANSLATE_ID = 3;
    private final int MENU_ROTATE_ID = 4;
    private final int MENU_COMBO_ID = 5;

    private TextView mTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson15);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson15_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        mTV = (TextView) findViewById(R.id.Lesson15_TV);

        // регистрируем контекстное меню для компонента mTV
        registerForContextMenu(mTV);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.Lesson15_ArrowBackWrapLL: startActivity(new Intent(Lesson15_SimpleAnimation.this, Main_Activity.class));
                                                break;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        // TODO Auto-generated method stub
        switch (v.getId()) {

            case R.id.Lesson15_TV:
                                    // добавляем пункты
                                    menu.add(0, MENU_ALPHA_ID,      0, "alpha");
                                    menu.add(0, MENU_SCALE_ID,      0, "scale");
                                    menu.add(0, MENU_TRANSLATE_ID,  0, "translate");
                                    menu.add(0, MENU_ROTATE_ID,     0, "rotate");
                                    menu.add(0, MENU_COMBO_ID,      0, "combo");
                                    break;
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        Animation anim = null;

        // определяем какой пункт был нажат
        switch (item.getItemId()) {
            case MENU_ALPHA_ID:
                                    // создаем объект анимации из файла anim/myalpha
                                    anim = AnimationUtils.loadAnimation(this, R.anim.lesson15_myalpha);
                                    break;
            case MENU_SCALE_ID:
                                    anim = AnimationUtils.loadAnimation(this, R.anim.lesson15_myscale);
                                    break;
            case MENU_TRANSLATE_ID:
                                    anim = AnimationUtils.loadAnimation(this, R.anim.lesson15_mytrans);
                                    break;
            case MENU_ROTATE_ID:
                                    anim = AnimationUtils.loadAnimation(this, R.anim.lesson15_myrotate);
                                    break;
            case MENU_COMBO_ID:
                                    anim = AnimationUtils.loadAnimation(this, R.anim.lesson15_mycombo);
                                    break;
        }

        // запускаем анимацию для компонента tv
        mTV.startAnimation(anim);

        return super.onContextItemSelected(item);
    }
}