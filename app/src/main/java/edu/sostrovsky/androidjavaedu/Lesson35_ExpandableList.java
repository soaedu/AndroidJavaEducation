package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.SimpleExpandableListAdapter;

import com.sostrovsky.androidjavaedu.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by OS1 on 03.12.2016.
 */
public class Lesson35_ExpandableList extends     Activity
                                        implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    // названия компаний (групп)
    private String[] groups = new String[] {"HTC", "Samsung", "LG"};

    // названия телефонов (элементов)
    private String[] phonesHTC = new String[] {"Sensation", "Desire", "Wildfire", "Hero"};
    private String[] phonesSams = new String[] {"Galaxy S II", "Galaxy Nexus", "Wave"};
    private String[] phonesLG = new String[] {"Optimus", "Optimus Link", "Optimus Black", "Optimus One"};

    // коллекция для групп
    private ArrayList<Map<String, String>> groupData;

    // коллекция для элементов одной группы
    private ArrayList<Map<String, String>> childDataItem;

    // общая коллекция для коллекций элементов
    private ArrayList<ArrayList<Map<String, String>>> childData;
    // в итоге получится childData = ArrayList<childDataItem>

    // список атрибутов группы или элемента
    private Map<String, String> m;

    private ExpandableListView mMainELV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson35);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson35_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

        // заполняем коллекцию групп из массива с названиями групп
        groupData = new ArrayList<Map<String, String>>();
        for (String group : groups) {
            // заполняем список атрибутов для каждой группы
            m = new HashMap<String, String>();
            m.put("groupName", group); // имя компании

            groupData.add(m);
        }

        // список атрибутов групп для чтения
        String groupFrom[] = new String[] {"groupName"};

        // список ID view-элементов, в которые будет помещены атрибуты групп
        int groupTo[] = new int[] {android.R.id.text1};

        ///////////////////////////////////////////////////////////////////////////////////

        // создаем коллекцию для коллекций элементов
        childData = new ArrayList<ArrayList<Map<String, String>>>();

        // создаем коллекцию элементов для первой группы
        childDataItem = new ArrayList<Map<String, String>>();
        // заполняем список атрибутов для каждого элемента
        for (String phone : phonesHTC) {
            m = new HashMap<String, String>();
            m.put("phoneName", phone); // название телефона
            childDataItem.add(m);
        }
        // добавляем в коллекцию коллекций
        childData.add(childDataItem);

        // создаем коллекцию элементов для второй группы
        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone : phonesSams) {
            m = new HashMap<String, String>();
            m.put("phoneName", phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        // создаем коллекцию элементов для третьей группы
        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone : phonesLG) {
            m = new HashMap<String, String>();
            m.put("phoneName", phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        // список атрибутов элементов для чтения
        String childFrom[] = new String[] {"phoneName"};
        // список ID view-элементов, в которые будет помещены атрибуты элементов
        int childTo[] = new int[] {android.R.id.text1};

        ///////////////////////////////////////////////////////////////////////////////////

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,
                groupTo,
                childData,
                android.R.layout.simple_list_item_1,
                childFrom,
                childTo);

        mMainELV = (ExpandableListView) findViewById(R.id.Lesson35_Main_ELV);
        mMainELV.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.Lesson35_ArrowBackWrapLL:
                startActivity(new Intent(Lesson35_ExpandableList.this, Main_Activity.class));
                break;
        }
    }
}