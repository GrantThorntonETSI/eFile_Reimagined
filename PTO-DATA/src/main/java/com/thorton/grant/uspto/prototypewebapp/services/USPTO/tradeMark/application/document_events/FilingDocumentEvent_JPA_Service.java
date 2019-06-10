package com.thorton.grant.uspto.prototypewebapp.services.USPTO.tradeMark.application.document_events;

import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.document_events.FilingDocumentEventService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.document_events.FilingDocumentEvent;
import com.thorton.grant.uspto.prototypewebapp.repositories.jpa.USPTO.tradeMark.document_events.FilingDocumentEventRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class FilingDocumentEvent_JPA_Service implements FilingDocumentEventService {



    private final FilingDocumentEventRepository filingDocumentEventRepository;


    public FilingDocumentEvent_JPA_Service(FilingDocumentEventRepository filingDocumentEventRepository) {
        this.filingDocumentEventRepository = filingDocumentEventRepository;
    }

    @Override
    public Set<FilingDocumentEvent> findAll() {

        Set<FilingDocumentEvent>  filingDocumentEvents = new HashSet<>();
        filingDocumentEventRepository.findAll().forEach(filingDocumentEvents::add);

        return filingDocumentEvents;
    }

    @Override
    public Optional<FilingDocumentEvent> findById(Long id) {
        return filingDocumentEventRepository.findById(id);
    }

    @Override
    public FilingDocumentEvent save(FilingDocumentEvent object) {
        filingDocumentEventRepository.save(object);
        return object;
    }

    @Override
    public void delete(FilingDocumentEvent object) {
        filingDocumentEventRepository.delete(object);

    }

    @Override
    public void deleteById(Long id) {
        filingDocumentEventRepository.deleteById(id);
    }
}
