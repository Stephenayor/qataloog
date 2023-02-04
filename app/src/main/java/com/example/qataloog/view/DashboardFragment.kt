package com.example.qataloog.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qataloog.adapter.BooksListAdapter
import com.example.qataloog.adapter.TVETBooksAdapter
import com.example.qataloog.base.BaseFragment
import com.example.qataloog.database.dashboardbooks.recommended.RecommendedDatabase
import com.example.qataloog.databinding.FragmentDashboardBinding
import com.example.qataloog.model.responsemodel.dashboard.Recommended
import com.example.qataloog.model.responsemodel.dashboard.TVET
import com.example.qataloog.model.responsemodel.dashboard.TopRead
import com.example.qataloog.viewmodel.BooksSearchViewmodel
import com.example.qataloog.viewmodel.DashBoardListViewModel
import javax.inject.Inject


class DashboardFragment : BaseFragment(), BooksListAdapter.RecommendedBooksClickInterface,
    TVETBooksAdapter.TVETBooksClickInterface {
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var dashBoardViewModel: DashBoardListViewModel
    private lateinit var preferences: SharedPreferences
    private lateinit var recommendedBooksList: List<Recommended>
    private lateinit var topReadBooksList: List<TopRead>
    private lateinit var searchTitle: String
    @Inject
    lateinit var booksSearchViewModel: BooksSearchViewmodel
    @Inject
    lateinit var dashBoardListViewModel: DashBoardListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        binding.dashboardListProgress.visibility = View.VISIBLE
        setupViewModel()
        return binding.root
    }

    private fun setupViewModel() {
//        val application = requireNotNull(activity).application
//        val viewModelFactory = DashBoardListDataViewmodelFactory(application)
//        dashBoardViewModel =
//            ViewModelProvider(
//                this, viewModelFactory
//            ).get(DashBoardListViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dashboardListProgress.visibility = View.VISIBLE
        preferences = activity?.getSharedPreferences("login", Context.MODE_PRIVATE)!!
        val userName = preferences.getString("Username", null)
        binding.usernameText.text = "$userName!"
        val recommendedDatabase =
            RecommendedDatabase.getInstance(requireActivity().applicationContext)
        booksSearchViewModel = qataloogAppComponent.getBooksSearchViewModel()
        dashBoardListViewModel = qataloogAppComponent.getDashBoardListViewModel()

//        dashBoardListViewModel.getDashBoardListData(preferences).observe(viewLifecycleOwner, Observer {
//            binding.dashboardListProgress.visibility = View.GONE
//            Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
//            successMessage(it.message!!)
//            recommendedBooksList = it.data?.recommended!!
//            topReadBooksList = it.data!!.topRead
//            displayTvetBooks(it.data!!.categorizedBooks?.TVET!!)
//            displayRecommendedBooksList(recommendedBooksList)
//        })

        showConnectionErrorMessage()

        dashBoardListViewModel.getRecommendedBooks(preferences, recommendedDatabase)
            .observe(viewLifecycleOwner,
                Observer {
                    binding.dashboardListProgress.visibility = View.GONE
                    recommendedBooksList = it
                    displayRecommendedBooksList(recommendedBooksList)
                })
        binding.dashboardListProgress.visibility = View.GONE

        binding.booksSearchButton.setOnClickListener {
            searchTitle = binding.booksSearchBarEdittext.text.toString()
            findNavController().navigate(
                DashboardFragmentDirections.actionDashboardFragmentToBooksSearchResultFragment(
                    searchTitle
                )
            )
        }
    }

    private fun showConnectionErrorMessage() {
        booksSearchViewModel.getSlowNetworkMessageError().observe(viewLifecycleOwner, Observer {
            binding.dashboardListProgress.visibility = View.GONE
            binding.booksNotFoundMessage.visibility = View.VISIBLE
        })
    }

    private fun displayRecommendedBooksList(bookList: List<Recommended>) {
        binding.dashboardListProgress.visibility = View.GONE
        val recommendedBooksAdapter = BooksListAdapter(this)
        recommendedBooksAdapter.recommendedBooksList = bookList
        binding.recommendedBooksRecyclerView.adapter = recommendedBooksAdapter
        val layoutManager = GridLayoutManager(activity, 4)
        binding.recommendedBooksRecyclerView.layoutManager = layoutManager
        binding.recommendedBooksRecyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    private fun displayTvetBooks(tvetBooksList: List<TVET>) {
        val tvetBooksAdapter = TVETBooksAdapter(this)
        tvetBooksAdapter.tvetBooksList = tvetBooksList
        binding.tvetBooksRecyclerView.adapter = tvetBooksAdapter
        val layoutManager = GridLayoutManager(activity, 4)
        binding.tvetBooksRecyclerView.layoutManager = layoutManager
    }

    private fun successMessage(serverResponseMessage: String) {
        val toast = Toast.makeText(activity, serverResponseMessage, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    override fun onBookClick(bookData: Recommended) {
        findNavController().navigate(
            DashboardFragmentDirections.actionDashboardFragmentToRecommendedBooksDetailsFragment(
                bookData
            )
        )
    }

    override fun onTvetBookClick(bookData: TVET) {
        findNavController().navigate(
            DashboardFragmentDirections.actionDashboardFragmentToTvetBooksFragmentDetails(bookData)
        )
    }

}