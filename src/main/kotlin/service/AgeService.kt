package service

import model.AgeDTO
import reactor.core.publisher.Mono

class AgeService {

    fun get() : Mono<AgeDTO> {
        // will be mocked anyway
        return Mono.empty()
    }
}