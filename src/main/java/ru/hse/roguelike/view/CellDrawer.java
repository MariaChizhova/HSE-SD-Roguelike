package ru.hse.roguelike.view;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;

import java.util.Arrays;
import java.util.List;

/**
 * This class draw different types of cell
 * on the field
 */
public class CellDrawer {

    private record CellChar(char elem, TextColor color) {
    }

    private static void drawer(int col_pos, int row_pos, Screen screen, List<CellChar> chars) {
        int cellSize = 3;
        for (int i = 0; i < cellSize; i++) {
            CellChar cellChar = chars.get(i);
            if (cellChar != null) {
                screen.setCharacter(col_pos + i, row_pos, new TextCharacter(
                        cellChar.elem(), cellChar.color(), TextColor.ANSI.DEFAULT));
            }
        }
    }

    /**
     * Draw player on the field
     * @param col_pos the column coordinate of item
     * @param row_pos the raw coordinate of item
     * @param screen where will be drawn item
     */
    public static void playerDrawer(int col_pos, int row_pos, Screen screen) {
        List<CellChar> chars = Arrays.asList(new CellChar('<', TextColor.ANSI.DEFAULT),
                new CellChar(Symbols.FACE_WHITE, TextColor.ANSI.DEFAULT),
                new CellChar('>', TextColor.ANSI.DEFAULT));
        drawer(col_pos, row_pos, screen, chars);
    }

    /**
     * Draw default enemy on the field
     * @param col_pos the column coordinate of item
     * @param row_pos the raw coordinate of item
     * @param screen where will be drawn item
     */
    public static void defaultEnemyDrawer(int col_pos, int row_pos, Screen screen) {
        List<CellChar> chars = Arrays.asList(new CellChar(Symbols.OUTLINED_SQUARE_SMALL, TextColor.ANSI.DEFAULT),
                new CellChar(Symbols.FACE_WHITE, TextColor.ANSI.DEFAULT),
                new CellChar(Symbols.OUTLINED_SQUARE_SMALL, TextColor.ANSI.DEFAULT));
        drawer(col_pos, row_pos, screen, chars);
    }

    /**
     * Draw dragon enemy on the field
     * @param col_pos the column coordinate of item
     * @param row_pos the raw coordinate of item
     * @param screen where will be drawn item
     */
    public static void dragonEnemyDrawer(int col_pos, int row_pos, Screen screen) {
        List<CellChar> chars = Arrays.asList(new CellChar('<', TextColor.ANSI.GREEN),
                new CellChar(Symbols.FACE_BLACK, TextColor.ANSI.RED),
                new CellChar('>', TextColor.ANSI.GREEN));
        drawer(col_pos, row_pos, screen, chars);
    }

    /**
     * Draw clone enemy on the field
     * @param col_pos the column coordinate of item
     * @param row_pos the raw coordinate of item
     * @param screen where will be drawn item
     */
    public static void cloneEnemyDrawer(int col_pos, int row_pos, Screen screen) {
        List<CellChar> chars = Arrays.asList(new CellChar(Symbols.SINGLE_LINE_BOTTOM_LEFT_CORNER, TextColor.ANSI.CYAN),
                new CellChar(Symbols.FEMALE, TextColor.ANSI.CYAN),
                new CellChar(Symbols.SINGLE_LINE_BOTTOM_RIGHT_CORNER, TextColor.ANSI.CYAN));
        drawer(col_pos, row_pos, screen, chars);
    }

    /**
     * Draw skeleton enemy on the field
     * @param col_pos the column coordinate of item
     * @param row_pos the raw coordinate of item
     * @param screen where will be drawn item
     */
    public static void skeletonEnemyDrawer(int col_pos, int row_pos, Screen screen) {
        List<CellChar> chars = Arrays.asList(new CellChar(Symbols.ARROW_LEFT, TextColor.ANSI.DEFAULT),
                new CellChar(Symbols.FACE_WHITE, TextColor.ANSI.DEFAULT),
                new CellChar(Symbols.ARROW_RIGHT, TextColor.ANSI.DEFAULT));
        drawer(col_pos, row_pos, screen, chars);
    }

    /**
     * Draw wall on the field
     * @param col_pos the column coordinate of item
     * @param row_pos the raw coordinate of item
     * @param screen where will be drawn item
     */
    public static void wallDrawer(int col_pos, int row_pos, Screen screen) {
        List<CellChar> chars = Arrays.asList(new CellChar(Symbols.BLOCK_DENSE, TextColor.ANSI.GREEN),
                new CellChar(Symbols.BLOCK_DENSE, TextColor.ANSI.GREEN),
                new CellChar(Symbols.BLOCK_DENSE, TextColor.ANSI.GREEN));
        drawer(col_pos, row_pos, screen, chars);
    }

    /**
     * Draw boots on the field
     * @param col_pos the column coordinate of item
     * @param row_pos the raw coordinate of item
     * @param screen where will be drawn item
     */
    public static void bootsDrawer(int col_pos, int row_pos, Screen screen) {
        List<CellChar> chars = Arrays.asList(new CellChar(Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER, TextColor.ANSI.BLUE),
                null,
                new CellChar(Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER, TextColor.ANSI.BLUE));
        drawer(col_pos, row_pos, screen, chars);
    }

    /**
     * Draw gloves on the field
     * @param col_pos the column coordinate of item
     * @param row_pos the raw coordinate of item
     * @param screen where will be drawn item
     */
    public static void glovesDrawer(int col_pos, int row_pos, Screen screen) {
        List<CellChar> chars = Arrays.asList(new CellChar(Symbols.CLUB, TextColor.ANSI.BLUE),
                null,
                new CellChar(Symbols.CLUB, TextColor.ANSI.BLUE));
        drawer(col_pos, row_pos, screen, chars);
    }

    /**
     * Draw helmet on the field
     * @param col_pos the column coordinate of item
     * @param row_pos the raw coordinate of item
     * @param screen where will be drawn item
     */
    public static void helmetDrawer(int col_pos, int row_pos, Screen screen) {
        List<CellChar> chars = Arrays.asList(null,
                new CellChar(Symbols.SINGLE_LINE_T_DOUBLE_UP, TextColor.ANSI.BLUE),
                null);
        drawer(col_pos, row_pos, screen, chars);
    }

    /**
     * Draw rapier on the field
     * @param col_pos the column coordinate of item
     * @param row_pos the raw coordinate of item
     * @param screen where will be drawn item
     */
    public static void rapierDrawer(int col_pos, int row_pos, Screen screen) {
        List<CellChar> chars = Arrays.asList(null,
                new CellChar(Symbols.BOLD_SINGLE_LINE_VERTICAL, TextColor.ANSI.CYAN),
                null);
        drawer(col_pos, row_pos, screen, chars);
    }

    /**
     * Draw cuirass on the field
     * @param col_pos the column coordinate of item
     * @param row_pos the raw coordinate of item
     * @param screen where will be drawn item
     */
    public static void cuirassDrawer(int col_pos, int row_pos, Screen screen) {
        List<CellChar> chars = Arrays.asList(null,
                new CellChar(Symbols.DOUBLE_LINE_CROSS, TextColor.ANSI.BLUE),
                null);
        drawer(col_pos, row_pos, screen, chars);
    }

    /**
     * Draw sword on the field
     * @param col_pos the column coordinate of item
     * @param row_pos the raw coordinate of item
     * @param screen where will be drawn item
     */
    public static void swordDrawer(int col_pos, int row_pos, Screen screen, TextColor color) {
        List<CellChar> chars = Arrays.asList(null,
                new CellChar(Symbols.ARROW_UP, color),
                null);
        drawer(col_pos, row_pos, screen, chars);
    }

    /**
     * Draw steel sword on the field
     * @param col_pos the column coordinate of item
     * @param row_pos the raw coordinate of item
     * @param screen where will be drawn item
     */
    public static void steelSwordDrawer(int col_pos, int row_pos, Screen screen) {
        swordDrawer(col_pos, row_pos, screen, TextColor.ANSI.CYAN);
    }

    /**
     * Draw cooper sword on the field
     * @param col_pos the column coordinate of item
     * @param row_pos the raw coordinate of item
     * @param screen where will be drawn item
     */
    public static void copperSwordDrawer(int col_pos, int row_pos, Screen screen) {
        swordDrawer(col_pos, row_pos, screen, TextColor.ANSI.RED);
    }

    /**
     * Draw wooden sword on the field
     * @param col_pos the column coordinate of item
     * @param row_pos the raw coordinate of item
     * @param screen where will be drawn item
     */
    public static void woodenSwordDrawer(int col_pos, int row_pos, Screen screen) {
        swordDrawer(col_pos, row_pos, screen, TextColor.ANSI.YELLOW);
    }

    /**
     * Draw food on the field
     * @param col_pos the column coordinate of item
     * @param row_pos the raw coordinate of item
     * @param screen where will be drawn item
     */
    public static void foodDrawer(int col_pos, int row_pos, Screen screen) {
        List<CellChar> chars = Arrays.asList(null,
                new CellChar(Symbols.DIAMOND, TextColor.ANSI.GREEN),
                null);
        drawer(col_pos, row_pos, screen, chars);
    }
}