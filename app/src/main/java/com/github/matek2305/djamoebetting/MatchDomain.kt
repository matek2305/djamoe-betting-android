package com.github.matek2305.djamoebetting

import java.time.LocalDateTime
import java.util.*

object MatchDomain {
    data class Match(val homeTeamName: String, val awayTeamName: String, val startDate: LocalDateTime)
    data class MatchScore(val homeTeam: Int, val awayTeam: Int)
    data class MatchListItem(val id: UUID, val status: String, val details: Match, val bet: MatchScore?, val result: MatchScore?)
    data class MatchesResponse(val matches: List<MatchListItem>)
}



