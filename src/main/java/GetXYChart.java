import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.timeseries.data.StockData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class GetXYChart {
    private List<StockData> stockData;

    public XYChart getXYChart(String stock){
        //build chart
        XYChart chart = new XYChartBuilder().width(600).height(400).build();
        chart.getStyler().setDatePattern("dd-MMM");
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        //get Stock Data
        GetStockData data = new GetStockData();
        try{
            stockData = data.getDaily(stock);
        }catch (AlphaVantageException e){
            System.out.println("You fucked up");
        }
        //prepare data for graph
        ArrayList<Double> yData = new ArrayList<>();
        ArrayList<Date> xData = new ArrayList<>();
        stockData.forEach(time -> {
            yData.add(time.getClose());
            xData.add(java.sql.Date.valueOf(time.getDateTime().toLocalDate()));
        });
        XYSeries series = chart.addSeries(stock, xData, yData);
        series.setMarker(SeriesMarkers.NONE);

        return chart;

    }

}
