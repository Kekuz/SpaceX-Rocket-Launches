package com.spacex_rocket_launches.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spacex_rocket_launches.databinding.PilotViewBinding
import com.spacex_rocket_launches.domain.models.Pilot

class PilotAdapter (private val launches: List<Pilot>) : RecyclerView.Adapter<PilotViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PilotViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PilotViewBinding.inflate(inflater, parent, false)
        return PilotViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PilotViewHolder, position: Int) {
        holder.bind(launches[position])
    }

    override fun getItemCount(): Int {
        return launches.size
    }

}