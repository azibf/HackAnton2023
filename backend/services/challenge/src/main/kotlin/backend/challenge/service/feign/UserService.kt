package backend.challenge.service.feign

import backend.challenge.model.dto.user.ProfileDto
import backend.shared.model.FeignResponse
import backend.shared.model.ListResponse
import backend.shared.util.ApiConstant
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.util.UUID

@FeignClient(name = "user", path = "${ApiConstant.PREFIX}/${ApiConstant.V1}")
interface UserService {
    @PostMapping(value = ["/user/ids"])
    fun listById(@RequestBody ids: List<UUID>): FeignResponse<ListResponse<ProfileDto>>

    @GetMapping(value = ["/user/{id}"])
    fun findById(@PathVariable id: UUID): FeignResponse<ProfileDto>

    @GetMapping(value = ["/user/token/{token}"])
    fun findByToken(@PathVariable token: String): FeignResponse<ProfileDto>
}
