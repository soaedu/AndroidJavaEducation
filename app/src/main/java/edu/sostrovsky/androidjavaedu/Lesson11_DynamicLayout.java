package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 19.11.2016.
 */
public class Lesson11_DynamicLayout extends Activity {

    // private LinearLayout arrowBackWrapLL;

    private float density;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        density = getResources().getDisplayMetrics().density;

        LayoutParams ww_LP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        ///////////////////////////////////////////////////////////////////////////////////

        // создаем корневой LinearLayout
        LinearLayout rootLayoutLL = new LinearLayout(this);

        // устанавливаем вертикальную ориентацию
        rootLayoutLL.setOrientation(LinearLayout.VERTICAL);

        // создаем LayoutParams
        LayoutParams linLayoutParam = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        ///////////////////////////////////////////////////////////////////////////////////

        // создаем навигационный LinearLayout
        LinearLayout navigationLL = new LinearLayout(this);
        navigationLL.setOrientation(LinearLayout.HORIZONTAL);
        navigationLL.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, ((int) (50 * density))));
        navigationLL.setPadding(5,5,5,5);
        navigationLL.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        LinearLayout imageWrapLL = new LinearLayout(this);
        imageWrapLL.setOrientation(LinearLayout.HORIZONTAL);
        imageWrapLL.setLayoutParams(new LayoutParams(((int) (70 * density)), LayoutParams.MATCH_PARENT, 0.0f));
        imageWrapLL.setGravity(Gravity.CENTER_VERTICAL);

        ImageView arrowBackIV = new ImageView(this);
        arrowBackIV.setBackgroundResource(R.drawable.arrow_back_icon);
        LayoutParams arrowBackLP = new LayoutParams(((int) (25 * density)), ((int) (25 * density)), 0.0f);
        arrowBackLP.setMargins(15,0,0,0);
        arrowBackLP.gravity = Gravity.CENTER_VERTICAL;
        arrowBackIV.setLayoutParams(arrowBackLP);

        imageWrapLL.addView(arrowBackIV);
        imageWrapLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Lesson11_DynamicLayout.this, Main_Activity.class));
            }
        });
        navigationLL.addView(imageWrapLL);

        TextView navigationTitleTV = new TextView(this);
        navigationTitleTV.setLayoutParams(ww_LP);
        navigationTitleTV.setTextSize(18.0f);
        navigationTitleTV.setTextColor(getResources().getColor(R.color.white));
        navigationTitleTV.setText(R.string.lesson11_dynamic_layout_title);
        navigationLL.addView(navigationTitleTV);

        View HStrut = new View(this);
        HStrut.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, ((int) (20 * density)), 1.0f));
        navigationLL.addView(HStrut);

        rootLayoutLL.addView(navigationLL);

        ///////////////////////////////////////////////////////////////////////////////////

        LinearLayout contentLL = new LinearLayout(this);
        contentLL.setOrientation(LinearLayout.VERTICAL);
        contentLL.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        contentLL.setPadding(16,16,16,16);

        TextView mTV = new TextView(this);
        mTV.setLayoutParams(ww_LP);
        mTV.setText("TextView");
        contentLL.addView(mTV);

        Button mBTN = new Button(this);
        mBTN.setText("Button");
        contentLL.addView(mBTN, ww_LP);

        Button m1BTN = new Button(this);
        m1BTN.setText("Button1");
        LinearLayout.LayoutParams leftMarginParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftMarginParams.leftMargin = 50;
        contentLL.addView(m1BTN, leftMarginParams);

        Button m2BTN = new Button(this);
        m2BTN.setText("Button2");
        LinearLayout.LayoutParams rightGravityParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rightGravityParams.gravity = Gravity.RIGHT;
        contentLL.addView(m2BTN, rightGravityParams);

        rootLayoutLL.addView(contentLL);

        ///////////////////////////////////////////////////////////////////////////////////

        // устанавливаем linLayout как корневой элемент экрана
        setContentView(rootLayoutLL, linLayoutParam);
    }
}