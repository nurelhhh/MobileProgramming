package com.example.multipleactivites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        Intent intent = getIntent();
        String getMessage = intent.getStringExtra("MESSAGE");

        TextView getMessageText = findViewById(R.id.get_message_text);
        getMessageText.setText(getMessage);

    }
}