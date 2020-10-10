package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 29.12.2016.
 */
public class Lesson94_FragmentActivity  extends     Activity
                                        implements  View.OnClickListener,
                                                    Lesson94_Fragment2.onSomeEventListener {

    private LinearLayout arrowBackWrapLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson94);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson94_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        //////////////////////////////////////////////////////////////////////////////////

        Lesson94_Fragment2 mFragment2 = new Lesson94_Fragment2();

        FragmentTransaction mFragmentTransaction = getFragmentManager().beginTransaction();
        mFragmentTransaction.add(R.id.Lesson94_Fragment2_FL, mFragment2);
        mFragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson94_ArrowBackWrapLL:
                startActivity(new Intent(Lesson94_FragmentActivity.this, Main_Activity.class));
                break;
            case R.id.Lesson94_Find_Btn:
                Fragment mFragment1 = getFragmentManager().findFragmentById(R.id.Lesson94_Fragment1);
                ((TextView) mFragment1.getView().findViewById(R.id.Lesson94_Fragment1_TV)).setText("Access to Fragment 1 from Activity");

                Fragment mFragment2 = getFragmentManager().findFragmentById(R.id.Lesson94_Fragment2_FL);
                ((TextView) mFragment2.getView().findViewById(R.id.Lesson94_Fragment2_TV)).setText("Access to Fragment 2 from Activity");
                break;
            default:
                break;
        }
    }

    @Override
    public void someEvent(String str) {
        Fragment mFragment1 = getFragmentManager().findFragmentById(R.id.Lesson94_Fragment1);
        ((TextView) mFragment1.getView().findViewById(R.id.Lesson94_Fragment1_TV)).setText("Text from Fragment 2:" + str);
    }
}