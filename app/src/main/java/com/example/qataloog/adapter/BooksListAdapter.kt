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
import com.example.qataloog.model.responsemodel.dashboard.Recommended

class BooksListAdapter(val recommendedBooksClickInterface: RecommendedBooksClickInterface) :
    RecyclerView.Adapter<BooksListAdapter.RecommendedBooksViewHolder>() {
    var recommendedBooksList: List<Recommended?> = listOf<Recommended>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private val nullRecommendedBookImagesUrl: String =
        "https://qataloog.s3.us-west-2.amazonaws." +
                "com/image/1659980230.png/Pe6dj2P7vwBrH1rYgGI4lCDp0U6rWhVqOnp8E26K.png"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedBooksViewHolder {
        return RecommendedBooksViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recommended_books_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecommendedBooksViewHolder, position: Int) {
        val recommendedBook = recommendedBooksList[position]
        if (recommendedBook?.cover != null) {
            Glide.with(holder.bookImages.context)
                .load(recommendedBook?.cover)
                .apply(RequestOptions().override(100, 100))
                .placeholder(R.drawable.qataloog_white_logo)
                .error(R.drawable.qataloog_white_logo)
                .centerCrop()
                .into(holder.bookImages)
        } else {
            Glide.with(holder.bookImages.context)
                .load(nullRecommendedBookImagesUrl)
                .into(holder.bookImages)
        }
        holder.bookTitle.text = recommendedBook?.title
        holder.bookOwnerName.text = recommendedBook?.author
        holder.itemView.setOnClickListener {
            if (recommendedBook != null) {
                recommendedBooksClickInterface.onBookClick(recommendedBook)
            }
        }
    }

    override fun getItemCount(): Int {
        return recommendedBooksList.size
    }

    class RecommendedBooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookImages = itemView.findViewById<ImageView>(R.id.recommended_book_images)!!
        val bookTitle = itemView.findViewById<TextView>(R.id.book_category)!!
        val bookOwnerName = itemView.findViewById<TextView>(R.id.book_owner_name)!!

    }

    interface RecommendedBooksClickInterface {
        // creating a method for click action
        // on recycler view item for updating it.
        fun onBookClick(bookData: Recommended)
    }
}