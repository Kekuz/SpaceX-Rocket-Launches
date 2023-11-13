package com.spacex_rocket_launches.presentation.mission_list

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spacex_rocket_launches.R
import com.spacex_rocket_launches.domain.models.Launch

class LaunchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val name: TextView = itemView.findViewById(R.id.name)


    fun bind(model: Launch, onClick: (Launch) -> Unit) {
        name.text = model.name
        itemView.setOnClickListener{
            onClick.invoke(model)
        }
    }

}