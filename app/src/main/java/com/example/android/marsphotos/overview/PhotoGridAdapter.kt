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

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsphotos.databinding.GridViewItemBinding
import com.example.android.marsphotos.network.ParkingInfo

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */
class PhotoGridAdapter(private val itemClickListener: OnMapAction):
    ListAdapter<ParkingInfo, PhotoGridAdapter.MarsPhotosViewHolder>(DiffCallback) {

    /**
     * The MarsPhotosViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [ParkingInfo] information.
     */
    class MarsPhotosViewHolder(
        private var binding: GridViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        var mapMe : ImageView = binding.imvMap
        fun bind(marsPhoto: ParkingInfo) {
            Log.d("TAG", "bind: distance " + marsPhoto.distance)
            binding.parking = marsPhoto
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()

            mapMe = binding.imvMap
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of
     * [ParkingInfo] has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<ParkingInfo>() {
        override fun areItemsTheSame(oldItem: ParkingInfo, newItem: ParkingInfo): Boolean {
            return oldItem.distance == newItem.distance
        }

        override fun areContentsTheSame(oldItem: ParkingInfo, newItem: ParkingInfo): Boolean {
            return oldItem.name == newItem.name
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarsPhotosViewHolder {
        return MarsPhotosViewHolder(
            GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: MarsPhotosViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
        holder.mapMe.setOnClickListener {
            itemClickListener.onAction(marsPhoto.longitude, marsPhoto.latitude)
        }
    }

    interface OnMapAction {
        fun onAction(longtitude: Double, latitude: Double)
    }
}
