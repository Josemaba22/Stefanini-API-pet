package com.stefanini.pruebatecnica.clients;

import com.stefanini.pruebatecnica.dto.PetResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PetClient {

  private final RestClient restClient;

  public PetClient(RestClient.Builder builder) {
    this.restClient = builder.baseUrl("https://petstore.swagger.io/v2/").build();
  }

  public PetResponse getPet(Long petId) {
    return restClient.get().uri("/pet/{petId}", petId).retrieve().body(PetResponse.class);
  }
}
