import java.util.EventListener;

public interface DetailsListener extends EventListener {
    public void stockEventOccurred(StockEvent event);
    public void chartEventOccurred(ChartEvent event);
    public void OHLCEventOccurred(OHLCEvent event);
}
