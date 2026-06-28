package com.stefanini.pruebatecnica.service;

import com.stefanini.pruebatecnica.dto.PetDTO;

public interface PetService {

  public PetDTO findById(Long petId);

  public PetDTO save(PetDTO petDTO);
}
