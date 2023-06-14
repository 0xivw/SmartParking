package com.example.android.marsphotos.overview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsphotos.MainActivity
import com.example.android.marsphotos.R
import com.example.android.marsphotos.databinding.FragmentLoginBinding
import com.example.android.marsphotos.network.MarsApi
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginFragment : Fragment() {
    private val viewModel : AccountViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        val activity = requireActivity() // Get the activity associated with the fragment
        if (activity is MainActivity) {
            activity.changeHeader(true, null, false)
        }
        binding.textView3.setOnClickListener(View.OnClickListener { onLogin() })
        return binding.root
    }

    private fun onLogin() {
        val account = binding.edtUserName.text.toString()
        val password = binding.edtPassword.text.toString()
        viewModel.onPressButton(account, password)
    }

}