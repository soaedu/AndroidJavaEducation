package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 16.12.2016.
 */
public class Lesson57_Parcel    extends     Activity
                                implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private Parcel p;

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson57);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson57_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        writeParcel();
        readParcel();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson57_ArrowBackWrapLL:
                startActivity(new Intent(Lesson57_Parcel.this, Main_Activity.class));
                break;
            default:
                break;
        }
    }

    private void writeParcel() {
        p = Parcel.obtain();

        byte b = 1;
        int i = 2;
        long l = 3;
        float f = 4;
        double d = 5;
        String s = "abcdefgh";

        logWriteInfo("before writing");

        p.writeByte(b);
        logWriteInfo("byte");

        p.writeInt(i);
        logWriteInfo("int");

        p.writeLong(l);
        logWriteInfo("long");

        p.writeFloat(f);
        logWriteInfo("float");

        p.writeDouble(d);
        logWriteInfo("double");

        p.writeString(s);
        logWriteInfo("String");
    }

    private void logWriteInfo(String txt) {
        Log.d(LOG_TAG, txt + ": " + "dataSize = " + p.dataSize());
    }

    private void readParcel() {

        logReadInfo("before reading");

        p.setDataPosition(0);

        logReadInfo("byte = " + p.readByte());
        logReadInfo("int = " + p.readInt());
        logReadInfo("long = " + p.readLong());
        logReadInfo("float = " + p.readFloat());
        logReadInfo("double = " + p.readDouble());
        logReadInfo("string = " + p.readString());
    }

    private void logReadInfo(String txt) {
        Log.d(LOG_TAG, txt + ": " + "dataPosition = " + p.dataPosition());
    }
}