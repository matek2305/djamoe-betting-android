package com.github.matek2305.djamoebetting

import java.time.LocalDateTime
import java.util.*

object DomainModel {
    data class Match(val homeTeamName: String, val awayTeamName: String, val startDate: LocalDateTime)
    data class MatchItem(val id: UUID, val status: String, val details: Match)
    data class MatchesResponse(val matches: List<MatchItem>)
}



