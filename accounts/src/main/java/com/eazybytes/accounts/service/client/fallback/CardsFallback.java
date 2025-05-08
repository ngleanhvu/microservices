package com.eazybytes.accounts.service.client.fallback;

import com.eazybytes.accounts.dto.CardsDto;
import com.eazybytes.accounts.service.client.CardsFeignClient;
import com.eazybytes.accounts.service.client.LoansFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient {
    @Override
    public ResponseEntity<CardsDto> fetchDetailCars(String correlationId, String mobileNumber) {
        return null;
    }
}
