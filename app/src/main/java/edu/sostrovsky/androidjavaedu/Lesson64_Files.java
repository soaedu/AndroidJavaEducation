package edu.sostrovsky.androidjavaedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.sostrovsky.androidjavaedu.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by OS1 on 18.12.2016.
 */
public class Lesson64_Files extends     Activity
                            implements  View.OnClickListener {

    private LinearLayout arrowBackWrapLL;

    private final String FILENAME = "file";
    private final String FILENAME_SD = "fileSD";

    private final String DIR_SD = "MyFiles";

    private final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson64);

        arrowBackWrapLL = (LinearLayout) findViewById(R.id.Lesson64_ArrowBackWrapLL);
        arrowBackWrapLL.setOnClickListener(this);

        ///////////////////////////////////////////////////////////////////////////////////

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.Lesson64_ArrowBackWrapLL:
                startActivity(new Intent(Lesson64_Files.this, Main_Activity.class));
                break;
            case R.id.Lesson64_Write_Btn:
                writeFile();
                break;
            case R.id.Lesson64_Read_Btn:
                readFile();
                break;
            case R.id.Lesson64_WriteSD_Btn:
                writeFileSD();
                break;
            case R.id.Lesson64_ReadSD_Btn:
                readFileSD();
                break;
            default:
                break;
        }
    }

    private void writeFile() {

        try {

            // открываем поток для записи
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(FILENAME, MODE_PRIVATE)));

            // пишем данные
            bw.write("Содержимое файла");

            // закрываем поток
            bw.close();

            Log.d(LOG_TAG, "Файл записан");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile() {

        try {

            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(FILENAME)));

            String str = "";

            // читаем содержимое
            while((str = br.readLine()) != null) {

                Log.d(LOG_TAG, str);
            }

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFileSD() {

        // проверяем доступность SD
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            Log.d(LOG_TAG, "SD-карта не доступна: " +Environment.getExternalStorageState());
            return;
        }

        // получаем путь к SD
        File sdPath = Environment.getExternalStorageDirectory();

        // добавляем свой каталог к пути
        sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);

        // создаем каталог
        sdPath.mkdirs();

        // формируем объект File, который содержит путь к файлу
        File sdFile = new File(sdPath, FILENAME_SD);

        try {

            // открываем поток для записи
            BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));

            // пишем данные
            bw.write("Содержимое файла на SD");

            // закрываем поток
            bw.close();

            Log.d(LOG_TAG, "Файл записан на SD: " + sdFile.getAbsolutePath());

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void readFileSD() {

        // проверяем доступность SD
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            Log.d(LOG_TAG, "SD карта не доступна: " + Environment.getExternalStorageState());
            return;
        }

        // получаем путь к SD
        File sdPath = Environment.getExternalStorageDirectory();

        // добавляем свой каталог к пути
        sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);

        // формируем объект File, который содержит путь к файлу
        File sdFile = new File(sdPath, FILENAME_SD);

        try {

            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new FileReader(sdFile));

            String str = "";

            // читаем содержимое
            while((str = br.readLine()) != null) {

                Log.d(LOG_TAG, str);
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}