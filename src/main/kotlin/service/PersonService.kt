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

        // TODO: Implement this method according to its unit tests: PersonServiceTest
        return Mono.empty()
    }
}