package com.example.addictionmanagement24;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.view.View;
import java.util.ArrayList;
import java.util.Map;
import android.content.SharedPreferences;

public class ViewDiaryEntriesActivity extends AppCompatActivity {

    private ListView listViewDiaryEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_diary_entries);

        listViewDiaryEntries = findViewById(R.id.listViewDiaryEntries);
        loadDiaryEntries();

        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the activity and return to the previous one
            }
        });

        Button buttonClearEntries = findViewById(R.id.buttonClearEntries);
        buttonClearEntries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearDiaryEntries();
            }
        });
    }

    private void loadDiaryEntries() {
        SharedPreferences prefs = getSharedPreferences("DiaryEntries", MODE_PRIVATE);
        Map<String, ?> entries = prefs.getAll();
        ArrayList<String> diaryEntries = new ArrayList<>();
        for (Map.Entry<String, ?> entry : entries.entrySet()) {
            diaryEntries.add(entry.getValue().toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, diaryEntries);
        listViewDiaryEntries.setAdapter(adapter);
    }

    private void clearDiaryEntries() {
        SharedPreferences prefs = getSharedPreferences("DiaryEntries", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear(); // Clear all entries
        editor.apply();

        // Update the list view
        ArrayList<String> diaryEntries = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, diaryEntries);
        listViewDiaryEntries.setAdapter(adapter);
    }
}

