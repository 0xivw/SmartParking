package com.example.android.marsphotos.overview

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.marsphotos.R
import com.example.android.marsphotos.databinding.FragmentAddTicketBinding
import com.example.android.marsphotos.databinding.FragmentLoginBinding
import com.example.android.marsphotos.network.TicketData
import java.util.*


class AddTicketFragment : Fragment(), OnSetTime {

    companion object {

        const val TAG = "AddTicketFragment"

    }
    private val viewModel: TicketViewModel by viewModels()
    private lateinit var binding: FragmentAddTicketBinding
    private var myInterface: OnSetTime? = null

    fun getSharedViewModel(): TicketViewModel {
        return viewModel
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTicketBinding.inflate(inflater)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.btnOk.setOnClickListener { l -> //getObject()
            getTicket()}
        binding.btnCancel.setOnClickListener{l ->
            binding.imvQr.visibility = View.VISIBLE
            binding.button.visibility = View.VISIBLE
        }
        binding.imageView7.setOnClickListener { l ->
            Log.d(TAG, "onCreateView: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
            activity?.let {
                val fragment : MonthYearPickerDialog = MonthYearPickerDialog(Date(), this)
                fragment.setListener(myInterface)
                fragment.show(
                    it.supportFragmentManager,
                    MonthYearPickerDialog.TAG
                )
            }
        }
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
        context?.let {
            viewModel.addTicket(it, ticketModel)
        }
        viewModel.cost.observe(this) { newData ->
            binding.tvCost.text = newData.toString()
        }
    }

    fun getTicket() {
        val id = binding.edtLicense.text.toString()
        context?.let {
            viewModel.findTicket(it, id)
        }
        /*viewModel.cost.observe(this) { newData ->
            binding.tvCost.text = newData.toString()
        }
        viewModel.time.observe(this) { newData ->
            binding.editTextTextPersonName2.text = newData.toString()
        }
        viewModel.type.observe(this) {newData ->
            binding.edtType.text = newData.toString()
        }*/
    }

    override fun setValue(year: Int, month: Int) {
        TODO("Not yet implemented")
        binding.editTextTextPersonName2.text = year.toString() + " " + month.toString()
    }

}