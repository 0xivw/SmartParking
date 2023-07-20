package com.example.android.marsphotos.overview

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.android.marsphotos.MainActivity
import com.example.android.marsphotos.databinding.FragmentDialogMonthYearPickerBinding
import java.util.*

class MonthYearPickerDialog(val date: Date = Date(), val fragment: AddTicketFragment) : DialogFragment() {

    companion object {
        private const val MAX_YEAR = 2040
        const val TAG = "MonthYearPickerDialog"
    }
    
    private lateinit var binding: FragmentDialogMonthYearPickerBinding

    private var listener: OnSetTime? = null

    fun setListener(listener: OnSetTime?) {
        this.listener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentDialogMonthYearPickerBinding.inflate(requireActivity().layoutInflater)
        val cal: Calendar = Calendar.getInstance().apply { time = date }

        binding.pickerMonth.run {
            minValue = 0
            maxValue = 11
            value = cal.get(Calendar.MONTH)
            displayedValues = arrayOf("Jan","Feb","Mar","Apr","May","June","July",
                "Aug","Sep","Oct","Nov","Dec")
        }

        binding.pickerYear.run {
            val year = cal.get(Calendar.YEAR)
            minValue = year
            maxValue = MAX_YEAR
            value = year
        }

        return AlertDialog.Builder(requireContext())
            .setTitle("Please Select View Month")
            .setView(binding.root)
            .setPositiveButton("Ok") { _, _ ->
                //listener?.onDateSet(null, binding.pickerYear.value, binding.pickerMonth.value, 1)
                val sharedViewModel = fragment.getSharedViewModel()
                Log.d(TAG, "onCreateDialog: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")
                val month = binding.pickerMonth.value + 1
                sharedViewModel.time.value = month.toString() + "-" + binding.pickerYear.value.toString()
            }
            .setNegativeButton("Cancel") { _, _ -> dialog?.cancel() }
            .create()
    }
}