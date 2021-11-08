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

import static com.matricaconsulting.simplebooklist.MainActivity.EXTRA;

public class EditBookActivity extends AppCompatActivity {

    private EditText etBookCode;
    private EditText etBookTitle;
    private EditText etPublisher;
    private EditText etAuthor;
    private EditText etYearRelease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        // Get firebase database location
        FirebaseDatabase root = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = root.getReference("Book");

        // Get the clicked book from main activity
        Book book = getIntent().getParcelableExtra(EXTRA);

        // Initiate each edit text field
        etBookCode = findViewById(R.id.editbook_edit_code);
        etBookTitle = findViewById(R.id.editbook_edit_title);
        etPublisher = findViewById(R.id.editbook_edit_publisher);
        etAuthor = findViewById(R.id.editbook_edit_author);
        etYearRelease = findViewById(R.id.editbook_edit_year);

        // Set each field with book data
        etBookCode.setText(book.getBookCode());
        etBookTitle.setText(book.getBookTitle());
        etPublisher.setText(book.getPublisher());
        etAuthor.setText(book.getAuthor());
        etYearRelease.setText(String.valueOf(book.getYearRelease()));

        String bookCode = book.getBookCode();

        // Initiate update button
        final Button btnUpdate = findViewById(R.id.editbook_btn_update);
        btnUpdate.setOnClickListener(v -> {

            // Save the data to firebase database if each field is not empty
            if (TextUtils.isEmpty(etBookCode.getText()) &&
                    TextUtils.isEmpty(etBookTitle.getText()) &&
                    TextUtils.isEmpty(etPublisher.getText()) &&
                    TextUtils.isEmpty(etAuthor.getText()) &&
                    TextUtils.isEmpty(etYearRelease.getText())) {
                Toast.makeText(this, "Not Saved", Toast.LENGTH_LONG).show();
            } else {
                book.setBookCode(etBookCode.getText().toString());
                book.setBookTitle(etBookTitle.getText().toString());
                book.setPublisher(etPublisher.getText().toString());
                book.setAuthor(etAuthor.getText().toString());
                book.setYearRelease(Integer.parseInt(etYearRelease.getText().toString()));
                if (bookCode.equals(etBookCode.getText().toString())) {
                    dbRef.child(bookCode).setValue(book);
                } else {
                    dbRef.child(bookCode).removeValue();
                    dbRef.child(etBookCode.getText().toString()).setValue(book);
                }
            }
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        // Initiate delete button
        final Button btnDelete = findViewById(R.id.editbook_btn_delete);
        btnDelete.setOnClickListener(v -> {
            dbRef.child(bookCode).removeValue();
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