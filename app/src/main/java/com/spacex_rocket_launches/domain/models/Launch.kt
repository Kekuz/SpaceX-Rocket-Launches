package com.spacex_rocket_launches.domain.models

data class Launch(
    val name: String,
    val status: String,
    val missionIcon: String,
    val repeatedUsesFirstStage: Int,
    val launchDate: String, //ДД-ММ-ГГГГ
    val missionLogo: String,
    val details: String,
    val missionTimeDate: String,//ЧЧ-ММ ДД-ММ-ГГГГ
    val crew: List<String>,

)
