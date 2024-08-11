package com.frankmoley.lil.designpatternsapp.controller;

import com.frankmoley.lil.designpatternsapp.builder.Contact;
import com.frankmoley.lil.designpatternsapp.builder.ContactBuilder;
import com.frankmoley.lil.designpatternsapp.factory.Pet;
import com.frankmoley.lil.designpatternsapp.factory.PetFactory;
import com.frankmoley.lil.designpatternsapp.repository.PresidentEntity;
import com.frankmoley.lil.designpatternsapp.repository.PresidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class AppController {

    @Autowired
    private PetFactory petFactory;

    @GetMapping
    public String getDefault(){
        return "{\"message\": \"Hello World\"}";
    }

    @PostMapping("adoptPet/{type}/{name}")
    public Pet adoptPet(@PathVariable String type, @PathVariable String name){
        Pet pet = this.petFactory.createPet(type);
        pet.setName(name);
        pet.feed();
        return pet;
    }

    @GetMapping("presidents")
    public List<Contact> getPresidents(){
        List<Contact> contacts = new ArrayList<>();

        Contact contact = new Contact();
        contact.setFirstName("George");
        contact.setLastName("Washington");
        contacts.add(contact);

        contacts.add(new Contact("John", "Adams", null));

        contacts.add(new ContactBuilder().setFirstName("Thomas").setLastName("Jefferson").buildContact());

        return contacts;
    }

    @Autowired
    PresidentRepository presidentRepository;

    @GetMapping("presidents/{id}")
    public PresidentEntity getPresById(@PathVariable Long id){
        return this.presidentRepository.findById(id).get();
    }

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("presidentContact/{id}")
    public Contact getPresContById(@PathVariable Long id){
        PresidentEntity entity = this.restTemplate
                .getForEntity("http://localhost:8080/presidents/{id}",
                        PresidentEntity.class,
                        id).getBody();
        return (new ContactBuilder()
            .setFirstName(entity.getFirstName())
        .setLastName(entity.getLastName())
        .setEmailAddress(entity.getEmailAddress())
        .buildContact());
    }

}
