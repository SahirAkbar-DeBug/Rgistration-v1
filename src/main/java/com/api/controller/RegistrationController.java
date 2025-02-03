package com.api.controller;

import com.api.entity.Registration;
import com.api.payload.RegistrationDto;
import com.api.services.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {
    private RegistrationService registrationService;
    public RegistrationController(RegistrationService registrationService){
        this.registrationService=registrationService;
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<RegistrationDto>> getAllRegistrations(){
        List<RegistrationDto> registrations = registrationService.getRegistrations();
        return new ResponseEntity<>(registrations, HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<?> createRegistration(
           @Valid @RequestBody RegistrationDto registration,
           BindingResult result
    ){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        RegistrationDto reg = registrationService.createRegistion(registration);
        return new ResponseEntity<>(reg, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteRegistration(
           @Param("id") long id
    ){
        registrationService.deleteRegistration(id);
        return new ResponseEntity<>("Record Deleted", HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Registration> updateRegistration(@PathVariable long id, @RequestBody Registration registration){
        Registration updatedRegistration=registrationService.updateRegistration(id,registration);
        return new ResponseEntity<>(updatedRegistration, HttpStatus.OK);
    }
    @GetMapping("/get")
    public ResponseEntity<RegistrationDto> getRegistrationById(@Param("id") long id){
        RegistrationDto registrationDto=registrationService.getRegistrationById(id);
        return new ResponseEntity<>(registrationDto, HttpStatus.OK);
    }

}
