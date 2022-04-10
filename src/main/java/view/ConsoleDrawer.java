package view;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import model.*;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class ConsoleDrawer {

    DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
    Terminal terminal = null;
    Screen screen = null;

    public ConsoleDrawer() {
        try {
            terminal = defaultTerminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);

            screen.startScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Screen getScreen() {
        return screen;
    }

    public void closeAll() throws IOException {
        screen.close();
        terminal.close();
    }

    private void drawBorder() {
        TerminalSize terminalSize = screen.getTerminalSize();
        System.out.println(terminalSize.getColumns());
        System.out.println(terminalSize.getRows());
        for (int column = 0; column < terminalSize.getColumns(); column++) {
            screen.setCharacter(column, 0, new TextCharacter(
                    '#', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
            screen.setCharacter(column, terminalSize.getRows() - 1, new TextCharacter(
                    '#', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
        }
        for (int row = 0; row < terminalSize.getRows(); row++) {
            screen.setCharacter(0, row, new TextCharacter(
                    '#', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
            screen.setCharacter(terminalSize.getColumns() - 1, row, new TextCharacter(
                    '#', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
        }
    }

    private void drawGameBorder(int start_column, int start_row) {
        for (int column = start_column + 1; column < start_column + Field.FIELD_WIDTH * 3 + 1; column++) {
            screen.setCharacter(column, start_row, new TextCharacter(
                    Symbols.DOUBLE_LINE_HORIZONTAL, TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
            screen.setCharacter(column, start_row + Field.FIELD_HEIGHT + 1, new TextCharacter(
                    Symbols.DOUBLE_LINE_HORIZONTAL, TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
        }
        for (int row = start_row + 1; row < start_row + Field.FIELD_HEIGHT + 1; row++) {
            screen.setCharacter(start_column, row, new TextCharacter(
                    Symbols.DOUBLE_LINE_VERTICAL, TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
            screen.setCharacter(start_column + Field.FIELD_WIDTH * 3 + 1, row, new TextCharacter(
                    Symbols.DOUBLE_LINE_VERTICAL, TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
        }
        screen.setCharacter(start_column, start_row + Field.FIELD_HEIGHT + 1, new TextCharacter(
                Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER, TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
        screen.setCharacter(start_column, start_row, new TextCharacter(
                Symbols.DOUBLE_LINE_TOP_LEFT_CORNER, TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
        screen.setCharacter(start_column + Field.FIELD_WIDTH * 3 + 1, start_row, new TextCharacter(
                Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER, TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
        screen.setCharacter(start_column + Field.FIELD_WIDTH * 3 + 1, start_row + Field.FIELD_HEIGHT + 1, new TextCharacter(
                Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER, TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
    }

    private void addTextButton(TextGraphics textGraphics, String textLabel, int column, int row, boolean selected) {
        TerminalPosition labelBoxTopLeft = new TerminalPosition(column, row);
        TerminalSize labelBoxSize = new TerminalSize(textLabel.length() + 2, 3);
        TerminalPosition labelBoxTopRightCorner = labelBoxTopLeft.withRelativeColumn(labelBoxSize.getColumns() - 1);

        textGraphics.fillRectangle(labelBoxTopLeft, labelBoxSize, ' ');

        /*
        Draw horizontal lines, first upper then lower
         */
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeColumn(1),
                labelBoxTopLeft.withRelativeColumn(labelBoxSize.getColumns() - 2),
                Symbols.DOUBLE_LINE_HORIZONTAL);
        textGraphics.drawLine(
                labelBoxTopLeft.withRelativeRow(2).withRelativeColumn(1),
                labelBoxTopLeft.withRelativeRow(2).withRelativeColumn(labelBoxSize.getColumns() - 2),
                Symbols.DOUBLE_LINE_HORIZONTAL);
        /*
        Manually do the edges and (since it's only one) the vertical lines, first on the left then on the right
         */
        textGraphics.setCharacter(labelBoxTopLeft, Symbols.DOUBLE_LINE_TOP_LEFT_CORNER);
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(1), Symbols.DOUBLE_LINE_VERTICAL);
        textGraphics.setCharacter(labelBoxTopLeft.withRelativeRow(2), Symbols.DOUBLE_LINE_BOTTOM_LEFT_CORNER);
        textGraphics.setCharacter(labelBoxTopRightCorner, Symbols.DOUBLE_LINE_TOP_RIGHT_CORNER);
        textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(1), Symbols.DOUBLE_LINE_VERTICAL);
        textGraphics.setCharacter(labelBoxTopRightCorner.withRelativeRow(2), Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER);

        /*
        Finally put the text inside the box
         */
        if (selected) {
            textGraphics.putString(labelBoxTopLeft.withRelative(1, 1), textLabel, SGR.REVERSE);
        } else {
            textGraphics.putString(labelBoxTopLeft.withRelative(1, 1), textLabel);
        }
    }

    private void drawCat(int column, int row) {
        screen.setCharacter(column + 3, row, new TextCharacter(
                ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
        screen.setCharacter(column + 13, row, new TextCharacter(
                ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
        screen.setCharacter(column + 2, row + 1, new TextCharacter(
                ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
        screen.setCharacter(column + 2, row + 2, new TextCharacter(
                ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
        screen.setCharacter(column + 4, row + 1, new TextCharacter(
                ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
        screen.setCharacter(column + 5, row + 2, new TextCharacter(
                ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
        screen.setCharacter(column + 11, row + 2, new TextCharacter(
                ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
        screen.setCharacter(column + 12, row + 1, new TextCharacter(
                ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
        screen.setCharacter(column + 14, row + 1, new TextCharacter(
                ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
        screen.setCharacter(column + 14, row + 2, new TextCharacter(
                ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
        for (int i = 0; i < 5; i++) {
            screen.setCharacter(column + 6 + i, row + 3, new TextCharacter(
                    ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
        }
        for (int i = 0; i < 3; i++) {
            screen.setCharacter(column + 1, row + 3 + i, new TextCharacter(
                    ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
            screen.setCharacter(column + 15, row + 3 + i, new TextCharacter(
                    ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
        }
        for (int i = 0; i < 7; i++) {
            screen.setCharacter(column, row + 6 + i, new TextCharacter(
                    ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
            screen.setCharacter(column + 16, row + 6 + i, new TextCharacter(
                    ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
        }
        screen.setCharacter(column + 1, row + 13, new TextCharacter(
                ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
        screen.setCharacter(column + 15, row + 13, new TextCharacter(
                ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
        for (int i = 0; i < 2; i++) {
            screen.setCharacter(column + 2 + i, row + 14, new TextCharacter(
                    ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
            screen.setCharacter(column + 13 + i, row + 14, new TextCharacter(
                    ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
        }
        for (int i = 0; i < 9; i++) {
            screen.setCharacter(column + 4 + i, row + 15, new TextCharacter(
                    ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
        }
        screen.setCharacter(column + 3, row + 8, new TextCharacter(
                ' ', TextColor.ANSI.GREEN, TextColor.ANSI.GREEN));
        screen.setCharacter(column + 5, row + 8, new TextCharacter(
                ' ', TextColor.ANSI.GREEN, TextColor.ANSI.GREEN));
        screen.setCharacter(column + 11, row + 8, new TextCharacter(
                ' ', TextColor.ANSI.GREEN, TextColor.ANSI.GREEN));
        screen.setCharacter(column + 13, row + 8, new TextCharacter(
                ' ', TextColor.ANSI.GREEN, TextColor.ANSI.GREEN));
        screen.setCharacter(column + 8, row + 10, new TextCharacter(
                ' ', TextColor.ANSI.MAGENTA, TextColor.ANSI.MAGENTA));

        screen.setCharacter(column + 2, row + 8, new TextCharacter(
                ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
        screen.setCharacter(column + 6, row + 8, new TextCharacter(
                ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
        screen.setCharacter(column + 10, row + 8, new TextCharacter(
                ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
        screen.setCharacter(column + 14, row + 8, new TextCharacter(
                ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));

        for (int i = 0; i < 3; i++) {
            screen.setCharacter(column + 3 + i, row + 7, new TextCharacter(
                    ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
            screen.setCharacter(column + 3 + i, row + 9, new TextCharacter(
                    ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
            screen.setCharacter(column + 11 + i, row + 7, new TextCharacter(
                    ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
            screen.setCharacter(column + 11 + i, row + 9, new TextCharacter(
                    ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
        }

        screen.setCharacter(column + 6, row + 11, new TextCharacter(
                ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
        screen.setCharacter(column + 7, row + 12, new TextCharacter(
                ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
        screen.setCharacter(column + 8, row + 11, new TextCharacter(
                ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
        screen.setCharacter(column + 9, row + 12, new TextCharacter(
                ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
        screen.setCharacter(column + 10, row + 11, new TextCharacter(
                ' ', TextColor.ANSI.WHITE, TextColor.ANSI.WHITE));
    }

    private void drawLives(int health, int experience, int start_column, int start_row) {
        TextGraphics livesGraphics = screen.newTextGraphics();
        livesGraphics.setForegroundColor(TextColor.ANSI.RED);
        String livesLabel = "LIVES: ";
        livesGraphics.putString(start_column + 1, start_row - 3, livesLabel);
        for (int i = 0; i < 10; i++) {
            if ((i + 1) * 10 <= health) {
                screen.setCharacter(start_column + livesLabel.length() + 2 * i + 1, start_row - 3, new TextCharacter(
                        Symbols.HEART, TextColor.ANSI.RED, TextColor.ANSI.DEFAULT));
            }
        }

        TextGraphics expGraphics = screen.newTextGraphics();
        expGraphics.setForegroundColor(TextColor.ANSI.GREEN);
        String expLabel = "EXP: " + String.valueOf(experience);
        expGraphics.putString(start_column + 23 + livesLabel.length(), start_row - 3, expLabel);

    }

    /**
     * Function that draws field in the terminal
     * @param field that will be drawn in the terminal
     */
    public void drawMap(Field field) {
        try {
            screen.setCursorPosition(null);
            screen.clear();

            TextGraphics textGraphics = screen.newTextGraphics();
            int start_column = 10;
            int start_row = 6;
            drawGameBorder(start_column - 1, start_row - 1);
            Player player = null;

            for (int i = 0; i < Field.FIELD_HEIGHT; i++) {
                for (int j = 0; j < Field.FIELD_WIDTH; j++) {
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
                        screen.setCharacter(start_column + 3 * j, start_row + i, new TextCharacter(
                                Symbols.ARROW_LEFT, TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
                        screen.setCharacter(start_column + 3 * j + 1, start_row + i, new TextCharacter(
                                Symbols.FACE_BLACK, TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
                        screen.setCharacter(start_column + 3 * j + 2, start_row + i, new TextCharacter(
                                Symbols.ARROW_RIGHT, TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
                    } else if (cell instanceof Wall) {
                        for (int k = 0; k < 3; k++) {
                            screen.setCharacter(start_column + 3 * j + k, start_row + i, new TextCharacter(
                                    Symbols.BLOCK_DENSE, TextColor.ANSI.GREEN, TextColor.ANSI.DEFAULT));
                        }
                    }
                }
            }
            drawLives(player == null ? 100 : player.getHealth(),
                    player == null ? 0 : player.getExperience(),
                    start_column, start_row);

            screen.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that draws menu in the terminal
     * @param mainMenuState tell information about selected button
     */
    public void drawMainMenu(MainMenuState mainMenuState) {
        try {
            screen.setCursorPosition(null);
            screen.clear();

            drawBorder();

            TextGraphics textGraphics = screen.newTextGraphics();
            String startLabel = "  Start game  ";
            String loadLabel  = "  Load game   ";
            String exitLabel  = "  Exit game   ";
            addTextButton(textGraphics, startLabel, 7, 4, mainMenuState.equals(MainMenuState.START));
            addTextButton(textGraphics, loadLabel, 7, 10, mainMenuState.equals(MainMenuState.LOAD_GAME));
            addTextButton(textGraphics, exitLabel, 7, 16, mainMenuState.equals(MainMenuState.EXIT));

            drawCat(33, 4);
            drawCat(53, 4);

            screen.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that draws menu
     * @param menuState tell information about selected button
     */
    public void drawMenu(MenuState menuState) {
        try {
            screen.setCursorPosition(null);
            screen.clear();

            drawBorder();

            TextGraphics textGraphics = screen.newTextGraphics();
            String continueLabel     = "     Continue     ";
            String exitLabel         = "    Exit game     ";
            String saveAndExitLabel  = "Save and exit game";
            addTextButton(textGraphics, continueLabel, 7, 4, menuState.equals(MenuState.CONTINUE));
            addTextButton(textGraphics, exitLabel, 7, 10, menuState.equals(MenuState.EXIT));
            addTextButton(textGraphics, saveAndExitLabel, 7, 16, menuState.equals(MenuState.SAVE_AND_EXIT));

            drawCat(35, 4);
            drawCat(55, 4);

            screen.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
