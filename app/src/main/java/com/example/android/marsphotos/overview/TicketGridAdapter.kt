package com.example.android.marsphotos.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsphotos.databinding.GridViewTicketBinding
import com.example.android.marsphotos.network.ParkingInfo
import com.example.android.marsphotos.network.TicketData

class TicketGridAdapter():
    ListAdapter<TicketData, TicketGridAdapter.MarsPhotosViewHolder>(DiffCallback) {

    /**
     * The MarsPhotosViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [ParkingInfo] information.
     */
    class MarsPhotosViewHolder(
        private var binding: GridViewTicketBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(marsPhoto: TicketData) {
            binding.ticket = marsPhoto
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()

        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of
     * [ParkingInfo] has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<TicketData>() {
        override fun areItemsTheSame(oldItem: TicketData, newItem: TicketData): Boolean {
            return oldItem.idCard == newItem.idCard
        }

        override fun areContentsTheSame(oldItem: TicketData, newItem: TicketData): Boolean {
            return oldItem.idCard == newItem.idCard
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
            GridViewTicketBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: MarsPhotosViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }

}