package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 31.12.2016.
 */
public class Lesson98_DialogFragment    extends Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private final String LOG_TAG = "myLogs";

    private Lesson98_Dialog1 mDialog1;
    private Lesson98_Dialog2 mDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson98);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson98_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ////////////////////////////////////////////////////////////////////////////////////

        mDialog1 = new Lesson98_Dialog1();
        mDialog2 = new Lesson98_Dialog2();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson98_ArrowBackWrapLL:
                startActivity(new Intent(Lesson98_DialogFragment.this, Main_Activity.class));
                break;
            case R.id.Lesson98_ShowDialog1_Btn:
                mDialog1.show(getFragmentManager(), "dlg1");
                break;
            case R.id.Lesson98_ShowDialog2_Btn:
                mDialog2.show(getFragmentManager(), "dlg2");
                break;
            default:
                break;
        }
    }
}