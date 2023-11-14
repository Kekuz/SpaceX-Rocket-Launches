package com.spacex_rocket_launches.presentation.mission_details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.spacex_rocket_launches.R
import com.spacex_rocket_launches.databinding.FragmentMissionDetailsBinding
import com.spacex_rocket_launches.presentation.MainViewModel

class MissionDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMissionDetailsBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMissionDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.goBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        /*binding.name.text = viewModel.launchLiveData.value?.name
        binding.details.text = viewModel.launchLiveData.value?.details
        binding.missionTimeDate.text = viewModel.launchLiveData.value?.missionTimeDate
        binding.launchDate.text = viewModel.launchLiveData.value?.launchDate*/
    }

}