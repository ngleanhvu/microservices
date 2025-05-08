package com.eazybytes.accounts.service.client.fallback;

import com.eazybytes.accounts.dto.LoansDto;
import com.eazybytes.accounts.service.client.LoansFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallback implements LoansFeignClient {
    @Override
    public ResponseEntity<LoansDto> fetchDetailLoans(String correlationId, String mobileNumber) {
        return null;
    }
}
