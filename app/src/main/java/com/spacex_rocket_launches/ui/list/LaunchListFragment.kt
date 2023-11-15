package com.spacex_rocket_launches.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.spacex_rocket_launches.R
import com.spacex_rocket_launches.presentation.model.SingletonLaunch
import com.spacex_rocket_launches.databinding.FragmentMissionsListBinding
import com.spacex_rocket_launches.domain.models.Launch
import com.spacex_rocket_launches.domain.models.LaunchRequestFilter
import com.spacex_rocket_launches.presentation.list.LaunchListViewModel
import com.spacex_rocket_launches.presentation.model.SingletoneHasNextPageInfo

class LaunchListFragment : Fragment() {

    private lateinit var binding: FragmentMissionsListBinding
    private lateinit var viewModel: LaunchListViewModel

    private val onClick: (Launch) -> Unit =
        {
            SingletonLaunch.launch = it
            Log.d("Launch", it.toString())
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

        binding.reconnectBtn.setOnClickListener {
            viewModel.doRequest()
        }


        viewModel.launchesLiveData.observe(activity as LifecycleOwner) {
            binding.launchesRv.adapter = LaunchAdapter(it, onClick)
            binding.pagingPb.isVisible = false
        }

        viewModel.placeholderLiveData.observe(activity as LifecycleOwner) {
            if (it != "-") {
                binding.placeholderErrorTv.isVisible = true
                binding.placeholderErrorTv.text = it
                binding.reconnectBtn.isVisible = true

                binding.launchesRv.isVisible = false
                binding.pagingPb.isVisible = false
            } else {
                binding.placeholderErrorTv.isVisible = false
                binding.reconnectBtn.isVisible = false

                binding.launchesRv.isVisible = true
            }

        }
        binding.nestedScroll.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                if (SingletoneHasNextPageInfo.hasNextPage) {
                    LaunchRequestFilter.body.options.page++
                    Log.d("Page", "Loaded page ${LaunchRequestFilter.body.options.page.toString()}")
                    binding.pagingPb.isVisible = true
                    viewModel.doRequest()
                }
            }
        })

    }

}