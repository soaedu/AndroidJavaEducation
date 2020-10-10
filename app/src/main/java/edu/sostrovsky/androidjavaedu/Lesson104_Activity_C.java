package edu.sostrovsky.androidjavaedu;

import android.content.Intent;
import android.view.View;

/**
 * Created by sostrovschi on 1/4/17.
 */

public class Lesson104_Activity_C extends Lesson104_MngTasks1 {

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, Lesson104_Activity_D.class));
    }
}
