package view;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.Player;
import model.inventory.ArtifactName;
import model.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class ElementDrawer {

    private static final TextCharacter borderChar = new TextCharacter('#', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
    private static final TextCharacter upChar = new TextCharacter('^', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
    private static final TextCharacter leftChar = new TextCharacter('>', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
    private static final TextCharacter rightChar = new TextCharacter('<', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
    private static final TextCharacter arobaseChar = new TextCharacter('@', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
    private static final TextCharacter minusChar = new TextCharacter('-', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
    private static final TextCharacter downChar = new TextCharacter('_', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
    private static final TextCharacter whiteChar = new TextCharacter(' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE);
    private static final TextCharacter greenChar = new TextCharacter(' ', TextColor.ANSI.GREEN, TextColor.ANSI.GREEN);
    private static final TextCharacter magentaChar = new TextCharacter(' ', TextColor.ANSI.MAGENTA, TextColor.ANSI.MAGENTA);
    private static final TextCharacter eyeChar = new TextCharacter(Symbols.BULLET, TextColor.ANSI.GREEN, TextColor.ANSI.DEFAULT);
    private static final TextCharacter noseChar = new TextCharacter(
            Symbols.TRIANGLE_DOWN_POINTING_BLACK, TextColor.ANSI.MAGENTA, TextColor.ANSI.DEFAULT);

    private static TextCharacter getChar(char c) {
        return switch (c) {
            case '^' -> upChar;
            case '>' -> leftChar;
            case '<' -> rightChar;
            case '_' -> downChar;
            case '-' -> minusChar;
            case '@' -> arobaseChar;
            case 'W' -> whiteChar;
            case 'G' -> greenChar;
            case 'M' -> magentaChar;
            case 'Y' -> eyeChar;
            case 'T' -> noseChar;
            default -> null;
        };
    }

    private static final int defaultHp = 100;
    private static final int hpPerHeart = 10;

    public static void addTextButton(TextGraphics textGraphics, String textLabel, int column, int row, boolean selected) {
        int bordersSize = 2;
        int rows = 3;
        char emptyChar = ' ';

        TerminalPosition labelBoxTopLeft = new TerminalPosition(column, row);
        TerminalSize labelBoxSize = new TerminalSize(textLabel.length() + bordersSize, rows);
        TerminalPosition labelBoxTopRightCorner = labelBoxTopLeft.withRelativeColumn(labelBoxSize.getColumns() - 1);

        textGraphics.fillRectangle(labelBoxTopLeft, labelBoxSize, emptyChar);

        int startLineIdx = 1;
        int endLineIdx = labelBoxSize.getColumns() - 2;

        int textRowIdx = 1;
        int sndLineIdx = 2;

        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeColumn(startLineIdx),
                labelBoxTopLeft.withRelativeColumn(endLineIdx),
                Symbols.DOUBLE_LINE_HORIZONTAL);
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeRow(2).withRelativeColumn(startLineIdx),
                labelBoxTopLeft.withRelativeRow(2).withRelativeColumn(endLineIdx),
                Symbols.DOUBLE_LINE_HORIZONTAL);

        textGraphics.setCharacter(labelBoxTopLeft, Symbols.DOUBLE_LINE_TOP_LEFT_CORNER);
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(textRowIdx), Symbols.DOUBLE_LINE_VERTICAL);
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(sndLineIdx), Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER);
        textGraphics.setCharacter(labelBoxTopRightCorner, Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER);
        textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(textRowIdx), Symbols.DOUBLE_LINE_VERTICAL);
        textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(sndLineIdx), Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER);

        if (selected) {
            textGraphics.putString(labelBoxTopLeft.withRelative(startLineIdx, textRowIdx), textLabel, SGR.REVERSE);
        } else {
            textGraphics.putString(labelBoxTopLeft.withRelative(startLineIdx, textRowIdx), textLabel);
        }
    }

    public static void drawImg(Screen screen, int start_column, int start_row, int height, int width, String img) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                TextCharacter curChar = getChar(img.charAt(i * width + j));
                if (curChar != null) {
                    screen.setCharacter(start_column + j, start_row + i, curChar);
                }
            }
        }
    }

    public static void drawMenuBorder(Screen screen) {
        TerminalSize terminalSize = screen.getTerminalSize();
        for (int column = 0; column < terminalSize.getColumns(); column++) {
            screen.setCharacter(column, 0, borderChar);
            screen.setCharacter(column, terminalSize.getRows() - 1, borderChar);
        }
        for (int row = 0; row < terminalSize.getRows(); row++) {
            screen.setCharacter(0, row, borderChar);
            screen.setCharacter(terminalSize.getColumns() - 1, row, borderChar);
        }
    }

    public static void drawBorder(Screen screen, int start_column, int start_row, int width, int height) {
        TextCharacter double_hor = new TextCharacter(
                Symbols.DOUBLE_LINE_HORIZONTAL, TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
        TextCharacter double_ver = new TextCharacter(
                Symbols.DOUBLE_LINE_VERTICAL, TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
        TextCharacter double_bot_left = new TextCharacter(
                Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER, TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
        TextCharacter double_top_left = new TextCharacter(
                Symbols.DOUBLE_LINE_TOP_LEFT_CORNER, TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
        TextCharacter double_top_right = new TextCharacter(
                Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER, TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
        TextCharacter double_bot_right = new TextCharacter(
                Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER, TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);

        for (int column = start_column + 1; column < start_column + width - 1; column++) {
            screen.setCharacter(column, start_row, double_hor);
            screen.setCharacter(column, start_row + height - 1, double_hor);
        }

        for (int row = start_row + 1; row < start_row + height - 1; row++) {
            screen.setCharacter(start_column, row, double_ver);
            screen.setCharacter(start_column + width - 1, row, double_ver);
        }
        screen.setCharacter(start_column, start_row + height - 1, double_bot_left);
        screen.setCharacter(start_column, start_row, double_top_left);
        screen.setCharacter(start_column + width - 1, start_row, double_top_right);
        screen.setCharacter(start_column + width - 1, start_row + height - 1, double_bot_right);
    }

    public static void drawPlayerInventory(Screen screen, int start_column, int start_row, Player player) {
        if (player == null) {
            return;
        }
        if (player.hasArtifact(ArtifactName.BOOTS) && player.isArtifactOn(ArtifactName.BOOTS)) {
            CellDrawer.bootsDrawer(start_column, start_row, screen);
        }
        if (player.hasArtifact(ArtifactName.HELMET) && player.isArtifactOn(ArtifactName.HELMET)) {
            CellDrawer.helmetDrawer(start_column, start_row, screen);
        }
        if (player.hasArtifact(ArtifactName.GLOVES) && player.isArtifactOn(ArtifactName.GLOVES)) {
            CellDrawer.glovesDrawer(start_column, start_row, screen);
        }
        if (player.hasArtifact(ArtifactName.CUIRASS) && player.isArtifactOn(ArtifactName.CUIRASS)) {
            CellDrawer.cuirassDrawer(start_column, start_row, screen);
        }
        if (player.hasArtifact(ArtifactName.RAPIER) && player.isArtifactOn(ArtifactName.RAPIER)) {
            CellDrawer.rapierDrawer(start_column, start_row, screen);
        }
        if (player.hasArtifact(ArtifactName.STEEL_SWORD) && player.isArtifactOn(ArtifactName.STEEL_SWORD)) {
            CellDrawer.steelSwordDrawer(start_column, start_row, screen);
        }
        if (player.hasArtifact(ArtifactName.COPPER_SWORD) && player.isArtifactOn(ArtifactName.COPPER_SWORD)) {
            CellDrawer.copperSwordDrawer(start_column, start_row, screen);
        }
        if (player.hasArtifact(ArtifactName.WOODEN_SWORD) && player.isArtifactOn(ArtifactName.WOODEN_SWORD)) {
            CellDrawer.woodenSwordDrawer(start_column, start_row, screen);
        }
    }

    public static void drawFullInventory(Screen screen, int start_column, int start_row, Player player) {
        List<ArtifactName> artifacts;
        List<Boolean> isOn;
        if (player == null) {
            artifacts = new ArrayList<>();
            isOn = new ArrayList<>();
            for (int i = 0; i < Inventory.INVENTORY_SIZE; i++) {
                artifacts.add(null);
                isOn.add(false);
            }
        } else {
            artifacts = player.getInventory();
            isOn = player.getArtifactsOn();
        }
        TextGraphics inventoryGraphics = screen.newTextGraphics();
        inventoryGraphics.setForegroundColor(TextColor.ANSI.MAGENTA);
        for (int i = 0; i < Inventory.INVENTORY_SIZE; i++) {
            String labelIsOn = isOn.get(i) ? "^" : " ";
            String label = "0" + (i + 1) + " " +
                    labelIsOn + (artifacts.get(i) == null ? "--------" : artifacts.get(i).interfaceName) + labelIsOn;
            inventoryGraphics.putString(start_column + 15 * (i / 2), start_row + 2 * (i % 2), label);
        }
    }

    private static void drawInfoLabel(Screen screen, String label, int start_column, int start_row, TextColor color) {
        TextGraphics expGraphics = screen.newTextGraphics();
        expGraphics.setForegroundColor(color);
        expGraphics.putString(start_column, start_row, label);
    }

    public static void drawHeroInfo(Screen screen, int health, int experience, int level, int start_column, int start_row) {
        TextGraphics livesGraphics = screen.newTextGraphics();
        livesGraphics.setForegroundColor(TextColor.ANSI.RED);
        String livesLabel = "LIVES: ";
        String expLabel = "EXP: " + experience;
        TextCharacter heart = new TextCharacter(
                Symbols.HEART, TextColor.ANSI.RED, TextColor.ANSI.DEFAULT);

        drawInfoLabel(screen, livesLabel, start_column, start_row, TextColor.ANSI.RED);

        for (int i = 0; i < (defaultHp / hpPerHeart); i++) {
            if ((i + 1) * hpPerHeart <= health) {
                screen.setCharacter(start_column + livesLabel.length() + 2 * i, start_row, heart);
            }
        }

        int expIndent = 23;

        drawInfoLabel(screen, expLabel, start_column + expIndent + livesLabel.length(), start_row, TextColor.ANSI.GREEN);

        String levelLabel = "LVL: " + level;
        int lvlIndent = 26;

        drawInfoLabel(screen, levelLabel, start_column + lvlIndent + livesLabel.length() + expLabel.length(), start_row, TextColor.ANSI.WHITE);
    }
}
