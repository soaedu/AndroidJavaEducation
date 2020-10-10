package edu.sostrovsky.androidjavaedu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by sostrovschi on 1/2/17.
 */

public class Lesson103_DetailsFragment extends Fragment {


    public static Lesson103_DetailsFragment newInstance(int pos) {

        Lesson103_DetailsFragment mDetails = new Lesson103_DetailsFragment();
        Bundle args = new Bundle();
        args.putInt("position", pos);
        mDetails.setArguments(args);

        return mDetails;
    }

    int getPosition() {

        return getArguments().getInt("position", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details_lesson103, container, false);

        TextView tv = (TextView) v.findViewById(R.id.Fragment_Details_Text_Tv);
        tv.setText(getResources().getStringArray(R.array.content)[getPosition()]);

        return v;
    }
}
