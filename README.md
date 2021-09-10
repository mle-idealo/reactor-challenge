# Project Reactor: Optional Properties - Challenge 

While programming with [project reactor][1] you have sometimes the situation where you want to build a data model through other data sources where some results of those dependent data sources are optional and some are mandatory in the resulting data model.
In project reactor it is not allowed to publish null values. Which make it a little tricky to handle this use case.

With this repository we want to collect good solutions for this use case. 

### (Dummy) Domain description
* 1 service for generating "persons"
* 3 services provide properties each property (name, age, hair)
* ```name``` is mandatory
* ```age``` & ```hair``` are optional


### Technical requirements
* keep model data class immutable
* avoid intermediate object creation as much as possible
* no blocking code
* avoid side effects (.doOnNext, .doOnEach etc.)
* parallelism of tasks (getting name, age, hair) should be support (not yet implemented)

### How?
1. Implement ```PersonService.create```
2. Run ```./gradlew test```

[1]: https://projectreactor.io/


### Possible solution

[a link to a branch](/chrgue-idealo/reactor-challenge/tree/possible-solution)