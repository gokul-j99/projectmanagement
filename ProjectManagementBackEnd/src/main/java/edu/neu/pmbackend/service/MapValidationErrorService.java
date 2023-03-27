/**
 * 
 */
package edu.neu.pmbackend.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @author gokuljayavel
 *
 */

@Service
public class MapValidationErrorService {

	public ResponseEntity<?> MapValidationErrorService(BindingResult result) {
		if (result.hasErrors()) {

			Map<String, String> errorMAp = new HashMap<>();
			for (FieldError s : result.getFieldErrors()) {
				errorMAp.put(s.getField(), s.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(errorMAp, HttpStatus.BAD_REQUEST);
		}

		return null;
	}

}
