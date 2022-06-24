package view;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import model.*;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import model.enemy.CloneEnemyFactory;
import model.enemy.DragonEnemyFactory;
import model.enemy.Enemy;
import model.enemy.SkeletonEnemyFactory;
import model.inventory.ArtifactWithPosition;
import model.inventory.FoodWithPosition;

import java.io.IOException;
import java.lang.Character;

/**
 * Represents console drawer
 */
public class ConsoleDrawer {

    DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
    Terminal terminal = null;
    Screen screen = null;

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

    private final int catWidth = 17;
    private final int catHeight = 16;

    private final int buttonsStartColumn = 7;
    private final int button1StartRow = 4;
    private final int button2StartRow = 10;
    private final int button3StartRow = 16;

    private final int cat1StartColumn = 35;
    private final int cat2StartColumn = 55;
    private final int catsStartRow = 4;

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

            int fullInventoryStartColumn = 2;
            int fullInventoryStartRow = 25;

            int heroStartColumn = 63;
            int heroStartRow = 4;

            int heroInfoStartColumn = 3;
            int heroInfoStartRow = 1;

            int cellSize = 3;
            int borderSize = 1;
            int bordersSize = 2;

            ElementDrawer.drawBorder(screen, fieldStartColumn - borderSize, fieldStartRow - borderSize,
                    cellSize * field.getWidth()  + bordersSize, field.getHeight() + bordersSize);
            int heroWidth = 13;
            int heroHeight = 14;
            ElementDrawer.drawBorder(screen, heroStartColumn - borderSize, heroStartRow - borderSize,
                    heroWidth + bordersSize, heroHeight + bordersSize);
            Player player = null;

            for (int i = 0; i < field.getHeight(); i++) {
                for (int j = 0; j < field.getWidth(); j++) {
                    var cell = field.getCell(new Position(j, i));
                    if (cell instanceof Player) {
                        CellDrawer.playerDrawer(fieldStartColumn + cellSize * j, fieldStartRow + i, screen);
                        player = (Player) cell;
                    } else if (cell instanceof Enemy enemy) {
                        switch (enemy.getName()) {
                            case DragonEnemyFactory.DRAGON_ENEMY -> CellDrawer.dragonEnemyDrawer(
                                    fieldStartColumn + cellSize * j, fieldStartRow + i, screen);
                            case CloneEnemyFactory.CLONE_ENEMY -> CellDrawer.cloneEnemyDrawer(
                                    fieldStartColumn + cellSize * j, fieldStartRow + i, screen);
                            case SkeletonEnemyFactory.SKELETON_ENEMY -> CellDrawer.skeletonEnemyDrawer(
                                    fieldStartColumn + cellSize * j, fieldStartRow + i, screen);
                            default -> CellDrawer.defaultEnemyDrawer(
                                    fieldStartColumn + cellSize * j, fieldStartRow + i, screen);
                        }
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
            int defaultHp = 100;
            int defaultExp = 0;
            int defaultLvl = 0;
            ElementDrawer.drawHeroInfo(screen,
                    player == null ? defaultHp : player.getHealth(),
                    player == null ? defaultExp : player.getExperience(),
                    player == null ? defaultLvl : player.getLevel(),
                    heroInfoStartColumn, heroInfoStartRow);
            ElementDrawer.drawImg(screen, heroStartColumn, heroStartRow, heroHeight, heroWidth, Pictures.HERO.getPicture());
            ElementDrawer.drawPlayerInventory(screen, heroStartColumn, heroStartRow, player);
            ElementDrawer.drawFullInventory(screen, fullInventoryStartColumn, fullInventoryStartRow, player);
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

            ElementDrawer.drawMenuBorder(screen);

            String startLabel = "  Start game  ";
            String loadLabel  = "  Load game   ";
            String exitLabel  = "  Exit game   ";

            TextGraphics textGraphics = screen.newTextGraphics();
            ElementDrawer.addTextButton(textGraphics, startLabel, buttonsStartColumn, button1StartRow, mainMenuState.equals(MainMenuState.START));
            ElementDrawer.addTextButton(textGraphics, loadLabel, buttonsStartColumn, button2StartRow, mainMenuState.equals(MainMenuState.LOAD_GAME));
            ElementDrawer.addTextButton(textGraphics, exitLabel, buttonsStartColumn, button3StartRow, mainMenuState.equals(MainMenuState.EXIT));

            ElementDrawer.drawImg(screen, cat1StartColumn, catsStartRow, catHeight, catWidth, Pictures.CAT.getPicture());
            ElementDrawer.drawImg(screen, cat2StartColumn, catsStartRow, catHeight, catWidth, Pictures.CAT.getPicture());

            screen.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that draws menu with field size
     */
    public void drawAskSize(Character newWidthChar, Character newHeightChar, boolean isOk, boolean toClear) {
        try {
            screen.setCursorPosition(null);
            screen.clear();

            ElementDrawer.drawMenuBorder(screen);

            TextGraphics textGraphics = screen.newTextGraphics();

            String wrongSizeLabel = "Wrong field size!";
            int wrongSizeStartColumn = 40;
            int wrongSizeStartRow = 25;

            int numbersRow = 14;
            int fstWidthColumn = 41;
            int fstHeightColumn = 59;
            int nextChar = 1;

            int boxWidth = 5;
            int boxHeight = 3;

            var selectedChar = new TextCharacter(' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE);

            if (toClear || !isOk) {
                height = "";
                width = "";
            }
            if (!isOk) {
                textGraphics.putString(wrongSizeStartColumn, wrongSizeStartRow, wrongSizeLabel);
            }

            if (newHeightChar == null && newWidthChar == null) {
                screen.setCharacter(fstWidthColumn, numbersRow, selectedChar);
            } else if (newWidthChar != null) {
                width += newWidthChar;
                if (width.length() == 1) {
                    screen.setCharacter(fstWidthColumn + nextChar, numbersRow, selectedChar);
                } else {
                    screen.setCharacter(fstHeightColumn, numbersRow, selectedChar);
                }
            } else {
                height += newHeightChar;
                if (height.length() == 1) {
                    screen.setCharacter(fstHeightColumn + nextChar, numbersRow, selectedChar);
                }
            }

            String enterLabel = "Enter the field size";
            String spaceLabel = "Press BACKSPACE to load game from file";
            String widthLabel = "width:";
            String heightLabel = "height:";

            int enterLabelStartColumn = 40;
            int enterLabelStartRow = 4;
            int spaceLabelStartColumn = 33;
            int spaceLabelStartRow = 27;
            int widthLabelStartColumn = 33;
            int heightLabelStartColumn = 50;

            textGraphics.putString(enterLabelStartColumn, enterLabelStartRow, enterLabel);
            textGraphics.putString(spaceLabelStartColumn, spaceLabelStartRow, spaceLabel);
            textGraphics.putString(widthLabelStartColumn, numbersRow, widthLabel);
            textGraphics.putString(heightLabelStartColumn, numbersRow, heightLabel);

            ElementDrawer.drawBorder(screen, fstWidthColumn - nextChar, numbersRow - nextChar, boxWidth, boxHeight);
            if (width.length() > 0) {
                textGraphics.putString(fstWidthColumn, numbersRow, width);
            }

            ElementDrawer.drawBorder(screen, fstHeightColumn - nextChar, numbersRow - nextChar, boxWidth, boxHeight);
            if (height.length() > 0) {
                textGraphics.putString(fstHeightColumn, numbersRow, height);
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

            ElementDrawer.drawMenuBorder(screen);

            String continueLabel    = "     Continue     ";
            String exitLabel        = "    Exit game     ";
            String saveAndExitLabel = "Save and exit game";

            TextGraphics textGraphics = screen.newTextGraphics();

            ElementDrawer.addTextButton(textGraphics, continueLabel, buttonsStartColumn, button1StartRow, menuState.equals(MenuState.CONTINUE));
            ElementDrawer.addTextButton(textGraphics, exitLabel, buttonsStartColumn, button2StartRow, menuState.equals(MenuState.EXIT));
            ElementDrawer.addTextButton(textGraphics, saveAndExitLabel, buttonsStartColumn, button3StartRow, menuState.equals(MenuState.SAVE_AND_EXIT));

            ElementDrawer.drawImg(screen, cat1StartColumn, catsStartRow, catHeight, catWidth, Pictures.CAT.getPicture());
            ElementDrawer.drawImg(screen, cat2StartColumn, catsStartRow, catHeight, catWidth, Pictures.CAT.getPicture());

            screen.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Print exception message on screen
     */
    public void drawException(String exceptionMsg) {
        int exceptionCol = 20;
        int exceptionRow = 28;
        ElementDrawer.drawLabel(screen, exceptionMsg, exceptionCol, exceptionRow, TextColor.ANSI.RED);
    }

}