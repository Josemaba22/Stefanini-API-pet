package com.stefanini.pruebatecnica.controller;

import com.stefanini.pruebatecnica.dto.Pet;
import com.stefanini.pruebatecnica.dto.PetResponse;
import com.stefanini.pruebatecnica.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  public ResponseEntity<PetResponse> getPetClient(@PathVariable Long petId) {
    PetResponse petResponse = petService.findById(petId);
    return ResponseEntity.ok(petResponse);
  }
}
