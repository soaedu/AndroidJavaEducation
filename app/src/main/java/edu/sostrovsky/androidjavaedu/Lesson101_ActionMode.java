package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 02.01.2017.
 */
public class Lesson101_ActionMode extends Activity
        implements View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private ActionMode mActionMode;

    private ListView mActionModeLv;
    private final String LOG_TAG = "myLogs";

    private String[] dataArr = {"one", "two", "three", "four", "five"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson101);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson101_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        /////////////////////////////////////////////////////////////////////////////////////

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, dataArr);
        mActionModeLv = (ListView) findViewById(R.id.Lesson101_ActionMode_LV);
        mActionModeLv.setAdapter(adapter);
        mActionModeLv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        mActionModeLv.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.menu_lesson101, menu);
                return true;
            }

            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                mode.finish();
                return false;
            }

            public void onDestroyActionMode(ActionMode mode) {
            }

            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                Log.d(LOG_TAG, "position = " + position + ", checked = " + checked);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lesson101, menu);
        return true;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson101_ArrowBackWrapLL:
                startActivity(new Intent(Lesson101_ActionMode.this, Main_Activity.class));
                break;
//            case R.id.Lesson101_ActionMode_Btn:
//                if (mActionMode == null)
//                    mActionMode = startActionMode(callback);
//                else
//                    mActionMode.finish();
//                break;
            default:
                break;
        }
    }

    private ActionMode.Callback callback = new ActionMode.Callback() {

        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_lesson101, menu);
            return true;
        }

        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            Log.d(LOG_TAG, "item " + item.getTitle());
            return false;
        }

        public void onDestroyActionMode(ActionMode mode) {
            Log.d(LOG_TAG, "destroy");
            mActionMode = null;
        }

    };
}