package com.spacex_rocket_launches.presentation.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.spacex_rocket_launches.R
import com.spacex_rocket_launches.domain.models.Launch

class LaunchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val name: TextView = itemView.findViewById(R.id.name)
    private val image: ImageView = itemView.findViewById(R.id.image)

    fun bind(model: Launch, onClick: (Launch) -> Unit) {
        name.text = model.name

        image.load(model.missionIcon) {
            crossfade(true)
            placeholder(R.drawable.baseline_question_mark_24)
            error(R.drawable.baseline_question_mark_24)
            //transformations(CircleCropTransformation())
        }

        itemView.setOnClickListener{
            onClick.invoke(model)
        }
    }

}