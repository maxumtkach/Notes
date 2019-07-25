package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private ImageButton buttonVisibility;
    private Button buttonSave;
    private EditText pinEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        buttonVisibility = findViewById(R.id.bottom_visibility);
        buttonSave = findViewById(R.id.btn_save);
        pinEdit = findViewById(R.id.pin_edit);


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

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingsActivity.this, "save", Toast.LENGTH_SHORT).show();

            }
        });
    }


}
