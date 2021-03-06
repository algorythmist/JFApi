package com.tecacet.finance.service.stock.yahoo;

import static org.junit.Assert.assertEquals;

import com.tecacet.finance.model.Quote;
import com.tecacet.finance.service.stock.yahoo.YahooPriceParser;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

public class YahooPriceParserTest {

    @Test
    public void testParseStockHistory() throws IOException {
        YahooPriceParser parser = new YahooPriceParser();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("TSLA.csv");
        List<Quote> prices = parser.parse(is);
        assertEquals(502, prices.size());
        Quote first = prices.get(0);
        assertEquals(150.43, first.getAdjustedClose(), 0.001);
        assertEquals(150.43, first.getClose(), 0.001);
        assertEquals(4262400L, first.getVolume());
        assertEquals(LocalDate.of(2013, 12, 31), first.getDate());
    }

}
