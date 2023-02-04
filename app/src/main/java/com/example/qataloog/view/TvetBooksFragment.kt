package com.example.qataloog.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qataloog.R
import com.example.qataloog.databinding.FragmentTvetBooksBinding


class TvetBooksFragment : Fragment() {
   private lateinit var binding: FragmentTvetBooksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTvetBooksBinding.inflate(inflater, container, false)


        return binding.root
    }

}