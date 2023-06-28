package com.example.android.marsphotos.overview

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.android.marsphotos.R
import com.example.android.marsphotos.databinding.FragmentAddTicketBinding
import com.example.android.marsphotos.databinding.FragmentLoginBinding

class AddTicketFragment : DialogFragment() {

    companion object {

        const val TAG = "AddTicketFragment"

    }
    private val viewModel: TicketViewModel by viewModels()
    private lateinit var binding: FragmentAddTicketBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTicketBinding.inflate(inflater)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }
}