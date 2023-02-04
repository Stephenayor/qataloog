package com.example.qataloog.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.qataloog.databinding.FragmentRecommendedBooksDetailsBinding
import com.example.qataloog.model.responsemodel.dashboard.Recommended
import com.example.qataloog.viewmodel.RecommendedBooksViewmodel
import java.net.URL


class RecommendedBooksDetailsFragment : Fragment() {
    private lateinit var binding: FragmentRecommendedBooksDetailsBinding
    private lateinit var recommendedBooks: Recommended
    private lateinit var filepath: String
    private lateinit var url: URL
    private var fileName: String? = null
    lateinit var recommendedBooksViewModel: RecommendedBooksViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecommendedBooksDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getRecommendedBooks()
        binding.downloadRecommendedBook.setOnClickListener {
            findNavController().navigate(
                RecommendedBooksDetailsFragmentDirections.
                actionRecommendedBooksDetailsFragmentToRecommendedPdfViewFragment(
                    recommendedBooks
                )
            )
            binding.progressBarBooks.visibility = View.VISIBLE
        }
    }

    private fun getRecommendedBooks() {
        val recommendedBookArgs = RecommendedBooksDetailsFragmentArgs
            .fromBundle(requireArguments())
        recommendedBooks = recommendedBookArgs.bookData
        showRecommendedBookDetails()
    }

    private fun initViewModel() {
        recommendedBooksViewModel =
            ViewModelProvider(this, object : ViewModelProvider.NewInstanceFactory() {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return context?.filesDir?.let {
                        RecommendedBooksViewmodel(
                            fileDir = it,
                            recommendedBooks
                        )
                    } as T
                }
            }).get(RecommendedBooksViewmodel::class.java)

        recommendedBooksViewModel
            .isFileReadyObserver.observe(viewLifecycleOwner, Observer<Boolean> {
                binding.progressBarBooks.visibility = View.GONE

                if (!it) {
                    Toast.makeText(activity, "FAILED TO DOWNLOAD PDF", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(activity, "PDF DOWNLOADED SUCCESSFULLY", Toast.LENGTH_LONG)
                        .show()
                    try {
                        binding.downloadRecommendedBook.visibility = View.INVISIBLE
                        binding.recommendedBookDetailsTitle.visibility = View.INVISIBLE
                        binding.recommendedBookDetailsImage.visibility = View.INVISIBLE
                        binding.recommendedBookDetailsAuthor.visibility = View.INVISIBLE
                        binding.pdfView.fromUri(
                            FileProvider.getUriForFile(
                                requireContext(),
                                "com.example.qataloog.fileprovider",
                                recommendedBooksViewModel
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
        recommendedBooks.downloadLink?.let {
            recommendedBooksViewModel
                .downloadPdfFile(it)
        }
    }

    private fun showRecommendedBookDetails() {
        binding.progressBarBooks.visibility = View.GONE
        Glide.with(requireContext())
            .load(recommendedBooks.cover)
            .placeholder(com.example.qataloog.R.drawable.qataloog_white_logo)
            .into(binding.recommendedBookDetailsImage)
        binding.recommendedBookDetailsTitle.text = recommendedBooks.title
        binding.recommendedBookDetailsAuthor.text = recommendedBooks.author
    }

}

