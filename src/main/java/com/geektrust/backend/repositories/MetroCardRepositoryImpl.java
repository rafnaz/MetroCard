package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import com.geektrust.backend.entities.MetroCard;

public class MetroCardRepositoryImpl implements MetroCardRepository{

    private final Map<String, MetroCard> cardMap;
    private int autoIncrement = 0;

    public MetroCardRepositoryImpl (Map<String, MetroCard> cardMap) {
        this.cardMap = cardMap;
        this.autoIncrement = cardMap.size();
    }

    public MetroCardRepositoryImpl() {
        this.cardMap = new HashMap<>();
    }

    @Override
    public MetroCard save(MetroCard entity) {
        if (entity.getId() == null){
            autoIncrement++;
            MetroCard metroCard = new MetroCard(Integer.toString(autoIncrement), entity.getCardNumber(), entity.getBalance());
            cardMap.put(metroCard.getId(), metroCard);
            return metroCard;
        }
        cardMap.put(entity.getId(), entity);
        return entity;

    }

    @Override
    public Optional<MetroCard> findByCardNumber(String cardNumber) {
        // TODO Auto-generated method stub
        Optional<MetroCard> metroCard = cardMap.values().stream().filter(card -> card.getCardNumber().equals(cardNumber)).findFirst();
        return metroCard;

    }
    
}
