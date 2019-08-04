package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText pinText;
    private ImageView circleImage1;
    private ImageView circleImage2;
    private ImageView circleImage3;
    private ImageView circleImage4;
    private int m = 0;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Toast.makeText(this, "значение пикод: " + SettingsActivity.getLogin(), Toast.LENGTH_SHORT).show();

        if (SettingsActivity.getLogin()==null) {  //  если не задан ипиин то переход в настройки
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
    }

    // кнопка добавки цифры
    public void numberClick(View view) {
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

    // совпал пин или нет
    public void pinInit() {
        if (pinText.getText().toString().equals(SettingsActivity.getLogin())) {
            Toast.makeText(this, "ПИН-КОД  СОВПАЛ  " + "\n" + "Открыть NOTES", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, ListNotesActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(this, "ПИН-КОД НЕ СОВПАЛ" + "\n" + "ОШИБКА!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
    }

    //  инициализация
    public void initView() {
        pinText = findViewById(R.id.pin_text);
        circleImage1 = findViewById(R.id.circle_1);
        circleImage2 = findViewById(R.id.circle_2);
        circleImage3 = findViewById(R.id.circle_3);
        circleImage4 = findViewById(R.id.circle_4);
    }

    //переключение кружков
    public void numberClickDelete(View view) {
        if (m > 4) {
            m = 4;
        }

        int charIndex = 0;
        String text = pinText.getText().toString();
        text = text.substring(0, charIndex) + text.substring(charIndex + 1);
        pinText.setText(text);

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
