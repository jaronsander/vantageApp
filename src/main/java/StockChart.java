
import javax.swing.*;


public class StockChart{
    /**
     * Create the GUI and show it. For thread safety, this method should be invoked from the event
     * dispatch thread.
     */
    private static void createAndShowGUI() {

        // Create and set up the window.
        JFrame frame = new JFrame("XChart Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Add content to the window.
        frame.add(new MainFrame());

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        // Schedule a job for the event dispatch thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(
                new Runnable() {

                    @Override
                    public void run() {
                        createAndShowGUI();
                    }
                });
    }

}
