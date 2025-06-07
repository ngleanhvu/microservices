package com.eazybytes.accounts.functions;

import com.eazybytes.accounts.entity.Accounts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AccountsFunctions {
    private static final Logger logger = LoggerFactory.getLogger(AccountsFunctions.class);

    @Bean
    public Consumer<Long> updateCommunication() {
        return accountNumber -> {
            logger.info("Account number is: " + accountNumber);
        };
    }
}
