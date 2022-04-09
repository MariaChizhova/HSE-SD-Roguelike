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

    public ConsoleDrawer() {
        try {
            terminal = defaultTerminalFactory.createTerminal();
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

    /**
     * Function that draws menu in the terminal
     */
    public void drawMenu(MenuState menuState) {
        try {
            /*
            terminal.enterPrivateMode();
            terminal.clearScreen();
            terminal.setCursorVisible(false);
            for (int i = 0; i < 60; i++) {
                terminal.putCharacter('#');
            }
            terminal.putCharacter('\n');
            for (int j = 0; j < 18; j++) {
                terminal.putCharacter('#');
                terminal.putCharacter('#');
                for (int i = 0; i < 56; i++) {
                    terminal.putCharacter(' ');
                }
                terminal.putCharacter('#');
                terminal.putCharacter('#');
                terminal.putCharacter('\n');
            }
            for (int i = 0; i < 60; i++) {
                terminal.putCharacter('#');
            }
            terminal.putCharacter('\n');

            terminal.flush();

             */

            Screen screen = new TerminalScreen(terminal);

            screen.startScreen();
            screen.setCursorPosition(null);

            TerminalSize terminalSize = screen.getTerminalSize();


            /*
            Just like with Terminal, it's probably easier to draw using TextGraphics. Let's do that to put a little
            box with information on the size of the terminal window
             */
            String sizeLabel = "Terminal Size: " + terminalSize;
            TerminalPosition labelBoxTopLeft = new TerminalPosition(1, 1);
            TerminalSize labelBoxSize = new TerminalSize(sizeLabel.length() + 2, 3);
            TerminalPosition labelBoxTopRightCorner = labelBoxTopLeft.withRelativeColumn(labelBoxSize.getColumns() - 1);
            TextGraphics textGraphics = screen.newTextGraphics();
            //This isn't really needed as we are overwriting everything below anyway, but just for demonstrative purpose
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
            textGraphics.putString(labelBoxTopLeft.withRelative(1, 1), sizeLabel);

            screen.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
