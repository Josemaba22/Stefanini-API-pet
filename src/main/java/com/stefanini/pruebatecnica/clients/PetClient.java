package com.stefanini.pruebatecnica.clients;

import com.stefanini.pruebatecnica.dto.PetDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PetClient {

  private final RestClient restClient;

  public PetClient(RestClient.Builder builder) {
    this.restClient = builder.baseUrl("https://petstore.swagger.io/v2/").build();
  }

  public PetDTO getPet(Long petId) {
    return restClient.get().uri("/pet/{petId}", petId).retrieve().body(PetDTO.class);
  }

  public PetDTO savePet(PetDTO petDTO) {
    return restClient.post().uri("/pet").body(petDTO).retrieve().body(PetDTO.class);
  }
}
