package com.example.qataloog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qataloog.R
import com.example.qataloog.adapter.LibraryAdapter.*
import com.example.qataloog.model.responsemodel.LibrariesData
import com.example.qataloog.model.responsemodel.LibraryBooks

class LibraryAdapter(val libraryBooksClickInterface: LibraryBooksClickInterface)
    : RecyclerView.Adapter<LibraryBooksViewHolder>()  {
    var createdLibrariesList: List<LibrariesData?> = listOf<LibrariesData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryBooksViewHolder {
        return LibraryBooksViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.library_item_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LibraryBooksViewHolder, position: Int) {
        val createdLibrary = createdLibrariesList[position]
        Glide.with(holder.libraryIcons.context)
            .load(createdLibrary?.icon)
            .placeholder(R.drawable.mock_recommended_books)
            .into(holder.libraryIcons);
        holder.libraryTitle.text = createdLibrary?.name
        holder.itemView.setOnClickListener {
            libraryBooksClickInterface.onLibraryBookClick(createdLibrary?.books!!)
        }
    }

    override fun getItemCount(): Int {
       return createdLibrariesList.size
    }

    class LibraryBooksViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val libraryIcons = itemView.findViewById<ImageView>(R.id.library_icon_images)!!
        val libraryTitle = itemView.findViewById<TextView>(R.id.library_title_view)!!
    }

    interface LibraryBooksClickInterface {
        // creating a method for click action
        // on recycler view item for updating it.
        fun onLibraryBookClick(bookInLibraryList: List<LibraryBooks>)
    }
}