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
import com.example.android.marsphotos.network.TicketData

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
        binding.btnOk.setOnClickListener { l -> getObject()}
        return binding.root
    }
    fun getObject(){
        val name = binding.tvLicense.text.toString()
        val license = binding.edtType.text.toString()
        val type = binding.edtColor.text.toString()
        val userName = binding.edtImage.text.toString()
        val parkingName = binding.editTextTextPersonName.text.toString()
        val time = binding.editTextTextPersonName2.text.toString()
        val ticketModel  = TicketData(name, license, type, userName, parkingName, time, null)
        context?.let { viewModel.addTicket(it, ticketModel) }
    }
}