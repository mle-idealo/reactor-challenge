package service

import model.AgeDTO
import model.HairDTO
import model.PersonDTO
import reactor.core.publisher.Mono

class PersonService(
    private val nameService: NameService,
    private val ageService: AgeService,
    private val hairService: HairService
) {

    companion object {
        private val DEFAULT = false
    }
    fun create(): Mono<PersonDTO> {
        val default = -1
        val name = nameService.get()
        val age = ageService.get().defaultIfEmpty(AgeDTO.DEFAULT)
        val hair = hairService.get().defaultIfEmpty(HairDTO.DEFAULT)


        return Mono.zip(name, age, hair)
            .map {
                val nullableAge = if (it.t2 == AgeDTO.DEFAULT) null else it.t2.value
                val nullableHair = if (it.t3 == HairDTO.DEFAULT) null else it.t3.value
                PersonDTO(it.t1.value, nullableAge, nullableHair)
            }
    }
}
