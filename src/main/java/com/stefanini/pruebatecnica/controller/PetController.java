package com.stefanini.pruebatecnica.controller;

import com.stefanini.pruebatecnica.dto.Pet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pet")
public class PetController {

  @GetMapping("/{petId}")
  public ResponseEntity<Pet> getPet(@PathVariable Long petId) {
    Pet pet = new Pet(petId, "testingPet1", "available");
    return ResponseEntity.ok(pet);
  }
}
