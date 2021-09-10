package service

import model.PersonDTO
import reactor.core.publisher.Mono

class PersonService(
    private val nameService: NameService,
    private val ageService: AgeService,
    private val hairService: HairService
) {
    fun create(): Mono<PersonDTO> {
        val default = -1
        val name = nameService.get()
        val age = ageService.get().defaultIfEmpty(default)
        val hair = hairService.get().defaultIfEmpty(default)


        return Mono.zip(name, age, hair)
            .map {
                val nullableAge = if (it.t2 == default) null else it.t2
                val nullableHair = if (it.t3 == default) null else it.t3
                PersonDTO(it.t1, nullableAge, nullableHair)
            }
    }
}
