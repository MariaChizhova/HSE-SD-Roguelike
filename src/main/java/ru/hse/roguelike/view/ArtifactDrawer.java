package ru.hse.roguelike.view;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents class that responsible for drawing artifacts
 */
public class ArtifactDrawer {
    private record ArtifactChar(char elem, TextColor color, int col_pos, int row_pos) {
    }

    private static void drawer(int start_column, int start_row, Screen screen, List<ArtifactChar> chars) {
        for (ArtifactChar artifactChar: chars) {
            screen.setCharacter(start_column + artifactChar.col_pos,
                    start_row + artifactChar.row_pos, new TextCharacter(
                            artifactChar.elem, artifactChar.color, TextColor.ANSI.DEFAULT));
        }
    }

    public static void bootsDrawer(int start_column, int start_row, Screen screen) {
        List<ArtifactChar> chars = new ArrayList<>();
        chars.add(new ArtifactChar(Symbols.BLOCK_MIDDLE, TextColor.ANSI.BLUE, 4, 13));
        chars.add(new ArtifactChar(Symbols.BLOCK_MIDDLE, TextColor.ANSI.BLUE, 8, 13));
        for (int i = 0; i < 3; i++) {
            chars.add(new ArtifactChar(Symbols.BLOCK_MIDDLE, TextColor.ANSI.BLUE, 5, 11 + i));
            chars.add(new ArtifactChar(Symbols.BLOCK_MIDDLE, TextColor.ANSI.BLUE, 7, 11 + i));
        }
        drawer(start_column, start_row, screen, chars);
    }

    public static void helmetDrawer(int start_column, int start_row, Screen screen) {
        List<ArtifactChar> chars = new ArrayList<>();
        chars.add(new ArtifactChar(Symbols.BLOCK_MIDDLE, TextColor.ANSI.BLUE, 3, 1));
        chars.add(new ArtifactChar(Symbols.BLOCK_MIDDLE, TextColor.ANSI.BLUE, 9, 1));
        for (int i = 0; i < 7; i++) {
            chars.add(new ArtifactChar(Symbols.BLOCK_MIDDLE, TextColor.ANSI.BLUE, 3 + i, 0));
        }
        drawer(start_column, start_row, screen, chars);
    }

    public static void glovesDrawer(int start_column, int start_row, Screen screen) {
        List<ArtifactChar> chars = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            chars.add(new ArtifactChar(Symbols.BLOCK_MIDDLE, TextColor.ANSI.BLUE, i, 5));
            chars.add(new ArtifactChar(Symbols.BLOCK_MIDDLE, TextColor.ANSI.BLUE, 11 + i, 5));
        }
        drawer(start_column, start_row, screen, chars);
    }

    public static void cuirassDrawer(int start_column, int start_row, Screen screen) {
        List<ArtifactChar> chars = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            chars.add(new ArtifactChar(Symbols.BLOCK_MIDDLE, TextColor.ANSI.BLUE, 4 + i, 5));
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                chars.add(new ArtifactChar(Symbols.BLOCK_MIDDLE, TextColor.ANSI.BLUE, 5 + j, 6 + i));
            }
        }
        drawer(start_column, start_row, screen, chars);
    }

    public static void rapierDrawer(int start_column, int start_row, Screen screen) {
        List<ArtifactChar> chars = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            if (i != 4) {
                chars.add(new ArtifactChar(Symbols.BOLD_SINGLE_LINE_VERTICAL, TextColor.ANSI.CYAN, 11, 1 + i));
            }
        }
        drawer(start_column, start_row, screen, chars);
    }

    public static void swordDrawer(int start_column, int start_row, Screen screen, TextColor color) {
        List<ArtifactChar> chars = new ArrayList<>();
        chars.add(new ArtifactChar(Symbols.DOUBLE_LINE_VERTICAL_SINGLE_LINE_CROSS, color, 11, 4));
        for (int i = 0; i < 6; i++) {
            if (i != 3 && i != 4) {
                chars.add(new ArtifactChar(Symbols.DOUBLE_LINE_VERTICAL, color, 11, 1 + i));
            }
        }
        drawer(start_column, start_row, screen, chars);
    }

    public static void steelSwordDrawer(int start_column, int start_row, Screen screen) {
        swordDrawer(start_column, start_row, screen, TextColor.ANSI.CYAN);
    }

    public static void cooperSwordDrawer(int start_column, int start_row, Screen screen) {
        swordDrawer(start_column, start_row, screen, TextColor.ANSI.RED);
    }

    public static void woodenSwordDrawer(int start_column, int start_row, Screen screen) {
        swordDrawer(start_column, start_row, screen, TextColor.ANSI.YELLOW);
    }
}
