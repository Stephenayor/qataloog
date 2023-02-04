package com.example.qataloog.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.qataloog.R
import com.example.qataloog.databinding.FragmentBooksSearchResultsDetailsBinding
import com.example.qataloog.model.responsemodel.bookssearch.BookQueryData


class BooksSearchResultsDetailsFragment : Fragment() {
    private lateinit var binding: FragmentBooksSearchResultsDetailsBinding
    private lateinit var searchResultData: BookQueryData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBooksSearchResultsDetailsBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBookSearchResultDetails()
        binding.downloadSearchedBook.setOnClickListener {
            findNavController().navigate(
                BooksSearchResultsDetailsFragmentDirections.
                actionBooksSearchResultsDetailsFragmentToBooksSearchedPdfViewFragment(
                    searchResultData
                )
            )
        }
    }

    private fun getBookSearchResultDetails() {
        val bookSearchResultDetails =
            BooksSearchResultsDetailsFragmentArgs.fromBundle(requireArguments())
        searchResultData = bookSearchResultDetails.bookQueryData
        showBookSearchResultDetails()
    }

    private fun showBookSearchResultDetails() {
        binding.booksSearchResultDetailsProgress.visibility = View.GONE
        Glide.with(requireContext())
            .load(searchResultData.cover)
            .placeholder(com.example.qataloog.R.drawable.qataloog_white_logo)
            .into(binding.searchedBookDetailsImage)
        binding.searchedBookDetailsTitle.text = searchResultData.title
        binding.searchedBookDetailsAuthor.text = searchResultData.author
    }
}