package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ApiOperation(value = "adds person to the database")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "success!")
            }
    )
    public void addPerson(@Valid @NotNull @RequestBody Person person) {
        personService.addPerson(person);
    }


    @GetMapping
    @ApiOperation(value = "returns list of all people")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "success!")
            }
    )
    public List<Person> selectAllPeople() {
        return personService.selectAllPeople();
    }

    @GetMapping(path = "{id}")
    @ApiOperation(value = "returns person with provided id")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "success!")
            }
    )
    public Person selectPersonById(@PathVariable("id") UUID id) {
        return personService.selectPersonById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    @ApiOperation(value = "deletes person with provided id from database")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "success!")
            }
    )
    public void deletePersonById(@PathVariable("id") UUID id) {
        personService.deletePerson(id);
    }

    @PutMapping(path = "{id}")
    @ApiOperation(value = "updates person with provided id with information provided")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "success!")
            }
    )
    public void updatePerson(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Person personToUpdate) {
        personService.updatePerson(id, personToUpdate);
    }
}
