package com.example.qataloog.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.qataloog.R
import com.example.qataloog.adapter.LibraryAdapter
import com.example.qataloog.adapter.LibraryBooksAdapter
import com.example.qataloog.databinding.FragmentLibraryBooksBinding
import com.example.qataloog.model.responsemodel.LibraryBooks


class LibraryBooksFragment : Fragment() {
    private lateinit var binding: FragmentLibraryBooksBinding
    private lateinit var libraryBooksList: List<LibraryBooks>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLibraryBooksBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.libraryBooksProgressBar.visibility = View.VISIBLE
        val libraryBooksFragmentArgs = LibraryBooksFragmentArgs.fromBundle(requireArguments())
        libraryBooksList = libraryBooksFragmentArgs.libraryBooks.toList()
        showLibraryBooks(libraryBooksList)
    }

    private fun showLibraryBooks(libraryBookList: List<LibraryBooks>) {
        val libraryBooksAdapter = LibraryBooksAdapter()
        libraryBooksAdapter.booksInLibraryList = libraryBookList
        binding.booksInLibraryRecyclerView.adapter = libraryBooksAdapter
        val layoutManager = GridLayoutManager(activity, 2)
        binding.booksInLibraryRecyclerView.layoutManager = layoutManager
        binding.booksInLibraryRecyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                GridLayoutManager.HORIZONTAL)
        )
        binding.libraryBooksProgressBar.visibility = View.GONE
    }
}