package service

import model.HairDTO
import reactor.core.publisher.Mono

class HairService {

    fun get() : Mono<HairDTO> {
        // will be mocked anyway
        return Mono.empty()
    }
}