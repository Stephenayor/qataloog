package com.example.qataloog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qataloog.R
import com.example.qataloog.model.responsemodel.dashboard.TVET

class TVETBooksAdapter (val tvetBooksClickInterface: TVETBooksClickInterface):
    RecyclerView.Adapter<TVETBooksAdapter.TVETBooksViewHolder>() {
    var tvetBooksList: List<TVET?> = listOf<TVET>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVETBooksViewHolder {
        return TVETBooksAdapter.TVETBooksViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.tvet_books_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TVETBooksViewHolder, position: Int) {
        val tvetBook = tvetBooksList[position]
        if (tvetBook?.cover != null) {
            Glide.with(holder.tvetBookImages.context)
                .load(tvetBook?.cover)
                .placeholder(R.drawable.qataloog_white_logo)
                .error(R.drawable.qataloog_white_logo)
                .centerCrop()
                .into(holder.tvetBookImages)
        } else {
            Glide.with(holder.tvetBookImages.context)
                .load(R.drawable.qataloog_white_logo)
                .into(holder.tvetBookImages)
        }
        holder.tvetBookTitle.text = tvetBook?.title
        holder.tvetBookAuthor.text = tvetBook?.author

        holder.itemView.setOnClickListener {
            if (tvetBook != null) {
                tvetBooksClickInterface.onTvetBookClick(tvetBook)
            }
        }
    }

    override fun getItemCount(): Int {
        return tvetBooksList.size
    }

    class TVETBooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvetBookImages = itemView.findViewById<ImageView>(R.id.tvet_book_images)!!
        val tvetBookTitle = itemView.findViewById<TextView>(R.id.tvet_book_title_view)!!
        val tvetBookAuthor = itemView.findViewById<TextView>(R.id.tvet_author)!!

    }

    interface TVETBooksClickInterface {
        // creating a method for click action
        // on recycler view item for updating it.
        fun onTvetBookClick(bookData: TVET)
    }
}