package com.stefanini.pruebatecnica.service.impl;

import com.stefanini.pruebatecnica.clients.PetClient;
import com.stefanini.pruebatecnica.dto.PetResponse;
import com.stefanini.pruebatecnica.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PetServiceImpl implements PetService {
  private PetClient petClient;

  public PetServiceImpl(PetClient petClient) {
    this.petClient = petClient;
  }

  public PetResponse findById(Long petId) {
    PetResponse petResponse = petClient.getPet(petId);
    log.info(petResponse.toString());
    return petClient.getPet(petId);
  }
}
