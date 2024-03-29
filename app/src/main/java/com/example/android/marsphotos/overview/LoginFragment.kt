package com.example.android.marsphotos.overview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsphotos.Constant
import com.example.android.marsphotos.MainActivity
import com.example.android.marsphotos.R
import com.example.android.marsphotos.databinding.FragmentLoginBinding
import com.example.android.marsphotos.network.MarsApi
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginFragment : Fragment() {
    private val viewModel: AccountViewModel by viewModels()
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
            (activity as MainActivity).setLeftIcon(context?.let {
                ContextCompat.getDrawable(
                    it,
                    R.drawable.ic_back
                )
            })
        }
        binding.textView3.setOnClickListener(View.OnClickListener { onLogin() })
        viewModel.isLoginSuccess.observe(
            /* owner = */
            viewLifecycleOwner,
        )
        
        /* observer = */
        { success ->
            if (success) {
                val overviewFragment = OverviewFragment.newInstance(true, Constant.USERNAME)
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.overviewFragment, overviewFragment, "overView")
                    .addToBackStack("overView")
                    .commit()
            }
        }
        
        binding.cbSignUp.setOnCheckedChangeListener { buttonView, isChecked  ->
            if (isChecked) {
                // CheckBox is checked
                binding.textView2.text = "Sign up your \nAccount"
                binding.textView3.text = "Sign up"
            } else {
                binding.textView2.text = "Login to your \nAccount"
                binding.textView3.text = "Log in"
            }
        }
        return binding.root
    }

    private fun onLogin() {
        val account = binding.edtUserName.text.toString()
        val password = binding.edtPassword.text.toString()
        val isSignUp = binding.cbSignUp.isChecked
        viewModel.onPressButton(context, account, password, isSignUp)
    }


}