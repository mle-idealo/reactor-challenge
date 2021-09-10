import io.mockk.every
import io.mockk.mockk
import model.PersonDTO
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import org.reactivestreams.Publisher
import reactor.kotlin.core.publisher.toMono
import reactor.test.StepVerifier
import service.AgeService
import service.HairService
import service.NameService
import service.PersonService

internal class PersonServiceTest {

    companion object {
        private const val NAME = "disco"
        private const val AGE = 99
        private const val HAIR = 12345

        private val TEST_DATA = listOf(
            Triple(NAME, AGE, HAIR) to PersonDTO(name = NAME, age = AGE, hair = HAIR),
            Triple(NAME, null, HAIR) to PersonDTO(name = NAME, age = null, hair = HAIR),
            Triple(NAME, AGE, null) to PersonDTO(name = NAME, age = AGE, hair = null),
            Triple(NAME, null, null) to PersonDTO(name = NAME, age = null, hair = null),
            Triple(null, AGE, HAIR) to null,
            Triple(null, null, HAIR) to null,
            Triple(null, null, null) to null
        )
    }

    private val nameService = mockk<NameService>()
    private val ageService = mockk<AgeService>()
    private val hairService = mockk<HairService>()

    private val personService = PersonService(
        nameService = nameService,
        ageService = ageService,
        hairService = hairService
    )

    @TestFactory
    fun `create person`() = TEST_DATA.map { (input, expected) ->
        val shouldBeEmpty = expected == null
        val expectedMsg = if (shouldBeEmpty) "empty" else "$expected"

        dynamicTest("name: ${input.first}, age: ${input.second}, hair: ${input.third} -> person: $expectedMsg") {

            every { nameService.get() } returns input.first.toMono()
            every { ageService.get() } returns input.second.toMono()
            every { hairService.get() } returns input.third.toMono()

            personService.create()
                .stepVerify()
                .recordWith { mutableListOf() }
                .expectNextCount(if (shouldBeEmpty) 0 else 1)
                .consumeRecordedWith {
                    if (!shouldBeEmpty) assert(it.first() == expected)
                }
                .verifyComplete()
        }
    }
}

private fun <T> Publisher<T>.stepVerify() =
    StepVerifier.create(this)