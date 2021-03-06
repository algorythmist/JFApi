package com.tecacet.finance.service.stock.yahoo;

import com.tecacet.finance.model.StandardPeriodType;
import com.tecacet.finance.model.Quote;
import com.tecacet.finance.service.stock.StockPriceService;
import com.tecacet.finance.service.stock.StockServiceException;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class YahooStockPriceService extends AbstractYahooService implements StockPriceService {

    private final static String[] PRICE_PROPERTIES = new String[] {"date", "open", "high", "low", "close", "adjustedClose", "volume"};
    private final static String[] PRICE_COLUMNS = new String[] {"Date", "Open", "High", "Low", "Close", "Adj Close", "Volume"};

    private final YahooPriceParser parser = new YahooPriceParser(PRICE_PROPERTIES, PRICE_COLUMNS);

    @Override
    public List<Quote> getPriceHistory(String symbol, LocalDate fromDate, LocalDate toDate, StandardPeriodType periodType) throws StockServiceException {
        try {
            Map<String, String> params = getRequestParams(fromDate, toDate, periodType);
            InputStream is = getUrlStream(symbol, params);
            return parser.parse(is);
        } catch (Exception e) {
            throw new StockServiceException(e);
        }
    }


}
