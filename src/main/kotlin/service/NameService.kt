package service

import model.NameDTO
import reactor.core.publisher.Mono

class NameService {

    fun get() : Mono<NameDTO> {
        // will be mocked anyway
        return Mono.empty()
    }
}