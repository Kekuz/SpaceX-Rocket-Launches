package com.spacex_rocket_launches.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import coil.load
import com.spacex_rocket_launches.R
import com.spacex_rocket_launches.presentation.model.SingletonLaunch
import com.spacex_rocket_launches.databinding.FragmentMissionDetailsBinding
import com.spacex_rocket_launches.presentation.details.LaunchDetailsViewModel
import com.spacex_rocket_launches.presentation.details.LaunchFactory

class LaunchDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMissionDetailsBinding
    private val currentLaunch = SingletonLaunch.launch
    private lateinit var viewModel: LaunchDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMissionDetailsBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(
            this,
            LaunchFactory(currentLaunch)
        )[LaunchDetailsViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            arrowBackIv.setOnClickListener {
                findNavController().popBackStack()
            }

            nameTv.text = currentLaunch.name
            if (currentLaunch.missionLogo != "-") {
                missionLogoIv.load(currentLaunch.missionLogo) {
                    crossfade(true)
                    placeholder(R.drawable.place_holder_icon)
                    error(R.drawable.place_holder_icon)
                }
            } else {
                missionLogoIv.isVisible = false
            }

            if (currentLaunch.repeatedUsesFirstStage != "-") {
                repeatedUsesFirstStageTv.text =
                    "The number of repeated uses of the first stage: ${currentLaunch.repeatedUsesFirstStage}"
            } else {
                repeatedUsesFirstStageTv.isVisible = false
            }

            if (currentLaunch.status != "-") {
                statusTv.text =
                    "Status: ${currentLaunch.status}"
            } else {
                statusTv.isVisible = false
            }

            if (currentLaunch.details != "-") {
                detailsTv.text =
                    "Details: ${currentLaunch.details}"
            } else {
                detailsTv.isVisible = false
            }

            if (currentLaunch.missionTimeDate != "-") {
                missionTimeDateTv.text =
                    "Date and time of the mission: ${currentLaunch.missionTimeDate}"
            } else {
                missionTimeDateTv.isVisible = false
            }

        }


    }

}