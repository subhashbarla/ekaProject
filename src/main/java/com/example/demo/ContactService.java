package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
	
	@Autowired
	ContactRepository repository;

	public List<Contact>  getAllContact (String place){
		Iterable<Contact> all = repository.findAll();
		List<Contact> resp = new ArrayList<Contact>();
		for(Contact c:all) {
			if(place==null || place.trim().isEmpty() || (c.getPlace().toString().toLowerCase().contains(place.toLowerCase()))) {
				resp.add(c);
			}
		}
		return resp;
	}
	
	public Contact  addContact (Contact contact){
		Contact res = null;
		try {
		 res = repository.save(contact);
		 
		}catch(Exception e) {
			return null;
		}
		return res;
	}

	public boolean delete(String email) {
		try {
			if(email==null || email.trim().isEmpty()) {
				return false;
			}
				
			Iterable<Contact> all = repository.findAll();
			List<Contact> resp = new ArrayList<Contact>();
			for(Contact c:all) {
				if(c.getEmail().equals(email)) {
					repository.delete(c);
					return true;
				}
			}
			 
			}catch(Exception e) {
				return false;
			}
			return false;
	}
	
}