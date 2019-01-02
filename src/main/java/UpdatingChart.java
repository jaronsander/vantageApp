import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;
import org.patriques.AlphaVantageConnector;
import org.patriques.TimeSeries;
import org.patriques.input.timeseries.OutputSize;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.timeseries.Daily;
import org.patriques.output.timeseries.data.StockData;

import java.time.LocalDate;
import java.util.*;

public class UpdatingChart implements TheChart<XYChart>, RealtimeChart {
    private XYChart xyChart;

    private String apiKey = "YE4PNNTEGYCF0VMD";
    private int timeout = 3000;
    private AlphaVantageConnector apiConnector;
    private TimeSeries stockTimeSeries ;

    private List<StockData> stockData;
    private ArrayList<Double> yData;
    private ArrayList<LocalDate> dateTime;
    private ArrayList<Date> xData;

    private String stock;



    public static void main(String[] args) {

        // Setup the panel
        final UpdatingChart realtimeChart01 = new UpdatingChart();
        realtimeChart01.go();
    }

    private void go() {

        final SwingWrapper<XYChart> swingWrapper = new SwingWrapper<XYChart>(getChart());
        swingWrapper.displayChart();

        // Simulate a data feed
        TimerTask chartUpdaterTask =
                new TimerTask() {

                    @Override
                    public void run() {

                        updateData();

                        javax.swing.SwingUtilities.invokeLater(
                                new Runnable() {

                                    @Override
                                    public void run() {

                                        swingWrapper.repaintChart();
                                    }
                                });
                    }
                };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(chartUpdaterTask, 0, 500);
    }

    @Override
    public XYChart getChart(){
        instantiation();

        return xyChart;
    }

    @Override
    public void updateData() {
        instantiation();
        try{
            //get Daily response
            Daily response  = stockTimeSeries.daily(stock, OutputSize.COMPACT);

            stockData = response.getStockData();
            //prepare data for graph
            stockData.forEach(time -> {
                yData.add(time.getClose());
                dateTime.add(time.getDateTime().toLocalDate());
            });
            //transform LocalDate to Date and add to xData
            dateTime.forEach(day -> xData.add(java.sql.Date.valueOf(day)));
            xyChart.addSeries(stock, xData, yData);


        }catch(AlphaVantageException a){
            System.out.println(("Something went wrong"));
        }


        xyChart.updateXYSeries(stock, xData, yData, null);
    }

    public void setStock(String stock){
        this.stock = stock;
    }

    public void instantiation(){
        apiConnector = new AlphaVantageConnector(apiKey, timeout);
        stockTimeSeries = new TimeSeries(apiConnector);
        yData = new ArrayList<>();
        dateTime = new ArrayList<>();
        xData = new ArrayList<>();
        xyChart = new XYChartBuilder().width(600).height(400).build();
        xyChart.getStyler().setDatePattern("dd-MMM");
        xyChart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
    }
}
