package com.stefanini.pruebatecnica.service.impl;

import com.stefanini.pruebatecnica.clients.PetClient;
import com.stefanini.pruebatecnica.dto.PetDTO;
import com.stefanini.pruebatecnica.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PetServiceImpl implements PetService {
  private PetClient petClient;

  public PetServiceImpl(PetClient petClient) {
    this.petClient = petClient;
  }

  public PetDTO findById(Long petId) {
    PetDTO petDTO = petClient.getPet(petId);
    log.info(
        "Se consulta Pet: transactionId-" + MDC.get("transactionId") + "," + petDTO.toString());
    return petClient.getPet(petId);
  }

  public PetDTO save(PetDTO petDTO) {
    petDTO = petClient.savePet(petDTO);
    log.info("Se guarda Pet: transactionId-" + MDC.get("transactionId") + "," + petDTO.toString());
    return petDTO;
  }
}
