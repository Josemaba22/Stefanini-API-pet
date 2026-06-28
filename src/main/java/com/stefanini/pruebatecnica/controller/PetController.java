package com.stefanini.pruebatecnica.controller;

import com.stefanini.pruebatecnica.dto.ApiResponse;
import com.stefanini.pruebatecnica.dto.Pet;
import com.stefanini.pruebatecnica.dto.PetDTO;
import com.stefanini.pruebatecnica.service.PetService;
import jakarta.validation.Valid;
import java.time.Instant;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pet")
public class PetController {

  @Autowired private PetService petService;

  @GetMapping("/{petId}")
  public ResponseEntity<Pet> getPet(@PathVariable Long petId) {
    Pet pet = new Pet(petId, "testingPet1", "available");
    return ResponseEntity.ok(pet);
  }

  @GetMapping("client/{petId}")
  public ResponseEntity<PetDTO> getPetClient(@PathVariable Long petId) {
    PetDTO petDTO = petService.findById(petId);
    return ResponseEntity.ok(petDTO);
  }

  @PostMapping("")
  public ApiResponse save(@RequestBody @Valid Pet pet) {
    ApiResponse apiResponse = new ApiResponse();
    apiResponse.setTransactionId(MDC.get("transactionId"));
    apiResponse.setDateCreated(Instant.now());
    apiResponse.setStatus(pet.getStatus());
    apiResponse.setName(pet.getName());

    return apiResponse;
  }

  @PostMapping("/client")
  public ApiResponse saveClient(@RequestBody @Valid PetDTO petDTO) {

    petDTO = petService.save(petDTO);

    ApiResponse apiResponse = new ApiResponse();
    apiResponse.setTransactionId(MDC.get("transactionId"));
    apiResponse.setDateCreated(Instant.now());
    apiResponse.setStatus(petDTO.getStatus());
    apiResponse.setName(petDTO.getName());

    return apiResponse;
  }
}
