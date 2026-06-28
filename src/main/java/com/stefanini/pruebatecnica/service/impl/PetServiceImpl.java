package com.stefanini.pruebatecnica.service.impl;

import com.stefanini.pruebatecnica.clients.PetClient;
import com.stefanini.pruebatecnica.dto.PetResponse;
import com.stefanini.pruebatecnica.service.PetService;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl implements PetService {
  private PetClient petClient;

  public PetServiceImpl(PetClient petClient) {
    this.petClient = petClient;
  }

  public PetResponse findById(Long petId) {
    return petClient.getPet(petId);
  }
}
