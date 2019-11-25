package com.guilhermemartinsdeoliveira.texoit.model.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class IntervalResponseDTO {
	
	private List<IntervalDTO> min;
	private List<IntervalDTO> max;

}
