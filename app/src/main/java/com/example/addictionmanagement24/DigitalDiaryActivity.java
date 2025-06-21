package com.example.addictionmanagement24;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.content.SharedPreferences;
import java.util.Date;
import java.text.SimpleDateFormat;

public class DigitalDiaryActivity extends AppCompatActivity {

    private EditText editTextDiaryEntry;
    private Button buttonSaveEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_diary);

        editTextDiaryEntry = findViewById(R.id.editTextDiaryEntry);
        buttonSaveEntry = findViewById(R.id.buttonSaveEntry);

        buttonSaveEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDiaryEntry();
            }
        });
    }

    private void saveDiaryEntry() {
        String entry = editTextDiaryEntry.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateAndTime = sdf.format(new Date());
        String diaryEntry = currentDateAndTime + ": " + entry;

        SharedPreferences prefs = getSharedPreferences("DiaryEntries", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(currentDateAndTime, diaryEntry);
        editor.apply();

        editTextDiaryEntry.setText(""); // Clear the EditText

        // Navigate back to the main activity
        finish();
    }
}
