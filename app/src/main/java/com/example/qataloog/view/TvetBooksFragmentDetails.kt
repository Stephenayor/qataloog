package com.example.qataloog.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.qataloog.databinding.FragmentTvetBooksDetailsBinding
import com.example.qataloog.model.responsemodel.dashboard.TVET


class TvetBooksFragmentDetails : Fragment() {
    private lateinit var binding: FragmentTvetBooksDetailsBinding
    private lateinit var tvetBook: TVET


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTvetBooksDetailsBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBarTvetBooks.visibility = View.VISIBLE
        getTvetBook()
        binding.downloadTvetBook.setOnClickListener {
            findNavController().navigate(
                TvetBooksFragmentDetailsDirections.actionTvetBooksFragmentDetailsToTVETBooksPdfViewFragment(
                    tvetBook
                )
            )
        }
    }

    private fun getTvetBook() {
        val tvetBookArgs = TvetBooksFragmentDetailsArgs
            .fromBundle(requireArguments())
        tvetBook = tvetBookArgs.tvetBook
        showTvetBookDetails()
    }

    private fun showTvetBookDetails() {
        binding.progressBarTvetBooks.visibility = View.GONE
        Glide.with(requireContext())
            .load(tvetBook.cover)
            .placeholder(com.example.qataloog.R.drawable.qataloog_white_logo)
            .into(binding.tvetBookDetailsImage)
        binding.tvetBookDetailsTitle.text = tvetBook.title
        binding.recommendedBookDetailsAuthor.text = tvetBook.author
    }

}