package data;
/**
 * The PentData class provides the definitions for various shapes of pentominoes.
 * It includes three types of pentominoes: T, L, and P, each with different orientations.
 */
public class PentData {





        // Definitions for T-shaped pentominoes with various rotations.
    public static boolean[][][][] tPentBool = {



            {
                    {{true}, {false}, {false}},
                    {{true}, {true}, {true}},
                    {{true}, {false}, {false}}
            },
            {
                    {{true}, {true}, {true}},
                    {{false}, {true}, {false}},
                    {{false}, {true}, {false}}
            },
            {
                    {{false}, {false}, {true}},
                    {{true}, {true}, {true}},
                    {{false}, {false}, {true}}
            },
            {
                    {{false}, {true}, {false}},
                    {{false}, {true}, {false}},
                    {{true}, {true}, {true}}
            },
            {
                    {{true, false, false}},
                    {{true, true, true}},
                    {{true, false, false}}
            },
            {
                    {{false, false, true}},
                    {{true, true, true}},
                    {{false, false, true}}
            },
            {
                    {{false, true, false}},
                    {{false, true, false}},
                    {{true, true, true}}
            },
            {
                    {{true, true, true}},
                    {{false, true, false}},
                    {{false, true, false}}
            },
            {
                    {{true, true, true}},
                    {{false, true, false}},
                    {{false, true, false}}
            },
            {
                    {{true, false, false}},
                    {{true, true, true}},
                    {{true, false, false}}
            },
            {
                    {{false, true, false}},
                    {{false, true, false}},
                    {{true, true, true}}
            },
            {
                    {{false, false, true}},
                    {{true, true, true}},
                    {{false, false, true}}
            }
    };

        // Definitions for L-shaped pentominoes with various rotations.
    public static boolean[][][][] lPentBool = {

            {
                    {{true}, {false}},
                    {{true}, {false}},
                    {{true}, {false}},
                    {{true}, {true}}
            },
            {
                    {{false}, {true}},
                    {{false}, {true}},
                    {{false}, {true}},
                    {{true}, {true}}
            },
            {
                    {{true}, {true}},
                    {{true}, {false}},
                    {{true}, {false}},
                    {{true}, {false}}
            },
            {
                    {{true}, {true}},
                    {{false}, {true}},
                    {{false}, {true}},
                    {{false}, {true}}
            },
            {
                    {{true}, {true}, {true}, {true}},
                    {{true}, {false}, {false}, {false}}
            },
            {
                    {{true}, {true}, {true}, {true}},
                    {{false}, {false}, {false}, {true}}
            },
            {
                    {{true}, {false}, {false}, {false}},
                    {{true}, {true}, {true}, {true}}
            },
            {
                    {{false}, {false}, {false}, {true}},
                    {{true}, {true}, {true}, {true}}
            },
            {
                    {{true, false}},
                    {{true, false}},
                    {{true, false}},
                    {{true, true}}
            },
            {
                    {{false, true}},
                    {{false, true}},
                    {{false, true}},
                    {{true, true}}
            },
            {
                    {{true, true}},
                    {{true, false}},
                    {{true, false}},
                    {{true, false}}
            },
            {
                    {{true, true}},
                    {{false, true}},
                    {{false, true}},
                    {{false, true}}
            },
            {
                    {{true, true}, {true, false}, {true, false}, {true, false}},
                    {{false, true}, {false, true}, {false, true}, {true, true}}
            },
            {
                    {{true, true}, {false, true}, {false, true}, {false, true}},
                    {{true, false}, {true, false}, {true, false}, {true, true}}
            },
            {
                    {{true, true, true, true}, {true, false, false, false}},
                    {{true, true, true, true}, {false, false, false, true}}
            },
            {
                    {{true, false, false, false}, {true, true, true, true}},
                    {{false, false, false, true}, {true, true, true, true}}
            },
            {
                    {{true, false, false, false}, {true, true, true, true}},
                    {{true, true, true, true}, {false, false, false, true}}
            },
            {
                    {{true, true, true, true}, {true, false, false, false}},
                    {{false, false, false, true}, {true, true, true, true}}
            }
    };

        // Definitions for P-shaped pentominoes with various rotations.
    public static boolean[][][][] pPentBool = {


            {
                    {{true}, {false}},
                    {{true}, {true}},
                    {{true}, {true}}
            },
            {
                    {{true}, {true}},
                    {{true}, {true}},
                    {{false}, {true}}
            },
            {
                    {{true}, {true}},
                    {{true}, {true}},
                    {{true}, {false}}
            },
            {
                    {{false}, {true}},
                    {{true}, {true}},
                    {{true}, {true}}
            },
            {
                    {{true}, {true}, {true}},
                    {{true}, {true}, {false}}
            },
            {
                    {{true}, {true}, {true}},
                    {{false}, {true}, {true}}
            },
            {
                    {{false}, {true}, {true}},
                    {{true}, {true}, {true}}
            },
            {
                    {{true}, {true}, {false}},
                    {{true}, {true}, {true}}
            },
            {
                    {{true, false}},
                    {{true, true}},
                    {{true, true}}
            },
            {
                    {{false, true}},
                    {{true, true}},
                    {{true, true}}
            },
            {
                    {{true, true}},
                    {{true, true}},
                    {{false, true}}
            },
            {
                    {{true, true}},
                    {{true, true}},
                    {{true, false}}
            },
            {
                    {{true, true}, {true, true}, {true, false}},
                    {{false, true}, {true, true}, {true, true}}
            },
            {
                    {{true, true}, {true, true}, {false, true}},
                    {{true, false}, {true, true}, {true, true}}
            },
            {
                    {{true, true, false}},
                    {{true, true, true}}
            },
            {
                    {{true, true, true}},
                    {{true, true, false}}
            },
            {
                    {{true, true, true}},
                    {{false, true, true}}
            },
            {
                    {{false, true, true}},
                    {{true, true, true}}
            },
            {
                    {{true, true, false}, {true, true, true}},
                    {{true, true, true}, {false, true, true}}
            },
            {
                    {{true, true, true}, {true, true, false}},
                    {{false, true, true}, {true, true, true}}
            }
    };
        /**
         * This method combines the different pentomino shapes into one array for easy access.
         *
         * @return A 3D boolean array containing all types of pentominoes and their rotations.
         */
        public static boolean[][][][][] getDatabase() {
            return new boolean[][][][][]{lPentBool, pPentBool, tPentBool};
        }


}
