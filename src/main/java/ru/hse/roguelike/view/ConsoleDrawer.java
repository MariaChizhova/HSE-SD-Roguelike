package ru.hse.roguelike.view;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import ru.hse.roguelike.model.Field;
import ru.hse.roguelike.model.Player;
import ru.hse.roguelike.model.Position;
import ru.hse.roguelike.model.Wall;
import ru.hse.roguelike.model.enemy.CloneEnemyFactory;
import ru.hse.roguelike.model.enemy.DragonEnemyFactory;
import ru.hse.roguelike.model.enemy.Enemy;
import ru.hse.roguelike.model.enemy.SkeletonEnemyFactory;
import ru.hse.roguelike.model.inventory.ArtifactName;
import ru.hse.roguelike.model.inventory.ArtifactWithPosition;
import ru.hse.roguelike.model.inventory.FoodWithPosition;
import ru.hse.roguelike.model.inventory.Inventory;

import java.io.IOException;
import java.lang.Character;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents console drawer
 */
public class ConsoleDrawer {

    private DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
    private Terminal terminal = null;
    private Screen screen = null;

    private String height = "";
    private String width = "";

    /**
     * Creates ConsoleDrawer instance
     */
    public ConsoleDrawer() {
        try {
            defaultTerminalFactory.setInitialTerminalSize(new TerminalSize(100, 32));
            terminal = defaultTerminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);

            screen.startScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getting screen
     *
     * @return screen
     */
    public Screen getScreen() {
        return screen;
    }

    /**
     * Cloasing screen and terminal
     */
    public void closeAll() throws IOException {
        screen.close();
        terminal.close();
    }

    private final TextCharacter borderChar = new TextCharacter('#', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
    private final TextCharacter upChar = new TextCharacter('^', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
    private final TextCharacter leftChar = new TextCharacter('>', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
    private final TextCharacter rightChar = new TextCharacter('<', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
    private final TextCharacter arobaseChar = new TextCharacter('@', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
    private final TextCharacter minusChar = new TextCharacter('-', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
    private final TextCharacter downChar = new TextCharacter('_', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
    private final TextCharacter whiteChar = new TextCharacter(' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE);
    private final TextCharacter greenChar = new TextCharacter(' ', TextColor.ANSI.GREEN, TextColor.ANSI.GREEN);
    private final TextCharacter magentaChar = new TextCharacter(' ', TextColor.ANSI.MAGENTA, TextColor.ANSI.MAGENTA);
    private final TextCharacter eyeChar = new TextCharacter(Symbols.BULLET, TextColor.ANSI.GREEN, TextColor.ANSI.DEFAULT);
    private final TextCharacter noseChar = new TextCharacter(
            Symbols.TRIANGLE_DOWN_POINTING_BLACK, TextColor.ANSI.MAGENTA, TextColor.ANSI.DEFAULT);

    private final int heroWidth = 13;
    private final int heroHeight = 14;
    private final String hero = "AAAA^___^AAAA" +
                                "AAA<AAAAA>AAA" +
                                "AAA<AYAYA>AAA" +
                                "AAA<AATAA>AAA" +
                                "AAAA<AAA>AAAA" +
                                "<@----W----@>" +
                                "AAAAA<W>AAAAA" +
                                "AAAAA<W>AAAAA" +
                                "AAAAA<W>AAAAA" +
                                "AAAAA<W>AAAAA" +
                                "AAAAA<A>AAAAA" +
                                "AAAAA<A>AAAAA" +
                                "AAAAA<A>AAAAA" +
                                "AAAAWWAWWAAAA";

    private final int catWidth = 17;
    private final int catHeight = 16;

    private final String cat = "AAAWAAAAAAAAAWAAA" +
                               "AAWAWAAAAAAAWAWAA" +
                               "AAWAAWAAAAAWAAWAA" +
                               "AWAAAAWWWWWAAAAWA" +
                               "AWAAAAAAAAAAAAAWA" +
                               "AWAAAAAAAAAAAAAWA" +
                               "WAAAAAAAAAAAAAAAW" +
                               "WAAWWWAAAAAWWWAAW" +
                               "WAWGAGWAAAWGAGWAW" +
                               "WAAWWWAAAAAWWWAAW" +
                               "WAAAAAAAMAAAAAAAW" +
                               "WAAAAAWAWAWAAAAAW" +
                               "WAAAAAAWAWAAAAAAW" +
                               "AWAAAAAAAAAAAAAWA" +
                               "AAWWAAAAAAAAAWWAA" +
                               "AAAAWWWWWWWWWAAAA";

    private final int defaultHp = 100;
    private final int defaultLevel = 10;
    private final int hpPerHeart = 10;
    private final int defaultExp = 0;

    private final int buttonsStartColumn = 7;
    private final int button1StartRow = 4;
    private final int button2StartRow = 10;
    private final int button3StartRow = 16;

    private final int cat1StartColumn = 35;
    private final int cat2StartColumn = 55;
    private final int catsStartRow = 4;

    private void drawMenuBorder() {
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

    private void drawBorder(int start_column, int start_row, int width, int height) {
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

    private TextCharacter getChar(char c) {
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

    private void drawImg(int start_column, int start_row, int height, int width, String img) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                TextCharacter curChar = getChar(img.charAt(i * width + j));
                if (curChar != null) {
                    screen.setCharacter(start_column + j, start_row + i, curChar);
                }
            }
        }
    }

    private void drawInventory(int start_column, int start_row, Player player) {
        if (player == null) {
            return;
        }
        if (player.hasArtifact(ArtifactName.BOOTS)) {
            ArtifactDrawer.bootsDrawer(start_column, start_row, screen);
        }
        if (player.hasArtifact(ArtifactName.HELMET)) {
            ArtifactDrawer.helmetDrawer(start_column, start_row, screen);
        }
        if (player.hasArtifact(ArtifactName.GLOVES)) {
            ArtifactDrawer.glovesDrawer(start_column, start_row, screen);
        }
        if (player.hasArtifact(ArtifactName.CUIRASS)) {
            ArtifactDrawer.cuirassDrawer(start_column, start_row, screen);
        }
        if (player.hasArtifact(ArtifactName.RAPIER)) {
            ArtifactDrawer.rapierDrawer(start_column, start_row, screen);
        }
        if (player.hasArtifact(ArtifactName.STEEL_SWORD)) {
            ArtifactDrawer.steelSwordDrawer(start_column, start_row, screen);
        }
        if (player.hasArtifact(ArtifactName.COPPER_SWORD)) {
            ArtifactDrawer.cooperSwordDrawer(start_column, start_row, screen);
        }
        if (player.hasArtifact(ArtifactName.WOODEN_SWORD)) {
            ArtifactDrawer.woodenSwordDrawer(start_column, start_row, screen);
        }
    }

    private void drawFullInventory(int start_column, int start_row, Player player) {
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

    private void addTextButton(TextGraphics textGraphics, String textLabel, int column, int row, boolean selected) {
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

    private void drawInfoLabel(String label, int start_column, int start_row, TextColor color) {
        TextGraphics expGraphics = screen.newTextGraphics();
        expGraphics.setForegroundColor(color);
        expGraphics.putString(start_column, start_row, label);
    }

    private void drawHeroInfo(int health, int experience, int level, int start_column, int start_row) {
        String livesLabel = "LIVES: ";
        String expLabel = "EXP: " + experience;
        String levelLabel = "LVL: " + level;
        TextCharacter heart = new TextCharacter(Symbols.HEART, TextColor.ANSI.RED, TextColor.ANSI.DEFAULT);
        drawInfoLabel(livesLabel, start_column, start_row, TextColor.ANSI.RED);

        for (int i = 0; i < (defaultHp / hpPerHeart); i++) {
            if ((i + 1) * hpPerHeart <= health) {
                screen.setCharacter(start_column + livesLabel.length() + 2 * i, start_row, heart);
            }
        }

        int expIndent = 23;
        int levelIndent = 26;

        drawInfoLabel(expLabel, start_column + expIndent + livesLabel.length(), start_row, TextColor.ANSI.GREEN);
        drawInfoLabel(levelLabel, start_column + levelIndent + livesLabel.length() + expLabel.length(), start_row, TextColor.ANSI.WHITE);
    }

    private Player drawField(Field field, int start_column, int start_row) {
        Player player = null;
        for (int i = 0; i < field.getHeight(); i++) {
            for (int j = 0; j < field.getWidth(); j++) {
                var cell = field.getCell(new Position(j, i));
                if (cell instanceof Player) {
                    screen.setCharacter(start_column + 3 * j, start_row + i, new TextCharacter(
                            '<', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
                    screen.setCharacter(start_column + 3 * j + 1, start_row + i, new TextCharacter(
                            Symbols.FACE_WHITE, TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
                    screen.setCharacter(start_column + 3 * j + 2, start_row + i, new TextCharacter(
                            '>', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));

                    player = (Player) cell;
                } else if (cell instanceof Enemy) {
                    Enemy enemy = (Enemy) cell;
                    switch (enemy.getName()) {
                        case (DragonEnemyFactory.DRAGON_ENEMY):
                            screen.setCharacter(start_column + 3 * j, start_row + i, new TextCharacter(
                                    '<', TextColor.ANSI.GREEN, TextColor.ANSI.DEFAULT));
                            screen.setCharacter(start_column + 3 * j + 1, start_row + i, new TextCharacter(
                                    Symbols.FACE_BLACK, TextColor.ANSI.RED, TextColor.ANSI.DEFAULT));
                            screen.setCharacter(start_column + 3 * j + 2, start_row + i, new TextCharacter(
                                    '>', TextColor.ANSI.GREEN, TextColor.ANSI.DEFAULT));
                            break;
                        case (CloneEnemyFactory.CLONE_ENEMY):
                            screen.setCharacter(start_column + 3 * j, start_row + i, new TextCharacter(
                                    Symbols.SINGLE_LINE_BOTTOM_LEFT_CORNER, TextColor.ANSI.CYAN, TextColor.ANSI.DEFAULT));
                            screen.setCharacter(start_column + 3 * j + 1, start_row + i, new TextCharacter(
                                    Symbols.FEMALE, TextColor.ANSI.CYAN, TextColor.ANSI.DEFAULT));
                            screen.setCharacter(start_column + 3 * j + 2, start_row + i, new TextCharacter(
                                    Symbols.SINGLE_LINE_BOTTOM_RIGHT_CORNER, TextColor.ANSI.CYAN, TextColor.ANSI.DEFAULT));
                            break;
                        case (SkeletonEnemyFactory.SKELETON_ENEMY):
                            screen.setCharacter(start_column + 3 * j, start_row + i, new TextCharacter(
                                    Symbols.ARROW_LEFT, TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
                            screen.setCharacter(start_column + 3 * j + 1, start_row + i, new TextCharacter(
                                    Symbols.FACE_WHITE, TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
                            screen.setCharacter(start_column + 3 * j + 2, start_row + i, new TextCharacter(
                                    Symbols.ARROW_RIGHT, TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
                            break;
                        default:
                            screen.setCharacter(start_column + 3 * j, start_row + i, new TextCharacter(
                                    Symbols.OUTLINED_SQUARE_SMALL, TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
                            screen.setCharacter(start_column + 3 * j + 1, start_row + i, new TextCharacter(
                                    Symbols.FACE_WHITE, TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
                            screen.setCharacter(start_column + 3 * j + 2, start_row + i, new TextCharacter(
                                    Symbols.OUTLINED_SQUARE_SMALL, TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
                            break;
                    }
                } else if (cell instanceof Wall) {
                    for (int k = 0; k < 3; k++) {
                        screen.setCharacter(start_column + 3 * j + k, start_row + i, new TextCharacter(
                                Symbols.BLOCK_DENSE, TextColor.ANSI.GREEN, TextColor.ANSI.DEFAULT));
                    }
                } else if (cell instanceof ArtifactWithPosition) {
                    var artifact = ((ArtifactWithPosition) cell).getArtifact();
                    switch (artifact.getName()) {
                        case BOOTS:
                            screen.setCharacter(start_column + 3 * j, start_row + i, new TextCharacter(
                                    Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER, TextColor.ANSI.BLUE, TextColor.ANSI.DEFAULT));
                            screen.setCharacter(start_column + 3 * j + 2, start_row + i, new TextCharacter(
                                    Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER, TextColor.ANSI.BLUE, TextColor.ANSI.DEFAULT));
                            break;
                        case GLOVES:
                            screen.setCharacter(start_column + 3 * j, start_row + i, new TextCharacter(
                                    Symbols.CLUB, TextColor.ANSI.BLUE, TextColor.ANSI.DEFAULT));
                            screen.setCharacter(start_column + 3 * j + 2, start_row + i, new TextCharacter(
                                    Symbols.CLUB, TextColor.ANSI.BLUE, TextColor.ANSI.DEFAULT));
                            break;
                        case HELMET:
                            screen.setCharacter(start_column + 3 * j + 1, start_row + i, new TextCharacter(
                                    Symbols.SINGLE_LINE_T_DOUBLE_UP, TextColor.ANSI.BLUE, TextColor.ANSI.DEFAULT));
                            break;
                        case RAPIER:
                            screen.setCharacter(start_column + 3 * j + 1, start_row + i, new TextCharacter(
                                    Symbols.BOLD_SINGLE_LINE_VERTICAL, TextColor.ANSI.CYAN, TextColor.ANSI.DEFAULT));
                            break;
                        case CUIRASS:
                            screen.setCharacter(start_column + 3 * j + 1, start_row + i, new TextCharacter(
                                    Symbols.DOUBLE_LINE_CROSS, TextColor.ANSI.BLUE, TextColor.ANSI.DEFAULT));
                            break;
                        case STEEL_SWORD:
                            screen.setCharacter(start_column + 3 * j + 1, start_row + i, new TextCharacter(
                                    Symbols.ARROW_UP, TextColor.ANSI.CYAN, TextColor.ANSI.DEFAULT));
                            break;
                        case COPPER_SWORD:
                            screen.setCharacter(start_column + 3 * j + 1, start_row + i, new TextCharacter(
                                    Symbols.ARROW_UP, TextColor.ANSI.RED, TextColor.ANSI.DEFAULT));
                            break;
                        case WOODEN_SWORD:
                            screen.setCharacter(start_column + 3 * j + 1, start_row + i, new TextCharacter(
                                    Symbols.ARROW_UP, TextColor.ANSI.YELLOW, TextColor.ANSI.DEFAULT));
                            break;
                    }
                } else if (cell instanceof FoodWithPosition) {
                    screen.setCharacter(start_column + 3 * j + 1, start_row + i, new TextCharacter(
                            Symbols.DIAMOND, TextColor.ANSI.GREEN, TextColor.ANSI.DEFAULT));
                }
            }
        }
        return player;
    }

    /**
     * Function that draws field in the terminal
     *
     * @param field that will be drawn in the terminal
     */
    public void drawMap(Field field) {
        try {
            screen.setCursorPosition(null);
            screen.clear();

            int fieldStartColumn = 2;
            int fieldStartRow = 4;

            int heroStartColumn = 63;
            int heroStartRow = 4;

            int heroInfoStartColumn = 3;
            int heroInfoStartRow = 1;

            int cellSize = 3;
            int borderSize = 1;
            int bordersSize = 2;

            drawBorder(fieldStartColumn - borderSize, fieldStartRow - borderSize,
                    cellSize * field.getWidth() + bordersSize, field.getHeight() + bordersSize);
            drawBorder(heroStartColumn - borderSize, heroStartRow - borderSize,
                    heroWidth + bordersSize, heroHeight + bordersSize);

            Player player = null;

            for (int i = 0; i < field.getHeight(); i++) {
                for (int j = 0; j < field.getWidth(); j++) {
                    var cell = field.getCell(new Position(j, i));
                    if (cell instanceof Player) {
                        CellDrawer.playerDrawer(fieldStartColumn + cellSize * j, fieldStartRow + i, screen);
                        player = (Player) cell;
                    } else if (cell instanceof Enemy) {
                        CellDrawer.enemyDrawer(fieldStartColumn + cellSize * j, fieldStartRow + i, screen);
                    } else if (cell instanceof Wall) {
                        CellDrawer.wallDrawer(fieldStartColumn + cellSize * j, fieldStartRow + i, screen);
                    } else if (cell instanceof ArtifactWithPosition) {
                        var artifact = ((ArtifactWithPosition) cell).getArtifact();
                        switch (artifact.getName()) {
                            case BOOTS -> CellDrawer.bootsDrawer(fieldStartColumn + cellSize * j,
                                    fieldStartRow + i, screen);
                            case GLOVES -> CellDrawer.glovesDrawer(fieldStartColumn + cellSize * j,
                                    fieldStartRow + i, screen);
                            case HELMET -> CellDrawer.helmetDrawer(fieldStartColumn + cellSize * j,
                                    fieldStartRow + i, screen);
                            case RAPIER -> CellDrawer.rapierDrawer(fieldStartColumn + cellSize * j,
                                    fieldStartRow + i, screen);
                            case CUIRASS -> CellDrawer.cuirassDrawer(fieldStartColumn + cellSize * j,
                                    fieldStartRow + i, screen);
                            case STEEL_SWORD -> CellDrawer.steelSwordDrawer(fieldStartColumn + cellSize * j,
                                    fieldStartRow + i, screen);
                            case COPPER_SWORD -> CellDrawer.copperSwordDrawer(fieldStartColumn + cellSize * j,
                                    fieldStartRow + i, screen);
                            case WOODEN_SWORD -> CellDrawer.woodenSwordDrawer(fieldStartColumn + cellSize * j,
                                    fieldStartRow + i, screen);
                        }
                    } else if (cell instanceof FoodWithPosition) {
                        CellDrawer.foodDrawer(fieldStartColumn + cellSize * j,
                                fieldStartRow + i, screen);
                    }
                }
            }
            drawHeroInfo(player == null ? defaultHp : player.getHealth(),
                         player == null ? defaultExp : player.getExperience(),
                         player == null ? defaultLevel : player.getLevel(),
                         heroInfoStartColumn, heroInfoStartRow);
            drawImg(heroStartColumn, heroStartRow, heroHeight, heroWidth, hero);
            drawInventory(heroStartColumn, heroStartRow, player);

            screen.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that draws menu in the terminal
     *
     * @param mainMenuState tell information about selected button
     */
    public void drawMainMenu(MainMenuState mainMenuState) {
        try {
            screen.setCursorPosition(null);
            screen.clear();

            drawMenuBorder();

            String startLabel = "  Start game  ";
            String loadLabel  = "  Load game   ";
            String exitLabel  = "  Exit game   ";

            TextGraphics textGraphics = screen.newTextGraphics();
            addTextButton(textGraphics, startLabel, buttonsStartColumn, button1StartRow, mainMenuState.equals(MainMenuState.START));
            addTextButton(textGraphics, loadLabel, buttonsStartColumn, button2StartRow, mainMenuState.equals(MainMenuState.LOAD_GAME));
            addTextButton(textGraphics, exitLabel, buttonsStartColumn, button3StartRow, mainMenuState.equals(MainMenuState.EXIT));

            drawImg(cat1StartColumn, catsStartRow, catHeight, catWidth, cat);
            drawImg(cat2StartColumn, catsStartRow, catHeight, catWidth, cat);
            screen.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that draws menu with field size
     */
    public void drawAskSize(Character newWidthChar, Character newHeightChar, boolean isOk, boolean isNew) {
        try {
            screen.setCursorPosition(null);
            screen.clear();

            drawMenuBorder();

            TextGraphics textGraphics = screen.newTextGraphics();

            if (!isOk || isNew) {
                height = "";
                width = "";
            }

            if (!isOk) {
                textGraphics.putString(40, 25, "Wrong field size!");
            }

            if (newHeightChar == null && newWidthChar == null) {
                screen.setCharacter(41, 14, new TextCharacter(' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
            } else if (newWidthChar != null) {
                width += newWidthChar;
                if (width.length() == 1) {
                    screen.setCharacter(42, 14, new TextCharacter(' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
                } else {
                    screen.setCharacter(59, 14, new TextCharacter(' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
                }
            } else {
                height += newHeightChar;
                if (height.length() == 1) {
                    screen.setCharacter(60, 14, new TextCharacter(' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
                }
            }

            String enterLabel = "Enter the field size (10 <= width <= 23, 10 <= height <= 20)";
            String spaceLabel = "Press BACKSPACE to load game from file";
            String widthLabel = "width:";
            String heightLabel = "height:";
            textGraphics.putString(18, 4, enterLabel);
            textGraphics.putString(33, 27, spaceLabel);
            textGraphics.putString(33, 14, widthLabel);
            drawBorder(40, 13, 5, 3);
            if (width.length() > 0) {
                textGraphics.putString(41, 14, width);
            }
            textGraphics.putString(50, 14, heightLabel);
            drawBorder(58, 13, 5, 3);
            if (height.length() > 0) {
                textGraphics.putString(59, 14, height);
            }

            screen.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that draws menu
     *
     * @param menuState tell information about selected button
     */
    public void drawMenu(MenuState menuState) {
        try {
            screen.setCursorPosition(null);
            screen.clear();

            drawMenuBorder();

            String continueLabel    = "     Continue     ";
            String exitLabel        = "    Exit game     ";
            String saveAndExitLabel = "Save and exit game";

            TextGraphics textGraphics = screen.newTextGraphics();

            addTextButton(textGraphics, continueLabel, buttonsStartColumn, button1StartRow, menuState.equals(MenuState.CONTINUE));
            addTextButton(textGraphics, exitLabel, buttonsStartColumn, button2StartRow, menuState.equals(MenuState.EXIT));
            addTextButton(textGraphics, saveAndExitLabel, buttonsStartColumn, button3StartRow, menuState.equals(MenuState.SAVE_AND_EXIT));

            drawImg(cat1StartColumn, catsStartRow, catHeight, catWidth, cat);
            drawImg(cat2StartColumn, catsStartRow, catHeight, catWidth, cat);

            screen.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}