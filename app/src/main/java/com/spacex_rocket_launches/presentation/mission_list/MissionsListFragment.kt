package com.spacex_rocket_launches.presentation.mission_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.spacex_rocket_launches.R
import com.spacex_rocket_launches.databinding.FragmentMissionsListBinding

class MissionsListFragment : Fragment() {

    private lateinit var binding: FragmentMissionsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMissionsListBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.detailsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_missionsListFragment_to_missionDetailsFragment)
        }
    }

}