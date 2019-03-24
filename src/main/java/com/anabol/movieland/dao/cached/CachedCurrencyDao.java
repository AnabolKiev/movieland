package com.anabol.movieland.dao.cached;

import com.anabol.movieland.dao.CurrencyDao;
import com.anabol.movieland.web.utils.Currency;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.USER_AGENT;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CachedCurrencyDao implements CurrencyDao {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private volatile Map<Currency, Double> currencyMap;

    @Value("${currency.url}")
    private String url;

    @PostConstruct
    @Scheduled(cron = "${currency.refreshCron}")
    void refreshCache() {

        try {
            HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET"); // optional default is GET
            connection.setRequestProperty("User-Agent", USER_AGENT);

            currencyMap = new HashMap<>();
            ObjectNode[] nodes = OBJECT_MAPPER.readValue(connection.getInputStream(), ObjectNode[].class);
            for (ObjectNode node : nodes) {
                if (node.has("cc")) {
                    String currencyCode = node.get("cc").textValue();
                    if (Currency.contains(currencyCode) && node.has("rate")) {
                        double rate = node.get("rate").asDouble();
                        currencyMap.put(Currency.getByName(currencyCode), rate);
                        log.info("Currency rate for {} updated with value {}", currencyCode, rate);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Couldn't update rate", e);
        }
    }

    @Override
    public double getRate(Currency currency) {
        double rate = currencyMap.get(currency);
        if (rate == 0) {
            throw new RuntimeException("Requested currency rate isn't available at the moment");
        }
        return rate;
    }

}
