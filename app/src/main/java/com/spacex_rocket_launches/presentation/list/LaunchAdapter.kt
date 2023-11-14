package com.spacex_rocket_launches.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spacex_rocket_launches.R
import com.spacex_rocket_launches.domain.models.Launch

class LaunchAdapter (private val launches: List<Launch>, private val onClick: (Launch) -> Unit) : RecyclerView.Adapter<LaunchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.launch_view, parent, false)
        return LaunchViewHolder(view)
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bind(launches[position], position, onClick)
    }

    override fun getItemCount(): Int {
        return launches.size
    }

}