package com.example.notes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ListNotesActivity extends AppCompatActivity {

   private    ItemsDataAdapter adapter;
    AlertDialog.Builder ad;
    Context context;
    private static final String TITLE_FILE_NAME = "title text";
    private static final String SUBTITLE_FILE_NAME = "subtitle text";
    private static final String DEADLINE_FILE_NAME = "deadline text";
    private static final int CM_DELETE_ID = 1;//--------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);

         ListView listView = findViewById(R.id.list_item);
        FloatingActionButton fab = findViewById(R.id.fab);

        // Создаем и устанавливаем адаптер на наш список
        adapter = new ItemsDataAdapter(this, null);
        listView.setAdapter(adapter);

        adapter.addItem(new ItemData(readLineFromFile(TITLE_FILE_NAME),readLineFromFile(SUBTITLE_FILE_NAME),
                readLineFromFile(DEADLINE_FILE_NAME)));

        // При долгом тапе по элементу списка будем удалять его
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                context = ListNotesActivity.this;
                String title = "Warning";
                String message = "Are you sure you want to delete this note?";
                String button1String = "delete";
                String button2String = "cancel";

                ad = new AlertDialog.Builder(context);
                ad.setTitle(title);  // заголовок
                ad.setMessage(message); // сообщение
                ad.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        adapter.removeItem(position);  //  удаление эл-а
                        Toast.makeText(context, "Вы сделали правильный выбор",
                                Toast.LENGTH_LONG).show();
                    }
                });
                ad.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        Toast.makeText(context, "Возможно вы правы", Toast.LENGTH_LONG)
                                .show();
                    }
                });
                ad.setCancelable(true);
                ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        Toast.makeText(context, "Вы ничего не выбрали",
                                Toast.LENGTH_LONG).show();
                    }
                });
                ad.show();
                return true;
            }
        });

        //редактирование заметки
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListNotesActivity.this, NotesActivity.class);
                intent.putExtra("title", readLineFromFile(TITLE_FILE_NAME));
                intent.putExtra("subTitle", readLineFromFile(SUBTITLE_FILE_NAME));
                intent.putExtra("deadline", readLineFromFile(DEADLINE_FILE_NAME));
                startActivity(intent);

                Toast.makeText(ListNotesActivity.this, "onclick редактирование", Toast.LENGTH_SHORT).show();
            }
        });

        // КЛИК ДОБАВКИ новой заметки для ее создания
        fab.setOnClickListener(new View.OnClickListener() {  // onclick fab
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ListNotesActivity.this, NotesActivity.class);
                startActivity(intent);
            }
        });
    }

    //  чтение из внутр хранилища
    public String readLineFromFile(String fileName) {   //read   внутренний
        FileInputStream fis = null;
        try {
            fis = openFileInput(fileName);
            final InputStreamReader streamReader = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(streamReader);

            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mein, menu);
        return true;
    }
//
//    @Override
//    public boolean onOptionsItemSelected(final MenuItem item) {
//
//        if (item.getItemId() == R.id.item1) {   // кнопка сохранить
//adapter.addItem(new ItemData("fnj","gff","hnfb"));
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, (R.string.delete_text));
    }

}
