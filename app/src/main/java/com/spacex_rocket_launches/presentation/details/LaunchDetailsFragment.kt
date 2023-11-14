package com.spacex_rocket_launches.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.spacex_rocket_launches.presentation.model.SingletonLaunch
import com.spacex_rocket_launches.databinding.FragmentMissionDetailsBinding
import com.spacex_rocket_launches.domain.models.Launch

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
        binding.goBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.name.text = currentLaunch.name
        binding.details.text = currentLaunch.details
        binding.missionTimeDate.text = currentLaunch.missionTimeDate
        binding.launchDate.text = currentLaunch.launchDate


    }

}