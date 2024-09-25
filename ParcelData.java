package data;
import gui.GUI;

/**
 * The ParcelData class provides the pattern definitions and relevant data for various parcel types.
 * It includes methods to create parcel patterns and retrieve their rotations and values.
 */


public class ParcelData {


    // Static variables defining the patterns for different types of parcels.
    private static final boolean[][][][] TYPE_A_PATTERN = createTypeAPattern();
    private static final boolean[][][][] TYPE_B_PATTERN = createTypeBPattern();
    private static final boolean[][][][] TYPE_C_PATTERN = createTypeCPattern();


    /**
     * Creates the pattern for Type A parcels.
     * This method defines a specific 3D boolean array representing the shape of Type A parcels.
     *
     * @return A 3D boolean array representing the Type A parcel's shape in different rotations.
     */
    private static boolean[][][][] createTypeAPattern() {
        return new boolean[][][][]{
                {
                        {{true, true, true, true}, {true, true, true, true}},
                        {{true, true, true, true}, {true, true, true, true}}
                },
                {
                        {{true, true}, {true, true}, {true, true}, {true, true}},
                        {{true, true}, {true, true}, {true, true}, {true, true}}
                },
                {
                        {{true, true}, {true, true}},
                        {{true, true}, {true, true}},
                        {{true, true}, {true, true}},
                        {{true, true}, {true, true}}
                }
        };
    }


    /**
     * Creates the pattern for Type B parcels.
     * Similar to createTypeAPattern, this method defines the shape of Type B parcels.
     *
     * @return A 3D boolean array for Type B parcel's shape in different rotations.
     */

    private static boolean[][][][] createTypeBPattern() {
        return new boolean[][][][]{
                {
                        {{true, true}, {true, true}, {true, true}},
                        {{true, true}, {true, true}, {true, true}},
                        {{true, true}, {true, true}, {true, true}},
                        {{true, true}, {true, true}, {true, true}}
                },
                {
                        {{true, true, true}, {true, true, true}},
                        {{true, true, true}, {true, true, true}},
                        {{true, true, true}, {true, true, true}},
                        {{true, true, true}, {true, true, true}}
                },
                {
                        {{true, true, true, true}, {true, true, true, true}},
                        {{true, true, true, true}, {true, true, true, true}},
                        {{true, true, true, true}, {true, true, true, true}}
                },
                {
                        {{true, true, true, true}, {true, true, true, true}, {true, true, true, true}},
                        {{true, true, true, true}, {true, true, true, true}, {true, true, true, true}}
                }
        };
    }

    /**
     * Creates the pattern for Type C parcels.
     * Defines the 3D boolean array for Type C parcels' shape in various rotations.
     *
     * @return A 3D boolean array for Type C parcel's shape.
     */

    private static boolean[][][][] createTypeCPattern() {
        return new boolean[][][][]{
                {
                        {{true, true, true}, {true, true, true}, {true, true, true}},
                        {{true, true, true}, {true, true, true}, {true, true, true}},
                        {{true, true, true}, {true, true, true}, {true, true, true}}
                }
        };
    }


    /**
     * Retrieves the database of all parcel types and their rotations.
     * This method consolidates all the parcel types into a single database.
     *
     * @return A 3D boolean array containing all parcel types and their rotations.
     */

    public static boolean[][][][][] getDatabase() {
        // Consolidating all parcel types into one database.

        return new boolean[][][][][]{
                TYPE_A_PATTERN,
                TYPE_B_PATTERN,
                TYPE_C_PATTERN
        };
    }

    /**
     * Returns the value associated with a given parcel type.
     * This method is currently returning a constant value to avoid errors.
     *
     * @param id The identifier for the parcel type.
     * @return The value of the specified parcel type.
     */
    public static int getValue(int id) {
        return switch (id) {
            case 1 -> Integer.parseInt(GUI.value1Text);
            case 2 -> Integer.parseInt(GUI.value2Text);
            case 3 -> Integer.parseInt(GUI.value3Text);
            default -> -1;
        };
    }




    /**
     * Retrieves a specific parcel shape based on genetic data and index.
     * This method is used in genetic algorithms to select a specific parcel rotation.
     *
     * @param geneticData The genetic data array specifying parcel types and rotations.
     * @param index The index to select the specific parcel.
     * @return The selected parcel shape as a 3D boolean array.
     */
    public static boolean[][][] getParcel(int[][] geneticData, int index) {
        return getDatabase()[geneticData[0][index]][geneticData[1][index]];
    }
}

