package com.stefanini.pruebatecnica.service;

import com.stefanini.pruebatecnica.dto.PetResponse;

public interface PetService {

  public PetResponse findById(Long petId);
}
