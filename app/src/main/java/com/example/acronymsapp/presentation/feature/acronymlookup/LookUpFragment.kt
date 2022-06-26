package com.example.acronymsapp.presentation.feature.acronymlookup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.acronymsapp.R
import com.example.acronymsapp.core.extensions.hideActionBar
import com.example.acronymsapp.core.util.ResultStatus
import com.example.acronymsapp.databinding.FragmentLookUpBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LookUpFragment : Fragment() {
    private val lookUpViewModel: LookUpViewModel by viewModels()
    private var _binding: FragmentLookUpBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_look_up, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = lookUpViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).hideActionBar()
    }

    private fun observeViewModel() {
        lookUpViewModel.data.observe(this) { result ->
            result?.let {
                when (result) {
                    is ResultStatus.Success -> {
                        result.data?.let {
                            view?.findNavController()
                                ?.navigate(
                                    LookUpFragmentDirections.actionLookUpFragmentToAcronymMeaningListFragment(
                                        it.lfs.toTypedArray(), it.sf
                                    )
                                )
                            binding.pbLoadingData.visibility = View.GONE
                        }
                    }
                    is ResultStatus.Loading -> {
                        binding.pbLoadingData.visibility = View.VISIBLE
                    }
                    is ResultStatus.Error -> {
                        binding.pbLoadingData.visibility = View.GONE
                        Snackbar.make(
                            binding.root,
                            result.message.toString(),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}