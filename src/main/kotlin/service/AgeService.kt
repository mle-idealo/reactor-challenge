package service

import reactor.core.publisher.Mono

class AgeService {

    fun get() : Mono<Int> {
        // will be mocked anyway
        return Mono.empty()
    }
}