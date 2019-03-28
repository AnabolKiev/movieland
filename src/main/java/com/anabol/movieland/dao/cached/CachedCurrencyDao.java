package com.anabol.movieland.dao.cached;

import com.anabol.movieland.dao.CurrencyDao;
import com.anabol.movieland.dao.cached.dto.CurrencyDto;
import com.anabol.movieland.web.utils.Currency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CachedCurrencyDao implements CurrencyDao {
    private static final Currency BASIC_CURRENCY = Currency.UAH;
    private Map<Currency, Double> currencyMap = new HashMap<>();
    private final RestTemplate restTemplate;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    @Value("${currency.url}")
    private String url;

    @PostConstruct
    @Scheduled(cron = "${currency.refreshCron}")
    void refreshCache() {
        CurrencyDto[] currencies = restTemplate.getForObject(url, CurrencyDto[].class);
        try {
            lock.writeLock().lock();
            currencyMap.put(BASIC_CURRENCY, 1.00);
            for (CurrencyDto currencyDto : currencies) {
                String currencyCode = currencyDto.getCurrencyCode();
                if (Currency.contains(currencyCode)) {
                    currencyMap.put(Currency.getByName(currencyCode), currencyDto.getRate());
                    log.info("Currency rate for {} updated with value {}", currencyCode, currencyDto.getRate());
                }
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public double getRate(Currency currency) {
        try {
            lock.readLock().lock();
            Double rate = currencyMap.get(currency);
            if (rate == null) {
                throw new RuntimeException("Requested currency rate isn't available at the moment");
            }
            return rate;
        } finally {
            lock.readLock().unlock();
        }
    }

}
