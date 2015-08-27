package com.gmail.s8521444.activities;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.gmail.s8521444.R;
import com.gmail.s8521444.fragments.FolderFragment;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DirectoryChooseActivity extends ListActivity implements View.OnClickListener{

    final String LOG_TAG = "myLogs";

    private List<String> mPathList = null;
    // private String root = "/"; // символ для корневого элемента ТАК РАБОТАЕТ
    private String root;
    private TextView mPathTextView;
    // private TextView mPathTextView1;

    // private String arr;

    Button btnOK;

    private File[] pathsArray1;
    private String[] pathsArray;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_directory_choose);

        mPathTextView = (TextView) findViewById(R.id.textViewPath); // здесь будем выводить текущее местоположение
        // mPathTextView1 = (TextView) findViewById(R.id.textViewPath1);
        btnOK = (Button) findViewById(R.id.buttonOK);
        btnOK.setOnClickListener(this);

        // путь внешнего хранилища
        root = Environment.getExternalStorageDirectory().getPath();

        getDir(root); // выводим список файлов и папок в корневой папке системы
    }

    private void getDir(String dirPath) {
        mPathTextView.setText("Путь: " + dirPath); // где мы сейчас
        List<String> itemList = new ArrayList<>();
        mPathList = new ArrayList<>();
        File file = new File(dirPath);
        File[] filesArray = file.listFiles(); // получаем список файлов
        // File[] pathsArray1;
        // В ЭТОМ БЛОКЕ Я ВРОДЕ КАК ПОЛУЧАЮ ОТФИЛЬТРОВАННЫЙ МАССИВ  File, КОНВЕРТИРУЮ ЕГО В МАССИВ
        // String И ПОТОМ ПЕРЕДАЮ ЧЕРЕЗ ИНТЕНТ
        pathsArray1 = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                return filename.toLowerCase().endsWith(".mp3");
            }
        });

        pathsArray = new String[pathsArray1.length];
        for (int i = 0; i < pathsArray1.length; i++){

            pathsArray[i] = pathsArray1[i].getPath();

        }



        //mPathTextView1.setText("Путь1: " + pathsArray1[0]);

        // если мы не в корневой папке
        if (!dirPath.equals(root)) {
            itemList.add(root);
            mPathList.add(root);
            itemList.add("../");
            mPathList.add(file.getParent());
        }

        // формируем список папок и файлов для передачи адаптеру
        for (File aFilesArray : filesArray) {
            file = aFilesArray;
            mPathList.add(file.getPath());
            if (file.isDirectory()) // Это папка
                itemList.add(file.getName() + "/");
            else
                itemList.add(file.getName());
        }

        // Можно выводить на экран список
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.list_item, itemList);
        setListAdapter(adapter);
    }







    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        // обработка нажатий на элементах списка
        File file = new File(mPathList.get(position));
        // если это папка
        if (file.isDirectory()) {
            if (file.canRead()) // если она доступна для просмотра, то заходим в неё
                getDir(mPathList.get(position));
            else { // если папка закрыта, то сообщаем об этом
                new AlertDialog.Builder(this)
                        .setIcon(R.drawable.ic_cafe)
                        .setTitle(
                                "[" + file.getName()
                                        + "] папка не доступна!")
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                    }
                                }).show();
            }
        } else { // если элемент списка является файлом, то выводим его имя
            String fileInfo = "Абсолютный путь: " + file.getAbsolutePath()
                    + "\n" + "Путь: " + file.getPath() + "\n" + "Родитель: "
                    + file.getParent() + "\n" + "Имя: " + file.getName() + "\n"
                    + "Последнее изменение: " + new Date(file.lastModified());

            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_cafe)
                    .setTitle("[" + file.getName() + "]")
                    .setMessage(fileInfo)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                }
                            }).show();
        }
    }

    @Override
    public void onClick(View v) {
        /*
        arr = "";
        for (int i=0; i < pathsArray.length; i++){
            arr = arr + pathsArray[i] + ", ";
        }

        mPathTextView1.setText("Массив " + arr);
        */

        /*
        Intent intent = new Intent();
        intent.putExtra("pathsArray", pathsArray);
        setResult(RESULT_OK, intent);
        */
        startService(new Intent(this, FolderFragment.class).putExtra("paths", pathsArray));
        //Intent intent = new Intent(this, PlaylistActivity.class);
        //startActivity(intent);
        Log.d(LOG_TAG, "DirectoryChooseActivity onClick ");
        finish();


    }
}
