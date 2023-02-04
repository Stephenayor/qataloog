package com.example.qataloog

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.qataloog.adapter.LibraryAdapter
import com.example.qataloog.base.BaseFragment
import com.example.qataloog.database.LibraryDatabase
import com.example.qataloog.databinding.FragmentMyLibraryBinding
import com.example.qataloog.model.responsemodel.LibrariesData
import com.example.qataloog.model.responsemodel.LibraryBooks
import com.example.qataloog.model.responsemodel.LibraryResponse
import com.example.qataloog.network.MyLibraryApi
import com.example.qataloog.network.RecommendedBooksClientInstance
import com.example.qataloog.viewmodel.LibraryViewModel
import com.example.qataloog.viewmodel.LibraryViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyLibraryFragment : BaseFragment(), LibraryAdapter.LibraryBooksClickInterface {
    private lateinit var binding: FragmentMyLibraryBinding
    private lateinit var libraryViewModel: LibraryViewModel
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyLibraryBinding.inflate(inflater, container, false)

        binding.createLibraryButton.setOnClickListener {
            findNavController().navigate(
                MyLibraryFragmentDirections.actionMyLibraryFragmentToCreateLibraryFragment()
            )
        }
        setupViewModel()
        return binding.root
    }

    private fun setupViewModel() {
        val application = requireNotNull(activity).application
        val dataSource = LibraryDatabase.getInstance(application).libraryDao
        val viewModelFactory = LibraryViewModelFactory(dataSource, application)
        libraryViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(LibraryViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        libraryViewModel.createdLibraries.observe(viewLifecycleOwner, Observer {

//        })

        binding.progressBarLibraryFragment.visibility = View.VISIBLE
        preferences = activity?.getSharedPreferences("login", Context.MODE_PRIVATE)!!
        libraryViewModel.getAllExistingLibraries(preferences).observe(viewLifecycleOwner, Observer {
            it -> showCreatedLibrariesList(it.data)
        })
    }

    private fun showCreatedLibrariesList(createdLibraries: List<LibrariesData>) {
        val libraryAdapter = LibraryAdapter(this)
        libraryAdapter.createdLibrariesList = createdLibraries
        binding.libraryBooksRecyclerView.adapter = libraryAdapter
        val layoutManager = GridLayoutManager(activity, 3)
        binding.libraryBooksRecyclerView.layoutManager = layoutManager
        binding.libraryBooksRecyclerView.addItemDecoration(
            DividerItemDecoration(
            context,
            GridLayoutManager.HORIZONTAL)
        )
        binding.progressBarLibraryFragment.visibility = View.GONE
    }

    fun Library() {
        val authToken: String? = preferences.getString("authToken", null)
        val api: MyLibraryApi? =
            RecommendedBooksClientInstance.getRetrofitInstance()
                ?.create(MyLibraryApi::class.java)
        val call: Call<LibraryResponse>? =
            api?.getAllCreatedLibrary("Bearer $authToken")
        call?.enqueue(object : Callback<LibraryResponse> {
            override fun onResponse(
                call: Call<LibraryResponse>,
                response: Response<LibraryResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d("Check Code", response.code().toString())
                    showCreatedLibrariesList(response.body()?.data!!)
                }
            }
            override fun onFailure(call: Call<LibraryResponse>, t: Throwable) {
                t.printStackTrace()

            }

        })

    }

    override fun onLibraryBookClick(bookInLibraryList: List<LibraryBooks>) {
        findNavController().navigate(
            MyLibraryFragmentDirections.actionMyLibraryFragmentToLibraryBooksFragment(
                bookInLibraryList.toTypedArray()
            )
        )
    }
}