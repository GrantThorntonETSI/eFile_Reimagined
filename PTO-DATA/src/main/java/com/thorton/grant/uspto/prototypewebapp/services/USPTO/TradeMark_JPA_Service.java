package com.thorton.grant.uspto.prototypewebapp.services.USPTO;

import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.TradeMarkService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.TradeMark;
import com.thorton.grant.uspto.prototypewebapp.repositories.jpa.USPTO.TradeMarkRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class TradeMark_JPA_Service implements TradeMarkService {



    private final TradeMarkRepository tradeMarkRepository;

    public TradeMark_JPA_Service(TradeMarkRepository tradeMarkRepository) {
        this.tradeMarkRepository = tradeMarkRepository;
    }

    @Override
    public Set<TradeMark> findAll() {
        Set<TradeMark> tradeMarks= new HashSet<>();

        tradeMarkRepository.findAll().forEach(tradeMarks::add);
        return tradeMarks;
    }

    @Override
    public Optional<TradeMark> findById(Long id) {
        return tradeMarkRepository.findById(id);
    }

    @Override
    public TradeMark save(TradeMark object) {
        return tradeMarkRepository.save(object);
    }

    @Override
    public void delete(TradeMark object) {
            tradeMarkRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        tradeMarkRepository.deleteById(id);

    }
}
