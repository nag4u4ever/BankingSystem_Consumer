package com.nag.soap.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebitCard {
	private int bankid;

	private String name;

	private int age;

	private String state;

	private String zipcode;

	private String cardtype;
}
