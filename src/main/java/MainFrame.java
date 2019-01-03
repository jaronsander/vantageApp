import org.knowm.xchart.OHLCChart;
import org.knowm.xchart.XChartPanel;


import javax.swing.*;
import java.awt.*;


public class MainFrame extends JPanel implements DetailsListener {
    private DetailsPanel detailsPanel;
    protected XChartPanel chartPanel;
    private final JSplitPane splitPane;
    private String stock;
    public MainFrame(){
        //set layout manager
        super(new GridLayout(0, 1));
        //create components
        detailsPanel = new DetailsPanel();
        detailsPanel.addDetailListener(this);
        stock = "MSFT";
        chartPanel = new XChartPanel(new GetXYChart().getXYChart(stock));

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(detailsPanel);
        splitPane.setRightComponent(chartPanel);

        Dimension minimumSize = new Dimension(200, 100);
        detailsPanel.setMinimumSize(minimumSize);
        splitPane.setPreferredSize(new Dimension(800, 400));

        add(splitPane);

    }
    @Override
    public void stockEventOccurred(StockEvent event){
        stock = event.getText();
        chartPanel = new XChartPanel(new GetXYChart().getXYChart(stock));
        splitPane.setRightComponent(chartPanel);
        chartPanel.revalidate();
        chartPanel.repaint();
    }
    @Override
    public void chartEventOccurred(ChartEvent event){
        chartPanel = new XChartPanel(new GetXYChart().getXYChart(stock));
        splitPane.setRightComponent(chartPanel);
        chartPanel.revalidate();
        chartPanel.repaint();
    }
    @Override
    public void OHLCEventOccurred(OHLCEvent event){
        chartPanel = new XChartPanel(new GetOHLCChart().getOHLCChart(stock));
        splitPane.setRightComponent(chartPanel);
        chartPanel.revalidate();
        chartPanel.repaint();
    }
}
