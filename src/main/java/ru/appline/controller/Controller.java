package ru.appline.controller;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.appline.logic.Pet;
import ru.appline.logic.PetModel;

@RestController
public class Controller {
	private static final PetModel petModel = PetModel.getInstance();
	private static final AtomicInteger newId = new AtomicInteger(1);
	
	@PostMapping(value = "/createPet", consumes = "application/json", produces = "text/plain")
	public String createPet(@RequestBody Pet pet) {
		petModel.add(pet, newId.getAndIncrement());
		return (petModel.getAll().size() == 1) ? "Congratulations, it's your first pet!" : "Created new pet " + pet.getName();
	}
	
	@GetMapping(value = "/getAll", produces = "application/json")
	public Map<Integer, Pet> getAll() {
		return petModel.getAll();
	}
	
	/*
	{
	    "id": 1
	}
*/
	@GetMapping(value = "/getPet", consumes = "application/json", produces = "application/json")
	public Pet getPet(@RequestBody Map<String, Integer> id) {
		return petModel.getFromList(id.get("id"));
	}
	
	@DeleteMapping(value = "/deletePet", consumes = "application/json", produces = "text/plain")
	public String deletePet(@RequestBody Map<String, Integer> id) {
		if (petModel.getAll().containsKey(id.get("id"))) petModel.del(id.get("id")); 
		else return "wrong PetID";
		
		return "Pet was removed from list";
	}
	
	@PutMapping(value = "/updatePet", consumes = "application/json", produces = "text/plain")
	public String updatePet(@RequestBody Map<String, String> json) {
		if (petModel.getAll().containsKey(Integer.parseInt(json.get("id")))) {
			petModel.upd(new Pet(json.get("name"), json.get("type"), Integer.parseInt(json.get("age"))), Integer.parseInt(json.get("id")));
		} 
		else return "wrong PetID";
		
		return "Pet " + json.get("id") + " was updated";
	}
}
