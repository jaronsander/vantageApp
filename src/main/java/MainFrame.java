import org.knowm.xchart.XChartPanel;


import javax.swing.*;
import java.awt.*;


public class MainFrame extends JPanel implements DetailListener {
    private DetailsPanel detailsPanel;
    protected XChartPanel chartPanel;
    private final JSplitPane splitPane;

    final UpdatingChart chart = new UpdatingChart();
    public MainFrame(){
        //set layout manager
        super(new GridLayout(0, 1));
        //create components
        detailsPanel = new DetailsPanel();
        detailsPanel.addDetailListener(this);

        chartPanel = new XChartPanel(getXYChart.getXYChart("MSFT"));

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(detailsPanel);
        splitPane.setRightComponent(chartPanel);

        Dimension minimumSize = new Dimension(200, 100);
        detailsPanel.setMinimumSize(minimumSize);
        splitPane.setPreferredSize(new Dimension(800, 400));

        add(splitPane);

    }
    @Override
    public void detailEventOccurred(DetailEvent event){
        String stock = event.getText();
        chartPanel = new XChartPanel(getXYChart.getXYChart(stock));
        splitPane.setRightComponent(chartPanel);
        chart.setStock(stock);
        chart.updateData();
        chartPanel.revalidate();
        chartPanel.repaint();
    }
}
