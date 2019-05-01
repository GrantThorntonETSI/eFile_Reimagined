package com.thorton.grant.uspto.prototypewebapp.repositories.jpa.USPTO.tradeMark.action;

import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions.OfficeActions;
import org.springframework.data.repository.CrudRepository;

public interface OfficeActionRepository  extends CrudRepository<OfficeActions, Long> {
}
