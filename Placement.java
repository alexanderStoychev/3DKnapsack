package bot;

import data.ParcelData;
/**
 * The Placement class is part of the 'bot' package and is responsible for handling the logic
 * of placing parcels in a 3D grid. The class works with a 3D grid represented
 * as a 3-dimensional int array and parcel shapes represented as 3-dimensional boolean arrays.
 *
 * https://stackoverflow.com/questions/73158300/algorithm-to-place-3d-objects-within-a-defined-space
 */

public class Placement {

    public static int value = 0;


    /**
     * Places the parcel in the deepest available position from the back left corner for a greedy approach.
     * @param grid The 3D grid where parcels are placed.
     * @param shape The shape of the parcel to place.
     * @param type The type of the parcel, which influences its color.
     * @return true if the parcel was successfully placed, false otherwise.
     */

    public static boolean placeParcelBackLeftGreedy(int[][][] grid, boolean[][][] shape, int type) {
        int[] positionToPlace = findPositionForPlacement(grid, shape);
        if (positionToPlace != null) {
            placeShape(grid, shape, positionToPlace, type);
            value += ParcelData.getValue(type);
            return true;
        }
        return false;
    }

    /**
     * Places the parcel in the deepest available position from the back left corner for a greedy approach.
     * @param grid The 3D grid where parcels are placed.
     * @param shape The shape of the parcel to place.
     * @return position
     */

    private static int[] findPositionForPlacement(int[][][] grid, boolean[][][] shape) {
        for (int z = grid.length - 1; z >= 0; z--) {
            for (int y = 0; y < grid[z].length; y++) {
                for (int x = grid[z][y].length - 1; x >= 0; x--) {
                    int[] position = {z, y, x};
                    if (canInsertShape(grid, shape, position)) {
                        return position;
                    }
                }
            }
        }
        return null; // No suitable position found
    }


    /**
     * Checks if the given parcel shape can be inserted into the grid at the specified location.
     * It verifies that each occupied space of the parcel is within the bounds of the grid
     * and that the corresponding grid space is not already occupied.
     *
     * @param grid The 3D grid representing the container space.
     * @param parcel The 3D boolean array representing the shape of the parcel.
     * @param location The starting location in the grid for placing the parcel.
     * @return true if the parcel can be inserted at the location, false otherwise.
     */

    static public boolean canInsertShape(int[][][] grid, boolean[][][] parcel, int[] location) {
        for (int z = 0; z < parcel.length; z++) { // Iterate over parcel depth
            for (int y = 0; y < parcel[z].length; y++) { // Iterate over parcel height
                for (int x = 0; x < parcel[z][y].length; x++) { // Iterate over parcel width
                    if (!parcel[z][y][x]) continue; // Skip empty parcel spaces

                    if (isOutsideGrid(grid, location, z, y, x) || isGridSpaceOccupied(grid, location, z, y, x)) {
                        return false; // Parcel can't be placed due to grid bounds or occupied space
                    }
                }
            }
        }
        return true; // Parcel can be placed
    }


    /**
     * Checks if the specified position in the parcel extends outside the grid boundaries.
     * This method helps in ensuring that a parcel is placed within the confines of the grid.
     *
     * @param grid The 3D grid representing the container space.
     * @param location The starting location in the grid for the parcel.
     * @param z The depth index of the parcel relative to the location.
     * @param y The height index of the parcel relative to the location.
     * @param x The width index of the parcel relative to the location.
     * @return true if the specified position is outside the grid, false otherwise.
     */

    private static boolean isOutsideGrid(int[][][] grid, int[] location, int z, int y, int x) {
        return location[0] + z >= grid.length ||
                location[1] + y >= grid[z].length ||
                location[2] + x >= grid[z][y].length;
    }

    /**
     * Checks if the specified grid space is already occupied.
     * This method is used to ensure that a parcel does not overlap with other parcels
     * already placed in the grid.
     *
     * @param grid The 3D grid representing the container space.
     * @param location The starting location in the grid for the parcel.
     * @param z The depth index of the parcel relative to the location.
     * @param y The height index of the parcel relative to the location.
     * @param x The width index of the parcel relative to the location.
     * @return true if the grid space is occupied, false otherwise.
     */

    private static boolean isGridSpaceOccupied(int[][][] grid, int[] location, int z, int y, int x) {
        return grid[location[0] + z][location[1] + y][location[2] + x] != 0;
    }


    /**
     * Places the given parcel shape into the grid at the specified position.
     * It updates the grid spaces to reflect the presence of the parcel, marking them
     * with the parcel type.
     *
     * @param grid The 3D grid representing the container space.
     * @param shape The 3D boolean array representing the shape of the parcel.
     * @param position The starting location in the grid for placing the parcel.
     * @param type The type of the parcel, used to mark the grid spaces.
     */

    public static void placeShape(int[][][] grid, boolean[][][] shape, int[] position, int type) {
        for (int z = 0; z < shape.length; z++) {
            for (int y = 0; y < shape[z].length; y++) {
                for (int x = 0; x < shape[z][y].length; x++) {
                    if (shape[z][y][x]) {
                        grid[position[0] + z][position[1] + y][position[2] + x] = type;
                    }
                }
            }
        }
    }

}
