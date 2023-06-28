/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.marsphotos.overview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.getDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.example.android.marsphotos.MainActivity
import com.example.android.marsphotos.R
import com.example.android.marsphotos.databinding.FragmentOverviewBinding
import com.example.android.marsphotos.network.MarsApi
import com.example.android.marsphotos.network.YourLocation
import kotlinx.coroutines.launch

private const val IS_LOGIN = "IS_LOGIN"
private const val HEADER = "HEADER"
/**
 * This fragment shows the the status of the Mars photos web services transaction.
 */
class OverviewFragment : Fragment(), PhotoGridAdapter.OnMapAction {

    private val viewModel: OverviewViewModel by viewModels()
    private var isLoginSuccess: Boolean? = null
    private var headerName: String? = null
    private lateinit var llButton: LinearLayout
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Boolean, param2: String?) =
            OverviewFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(IS_LOGIN, param1)
                    putString(HEADER, param2)
                }
            }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isLoginSuccess = it.getBoolean(IS_LOGIN)
            headerName = it.getString(HEADER)
        }
    }
    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        // Sets the adapter of the photosGrid RecyclerView
        binding.photosGrid.adapter = PhotoGridAdapter(this)

        llButton = binding.llButton
        llButton.setOnClickListener() {
            val location : YourLocation = (activity as MainActivity).getYourLocation()
            viewModel.getNearLocation(location.longtitude, location.latitude)
        }

        if (isLoginSuccess == true) {
            (activity as MainActivity).changeHeader(true, headerName, false)
            (activity as MainActivity).setLeftIcon(context?.let { getDrawable(it, R.drawable.ic_menu) })
        }

        return binding.root
    }

    override fun onAction(longitude: Double, latitude: Double) {
        val uri = Uri.parse("geo:$latitude,$longitude")
        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
        mapIntent.setPackage("com.google.android.apps.maps")

        // Verify that the Maps app is installed on the device
        if (context?.let { mapIntent.resolveActivity(it.packageManager) } != null) {
            startActivity(mapIntent)
        }
    }


}
