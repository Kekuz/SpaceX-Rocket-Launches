package com.spacex_rocket_launches.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.spacex_rocket_launches.R
import com.spacex_rocket_launches.presentation.model.SingletonLaunch
import com.spacex_rocket_launches.databinding.FragmentMissionsListBinding
import com.spacex_rocket_launches.domain.models.Launch

class LaunchListFragment : Fragment() {

    private lateinit var binding: FragmentMissionsListBinding
    private lateinit var viewModel: LaunchListViewModel

    private val onClick: (Launch) -> Unit =
        {
            SingletonLaunch.launch = it
            findNavController().navigate(R.id.action_missionsListFragment_to_missionDetailsFragment)
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMissionsListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[LaunchListViewModel::class.java]


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.launchesLiveData.observe(activity as LifecycleOwner) {
            binding.launchesRv.adapter = LaunchAdapter(it, onClick)
        }

    }

}