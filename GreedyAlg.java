package bot;
import data.ParcelData;
import data.PentData;
import gui.GUI;
import javafx.application.Platform;

import java.util.stream.IntStream;

/**
 * GreedyAlg class extends Placement and implements Runnable.
 * This class is designed to implement a greedy algorithm approach for a packing problem in a 3D grid.
 * It is capable of running in a separate thread due to its Runnable interface implementation.
 * https://www.geeksforgeeks.org/intstream-range-java/
 */

public class GreedyAlg extends Placement implements Runnable {


    static GUI visual;

    /**
     * Sets the visualizer for this algorithm.
     * @param arrayVisualization The GUI instance used for visualizing the algorithm's process.
     */
    public static void setVisualizer(GUI arrayVisualization) {
        visual = arrayVisualization;
    }


    /**
     * Executes the greedy search algorithm on a given 3D grid.
     * The method attempts to place parcels or pentominoes in the grid in a way that maximizes value.
     *
     * @param grid The 3D grid in which parcels or pentominoes are to be placed.
     */

    public void search(int[][][] grid) {
        int value1 = Integer.parseInt(GUI.value1Text);
        int value2 = Integer.parseInt(GUI.value2Text);
        int value3 = Integer.parseInt(GUI.value3Text);

        boolean[][][][][] data = GUI.type.equals("Parcels") ? ParcelData.getDatabase() : PentData.getDatabase();
        int[] values = {value1, value2, value3};

        IntStream.range(0, 3).forEach(i -> {
            int largest = IntStream.range(0, values.length)
                    .reduce((k, l) -> values[k] > values[l] ? k : l)
                    .getAsInt();

            IntStream.range(0, data[largest].length).forEach(j -> {
                boolean[][][] pieceToPlace = data[largest][j];
                boolean isRunning = true;
                while(isRunning) {
                    isRunning = placeParcelBackLeftGreedy(grid, pieceToPlace, largest + 1);
                }
            });

            values[largest] = 0;
        });

        Platform.runLater(() -> {
            visual.updateState(grid);
            GUI.setScore(value);
        });
    }


    /**
     * The main run method for the Runnable interface.
     * Initializes the search algorithm and finalizes the search once complete.
     */
    public void run(){
        value = 0;
        search(new int[33][8][5]); // Start the search with a new empty grid.
        visual.clearButton.fire(); // Trigger the end of search in the GUI.


    }
}
