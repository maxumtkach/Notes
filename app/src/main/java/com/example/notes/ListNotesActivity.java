package com.example.notes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListNotesActivity extends AppCompatActivity {

    private ItemsDataAdapter adapter;
    private List<Drawable> images = new ArrayList<>();
    private Random random = new Random();
    AlertDialog.Builder ad;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);

        ListView listView = findViewById(R.id.list_item);
        //  Toolbar toolbar = findViewById(R.id.my_toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);


        // Создаем и устанавливаем адаптер на наш список
        adapter = new ItemsDataAdapter(this, null);
        listView.setAdapter(adapter);

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListNotesActivity.this, "onclick редактирование", Toast.LENGTH_SHORT).show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {  // onclick fab
            @Override
            public void onClick(View view) {
                generateRandomItemData();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mein, menu);
        return true;
    }

    private void generateRandomItemData() {

        adapter.addItem(new ItemData(
                "title" + adapter.getCount(),
                "Hello" + adapter.getCount()));
    }
}
