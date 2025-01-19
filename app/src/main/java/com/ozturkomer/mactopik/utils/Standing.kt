package com.ozturkomer.mactopik.utils

data class StandingsResponse(
    val standings: List<Standing>
)

data class Standing(
    val position: Int,
    val team: String,
    val played: Int,
    val won: Int,
    val drawn: Int,
    val lost: Int,
    val goalsFor: Int,
    val goalsAgainst: Int,
    val goalDifference: Int,
    val points: Int,
    val logo: String
)