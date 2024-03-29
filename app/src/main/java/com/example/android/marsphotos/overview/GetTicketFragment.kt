package com.example.android.marsphotos.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.marsphotos.MainActivity
import com.example.android.marsphotos.R
import com.example.android.marsphotos.databinding.FragmentGetTicketBinding
import com.example.android.marsphotos.databinding.FragmentOverviewBinding
import com.example.android.marsphotos.network.YourLocation

class GetTicketFragment : Fragment() {
    private val viewModel: AllTicketViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGetTicketBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        // Sets the adapter of the photosGrid RecyclerView
        binding.photosGrid.adapter = TicketGridAdapter()


        return binding.root
    }
}