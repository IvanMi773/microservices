package com.example.currency_convertion_service.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "forex-service", url = "localhost:8000")
//@RibbonClient(name="forex-service")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/api/forex/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}
