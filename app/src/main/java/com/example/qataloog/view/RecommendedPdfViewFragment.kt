package com.example.qataloog.view

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
import com.example.qataloog.databinding.FragmentRecommendedPdfViewBinding
import com.example.qataloog.model.responsemodel.dashboard.Recommended
import com.example.qataloog.viewmodel.RecommendedBooksViewmodel


class RecommendedPdfViewFragment : Fragment() {
    lateinit var binding: FragmentRecommendedPdfViewBinding
    private lateinit var recommendedBooksViewModel: RecommendedBooksViewmodel
    lateinit var recommendedBooks: Recommended

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecommendedPdfViewBinding.inflate(inflater, container, false)
        val args = RecommendedPdfViewFragmentArgs.fromBundle(requireArguments())
        recommendedBooks = args.bookData
        displayRecommendedBookPdf()
        return binding.root
    }

    private fun displayRecommendedBookPdf() {
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

}