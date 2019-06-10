package com.thorton.grant.uspto.prototypewebapp.repositories.jpa.USPTO.tradeMark.document_events;

import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.document_events.FilingDocumentEvent;
import org.springframework.data.repository.CrudRepository;

public interface FilingDocumentEventRepository extends CrudRepository<FilingDocumentEvent, Long> {
}
