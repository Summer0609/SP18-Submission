package byog.lab5;

import org.junit.Test;;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;



/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    /*
    *   Draw the scale of the HexWorld
    * */

    private static final int HEIGHT = 50;

    private static final int WIDTH = 50;

    //Calculate the width of each row
    private static int widthCalculator(int n, int size) {
        if (n <= size) {
            return 2 * n + size;
        }
        return -2 * n + 5 * size - 2;
    }

    //abscissa offset
    private static int xOffset(int n, int size) {
        if (n < size) {
            return (-n);
        }
        return (n - 2 * size);
    }

    //ordinate offset
    private static int yOffset(int n, int size) {
       return n;
    }

    // add a row
    private static void addRow(TETile[][] World, TETile teTile, int xP, int yP, int width) {
        for (int i = 0; i < width; i++) {
            World[xP + i][yP] = teTile;
        }
    }


    // test the correctness of drawing s hexagon

    @Test
    public void testDrawingHexagon() {
        int size = 5;
        for (int i = 0; i < size*2; i++) {
            for (int j = 0; j < xOffset(i, size); j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < widthCalculator(i, size); j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }


    public static void addHexagon(TETile[][] World, TETile teTile, int xP, int yP, int size) {
        for (int i = 0; i < 2 * size; i++) {
            int xStart = xP + xOffset(i, size);
            int yStart = yP + yOffset(i, size);
            addRow(World, teTile, xStart, yStart, widthCalculator(i, size) );
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        //initialize the tetile
        TETile[][] mainWorld = new TETile[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                mainWorld[i][j] = Tileset.NOTHING;
            }
        }
        addHexagon(mainWorld, Tileset.FLOWER, 16, 24, 10);
        ter.renderFrame(mainWorld);
    }
}
