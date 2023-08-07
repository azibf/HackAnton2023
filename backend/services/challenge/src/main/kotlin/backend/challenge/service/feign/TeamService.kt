package backend.challenge.service.feign

import backend.challenge.model.dto.user.TeamDto
import backend.challenge.model.dto.user.UserDto
import backend.shared.model.FeignResponse
import backend.shared.model.ListResponse
import backend.shared.util.ApiConstant
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.util.UUID

@FeignClient(name = "user", contextId = "team", path = "${ApiConstant.PREFIX}/${ApiConstant.V1}")
interface TeamService {
    @PostMapping(value = ["/team/ids"])
    fun listById(@RequestBody ids: List<UUID>): FeignResponse<ListResponse<TeamDto>>

    @GetMapping(value = ["/team/{id}"])
    fun findById(@PathVariable id: UUID): FeignResponse<TeamDto>

    @GetMapping(value = ["/team/{id}/participant"])
    fun findParticipants(@PathVariable id: UUID): FeignResponse<ListResponse<UserDto>>
}
