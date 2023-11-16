package com.spacex_rocket_launches.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import coil.load
import com.spacex_rocket_launches.R
import com.spacex_rocket_launches.app.App
import com.spacex_rocket_launches.presentation.model.SingletonLaunch
import com.spacex_rocket_launches.databinding.FragmentMissionDetailsBinding
import com.spacex_rocket_launches.presentation.details.LaunchDetailsViewModel
import com.spacex_rocket_launches.presentation.details.LaunchDetailsFactory
import com.spacex_rocket_launches.util.Debounce
import javax.inject.Inject

class LaunchDetailsFragment : Fragment() {

    @Inject
    lateinit var launchListFactory: LaunchDetailsFactory
    @Inject
    lateinit var debounce: Debounce

    private lateinit var binding: FragmentMissionDetailsBinding
    private val currentLaunch = SingletonLaunch.launch
    private lateinit var viewModel: LaunchDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.applicationContext as App).appComponent.inject(this)
        binding = FragmentMissionDetailsBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(
            this,
            launchListFactory
        )[LaunchDetailsViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            arrowBackIv.setOnClickListener {
                if(debounce.clickDebounce()){
                    findNavController().popBackStack()
                }
            }
            //Чтоб не пропал
            crewListRv.adapter = PilotAdapter(listOf())

            //Чтоб пропал
            if(viewModel.isCrewEmpty){
                loadingCrewPb.isVisible = false
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
                    "${getString(R.string.number_of_flight_full_phrase)} ${currentLaunch.repeatedUsesFirstStage}"
            } else {
                repeatedUsesFirstStageTv.isVisible = false
            }

            if (currentLaunch.status != "-") {
                statusTv.text =
                    "${getString(R.string.success)} ${currentLaunch.status}"
            } else {
                statusTv.isVisible = false
            }

            if (currentLaunch.details != "-") {
                detailsTv.text =
                    "${getString(R.string.details)} ${currentLaunch.details}"
            } else {
                detailsTv.isVisible = false
            }

            if (currentLaunch.missionTimeDate != "-") {
                missionTimeDateTv.text =
                    "${getString(R.string.date_time_mission)} ${currentLaunch.missionTimeDate}"
            } else {
                missionTimeDateTv.isVisible = false
            }

            viewModel.pilotsLiveData.observe(activity as LifecycleOwner) {
                crewListRv.adapter = PilotAdapter(it)
                binding.crewTv.isVisible = true
                binding.loadingCrewPb.isVisible = false
            }

            viewModel.placeholderLiveData.observe(activity as LifecycleOwner){
                if (it != "-") {
                    binding.crewTv.isVisible = true
                    binding.errorPlaceholderTv.isVisible = true
                    binding.errorPlaceholderTv.text = it

                    binding.loadingCrewPb.isVisible = false
                }
            }

        }

    }

}