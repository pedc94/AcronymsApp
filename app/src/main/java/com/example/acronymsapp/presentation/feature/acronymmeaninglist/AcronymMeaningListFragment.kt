package com.example.acronymsapp.presentation.feature.acronymmeaninglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acronymsapp.core.extensions.showActionBar
import com.example.acronymsapp.databinding.FragmentMeaningListBinding
import com.example.acronymsapp.presentation.feature.acronymmeaninglist.adapter.AcronymMeaningListAdapter

class AcronymMeaningListFragment : Fragment() {
    private var _binding: FragmentMeaningListBinding? = null
    private val acronymMeaningAdapter by lazy { AcronymMeaningListAdapter() }
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMeaningListBinding.inflate(inflater, container, false)
        setUpRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).showActionBar()
    }

    private fun setUpRecyclerView() {
        binding.acronymMeaningList.adapter = acronymMeaningAdapter.apply {
            submitList(navArgs<AcronymMeaningListFragmentArgs>().value.acronymMeanings.toList())
        }
        binding.acronymMeaningList.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            navArgs<AcronymMeaningListFragmentArgs>().value.acronym

    }
}