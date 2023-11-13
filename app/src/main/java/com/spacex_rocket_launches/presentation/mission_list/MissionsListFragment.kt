package com.spacex_rocket_launches.presentation.mission_list

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.spacex_rocket_launches.Creator
import com.spacex_rocket_launches.R
import com.spacex_rocket_launches.databinding.FragmentMissionsListBinding
import com.spacex_rocket_launches.domain.api.LaunchInteractor
import com.spacex_rocket_launches.domain.models.Launch
import com.spacex_rocket_launches.domain.models.LaunchRequestFilter

class MissionsListFragment : Fragment() {

    private lateinit var viewModel: MissionListViewModel
    private lateinit var binding: FragmentMissionsListBinding

    private val handler = Handler(Looper.getMainLooper())
    private var detailsRunnable: Runnable? = null

    private val onClick: (Launch) -> Unit =
        {

        }

    private val launches = ArrayList<Launch>()
    private val launchAdapter = LaunchAdapter(launches, onClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMissionsListBinding.inflate(inflater, container, false)

        Creator.provideTrackInteractor()
            .searchLaunch(LaunchRequestFilter.body, object : LaunchInteractor.LaunchConsumer {
                override fun consume(foundLaunches: List<Launch>) {
                    Log.e("Launches", foundLaunches.toString())
                    val currentRunnable = detailsRunnable
                    if (currentRunnable != null) {
                        handler.removeCallbacks(currentRunnable)
                    }
                    val newDetailsRunnable = Runnable {
                        launches.addAll(foundLaunches)
                        launchAdapter.notifyDataSetChanged()
                    }
                    detailsRunnable = newDetailsRunnable
                    handler.post(newDetailsRunnable)
                }
            })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*binding.detailsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_missionsListFragment_to_missionDetailsFragment)
        }*/
        binding.launchesRv.adapter = launchAdapter
    }

}