package com.spacex_rocket_launches.data.network.dto.launch

import com.spacex_rocket_launches.data.network.dto.launch.core.Core
import com.spacex_rocket_launches.data.network.dto.launch.links.LinksDto

data class LaunchDto(
    val name: String?,
    val date_unix: Int,
    val links: LinksDto,
    val cores: List<Core>,
    val success: Boolean?,
    val details: String?,
    val crew: List<String>,

)