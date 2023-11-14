package com.spacex_rocket_launches.presentation.mission_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.spacex_rocket_launches.R
import com.spacex_rocket_launches.databinding.FragmentMissionsListBinding
import com.spacex_rocket_launches.domain.models.Launch
import com.spacex_rocket_launches.presentation.MainViewModel

class MissionsListFragment : Fragment() {

    private lateinit var binding: FragmentMissionsListBinding
    private val viewModel: MainViewModel by activityViewModels()

    private val onClick: (Launch) -> Unit =
        {
            findNavController().navigate(R.id.action_missionsListFragment_to_missionDetailsFragment)
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMissionsListBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.launchesLiveData.observe(activity as LifecycleOwner) {
            binding.launchesRv.adapter = LaunchAdapter(it, onClick)
        }

    }

}