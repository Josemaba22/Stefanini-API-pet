package com.stefanini.pruebatecnica.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PetDTO {

  private Long id;
  private CategoryDTO category;
  private String name;
  private List<String> photoUrls;
  private List<TagDTO> tags;
  private String status;
}
