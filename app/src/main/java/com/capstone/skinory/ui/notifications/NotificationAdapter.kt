package com.capstone.skinory.ui.notifications

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.skinory.data.remote.response.RoutinesItem
import com.capstone.skinory.databinding.ItemNotificationBinding

class NotificationAdapter : ListAdapter<RoutinesItem, NotificationAdapter.NotificationViewHolder>(RoutineItemDiffCallback()) {

    private var routineType: String = "Day"
    fun submitRoutineType(type: String) {
        routineType = type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val binding = ItemNotificationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NotificationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(getItem(position), routineType)
    }

    class NotificationViewHolder(
        private val binding: ItemNotificationBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(routine: RoutinesItem, routineType: String) {
            binding.textView.text = routineType


            val products = routine.nameProduct?.split(",")?.map { it.trim() } ?: listOf("No Product")
            binding.textView3.text = products.mapIndexed { index, product ->
                "${index + 1}. $product"
            }.joinToString("\n")

            // You might want to add logic to handle the switch state
            // This depends on how you want to manage routine activation
            binding.switch2.isChecked = true // Default to checked
        }
    }
}

class RoutineItemDiffCallback : DiffUtil.ItemCallback<RoutinesItem>() {
    override fun areItemsTheSame(oldItem: RoutinesItem, newItem: RoutinesItem): Boolean {
        return oldItem.idProduct == newItem.idProduct
    }

    override fun areContentsTheSame(oldItem: RoutinesItem, newItem: RoutinesItem): Boolean {
        return oldItem == newItem
    }
}