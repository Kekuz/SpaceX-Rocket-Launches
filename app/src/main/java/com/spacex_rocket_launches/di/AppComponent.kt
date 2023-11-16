package com.spacex_rocket_launches.di

import com.spacex_rocket_launches.ui.details.LaunchDetailsFragment
import com.spacex_rocket_launches.ui.list.LaunchListFragment
import dagger.Component

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent {

    fun inject(launchListFragment: LaunchListFragment)

    fun inject(launchDetailsFragment: LaunchDetailsFragment)
}