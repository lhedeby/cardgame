package War;

import graphics.Renderable;

import java.awt.*;

public class Rules implements Renderable {
    String[] rules = {
            "Both players are dealt half the deck each",
            "Both players turn the top card of their deck",
            "onto the battlefield",
            "the one with the highest card takes the cards",
            "and puts it it in their win stack",
            "",
            "if 2 cards of the same rank are put on the",
            "battlefield a 'war' starts",
            "In war, both players put 3 cards from the top",
            "of their deck face down onto the battlefield",
            "then they continue as usual and puts a card",
            "face up "
    };


    @Override
    public void draw(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        g.setColor(Color.PINK);
        g.fillRect(350,150, 300, 400);

        g.setColor(Color.BLACK);
        g.drawString("Rules", 350 + 30, 150 + 30);
        for(int i = 0; i < rules.length; i++) {
            g.drawString(rules[i], 350 + 30, 200 + 20 * i);

        }
    }
}
