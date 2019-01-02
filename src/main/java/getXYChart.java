import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.patriques.AlphaVantageConnector;
import org.patriques.TimeSeries;
import org.patriques.input.timeseries.Interval;
import org.patriques.input.timeseries.OutputSize;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.timeseries.Daily;
import org.patriques.output.timeseries.IntraDay;
import org.patriques.output.timeseries.data.StockData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class getXYChart {
    private static String apiKey = "YE4PNNTEGYCF0VMD";
    private static int timeout = 3000;
    private static AlphaVantageConnector apiConnector;
    private static TimeSeries stockTimeSeries ;

    private static XYChart chart;

    private static List<StockData> stockData;
    private static ArrayList<Double> yData;
    private static ArrayList<LocalDate> dateTime;
    private static ArrayList<Date> xData;


    public static XYChart getXYChart(String stock){
        //connect to Alphavantage
        apiConnector = new AlphaVantageConnector(apiKey, timeout);
        stockTimeSeries = new TimeSeries(apiConnector);
        //build chart
        chart = new XYChartBuilder().width(600).height(400).build();
        chart.getStyler().setDatePattern("dd-MMM");

        yData = new ArrayList<>();
        dateTime = new ArrayList<>();
        xData = new ArrayList<>();

        try{
            //get Daily response
            Daily response = stockTimeSeries.daily(stock, OutputSize.COMPACT);

            stockData = response.getStockData();
            //prepare data for graph
            stockData.forEach(time -> {
                yData.add(time.getClose());
                dateTime.add(time.getDateTime().toLocalDate());
            });
            //transform LocalDate to Date and add to xData
            dateTime.forEach(day -> xData.add(java.sql.Date.valueOf(day)));
            chart.addSeries(stock, xData, yData);


        }catch(AlphaVantageException a){
            System.out.println(("Something went wrong"));
        }
        return chart;

    }

}
