package com.matricaconsulting.simplebooklist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder> {

    private ArrayList<Book> bookList;
    private OnBookListener onBookListener;

    public BookListAdapter(ArrayList<Book> bookList, OnBookListener onBookListener) {
        this.bookList = bookList;
        this.onBookListener = onBookListener;
    }

    @NonNull
    @Override
    public BookListAdapter.BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);
        return new BookViewHolder(view, onBookListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BookListAdapter.BookViewHolder holder, int position) {
        holder.tvBookCode.setText(bookList.get(position).getBookCode());
        holder.tvBookTitle.setText(bookList.get(position).getBookTitle());
        holder.tvPublisher.setText(bookList.get(position).getPublisher());
        holder.tvAuthor.setText(bookList.get(position).getAuthor());
        holder.tvYearRelease.setText(String.valueOf(bookList.get(position).getYearRelease()));
    }

    @Override
    public int getItemCount() {
        return (bookList != null) ? bookList.size() : 0;
    }

    public interface OnBookListener {
        void onBookClick(int position);
    }

    public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView tvBookCode;
        private final TextView tvBookTitle;
        private final TextView tvPublisher;
        private final TextView tvAuthor;
        private final TextView tvYearRelease;
        private BookListAdapter.OnBookListener onBookListener;

        public BookViewHolder(@NonNull View view, OnBookListener onBookListener) {
            super(view);
            tvBookCode = view.findViewById(R.id.row_text_code);
            tvBookTitle = view.findViewById(R.id.row_text_title);
            tvPublisher = view.findViewById(R.id.row_text_publisher);
            tvAuthor = view.findViewById(R.id.row_text_author);
            tvYearRelease = view.findViewById(R.id.row_text_year);
            this.onBookListener = onBookListener;
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onBookListener.onBookClick(getAdapterPosition());
        }
    }
}
