package com.spacex_rocket_launches.presentation.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.spacex_rocket_launches.R
import com.spacex_rocket_launches.databinding.FragmentMissionDetailsBinding
import com.spacex_rocket_launches.databinding.LaunchViewBinding
import com.spacex_rocket_launches.domain.models.Launch

class LaunchViewHolder(private val binding: LaunchViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(model: Launch, onClick: (Launch) -> Unit) = with(binding) {

        nameTv.text = model.name

        repeatedUsesFirstStageTv.text = model.repeatedUsesFirstStage
        statusTv.text = model.status
        launchDateTv.text = model.launchDate

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