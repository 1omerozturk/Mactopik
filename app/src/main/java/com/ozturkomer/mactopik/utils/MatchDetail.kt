package com.ozturkomer.mactopik.utils

data class MatchDetail(
    val score: String,
    val team1: Team,
    val team2: Team,
    val goals: List<String>
)

data class Team(
    val name: String,
    val logo: String,
)