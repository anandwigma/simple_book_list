package com.matricaconsulting.simplebooklist;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class SimpleBookList extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
