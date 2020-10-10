package edu.sostrovsky.androidjavaedu;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by OS1 on 16.12.2016.
 */

public class Lesson58_MyObject implements Parcelable {

    final static String LOG_TAG = "myLogs";

    public String s;
    public int i;

    // обычный конструктор
    public Lesson58_MyObject(String _s, int _i) {
        Log.d(LOG_TAG, "MyObject(String _s, int _i)");
        s = _s;
        i = _i;
    }

    public int describeContents() {
        return 0;
    }

    // упаковываем объект в Parcel
    public void writeToParcel(Parcel parcel, int flags) {
        Log.d(LOG_TAG, "writeToParcel");

        parcel.writeString(s);
        parcel.writeInt(i);
    }

    public static final Parcelable.Creator<Lesson58_MyObject> CREATOR = new Parcelable.Creator<Lesson58_MyObject>() {
        // распаковываем объект из Parcel
        public Lesson58_MyObject createFromParcel(Parcel in) {
            Log.d(LOG_TAG, "createFromParcel");
            return new Lesson58_MyObject(in);
        }

        public Lesson58_MyObject[] newArray(int size) {
            return new Lesson58_MyObject[size];
        }
    };

    // конструктор, считывающий данные из Parcel
    private Lesson58_MyObject(Parcel parcel) {
        Log.d(LOG_TAG, "MyObject(Parcel parcel)");

        s = parcel.readString();
        i = parcel.readInt();
    }
}