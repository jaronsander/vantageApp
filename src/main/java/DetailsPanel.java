import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailsPanel extends JPanel {


    private EventListenerList listenerList = new EventListenerList();

    public DetailsPanel(){
        Dimension size = getPreferredSize();
        size.width = 250;
        setPreferredSize(size);

        JLabel label = new JLabel("Stock:");

        final JTextField stock = new JTextField(10);

        final JButton stockChart = new JButton();
        stockChart.setText("Chart");
        final JButton OHLCChart = new JButton();
        OHLCChart.setText("Candle");

        stock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = stock.getText().toUpperCase();
                stock.setText("");
                fireDetailEvent(new StockEvent(this, name));
            }
        });

        stockChart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireDetailEvent(new ChartEvent(this));
            }
        });

        OHLCChart.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireDetailEvent((new OHLCEvent(this)));
            }
        }));


        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        //First column
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.weightx = 0.5;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy = 0;

        add(label, gc);

        gc.gridy = 1;
        add(stockChart);

        gc.gridy = 2;
        add(OHLCChart);

        //second column
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridy = 0;
        gc.gridx = 1;

        add(stock, gc);

    }
    public void fireDetailEvent(StockEvent event){
        Object[] listeners = listenerList.getListenerList();
        for(int i = 0; i< listeners.length; i+=2){
            if(listeners[i] == DetailsListener.class);{
                ((DetailsListener)listeners[i+1]).stockEventOccurred(event);
            }
        }
    }
    public void fireDetailEvent(ChartEvent event){
        Object[] listeners = listenerList.getListenerList();
        for(int i = 0; i< listeners.length; i+=2){
            if(listeners[i] == DetailsListener.class);{
                ((DetailsListener)listeners[i+1]).chartEventOccurred(event);
            }
        }
    }
    public void fireDetailEvent(OHLCEvent event){
        Object[] listeners = listenerList.getListenerList();
        for(int i = 0; i< listeners.length; i+=2){
            if(listeners[i] == DetailsListener.class);{
                ((DetailsListener)listeners[i+1]).OHLCEventOccurred(event);
            }
        }
    }

    public void addDetailListener(DetailsListener listener){
        listenerList.add(DetailsListener.class, listener);
    }
    public void removeDetailListener(DetailsListener listener){
        listenerList.remove(DetailsListener.class, listener);
    }
}
