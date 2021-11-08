package com.matricaconsulting.simplebooklist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddBookActivity extends AppCompatActivity {

    private EditText etBookCode;
    private EditText etBookTitle;
    private EditText etPublisher;
    private EditText etAuthor;
    private EditText etYearRelease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        // Get firebase database location
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = database.getReference("Book");

        // Initiate each edit text field
        etBookCode = findViewById(R.id.addbook_edit_code);
        etBookTitle = findViewById(R.id.addbook_edit_title);
        etPublisher = findViewById(R.id.addbook_edit_publisher);
        etAuthor = findViewById(R.id.addbook_edit_author);
        etYearRelease = findViewById(R.id.addbook_edit_year);

        // Initiate submit button
        final Button btnSubmit = findViewById(R.id.addbook_btn_submit);
        btnSubmit.setOnClickListener(v -> {
            // Save the data to firebase database if each field is not empty
            if (!TextUtils.isEmpty(etBookCode.getText()) &&
                    !TextUtils.isEmpty(etBookTitle.getText()) &&
                    !TextUtils.isEmpty(etPublisher.getText()) &&
                    !TextUtils.isEmpty(etAuthor.getText()) &&
                    !TextUtils.isEmpty(etYearRelease.getText())) {
                Book book = new Book(etBookCode.getText().toString(), etBookTitle.getText().toString(),
                        Integer.parseInt(etYearRelease.getText().toString()), etPublisher.getText().toString(), etAuthor.getText().toString());
                dbRef.child(etBookCode.getText().toString()).setValue(book);
            } else {
                Toast.makeText(this, "Not Saved", Toast.LENGTH_LONG).show();
            }
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}