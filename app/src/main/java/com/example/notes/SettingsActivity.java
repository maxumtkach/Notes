package com.example.notes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SettingsActivity extends AppCompatActivity {   //  активити настроек

    private ImageButton buttonVisibility;
    private Button buttonSave;
    private EditText pinEdit;
    private static final String LOGIN_FILE_NAME = "login text";//---------------------------------------------

    SharedPreferences sharedPreferences;
    private static final String APP_PREFERENCES = "Account"; //имя файла
    private static final String LOGIN_FILE = "login";  // ключ
    private static String strLogin = "";

    public static String getLogin() {
        return strLogin;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initView();
        sharedPreferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        strLogin = getStrLog();
        //  strLogin= readLineFromFile(LOGIN_FILE_NAME);

        // кнопка видимости пароля
        buttonVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingsActivity.this, "settings", Toast.LENGTH_SHORT).show();
                if (pinEdit.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    pinEdit.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    buttonVisibility.setImageResource(R.drawable.ic_visibility_off_black_24dp);

                } else {
                    pinEdit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    buttonVisibility.setImageResource(R.drawable.ic_visibility_black_24dp);

                }
                pinEdit.setSelection(pinEdit.getText().length());
            }
        });

        //  онклик сохранения  пароля
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   String text=pinEdit.getText().toString();
                // saveIntData(LOGIN_FILE_NAME,text);
                //  String logSave= readLineFromFile(LOGIN_FILE_NAME);
                saveLogin();
                getStrLog();
                Toast.makeText(SettingsActivity.this, "save" + strLogin, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(SettingsActivity.this, SettingsActivity.this.getClass());//перезапуск акт
                finish();
                SettingsActivity.this.startActivity(i);
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

    //  чтение  логина
    private String getStrLog() {
        String g;
        if (sharedPreferences.contains(LOGIN_FILE)) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        }
        g = sharedPreferences.getString(LOGIN_FILE, "");
        return g;
    }

    //  сохранение  логина
    public void saveLogin() {
        String strLog = pinEdit.getText().toString();   //значение логин
        // Запоминаем данные
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LOGIN_FILE, strLog);
        editor.apply();

    }

    //  инициализация
    public void initView() {
        buttonVisibility = findViewById(R.id.bottom_visibility);
        buttonSave = findViewById(R.id.btn_save);
        pinEdit = findViewById(R.id.pin_edit);
    }
}
