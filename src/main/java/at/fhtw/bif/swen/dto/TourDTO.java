package at.fhtw.bif.swen.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public record TourDTO(Integer id, String name) {
}
