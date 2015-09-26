package com.percolate.integrations.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
/**
 * Created by vijay.polsani on 9/25/15.
 */
@Data @NoArgsConstructor
public class User {

	private String lastName;
	private String firstName;
	private String color;
	@NonNull
	private String phoneNumber;
	private String zipcode;


}
