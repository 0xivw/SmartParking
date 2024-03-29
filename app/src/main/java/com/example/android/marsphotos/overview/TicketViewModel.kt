package com.example.android.marsphotos.overview

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsphotos.network.MarsApi
import com.example.android.marsphotos.network.PaymentData
import com.example.android.marsphotos.network.TicketData
import kotlinx.coroutines.launch
import retrofit2.Response

class TicketViewModel : ViewModel() {

    private val _idCard = MutableLiveData<String>()
    val idCard: MutableLiveData<String>
        get() = _idCard

    private val _license = MutableLiveData<String?>()
    val license: MutableLiveData<String?>
        get() = _license

    private val _type = MutableLiveData<String?>()
    val type: MutableLiveData<String?>
        get() = _type

    private val _username = MutableLiveData<String?>()
    val username: MutableLiveData<String?>
        get() = _username

    private val _parkingName = MutableLiveData<String?>()
    val parkingName: MutableLiveData<String?>
        get() = _parkingName

    private val _time = MutableLiveData<String?>()
    val time: MutableLiveData<String?>
        get() = _time

    private val _cost = MutableLiveData<Long?>()
    val cost: MutableLiveData<Long?>
        get() = _cost


    private val _paymentStatus = MutableLiveData<String?>()
    val paymentStatus: MutableLiveData<String?>
        get() = _paymentStatus

    private val _findStatus = MutableLiveData<Boolean>()
    val findStatus: MutableLiveData<Boolean>
        get() = _findStatus

    init {

    }

    fun addTicket(context: Context, ticketMode : TicketData) {
        viewModelScope.launch {
            try {
                Log.d("TAG", "addTicket: " + _type.value)
                val status1: Response<TicketData> =
                    MarsApi.retrofitService.addTicket(ticketMode)
                val jwt = status1.body()
                if (status1.isSuccessful) {
                    Log.d("TAG", "addTicket: " + status1.body()?.cost)
                    _cost.value = status1.body()?.cost
                    Toast.makeText(context, "Payment successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Payment failed", Toast.LENGTH_SHORT).show()
                }
            } catch (e: java.lang.Exception) {
                Log.d("TAG", "send ticket : hhuhuhu " + e)
                Toast.makeText(context, "Payment failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun findTicket(context: Context, id : String) {
        viewModelScope.launch {
            try {
                Log.d("TAG", "addTicket: " + _type.value)
                val status1: Response<TicketData> =
                    MarsApi.retrofitService.findTicket(id)
                val jwt = status1.body()
                if (status1.isSuccessful) {
                    Log.d("TAG", "addTicket: " + status1.body()?.cost)
                    _cost.value = status1.body()?.cost
                    _license.value = status1.body()?.license
                    _type.value = status1.body()?.type
                    _parkingName.value = status1.body()?.parkingName
                    _username.value = status1.body()?.username
                    //_time.value = status1.body()?.time
                    _findStatus.value = true

                    Toast.makeText(context, "Find successfully", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(context, "Not found ticket", Toast.LENGTH_SHORT).show()
                    _findStatus.value = false
                }
            } catch (e: java.lang.Exception) {
                Log.d("TAG", "send ticket : hhuhuhu " + e)
                Toast.makeText(context, "Not found ticket", Toast.LENGTH_SHORT).show()
                _findStatus.value = false
            }
        }
    }

    fun checkPayment(context: Context, id : String) {
        viewModelScope.launch {
            try {
                Log.d("TAG", "addTicket: " + _type.value)
                val status1: Response<PaymentData> =
                    MarsApi.retrofitService.checkPayment(id)

                if (status1.isSuccessful) {
                    _paymentStatus.value = "true"
                } else {
                    _paymentStatus.value = "false"
                    Toast.makeText(context, "Ticket existed", Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (e: java.lang.Exception) {
                Log.d("TAG", "send ticket : hhuhuhu " + e)
                _paymentStatus.value = "false"
                Toast.makeText(context, "Ticket existed", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}