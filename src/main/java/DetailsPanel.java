import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class DetailsPanel extends JPanel {


    private EventListenerList listenerList = new EventListenerList();

    public DetailsPanel(){
        Dimension size = getPreferredSize();
        size.width = 250;
        setPreferredSize(size);

        JLabel label = new JLabel("Stock:");

        final JTextField stock = new JTextField(10);

        stock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = stock.getText();
                fireDetailEvent(new DetailEvent(this, name));
            }
        });


        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        //First column
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.weightx = 0.5;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy = 0;

        add(label, gc);

        //second column
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridy = 0;
        gc.gridx = 1;

        add(stock, gc);

    }
    public void fireDetailEvent(DetailEvent event){
        Object[] listeners = listenerList.getListenerList();
        for(int i = 0; i< listeners.length; i+=2){
            if(listeners[i] == DetailListener.class);{
                ((DetailListener)listeners[i+1]).detailEventOccurred(event);
            }
        }
    }

    public void addDetailListener(DetailListener listener){
        listenerList.add(DetailListener.class, listener);
    }
    public void removeDetailListener(DetailListener listener){
        listenerList.remove(DetailListener.class, listener);
    }
}
