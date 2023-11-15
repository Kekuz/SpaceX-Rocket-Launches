package com.spacex_rocket_launches.ui.list

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.spacex_rocket_launches.R
import com.spacex_rocket_launches.databinding.LaunchViewBinding
import com.spacex_rocket_launches.domain.models.Launch

class LaunchViewHolder(private val binding: LaunchViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: Launch, onClick: (Launch) -> Unit) = with(binding) {

        nameTv.text = model.name

        if (model.repeatedUsesFirstStage != "-") {
            repeatedUsesFirstStageTv.isVisible = true
            repeatedUsesFirstStageStaticTv.isVisible = true
            repeatedUsesFirstStageTv.text =
                model.repeatedUsesFirstStage
        } else {
            repeatedUsesFirstStageTv.isVisible = false
            repeatedUsesFirstStageStaticTv.isVisible = false
        }

        if (model.status != "-") {
            statusTv.isVisible = true
            statusStaticTv.isVisible = true
            statusTv.text = model.status
        } else {
            statusTv.isVisible = false
            statusStaticTv.isVisible = false
        }

        if (model.launchDate != "-") {
            launchDateTv.isVisible = true
            launchDateStaticTv.isVisible = true
            launchDateTv.text = model.launchDate
        } else {
            launchDateTv.isVisible = false
            launchDateStaticTv.isVisible = false
        }

        missionIconIv.load(model.missionIcon) {
            crossfade(true)
            placeholder(R.drawable.place_holder_icon)
            error(R.drawable.place_holder_icon)
        }

        itemViewCl.setOnClickListener {
            onClick.invoke(model)
        }
    }

}