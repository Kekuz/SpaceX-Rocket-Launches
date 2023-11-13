package com.spacex_rocket_launches.presentation.mission_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.spacex_rocket_launches.R
import com.spacex_rocket_launches.databinding.FragmentMissionDetailsBinding

class MissionDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMissionDetailsBinding

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
            findNavController().navigate(R.id.action_finish_missionDetailsFragment)
        }
    }

}