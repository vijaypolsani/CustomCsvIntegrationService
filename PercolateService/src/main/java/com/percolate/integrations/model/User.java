package com.percolate.integrations.model;

import java.io.Serializable;

import com.google.common.collect.ComparisonChain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Created by vijay.polsani on 9/25/15.
 */
@Data
@NoArgsConstructor
public class User implements Serializable, Comparable<User> {

	private String lastName;
	private String firstName;
	private String color;
	@NonNull
	private String phoneNumber;
	private String zipcode;

	@Override
	public int compareTo(User user) {
		return ComparisonChain.start()
				.compare(this.getLastName(), user.getLastName())
				.compare(this.getFirstName(), user.getFirstName())
				.result();
	}
}
