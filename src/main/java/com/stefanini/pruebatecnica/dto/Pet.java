package com.stefanini.pruebatecnica.dto;

public class Pet {
  public Long id;
  public String name;
  public String status;

  public Pet(Long id, String name, String status) {
    this.id = id;
    this.name = name;
    this.status = status;
  }
}
