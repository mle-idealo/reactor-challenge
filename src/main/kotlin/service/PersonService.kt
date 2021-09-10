package service

import model.PersonDTO
import reactor.core.publisher.Mono

class PersonService(
    private val nameService: NameService,
    private val ageService: AgeService,
    private val hairService: HairService
) {
    fun create() : Mono<PersonDTO> {
        val name = nameService.get()
        val age = ageService.get()
        val hair = hairService.get()

        return name
            .map { PersonDTO(it.value, null, null) }
            .flatMap { person -> age.map { person.copy(age = it.value) }.defaultIfEmpty(person) }
            .flatMap { person -> hair.map { person.copy(hair = it.value) }.defaultIfEmpty(person) }
    }
}