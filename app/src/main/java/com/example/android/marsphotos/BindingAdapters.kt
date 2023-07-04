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

package com.example.android.marsphotos

import android.graphics.Rect
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.marsphotos.network.MarsPhoto
import com.example.android.marsphotos.network.TicketData
import com.example.android.marsphotos.overview.MarsApiStatus
import com.example.android.marsphotos.overview.PhotoGridAdapter
import com.example.android.marsphotos.overview.TicketGridAdapter

/**
 * Updates the data shown in the [RecyclerView].
 */

@BindingAdapter("listData")
fun<T> bindRecyclerView(recyclerView: RecyclerView, data: List<T>?) {
    if (data != null) {
        if (data[0] is MarsPhoto) {
            Log.d("TAG", "bindRecyclerView:rrrrr over ")
            val listData = data?.map { item ->
                when (item) {
                    is MarsPhoto -> item
                    else -> null
                }
            } ?: emptyList()
            val adapter = recyclerView.adapter as PhotoGridAdapter
            Log.d("TAG", "bindRecyclerView: xxxxxxxxx " + (listData[0]?.distance ?: 0))
            adapter.submitList(listData)
        } else if (data[0] is TicketData) {
            Log.d("TAG", "bindRecyclerView: ticket")
            val listData = data?.map { item ->
                when (item) {
                    is TicketData -> item
                    else -> null
                }
            } ?: emptyList()
            val adapter = recyclerView.adapter as TicketGridAdapter
            adapter.submitList(listData)
        }
    }

}

/**
 * Uses the Coil library to load an image by URL into an [ImageView]
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

/**
 * This binding adapter displays the [MarsApiStatus] of the network request in an image view.  When
 * the request is loading, it displays a loading_animation.  If the request has an error, it
 * displays a broken image to reflect the connection error.  When the request is finished, it
 * hides the image view.
 */
@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView, status: MarsApiStatus) {
    when (status) {
        MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MarsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MarsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("android:text")
fun setDoubleText(view: TextView, value: Double) {
    val text = value.toString()
    view.text = text
}

@BindingAdapter("android:text")
fun setIntText(view: TextView, value: Int) {
    val text = value.toString()
    view.text = text
}

object ItemMarginBindingAdapter {
    @JvmStatic
    @BindingAdapter("itemMargin")
    fun setItemMargin(recyclerView: RecyclerView, margin: Int) {
        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.bottom = margin
            }
        })
    }
}

