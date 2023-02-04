package com.example.qataloog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.qataloog.R
import com.example.qataloog.model.responsemodel.bookssearch.BookQueryData


class BookSearchResultsAdapter( val bookSearchResultClickInterface: BooksSearchResultClickInterface) :
    RecyclerView.Adapter<BookSearchResultsAdapter.BooksSearchResultsViewHolder>() {
    var booksSearchResultList: List<BookQueryData?> = listOf<BookQueryData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private val nullRecommendedBookImagesUrl: String =
        "https://qataloog.s3.us-west-2.amazonaws." +
                "com/image/1659980230.png/Pe6dj2P7vwBrH1rYgGI4lCDp0U6rWhVqOnp8E26K.png"

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BooksSearchResultsViewHolder {
        return BooksSearchResultsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.book_search_result_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BooksSearchResultsViewHolder, position: Int) {
        val searchedBook = booksSearchResultList[position]
        if (searchedBook?.cover != null) {
            Glide.with(holder.bookSearchImages.context)
                .load(searchedBook?.cover)
                .apply(RequestOptions().override(100, 100))
                .placeholder(R.drawable.qataloog_white_logo)
                .error(R.drawable.qataloog_white_logo)
                .centerCrop()
                .into(holder.bookSearchImages)
        } else {
            Glide.with(holder.bookSearchImages.context)
                .load(nullRecommendedBookImagesUrl)
                .into(holder.bookSearchImages)
        }
        holder.bookSearchTitle.text = searchedBook?.title
        holder.bookSearchAuthor.text = searchedBook?.author
        holder.itemView.setOnClickListener {
            if (searchedBook != null) {
                bookSearchResultClickInterface.onBookSearchClick(searchedBook)
            }
        }
    }

    override fun getItemCount(): Int {
       return booksSearchResultList.size
    }


    class BooksSearchResultsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookSearchImages = itemView.findViewById<ImageView>(R.id.book_search_result_images)!!
        val bookSearchTitle = itemView.findViewById<TextView>(R.id.book_search_result_title)!!
        val bookSearchAuthor = itemView.findViewById<TextView>(R.id.book_search_result_author)!!

    }

    interface BooksSearchResultClickInterface {
        // creating a method for click action
        // on recycler view item for updating it.
        fun onBookSearchClick(bookData: BookQueryData)
    }

}