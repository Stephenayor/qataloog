package com.example.qataloog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.qataloog.databinding.FragmentBooksSearchedPdfViewBinding
import com.example.qataloog.model.responsemodel.bookssearch.BookQueryData
import com.example.qataloog.view.RecommendedPdfViewFragmentArgs
import com.example.qataloog.viewmodel.RecommendedBooksViewmodel
import com.example.qataloog.viewmodel.SearchedBookDetailsViewmodel


class BooksSearchedPdfViewFragment : Fragment() {
   private lateinit var binding: FragmentBooksSearchedPdfViewBinding
   private lateinit var bookSearchedDetailsViewModel: SearchedBookDetailsViewmodel
   private lateinit var searchedBooks: BookQueryData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBooksSearchedPdfViewBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = BooksSearchedPdfViewFragmentArgs.fromBundle(requireArguments())
        searchedBooks = args.bookQueryData
        displaySearchedBookPdf()
    }

    private fun displaySearchedBookPdf() {
        bookSearchedDetailsViewModel =
            ViewModelProvider(this, object : ViewModelProvider.NewInstanceFactory() {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return context?.filesDir?.let {
                        SearchedBookDetailsViewmodel(
                            fileDir = it,
                            searchedBooks
                        )
                    } as T
                }
            }).get(SearchedBookDetailsViewmodel::class.java)

        bookSearchedDetailsViewModel
            .isFileReadyObserver.observe(viewLifecycleOwner, Observer<Boolean> {

                if (!it) {
                    Toast.makeText(activity, "FAILED TO DOWNLOAD PDF", Toast.LENGTH_LONG).show()
                    binding.pdfViewProgressBar.visibility = View.GONE
                } else {
                    binding.pdfViewProgressBar.visibility = View.GONE
                    Toast.makeText(activity, "PDF DOWNLOADED SUCCESSFULLY", Toast.LENGTH_LONG)
                        .show()
                    try {
                        binding.pdfViewFragment.fromUri(
                            FileProvider.getUriForFile(
                                requireContext(),
                                "com.example.qataloog.fileprovider",
                                bookSearchedDetailsViewModel
                                    .getPdfFileUri()
                            )
                        )
                            .load()
                    } catch (e: Exception) {
                        Toast.makeText(
                            activity, "Failed to download PDF",
                            Toast.LENGTH_LONG
                        ).show()

                    }
                }
            })
        searchedBooks.downloadLink?.let {
            bookSearchedDetailsViewModel
                .downloadPdfFile(it)
        }
    }
    }
