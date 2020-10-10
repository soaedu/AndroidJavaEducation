package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 23.12.2016.
 */
public class Lesson85_ServiceBackBroadcast  extends     Activity
                                            implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private final int TASK1_CODE = 1;
    private final int TASK2_CODE = 2;
    private final int TASK3_CODE = 3;

    public final static int STATUS_START = 100;
    public final static int STATUS_FINISH = 200;

    public final static String PARAM_TIME = "time";
    public final static String PARAM_TASK = "task";
    public final static String PARAM_RESULT = "result";
    public final static String PARAM_STATUS = "status";

    public final static String BROADCAST_ACTION = "com.example.os1.start_android_education.lesson85_servicebackbroadcast";

    private TextView mTask1TV;
    private TextView mTask2TV;
    private TextView mTask3TV;

    private BroadcastReceiver broadcastReceiver;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson85);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson85_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        /////////////////////////////////////////////////////////////////////////////////

        mTask1TV = (TextView) findViewById(R.id.Lesson85_Task1_TV);
        mTask1TV.setText("Task1");

        mTask2TV = (TextView) findViewById(R.id.Lesson85_Task2_TV);
        mTask2TV.setText("Task2");

        mTask3TV = (TextView) findViewById(R.id.Lesson85_Task3_TV);
        mTask3TV.setText("Task3");

        // создаем BroadcastReceiver
        broadcastReceiver = new BroadcastReceiver() {

            // действия при получении сообщений
            public void onReceive(Context context, Intent intent) {
                int task = intent.getIntExtra(PARAM_TASK, 0);
                int status = intent.getIntExtra(PARAM_STATUS, 0);

                Log.d(LOG_TAG, "onReceive: task = " + task + ", status = " + status);

                // Ловим сообщения о старте задач
                if (status  == STATUS_START) {
                    switch (task) {
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
                if (status == STATUS_FINISH) {
                    int result = intent.getIntExtra(PARAM_RESULT, 0);
                    switch (task) {
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
        };

        // создаем фильтр для BroadcastReceiver
        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);

        // регистрируем (включаем) BroadcastReceiver
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // дерегистрируем (выключаем) BroadcastReceiver
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson85_ArrowBackWrapLL:
                startActivity(new Intent(Lesson85_ServiceBackBroadcast.this, Main_Activity.class));
                break;
            case R.id.Lesson85_Start_Btn:

                Intent intent;

                // Создаем Intent для вызова сервиса,
                // кладем туда параметр времени и код задачи
                intent = new Intent(this, Lesson85_MyService.class)
                        .putExtra(PARAM_TIME, 7)
                        .putExtra(PARAM_TASK, TASK1_CODE);
                // стартуем сервис
                startService(intent);

                intent = new Intent(this, Lesson85_MyService.class)
                        .putExtra(PARAM_TIME, 4)
                        .putExtra(PARAM_TASK, TASK2_CODE);
                startService(intent);

                intent = new Intent(this, Lesson85_MyService.class)
                        .putExtra(PARAM_TIME, 6)
                        .putExtra(PARAM_TASK, TASK3_CODE);
                startService(intent);
                break;
            default:
                break;
        }
    }
}