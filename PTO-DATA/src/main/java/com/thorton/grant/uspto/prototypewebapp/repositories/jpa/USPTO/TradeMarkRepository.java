package com.thorton.grant.uspto.prototypewebapp.repositories.jpa.USPTO;

import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.TradeMark;
import org.springframework.data.repository.CrudRepository;

public interface TradeMarkRepository extends CrudRepository<TradeMark, Long> {
}
