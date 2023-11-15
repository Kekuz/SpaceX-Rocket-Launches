package com.spacex_rocket_launches.ui.details

import androidx.recyclerview.widget.RecyclerView
import com.spacex_rocket_launches.databinding.PilotViewBinding
import com.spacex_rocket_launches.domain.models.Pilot

class PilotViewHolder(private val binding: PilotViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Pilot) = with(binding) {
        pilotNameTv.text = model.name
        agencyTv.text = model.agency
        pilotStatusTv.text = model.status
    }

}