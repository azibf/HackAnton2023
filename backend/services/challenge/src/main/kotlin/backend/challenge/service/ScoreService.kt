package backend.challenge.service

import backend.challenge.model.dto.score.ProfileScoreResponse
import backend.challenge.model.dto.score.ScoreResponse
import backend.shared.model.PageRequest
import backend.shared.model.PageResponse
import java.util.UUID

interface ScoreService {
    fun userScoreboard(page: PageRequest): PageResponse<ScoreResponse.User>
    fun teamScoreboard(page: PageRequest): PageResponse<ScoreResponse.Team>
    fun userScore(id: UUID): ScoreResponse
    fun teamScore(id: UUID): ScoreResponse
    fun profileScore(id: UUID): ProfileScoreResponse
    fun userHistory(id: UUID, page: PageRequest): ScoreResponse.History
    fun teamHistory(id: UUID, page: PageRequest): ScoreResponse.History
}
