package service

import reactor.core.publisher.Mono

class NameService {

    fun get() : Mono<String> {
        // will be mocked anyway
        return Mono.empty()
    }
}