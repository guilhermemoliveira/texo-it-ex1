package com.guilhermemartinsdeoliveira.texoit.model.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IntervalDTO {

	private String producer;
	private Integer interval;
	private Integer previousWin;
	private Integer followingWin;

}
