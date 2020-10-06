package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacts")
public class MyRESTController {

	@Autowired
	ContactService contactService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Contact>> getAllContacts(@PathVariable String place) {
		return new ResponseEntity<>(contactService.getAllContact(place),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = {"application/json"})
	public ResponseEntity<String> addContact(@RequestBody Contact contact) {
		Contact res = contactService.addContact(contact);
		if(res == null) {
			return new ResponseEntity<>("Error. Contact not saved.",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Success. Contact saved.",HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<String> addContact(@PathVariable String email) {
		boolean res = contactService.delete(email);
		if(!res) {
			return new ResponseEntity<>("Error. Contact not deleted.",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Success. Contact deleted.",HttpStatus.OK);
	}
	
}