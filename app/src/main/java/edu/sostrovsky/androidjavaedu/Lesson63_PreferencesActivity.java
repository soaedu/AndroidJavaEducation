package edu.sostrovsky.androidjavaedu;

import android.os.Bundle;
import android.preference.*;

import com.sostrovsky.androidjavaedu.R;

/**
 * Created by OS1 on 17.12.2016.
 */
public class Lesson63_PreferencesActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // создаем экран
        PreferenceScreen rootScreen = getPreferenceManager().createPreferenceScreen(this);
        // говорим Activity, что rootScreen - корневой
        setPreferenceScreen(rootScreen);

        // даллее создаем элементы, присваиваем атрибуты и формируем иерархию

        CheckBoxPreference chb1 = new CheckBoxPreference(this);
        chb1.setKey(getResources().getString(R.string.checkbox1_text));
        chb1.setTitle(getResources().getString(R.string.checkbox1_text));
        chb1.setSummaryOn(getResources().getString(R.string.lesson62_checkbox1_on_description_text));
        chb1.setSummaryOff(getResources().getString(R.string.lesson62_checkbox1_off_description_text));

        rootScreen.addPreference(chb1);


        ListPreference list = new ListPreference(this);
        list.setKey(getResources().getString(R.string.list_text));
        list.setTitle(getResources().getString(R.string.list_text));
        list.setSummary(getResources().getString(R.string.lesson61_list_description_text));
        list.setEntries(R.array.pref_entries);
        list.setEntryValues(R.array.pref_entry_values);

        rootScreen.addPreference(list);


        CheckBoxPreference chb2 = new CheckBoxPreference(this);
        chb2.setKey(getResources().getString(R.string.checkbox2_text));
        chb2.setTitle(getResources().getString(R.string.checkbox2_text));
        chb2.setSummary(getResources().getString(R.string.lesson61_checkbox2_description_text));

        rootScreen.addPreference(chb2);


        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(this);
        screen.setKey(getResources().getString(R.string.screen_text));
        screen.setTitle(getResources().getString(R.string.screen_text));
        screen.setSummary(getResources().getString(R.string.lesson61_screen_description_text));


        final CheckBoxPreference chb3 = new CheckBoxPreference(this);
        chb3.setKey(getResources().getString(R.string.checkbox3_text));
        chb3.setTitle(getResources().getString(R.string.checkbox3_text));
        chb3.setSummary(getResources().getString(R.string.lesson61_checkbox3_description_text));

        screen.addPreference(chb3);


        PreferenceCategory categ1 = new PreferenceCategory(this);
        categ1.setKey(getResources().getString(R.string.category1_text));
        categ1.setTitle(getResources().getString(R.string.category1_text));
        categ1.setSummary(getResources().getString(R.string.lesson61_category1_description_text));

        screen.addPreference(categ1);

        CheckBoxPreference chb4 = new CheckBoxPreference(this);
        chb4.setKey(getResources().getString(R.string.checkbox4_text));
        chb4.setTitle(getResources().getString(R.string.checkbox4_text));
        chb4.setSummary(getResources().getString(R.string.lesson61_checkbox4_description_text));

        categ1.addPreference(chb4);


        final PreferenceCategory categ2 = new PreferenceCategory(this);
        categ2.setKey(getResources().getString(R.string.category2_text));
        categ2.setTitle(getResources().getString(R.string.category2_text));
        categ2.setSummary(getResources().getString(R.string.lesson61_category2_description_text));

        screen.addPreference(categ2);


        CheckBoxPreference chb5 = new CheckBoxPreference(this);
        chb5.setKey(getResources().getString(R.string.checkbox5_text));
        chb5.setTitle(getResources().getString(R.string.checkbox5_text));
        chb5.setSummary(getResources().getString(R.string.lesson61_checkbox5_description_text));

        categ2.addPreference(chb5);


        CheckBoxPreference chb6 = new CheckBoxPreference(this);
        chb6.setKey(getResources().getString(R.string.checkbox6_text));
        chb6.setTitle(getResources().getString(R.string.checkbox6_text));
        chb6.setSummary(getResources().getString(R.string.lesson62_checkbox6_description_text));

        categ2.addPreference(chb6);

        rootScreen.addPreference(screen);

        list.setDependency(getResources().getString(R.string.checkbox1_text));
        screen.setDependency(getResources().getString(R.string.checkbox2_text));

        // код из прошлого урока для связи активности categ2 и значения chb3
        categ2.setEnabled(chb3.isChecked());
        chb3.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                categ2.setEnabled(chb3.isChecked());
                return false;
            }
        });
    }
}