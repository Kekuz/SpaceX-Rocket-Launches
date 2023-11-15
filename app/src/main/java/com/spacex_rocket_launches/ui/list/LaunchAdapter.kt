package com.spacex_rocket_launches.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spacex_rocket_launches.databinding.LaunchViewBinding
import com.spacex_rocket_launches.domain.models.Launch

class LaunchAdapter (private val launches: List<Launch>, private val onClick: (Launch) -> Unit) : RecyclerView.Adapter<LaunchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LaunchViewBinding.inflate(inflater, parent, false)
        return LaunchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bind(launches[position], onClick)
    }

    override fun getItemCount(): Int {
        return launches.size
    }

}