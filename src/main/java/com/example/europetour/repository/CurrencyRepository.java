package com.example.europetour.repository;

import com.example.europetour.model.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Currency findCurrencyByCurrencyCode(String currencyCode);

}
