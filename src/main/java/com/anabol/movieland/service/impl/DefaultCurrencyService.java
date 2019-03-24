package com.anabol.movieland.service.impl;

import com.anabol.movieland.dao.CurrencyDao;
import com.anabol.movieland.service.CurrencyService;
import com.anabol.movieland.web.utils.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultCurrencyService implements CurrencyService {
    private final CurrencyDao currencyDao;

    @Override
    public double getRate(Currency currency) {
      return currencyDao.getRate(currency);
    }
}
