package com.example.multipleactivites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    TextInputLayout messageLayout;
    TextInputEditText messageEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageLayout = findViewById(R.id.message_layout);
        messageEdit = findViewById(R.id.message_edit);

        messageEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    messageLayout.setError(null);
                }
            }
        });
    }

    public void onSend(View view) {
        TextInputEditText messageEdit = findViewById(R.id.message_edit);
        String message;

        hideKeyboard();
        messageEdit.clearFocus();

        try {
            message = messageEdit.getText().toString();
            if (message.equals("")) {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            messageLayout.setError(getString(R.string.error_message_input));
            return;
        }

        Intent intent = new Intent(this, SecondaryActivity.class);
        intent.putExtra("MESSAGE", message);
        startActivity(intent);
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }
}