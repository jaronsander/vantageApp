import org.knowm.xchart.OHLCChart;
import org.knowm.xchart.OHLCChartBuilder;
import org.knowm.xchart.OHLCSeries;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.timeseries.data.StockData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetOHLCChart {
    private List<StockData> stockData;

    public OHLCChart getOHLCChart(String stock){
        //build chart
        OHLCChart chart = new OHLCChartBuilder().width(600).height(400).build();
        chart.getStyler().setDatePattern("dd-MMM");

        //get Stock data
        GetStockData data = new GetStockData();
        try{
            stockData = data.getDaily(stock);
        }catch (AlphaVantageException e){
            System.out.println("You fucked up");
        }
        //prepare data for graph
        ArrayList<Double> closeData = new ArrayList<>();
        ArrayList<Double> openData = new ArrayList<>();
        ArrayList<Double> highData = new ArrayList<>();
        ArrayList<Double> lowData = new ArrayList<>();
        ArrayList<Date> xData = new ArrayList<>();
        stockData.forEach(time -> {
            closeData.add(time.getClose());
            openData.add(time.getOpen());
            highData.add(time.getHigh());
            lowData.add(time.getLow());
            xData.add(java.sql.Date.valueOf(time.getDateTime().toLocalDate()));
        });
        OHLCSeries series = chart.addSeries(stock, xData, openData, highData, lowData, closeData);

        return chart;
    }
}
