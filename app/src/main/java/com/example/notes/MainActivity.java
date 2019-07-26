package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView pinText;
    private ImageView circleImage1;
    private ImageView circleImage2;
    private ImageView circleImage3;
    private ImageView circleImage4;
    private  Button buttonSettinds;

    private ImageButton buttonDelete;
    int m = 0;
    String pin ="1111";
    Button button;
    private ItemsDataAdapter adapter;
    private List<Drawable> images = new ArrayList<>();
    private Random random = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        ListView listView = findViewById(R.id.list_item);
      //  Toolbar toolbar = findViewById(R.id.my_toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);



        // Создаем и устанавливаем адаптер на наш список
        adapter = new ItemsDataAdapter(this, null);
        listView.setAdapter(adapter);
//
//        // При долгом тапе по элементу списка будем удалять его
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                adapter.showItemData(position);
//                return true;
//            }
//        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // generateRandomItemData();
                Toast.makeText(MainActivity.this,"onclick",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void numberClick(View view) {  // кнопка добавки цифры

        m++;
        button = (Button) view;
        if (m == 1) {
            pinText.append(button.getText().toString());
            circleImage1.setColorFilter(getResources().getColor(R.color.colorCircle));
        } else if (m == 2) {
            circleImage2.setColorFilter(getResources().getColor(R.color.colorCircle));
            pinText.append(button.getText().toString());
        } else if (m == 3) {
            circleImage3.setColorFilter(getResources().getColor(R.color.colorCircle));
            pinText.append(button.getText().toString());
        } else if (m == 4) {
            circleImage4.setColorFilter(getResources().getColor(R.color.colorCircle));
            pinText.append(button.getText().toString());
            pinInit();
        }
    }

    public void pinInit() {
        if (pinText.getText().toString().equals(pin)) {
            Toast.makeText(this, "ПИН-КОД  СОВПАЛ" + m, Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "ПИН-КОД НЕ СОВПАЛ" + m, Toast.LENGTH_SHORT).show();
        }
    }

    public void initView() {
        pinText = findViewById(R.id.pin_text);
        circleImage1 = findViewById(R.id.circle_1);
        circleImage2 = findViewById(R.id.circle_2);
        circleImage3 = findViewById(R.id.circle_3);
        circleImage4 = findViewById(R.id.circle_4);
        buttonDelete = findViewById(R.id.btn_delete);
//        button0 = findViewById(R.id.btn_0);
//        button1 = findViewById(R.id.btn_1);
//        button2 = findViewById(R.id.btn_2);
//        button3 = findViewById(R.id.btn_3);
//        button4 = findViewById(R.id.btn_4);
//        button5 = findViewById(R.id.btn_5);
//        button6 = findViewById(R.id.btn_6);
//        button7 = findViewById(R.id.btn_7);
//        button8 = findViewById(R.id.btn_8);
//        button9 = findViewById(R.id.btn_9);
    }

    public void numberClickDelete(View view) {   //переключение кружков
        if (m>4){
            m=4;
        }
        int charIndex = 0;
        String text = pinText.getText().toString();
        text = text.substring(0, charIndex) + text.substring(charIndex + 1);
        pinText.setText(text);
        Toast.makeText(MainActivity.this, "" + m, Toast.LENGTH_SHORT).show();

        if (m == 4) {
            circleImage4.setColorFilter(getResources().getColor(R.color.colorCircleDef));
        } else if (m == 3) {
            circleImage3.setColorFilter(getResources().getColor(R.color.colorCircleDef));
        } else if (m == 2) {
            circleImage2.setColorFilter(getResources().getColor(R.color.colorCircleDef));
        } else if (m == 1) {
            circleImage1.setColorFilter(getResources().getColor(R.color.colorCircleDef));
        }
        m--;
    }


}
