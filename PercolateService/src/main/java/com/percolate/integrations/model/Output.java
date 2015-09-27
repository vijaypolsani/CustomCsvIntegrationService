package com.percolate.integrations.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by vijay.polsani on 9/26/15.
 */
@Data
@NoArgsConstructor
public class Output implements Serializable {
	private final List<User> entries = new ArrayList<>();
	private final List<Long> errors = new ArrayList<>();
}
