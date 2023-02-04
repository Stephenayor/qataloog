package com.example.qataloog.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qataloog.adapter.BookSearchResultsAdapter
import com.example.qataloog.base.BaseFragment
import com.example.qataloog.databinding.FragmentBooksSearchResultBinding
import com.example.qataloog.model.responsemodel.bookssearch.BookQueryData
import com.example.qataloog.viewmodel.BooksSearchViewmodel
import javax.inject.Inject


class BooksSearchResultFragment : BaseFragment(),
    BookSearchResultsAdapter.BooksSearchResultClickInterface {
    private lateinit var binding: FragmentBooksSearchResultBinding
    private lateinit var bookSearchTitle: String
    private lateinit var booksSearchArgs: BooksSearchResultFragmentArgs
    @Inject
    lateinit var bookSearchViewModel: BooksSearchViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBooksSearchResultBinding.inflate(inflater, container, false)
        booksSearchArgs = BooksSearchResultFragmentArgs.fromBundle(requireArguments())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBarBookSearchFragment.visibility = View.VISIBLE
        bookSearchViewModel = qataloogAppComponent.getBooksSearchViewModel()
        bookSearchTitle = booksSearchArgs.booksSearchTitle
        getBookSearchResults()
    }

    private fun getBookSearchResults() {
        bookSearchViewModel.getBooksSearchResults(bookSearchTitle)
            .observe(viewLifecycleOwner, Observer {
                displaySearchedBooks(it.data)
//                val toast = Toast.makeText(activity, it.message, Toast.LENGTH_LONG)
//                toast.setGravity(Gravity.CENTER, 0, 0)
//                toast.show()
            })
        checkSearchedBookDataValidity()
    }

    private fun displaySearchedBooks(bookList: List<BookQueryData?>) {
        binding.progressBarBookSearchFragment.visibility = View.GONE
        val bookSearchResultsAdapter = BookSearchResultsAdapter(this)
        bookSearchResultsAdapter.booksSearchResultList = bookList
        binding.booksSearchRecyclerView.adapter = bookSearchResultsAdapter
        binding.booksSearchRecyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    private fun checkSearchedBookDataValidity() {
        bookSearchViewModel.getSlowNetworkMessageError().observe(viewLifecycleOwner, Observer {
            binding.progressBarBookSearchFragment.visibility = View.GONE
            binding.noNetworkErrorMessage.visibility = View.VISIBLE
        })

    }

    private fun showBooksNotFoundErrorMessage() {
        bookSearchViewModel.getBooksNotFoundError().observe(viewLifecycleOwner, Observer {
            binding.progressBarBookSearchFragment.visibility = View.GONE
            binding.booksNotFoundMessage.visibility = View.VISIBLE
        })
    }

    override fun onBookSearchClick(bookData: BookQueryData) {
        findNavController().navigate(
            BooksSearchResultFragmentDirections.
            actionBooksSearchResultFragmentToBooksSearchResultsDetailsFragment(
                bookData
            )
        )
    }
}