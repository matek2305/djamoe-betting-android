package com.github.matek2305.djamoebetting

import java.util.*

data class MatchDetails(val homeTeamName: String, val awayTeamName: String, val startDate: String)

data class MatchItem(val id: UUID, val status: String, val details: MatchDetails)

data class MatchesResponse(val matches: List<MatchItem>)