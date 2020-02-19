package pl.ss.currency.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.ss.currency.domain.Currency;

import java.io.*;
import java.util.List;
import java.util.Set;

@Component
public class CountryImportService {

    private final File importFile = new File("src/main/resources/countryList.csv");

    @EventListener(ApplicationReadyEvent.class)
    public String importFromFile() throws IOException {
        Reader in = new FileReader(importFile);
        CSVParser records = CSVFormat.newFormat(';').withRecordSeparator("\r\n").parse(in);

        for (CSVRecord record : records) {
            String columnOne = record.get(0);
            String columnTwo = record.get(1);
        }

        return null;
    }

}
