import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.patriques.AlphaVantageConnector;
import org.patriques.TimeSeries;
import org.patriques.input.timeseries.Interval;
import org.patriques.input.timeseries.OutputSize;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.timeseries.Daily;
import org.patriques.output.timeseries.IntraDay;
import org.patriques.output.timeseries.data.StockData;

import java.util.List;

public class GetStockData {
    private String apiKey = "YE4PNNTEGYCF0VMD";
    private int timeout = 3000;
    private AlphaVantageConnector apiConnector;
    private TimeSeries stockTimeSeries;

    public GetStockData(){
        //connect to Alphavantage
        apiConnector = new AlphaVantageConnector(apiKey, timeout);
        stockTimeSeries = new TimeSeries(apiConnector);
    }

    public List<StockData> getDaily(String stock) throws AlphaVantageException{
        // get Daily response
        Daily response = stockTimeSeries.daily(stock, OutputSize.COMPACT);
        return response.getStockData();
    }
    public List<StockData> getIntraDay(String stock, Interval interval) throws AlphaVantageException{
        // get Daily response
        IntraDay response = stockTimeSeries.intraDay(stock.toUpperCase(), interval, OutputSize.COMPACT);
        return response.getStockData();
    }
}
