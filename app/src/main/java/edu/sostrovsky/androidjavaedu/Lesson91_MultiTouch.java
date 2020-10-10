package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 28.12.2016.
 */
public class Lesson91_MultiTouch    extends     Activity
                                    implements  View.OnClickListener,
                                                View.OnTouchListener {

    private LinearLayout arrowBackWrapLL;

    private final String LOG_TAG = "myLogs";

    private StringBuilder sb = new StringBuilder();

    private TextView mTV;

    private int upPI = 0;
    private int downPI = 0;

    private boolean inTouch = false;

    private String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson91);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson91_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        /////////////////////////////////////////////////////////////////////////////////

        mTV = (TextView) findViewById(R.id.Lesson91_TV);
        mTV.setOnTouchListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson91_ArrowBackWrapLL:
                startActivity(new Intent(Lesson91_MultiTouch.this, Main_Activity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        // для API 5 используются методы:

        // событие
        // actionMask = event.getAction() & MotionEvent.ACTION_MASK;
        // индекс касания
        // pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;


        // для API 8 и выше используются методы:

        // событие
        int actionMask = motionEvent.getActionMasked();

        // индекс касания
        int pointerIndex = motionEvent.getActionIndex();

        // число касаний
        int pointerCount = motionEvent.getPointerCount();

        switch (actionMask) {

            case MotionEvent.ACTION_DOWN:           // первое касание
                inTouch = true;
            case MotionEvent.ACTION_POINTER_DOWN:   // последующие касания
                downPI = pointerIndex;
                break;
            case MotionEvent.ACTION_UP:             // прерывание последнего касания
                inTouch = false;
                sb.setLength(0);
            case MotionEvent.ACTION_POINTER_UP:     // прерывания касаний
                upPI = pointerIndex;
                break;
            case MotionEvent.ACTION_MOVE:           // движение
                sb.setLength(0);

                for (int i = 0; i < 10; i++) {
                    sb.append("Index = " + i);

                    if (i < pointerCount) {
                        sb.append(", ID = " + motionEvent.getPointerId(i));
                        sb.append(", X = "  + motionEvent.getX(i));
                        sb.append(", Y = "  + motionEvent.getY(i));
                    } else {
                        sb.append(", ID = ");
                        sb.append(", X = ");
                        sb.append(", Y = ");
                    }
                    sb.append("\r\n");
                }
                break;
        }

        result = "down: " + downPI + "\n" + "up: " + upPI + "\n";

        if (inTouch) {
            result += "pointerCount = " + pointerCount + "\n" + sb.toString();
        }

        mTV.setText(result);

        return true;
    }
}