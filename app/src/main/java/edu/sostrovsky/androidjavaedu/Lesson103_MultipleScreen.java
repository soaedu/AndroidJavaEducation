package edu.sostrovsky.androidjavaedu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 02.01.2017.
 */
public class Lesson103_MultipleScreen extends FragmentActivity
        implements View.OnClickListener,
        Lesson103_TitlesFragment.onItemClickListener {

    private LinearLayout arrowBackWrapLL;

    private int position = 0;

    private boolean withDetails = true;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson103);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson103_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        /////////////////////////////////////////////////////////////////////////////////////

        if (savedInstanceState != null)
            position = savedInstanceState.getInt("position");

//        showDetails(position);

        withDetails = (findViewById(R.id.Lesson103_Container_FL) != null);

        if (withDetails)
            showDetails(position);
    }

    void showDetails(int pos) {
//        Lesson103_DetailsFragment details = (Lesson103_DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.Lesson103_Container_FL);
//
//        if (details == null || details.getPosition() != pos) {
//            details = Lesson103_DetailsFragment.newInstance(pos);
//            getSupportFragmentManager().beginTransaction().replace(R.id.Lesson103_Container_FL, details).commit();
//        }

        if (withDetails) {
            Log.d(LOG_TAG, "Lesson103_MultipleScreen");

            Lesson103_DetailsFragment details = (Lesson103_DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.Lesson103_Container_FL);

            if (details == null || details.getPosition() != pos) {
                details = Lesson103_DetailsFragment.newInstance(pos);
                getSupportFragmentManager().beginTransaction().replace(R.id.Lesson103_Container_FL, details).commit();
            }
        } else {
            Log.d(LOG_TAG, "Lesson103_DetailsActivity");

            startActivity(new Intent(this, Lesson103_DetailsActivity.class).putExtra("position", position));
        }
    }

    @Override
    public void itemClick(int position) {
        this.position = position;
        showDetails(position);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", position);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson103_ArrowBackWrapLL:
                startActivity(new Intent(Lesson103_MultipleScreen.this, Main_Activity.class));
                break;
            default:
                break;
        }
    }
}