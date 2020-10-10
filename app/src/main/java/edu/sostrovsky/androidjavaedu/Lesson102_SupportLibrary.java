package edu.sostrovsky.androidjavaedu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 02.01.2017.
 */
public class Lesson102_SupportLibrary extends FragmentActivity
        implements View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson102);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson102_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        /////////////////////////////////////////////////////////////////////////////////////

        Lesson102_Fragment mFragment = new Lesson102_Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.Lesson102_Container_FL, mFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lesson101, menu);
        return true;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson102_ArrowBackWrapLL:
                startActivity(new Intent(Lesson102_SupportLibrary.this, Main_Activity.class));
                break;
            default:
                break;
        }
    }
}