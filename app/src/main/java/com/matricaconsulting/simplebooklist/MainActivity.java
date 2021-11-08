package com.matricaconsulting.simplebooklist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity implements BookListAdapter.OnBookListener {

    public static final String EXTRA = "BOOK";
    private ArrayList<Book> bookList;
    private RecyclerView recyclerView;
    private BookListAdapter adapter;
    private FirebaseDatabase root;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get firebase database location
        root = FirebaseDatabase.getInstance();
        dbRef = root.getReference("Book");

        // Keep data sync between app and firebase
        dbRef.keepSynced(true);

        // Get the latest data from firebase
        getAllBook();

        // Initiate recycler view
        recyclerView = findViewById(R.id.main_recycler_book);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new BookListAdapter(bookList, MainActivity.this);
        recyclerView.setAdapter(adapter);

        // Initiate add button
        FloatingActionButton fabAddBook = findViewById(R.id.main_floatingbtn_add);
        fabAddBook.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
            startActivity(intent);
            finish();
        });

    }

    public void getAllBook() {
        bookList = new ArrayList<>();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    bookList.add(dataSnapshot.getValue(Book.class));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onBookClick(int position) {
        Intent intent = new Intent(MainActivity.this, EditBookActivity.class);
        intent.putExtra(EXTRA, bookList.get(position));
        Log.d(TAG, "Clicked");
        startActivity(intent);
        finish();
    }
}