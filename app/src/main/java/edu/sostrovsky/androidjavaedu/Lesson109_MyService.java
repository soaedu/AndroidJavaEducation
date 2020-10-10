package edu.sostrovsky.androidjavaedu;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by sostrovschi on 09.01.2017
 */

public class Lesson109_MyService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new Lesson109_MyFactory(getApplicationContext(), intent);
    }
}
