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
import com.example.qataloog.R
import com.example.qataloog.databinding.FragmentTVETBooksPdfViewBinding
import com.example.qataloog.model.responsemodel.dashboard.TVET
import com.example.qataloog.viewmodel.RecommendedBooksViewmodel
import com.example.qataloog.viewmodel.TVETBooksViewmodel


class TVETBooksPdfViewFragment : Fragment() {
    private lateinit var tvetBooks: TVET
    private lateinit var binding: FragmentTVETBooksPdfViewBinding
    private lateinit var tvetBooksViewModel: TVETBooksViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTVETBooksPdfViewBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvetBookArgs = TVETBooksPdfViewFragmentArgs.fromBundle(requireArguments())
        tvetBooks = tvetBookArgs.tvetBook
        displayTvetBookPdf()
    }

    private fun displayTvetBookPdf() {
        tvetBooksViewModel =
            ViewModelProvider(this, object : ViewModelProvider.NewInstanceFactory() {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return context?.filesDir?.let {
                        TVETBooksViewmodel(
                            fileDir = it,
                            tvetBooks
                        )
                    } as T
                }
            }).get(TVETBooksViewmodel::class.java)

        tvetBooksViewModel
            .isFileReadyObserver.observe(viewLifecycleOwner, Observer<Boolean> {

                if (!it) {
                    Toast.makeText(activity, "FAILED TO DOWNLOAD PDF", Toast.LENGTH_LONG).show()
                    binding.tvetPdfViewProgressBar.visibility = View.GONE
                } else {
                    binding.tvetPdfViewProgressBar.visibility = View.GONE
                    Toast.makeText(activity, "PDF DOWNLOADED SUCCESSFULLY", Toast.LENGTH_LONG)
                        .show()
                    try {

                        binding.tvetPdfViewFragment.fromUri(
                            FileProvider.getUriForFile(
                                requireContext(),
                                "com.example.qataloog.fileprovider",
                                tvetBooksViewModel
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
        tvetBooks.downloadLink?.let {
            tvetBooksViewModel
                .downloadPdfFile(it)
        }
    }


}