
import org.knowm.xchart.internal.chartpart.Chart;
public interface TheChart<C extends Chart<?, ?>>{
    C getChart();
}