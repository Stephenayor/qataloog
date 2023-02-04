package com.example.qataloog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qataloog.R
import com.example.qataloog.model.responsemodel.LibraryBooks

class LibraryBooksAdapter() : RecyclerView.Adapter<LibraryBooksAdapter.LibraryBooksDetailsViewHolder>()  {
    var booksInLibraryList: List<LibraryBooks?> = listOf<LibraryBooks>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LibraryBooksDetailsViewHolder {
        return LibraryBooksAdapter.LibraryBooksDetailsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.books_in_library_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LibraryBooksDetailsViewHolder, position: Int) {
        val libraryBooks = booksInLibraryList[position]
        Glide.with(holder.libraryBooksDetailsImage.context)
            .load(libraryBooks?.cover)
            .placeholder(R.drawable.mock_recommended_books)
            .into(holder.libraryBooksDetailsImage);
        holder.libraryBookDetailsTitle.text = libraryBooks?.title
        holder.libraryBookDetailsAuthor.text = libraryBooks?.author
    }

    override fun getItemCount(): Int {
        return booksInLibraryList.size
    }

    class LibraryBooksDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val libraryBooksDetailsImage = itemView.findViewById<ImageView>(R.id.library_book_details_images)!!
        val libraryBookDetailsTitle = itemView.findViewById<TextView>(R.id.library_book_details_title)!!
        val libraryBookDetailsAuthor = itemView.findViewById<TextView>(R.id.library_book_details_author)!!

    }

}