package com.thorton.grant.uspto.prototypewebapp.services.USPTO.tradeMark.application.actions;

import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.actions.PetitionService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions.Petition;
import com.thorton.grant.uspto.prototypewebapp.repositories.jpa.USPTO.tradeMark.application.actions.PetitionRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class Petition_JPA_Service implements PetitionService {



    private  final PetitionRepository  petitionRepository;

    public Petition_JPA_Service(PetitionRepository petitionRepository) {
        this.petitionRepository = petitionRepository;
    }


    @Override
    public Set<Petition> findAll() {




        Set<Petition>  petitions = new HashSet<>();

        petitionRepository.findAll().forEach(petitions::add);
        return  petitions;
    }

    @Override
    public Optional<Petition> findById(Long id) {
        return petitionRepository.findById(id);
    }

    @Override
    public Petition save(Petition object) {
        petitionRepository.save(object);
        return object;
    }

    @Override
    public void delete(Petition object) {
        petitionRepository.delete(object);

    }

    @Override
    public void deleteById(Long id) {
          petitionRepository.deleteById(id);
    }
}
