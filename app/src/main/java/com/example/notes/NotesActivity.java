package com.example.notes;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Objects;

public class NotesActivity extends AppCompatActivity {

    private static final String FILE_NAME = "text";
    private EditText titleText;
    private EditText subtitleText;
    private EditText deadlineText;

    Calendar dateAndTime = Calendar.getInstance();// календарь

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        deadlineText = findViewById(R.id.edit_text_deadline);
        titleText = findViewById(R.id.notes_title);
        subtitleText = findViewById(R.id.notes_subtitle);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);  // кнопка назад

        //  поля заметки для редактирования
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        if (bundle != null) {
//            String j = bundle.getString("title");
//            titleText.setText(j);
//            String l = bundle.getString("subTitle");
//            subtitleText.setText(l);
//            String deadline = bundle.getString("deadline");
//            deadlineText.setText(deadline);
//        }
    }

    public void toSsve() {
        String textTitleValue = titleText.getText().toString() ;
        String textSubtitleValue = subtitleText.getText().toString() ;
        String textDeadlineValue = deadlineText.getText().toString();

        saveIntData(FILE_NAME, textTitleValue +"::"+ textSubtitleValue + "::"+textDeadlineValue);
    }


    //  кнопки : назад и сохранить
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        if (item.getItemId() == R.id.item_notes_save) {   // кнопка сохранить

            String textTitleValue = titleText.getText().toString();
            String textSubtitleValue = subtitleText.getText().toString();
            String textDeadlineValue = deadlineText.getText().toString();

            toSsve();
            // saveIntData(SUBTITLE_FILE_NAME, textSubtitleValue);
            //   saveIntData(DEADLINE_FILE_NAME, textDeadlineValue);

            Intent intent = new Intent(this, ListNotesActivity.class);
            startActivity(intent);

            Toast.makeText(this, "Сохраняем заметку", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notes, menu);
        return true;
    }

    //  сохранение во внутр файл
    private void saveIntData(String fileName, String text) {  //save во внутр.
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(fileName, MODE_PRIVATE);
            fos.write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    //очищение поля дедллайна
    public void onCheckboxClicked(View view) {
        // Получаем флажок
        CheckBox checkDeadline = (CheckBox) view;
        // Получаем, отмечен ли данный флажок
        boolean checked = checkDeadline.isChecked();

        if (checked) {
            deadlineText.setText("");
        }
    }

    //вызов диалога
    public void onClickDialog(View view) {
        setTime(view);
        setDate(view);
        Toast.makeText(this, "Введите пожалуйста" + "\n" + "дату и время" + "\n" + "дедлайна", Toast.LENGTH_LONG).show();
    }

    // отображаем диалоговое окно для выбора даты
    public void setDate(View v) {
        new DatePickerDialog(NotesActivity.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    // отображаем диалоговое окно для выбора времени
    public void setTime(View v) {
        new TimePickerDialog(NotesActivity.this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();
    }

    // установка начальных даты и времени
    private void setInitialDateTime() {

        deadlineText.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME));
    }

    // установка обработчика выбора времени
    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            setInitialDateTime();
        }
    };

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };

}