package view;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import model.Field;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.Random;

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

    /**
     * Function that draws field in the terminal
     * @param field that will be drawn in the terminal
     */
    public void drawMap(Field field) {
        
    }

    private void addTextButton(TextGraphics textGraphics, String textLabel, int column, int row) {
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
        textGraphics.putString(labelBoxTopLeft.withRelative(1, 1), textLabel);
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

    /**
     * Function that draws menu in the terminal
     */
    public void drawMenu(MenuState menuState) {
        try {
            screen.setCursorPosition(null);

            TerminalSize terminalSize = screen.getTerminalSize();
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

            TextGraphics textGraphics = screen.newTextGraphics();
            String startLabel = "  Start game  ";
            String loadLabel  = "  Load game   ";
            String exitLabel  = "  Exit game   ";
            addTextButton(textGraphics, startLabel, 7, 4);
            addTextButton(textGraphics, loadLabel, 7, 10);
            addTextButton(textGraphics, exitLabel, 7, 16);

            drawCat(33, 4);
            drawCat(53, 4);

            screen.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
