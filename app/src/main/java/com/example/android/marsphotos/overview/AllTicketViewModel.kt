package com.example.android.marsphotos.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsphotos.Constant
import com.example.android.marsphotos.network.MarsApi
import com.example.android.marsphotos.network.TicketData
import kotlinx.coroutines.launch

class AllTicketViewModel : ViewModel() {
    private val _tickets = MutableLiveData<List<TicketData>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val tickets: LiveData<List<TicketData>> = _tickets

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        Log.d("TAG", "get Ticket xxxxxxxxxxxxx: ")
        viewModelScope.launch {
            try {
                _tickets.value = MarsApi.retrofitService.getTicket(Constant.USERNAME)
                Log.d("TAG", "getMarsPhotos: "+ tickets.value)
            } catch (e: java.lang.Exception) {
                Log.d("TAG", "send ticket : hhuhuhu " + e)
            }
        }
    }
}