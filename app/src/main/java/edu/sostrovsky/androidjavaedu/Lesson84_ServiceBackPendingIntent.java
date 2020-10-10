package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 22.12.2016.
 */
public class Lesson84_ServiceBackPendingIntent  extends     Activity
                                                implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private final int TASK1_CODE = 1;
    private final int TASK2_CODE = 2;
    private final int TASK3_CODE = 3;

    public final static int STATUS_START = 100;
    public final static int STATUS_FINISH = 200;

    public final static String PARAM_TIME = "time";
    public final static String PARAM_PINTENT = "pendingIntent";
    public final static String PARAM_RESULT = "result";

    private TextView mTask1TV;
    private TextView mTask2TV;
    private TextView mTask3TV;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson84);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson84_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        /////////////////////////////////////////////////////////////////////////////////

        mTask1TV = (TextView) findViewById(R.id.Lesson84_Task1_TV);
        mTask1TV.setText("Task1");

        mTask2TV = (TextView) findViewById(R.id.Lesson84_Task2_TV);
        mTask2TV.setText("Task2");

        mTask3TV = (TextView) findViewById(R.id.Lesson84_Task3_TV);
        mTask3TV.setText("Task3");
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson84_ArrowBackWrapLL:
                startActivity(new Intent(Lesson84_ServiceBackPendingIntent.this, Main_Activity.class));
                break;
            case R.id.Lesson84_Start_Btn:

                PendingIntent pIntent;
                Intent intent;

                // Создаем PendingIntent для Task1
                pIntent = createPendingResult(TASK1_CODE, new Intent(), 0);
                // Создаем Intent для вызова сервиса, кладем туда параметр времени
                // и созданный PendingIntent
                intent = new Intent(this, Lesson84_MyService.class)
                                .putExtra(PARAM_TIME, 7)
                                .putExtra(PARAM_PINTENT, pIntent);
                // стартуем сервис
                startService(intent);

                pIntent = createPendingResult(TASK2_CODE, new Intent(), 0);
                intent = new Intent(this, Lesson84_MyService.class)
                                .putExtra(PARAM_TIME, 4)
                                .putExtra(PARAM_PINTENT, pIntent);
                startService(intent);

                pIntent = createPendingResult(TASK3_CODE, new Intent(), 0);
                intent = new Intent(this, Lesson84_MyService.class)
                                .putExtra(PARAM_TIME, 6)
                                .putExtra(PARAM_PINTENT, pIntent);
                startService(intent);

                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(LOG_TAG, "requestCode = " + requestCode + ", resultCode = "
                + resultCode);

        // Ловим сообщения о старте задач
        if (resultCode == STATUS_START) {
            switch (requestCode) {
                case TASK1_CODE:
                    mTask1TV.setText("Task1 start");
                    break;
                case TASK2_CODE:
                    mTask2TV.setText("Task2 start");
                    break;
                case TASK3_CODE:
                    mTask3TV.setText("Task3 start");
                    break;
            }
        }

        // Ловим сообщения об окончании задач
        if (resultCode == STATUS_FINISH) {
            int result = data.getIntExtra(PARAM_RESULT, 0);
            switch (requestCode) {
                case TASK1_CODE:
                    mTask1TV.setText("Task1 finish, result = " + result);
                    break;
                case TASK2_CODE:
                    mTask2TV.setText("Task2 finish, result = " + result);
                    break;
                case TASK3_CODE:
                    mTask3TV.setText("Task3 finish, result = " + result);
                    break;
            }
        }
    }
}