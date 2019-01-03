import java.util.EventObject;

public class StockEvent extends EventObject {
    private String text;
    public StockEvent(Object source, String text){
        super(source);
        this.text = text;
    }

    public String getText(){
        return text;
    }
}
