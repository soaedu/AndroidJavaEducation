package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 27.12.2016.
 */
public class Lesson89_ContentProviderClient extends     Activity
                                            implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private final String LOG_TAG = "myLogs";

    private final Uri CONTACT_URI = Uri.parse("content://com.example.os1.mycontentprovider.AddressBook/contacts");

    final String CONTACT_NAME = "name";
    final String CONTACT_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson89);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson89_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        /////////////////////////////////////////////////////////////////////////////////

        Cursor cursor = getContentResolver().query(CONTACT_URI, null, null, null, null);
        startManagingCursor(cursor);

        String from[] = { "name", "email" };
        int to[] = { android.R.id.text1, android.R.id.text2 };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,  android.R.layout.simple_list_item_2, cursor, from, to);

        ListView lvContact = (ListView) findViewById(R.id.Lesson89_Contact_LV);
        lvContact.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson89_ArrowBackWrapLL:
                startActivity(new Intent(Lesson89_ContentProviderClient.this, Main_Activity.class));
                break;
            case R.id.Lesson89_Insert_Btn:
                ContentValues cv = new ContentValues();
                cv.put(CONTACT_NAME, "name 4");
                cv.put(CONTACT_EMAIL, "email 4");

                Uri newUri = getContentResolver().insert(CONTACT_URI, cv);

                Log.d(LOG_TAG, "insert, result Uri : " + newUri.toString());
                break;
            case R.id.Lesson89_Update_Btn:
                ContentValues cv1 = new ContentValues();
                cv1.put(CONTACT_NAME, "name 5");
                cv1.put(CONTACT_EMAIL, "email 5");

                Uri uri = ContentUris.withAppendedId(CONTACT_URI, 2);

                int cnt = getContentResolver().update(uri, cv1, null, null);

                Log.d(LOG_TAG, "update, count = " + cnt);
                break;
            case R.id.Lesson89_Delete_Btn:
                Uri uri2 = ContentUris.withAppendedId(CONTACT_URI, 3);
                int cnt2= getContentResolver().delete(uri2, null, null);

                Log.d(LOG_TAG, "delete, count = " + cnt2);
                break;
            case R.id.Lesson89_Error:
                Uri uri3 = Uri.parse("content://com.example.os1.mycontentprovider.AddressBook/phones");

                try {
                    Cursor cursor = getContentResolver().query(uri3, null, null, null, null);
                } catch (Exception ex) {
                    Log.d(LOG_TAG, "Error: " + ex.getClass() + ", " + ex.getMessage());
                }
                break;
            default:
                break;
        }
    }
}