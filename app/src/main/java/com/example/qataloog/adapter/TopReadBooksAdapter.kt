package com.example.qataloog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qataloog.R
import com.example.qataloog.model.responsemodel.dashboard.TopRead

class TopReadBooksAdapter(val recommendedBooksClickInterface: TopReadBooksClickInterface) :
    RecyclerView.Adapter<TopReadBooksAdapter.TopReadBooksViewHolder>() {
        var topReadBookList: List<TopRead?> = listOf<TopRead>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
        private val nullRecommendedBookImagesUrl: String =
            "https://qataloog.s3.us-west-2.amazonaws." +
                    "com/image/1659980230.png/Pe6dj2P7vwBrH1rYgGI4lCDp0U6rWhVqOnp8E26K.png"


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopReadBooksViewHolder {
            return TopReadBooksViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.top_read_books_item_list, parent, false)
            )
        }

        override fun onBindViewHolder(holder: TopReadBooksViewHolder, position: Int) {
            val topReadBook = topReadBookList[position]
            if (topReadBook?.cover != null) {
                Glide.with(holder.topReadBookImages.context)
                    .load(topReadBook?.cover)
                    .override(150, 200)
                    .error(R.drawable.mock_recommended_books)
                    .centerCrop()
                    .into(holder.topReadBookImages)
            } else {
                Glide.with(holder.topReadBookImages.context)
                    .load(nullRecommendedBookImagesUrl)
                    .into(holder.topReadBookImages)
            }
            holder.topReadBookTitle.text = topReadBook?.title
            holder.topReadBookAuthor.text = topReadBook?.author

        }

        override fun getItemCount(): Int {
            return topReadBookList.size
        }

        class TopReadBooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val topReadBookImages = itemView.findViewById<ImageView>(R.id.top_read_book_images)!!
            val topReadBookTitle = itemView.findViewById<TextView>(R.id.top_read_book_title)!!
            val topReadBookAuthor = itemView.findViewById<TextView>(R.id.top_read_book_owner_name)!!

        }

        interface TopReadBooksClickInterface {
            // creating a method for click action
            // on recycler view item for updating it.
            fun onTopReadBookClick(bookData: TopRead)
        }
}