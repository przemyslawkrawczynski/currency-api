package pl.ss.currency.dataprovider;

import pl.ss.currency.domain.CurrencyInfoEntity;
import pl.ss.currency.dtos.request.CurrencyRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDataProvider implements DataProvider {


    private List<CurrencyInfoEntity> database = new ArrayList<>();

    public DatabaseDataProvider() {

        database.add(new CurrencyInfoEntity("USD", "A", "2020-02-10", "3.9815"));
        database.add(new CurrencyInfoEntity("USD", "A", "2020-02-07", "3.9815"));
        database.add(new CurrencyInfoEntity("USD", "A", "2020-02-06", "3.9815"));
        database.add(new CurrencyInfoEntity("USD", "A", "2020-03-05", "3.9815"));

    }

    @Override
    public String getCurrencyDataByRequest(CurrencyRequest request) {
        return prepareStringResponse(getEntityByDate(request.getOnDate()));
    }

    private CurrencyInfoEntity getEntityByDate(LocalDate date) {
        CurrencyInfoEntity currencyInfoEntity = null;

        for (CurrencyInfoEntity entity : database) {
            if (entity.getCurrencyCheckDate().equals(date.toString())) {
                currencyInfoEntity = entity;
            }
        }

        return currencyInfoEntity;
    }

    private String prepareStringResponse(CurrencyInfoEntity entity) {
        if (entity == null) return null;
        return entity.getCurrencyCode() + ";" + entity.getCurrencyTable() + ";" + entity.getCurrencyCheckDate() + ";" + entity.getCurrencyRate();
    }
}
