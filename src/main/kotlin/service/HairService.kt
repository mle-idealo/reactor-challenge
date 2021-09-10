package service

import reactor.core.publisher.Mono

class HairService {

    fun get() : Mono<Int> {
        // will be mocked anyway
        return Mono.empty()
    }
}