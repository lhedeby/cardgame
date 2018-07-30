package War;

import graphics.Renderable;

import java.awt.*;
import java.io.*;

public class Statistics implements Renderable {
    int gamesPlayed;
    int wins, loses;
    int winRate;

    public Statistics() {

        try {
            BufferedReader br = new BufferedReader((new FileReader(".\\src\\stats.txt")));
            this.gamesPlayed = Integer.parseInt(br.readLine());
            this.wins = Integer.parseInt(br.readLine());
            this.loses = Integer.parseInt(br.readLine());
            this.updateWinRate();
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {

        }
    }

    public void save() {
        try {
            PrintWriter writer = new PrintWriter(".\\src\\stats.txt", "UTF-8");
            writer.println(this.gamesPlayed);
            writer.println(this.wins);
            writer.println(this.loses);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void draw(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.BOLD, 14));

        g.setColor(Color.PINK);
        g.fillRect(350,100, 300, 200);
        g.setColor(Color.BLACK);
        g.drawString("Statistics", 350 + 30, 100 + 30);
        g.drawString("Games Played: " + gamesPlayed, 350 + 30, 100 + 50);
        g.drawString("Wins: " + wins, 350 + 30, 100 + 70);
        g.drawString("Loses: " + loses, 350 + 30, 100 + 90);
        g.drawString("Win Rate: " + winRate + "%", 350 + 30, 100 + 110);

    }

    public void addWin() {
        this.gamesPlayed++;
        this.wins++;
        this.updateWinRate();
    }

    public void addLoss() {
        this.gamesPlayed++;
        this.loses++;
        this.updateWinRate();
    }
    private void updateWinRate() {
        winRate = (int) ((wins * 100d) / gamesPlayed);
    }

}
