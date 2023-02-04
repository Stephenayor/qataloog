package com.example.qataloog.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qataloog.adapter.TopReadBooksAdapter
import com.example.qataloog.databinding.FragmentTopReadBooksBinding
import com.example.qataloog.model.responsemodel.dashboard.TopRead


class TopReadBooksFragment : Fragment(), TopReadBooksAdapter.TopReadBooksClickInterface {
    private lateinit var binding: FragmentTopReadBooksBinding
    private lateinit var topReadBooksList: List<TopRead>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTopReadBooksBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topReadsFragmentProgressBar.visibility = View.VISIBLE
        val topReadBooksArgs = TopReadBooksFragmentArgs.fromBundle(requireArguments())
        topReadBooksList = topReadBooksArgs.topReadList.toList()
        displayTopReadBooks(topReadBooksList)
    }

    private fun displayTopReadBooks(bookList: List<TopRead?>) {
        val topReadBooksAdapter = TopReadBooksAdapter(this)
        topReadBooksAdapter.topReadBookList = bookList
        binding.topReadBooksRecyclerView.adapter = topReadBooksAdapter
        binding.topReadBooksRecyclerView.setHasFixedSize(true)
        binding.topReadsFragmentProgressBar.visibility = View.GONE
        binding.topReadBooksRecyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    override fun onTopReadBookClick(bookData: TopRead) {
        TODO("Not yet implemented")
    }
}