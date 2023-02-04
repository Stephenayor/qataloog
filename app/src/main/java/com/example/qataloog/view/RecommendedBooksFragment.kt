package com.example.qataloog.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qataloog.adapter.BooksListAdapter
import com.example.qataloog.databinding.FragmentRecommendedBooksBinding
import com.example.qataloog.model.responsemodel.BookData
import com.example.qataloog.model.responsemodel.dashboard.Recommended


class RecommendedBooksFragment : Fragment(), BooksListAdapter.RecommendedBooksClickInterface {
    private lateinit var binding: FragmentRecommendedBooksBinding
    private lateinit var recommendedBookList: List<BookData>
    private lateinit var preferences: SharedPreferences
    private lateinit var recommendedBooksList: List<Recommended>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecommendedBooksBinding.inflate(inflater, container, false)
        binding.progressBar.visibility = View.VISIBLE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.progressBar.visibility = View.VISIBLE
        super.onViewCreated(view, savedInstanceState)
        preferences = activity?.getSharedPreferences("login", Context.MODE_PRIVATE)!!
        val recommendedBooksListArgs = RecommendedBooksFragmentArgs.fromBundle(requireArguments())
        recommendedBooksList = recommendedBooksListArgs.recommendedBooks.toList()

    }


    private fun displayRecommendedBooks(bookList: List<Recommended?>) {
        binding.progressBar.visibility = View.GONE
        val recommendedBooksAdapter = BooksListAdapter(this)
        recommendedBooksAdapter.recommendedBooksList = bookList
        binding.booksRecyclerView.adapter = recommendedBooksAdapter
        binding.booksRecyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    override fun onBookClick(bookData: Recommended) {
        findNavController().navigate(
            RecommendedBooksFragmentDirections.actionRecommendedBooksFragmentToRecommendedBooksDetailsFragment(
                bookData
            )
        )
    }


//    fun getRecommendedBooks() {
//        val authToken: String? = preferences.getString("authToken", null)
//        val recommendedBooksApi: RecommendedBooksApi? =
//            UsersRetrofitClientInstance.getRetrofitInstance()
//                ?.create(RecommendedBooksApi::class.java)
//        val call: Call<RecommendedBooks>? =
//            recommendedBooksApi?.getRecommendedBooks("Bearer $authToken")
//        call?.enqueue(object : Callback<RecommendedBooks> {
//            override fun onResponse(
//                call: Call<RecommendedBooks>,
//                response: Response<RecommendedBooks>
//            ) {
//                binding.progressBar.visibility = View.GONE
//                Toast.makeText(activity, "RECOMMENDED BOOKS", Toast.LENGTH_LONG).show()
//                if (response.isSuccessful) {
//                    recommendedBookList = response.body()?.data!!
//                }
//
//            }
//
//            override fun onFailure(call: Call<RecommendedBooks>, t: Throwable) {
//                Toast.makeText(activity, t.message, Toast.LENGTH_LONG).show()
//                t.printStackTrace()
//                binding.progressBar.visibility = View.GONE
//            }
//
//        })
//
//    }
}