package com.spacex_rocket_launches.domain.models

import com.spacex_rocket_launches.domain.models.launch_request_body.DateUtc
import com.spacex_rocket_launches.domain.models.launch_request_body.Options
import com.spacex_rocket_launches.domain.models.launch_request_body.Query
import com.spacex_rocket_launches.domain.models.launch_request_body.RequestBody
import com.spacex_rocket_launches.domain.models.launch_request_body.Sort

object LaunchRequestFilter {
    val body = RequestBody(
        Query(DateUtc("2021-00-00T00:00:00.000Z")),
        Options(
            10,
            1,
            Sort("desc")
        )
    )
}