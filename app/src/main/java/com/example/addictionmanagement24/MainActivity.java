package com.example.addictionmanagement24;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.content.SharedPreferences;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;

public class MainActivity extends AppCompatActivity {

    private TextView textViewStreakCount;
    private Button buttonResistAddiction;
    private Button buttonDigitalDiary;
    private Button buttonViewEntries;
    private int streakCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewStreakCount = findViewById(R.id.streakCountTextView);
        buttonResistAddiction = findViewById(R.id.button_resist_addiction);
        buttonDigitalDiary = findViewById(R.id.digitalDiaryButton);
        buttonViewEntries = findViewById(R.id.buttonViewEntries);

        loadStreakCount();

        buttonResistAddiction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Addiction Check")
                        .setMessage("Are you addiction-free today?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                incrementStreakCount();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                resetStreakCount();
                            }
                        })
                        .show();
            }
        });

        buttonDigitalDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DigitalDiaryActivity.class);
                startActivity(intent);
            }
        });

        buttonViewEntries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewDiaryEntriesActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadStreakCount() {
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        streakCount = prefs.getInt("streakCount", 0);
        updateStreakCountView();
    }

    private void incrementStreakCount() {
        streakCount++;
        updateStreakCountView();
        saveStreakCount();
    }

    private void resetStreakCount() {
        streakCount = 0;
        updateStreakCountView();
        saveStreakCount();
    }

    private void updateStreakCountView() {
        String streakText = getString(R.string.days_in_a_row_count) + " " + streakCount;
        textViewStreakCount.setText(streakText);
    }

    private void saveStreakCount() {
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putInt("streakCount", streakCount);
        editor.apply();
    }
}
