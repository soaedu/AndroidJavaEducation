package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 28.12.2016.
 */
public class Lesson93_FragmentDynamic   extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private Lesson93_Fragment1 mFragment1;
    private Lesson93_Fragment2 mFragment2;
    private FragmentTransaction mFragmentTransaction;

    private CheckBox mBackStackChb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson93);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson93_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        //////////////////////////////////////////////////////////////////////////////////

        mFragment1 = new Lesson93_Fragment1();
        mFragment2 = new Lesson93_Fragment2();

        mBackStackChb = (CheckBox)findViewById(R.id.Lesson93_BackStack_Chb);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson93_ArrowBackWrapLL:
                startActivity(new Intent(Lesson93_FragmentDynamic.this, Main_Activity.class));
                break;
            case R.id.Lesson93_Add_Btn:
                mFragmentTransaction = getFragmentManager().beginTransaction();
                mFragmentTransaction.add(R.id.Lesson93_FragmentContainer, mFragment1);

                if (mBackStackChb.isChecked())
                    mFragmentTransaction.addToBackStack(null);

                mFragmentTransaction.commit();
                break;
            case R.id.Lesson93_Remove_Btn:
                mFragmentTransaction = getFragmentManager().beginTransaction();
                mFragmentTransaction.remove(mFragment1);

                if (mBackStackChb.isChecked())
                    mFragmentTransaction.addToBackStack(null);

                mFragmentTransaction.commit();
                break;
            case R.id.Lesson93_Replace_Btn:
                mFragmentTransaction = getFragmentManager().beginTransaction();
                mFragmentTransaction.replace(R.id.Lesson93_FragmentContainer, mFragment2);

                if (mBackStackChb.isChecked())
                    mFragmentTransaction.addToBackStack(null);

                mFragmentTransaction.commit();
                break;
            default:
                break;
        }
    }

}