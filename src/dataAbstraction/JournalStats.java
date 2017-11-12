package dataAbstraction;

import java.math.BigDecimal;
import java.time.Duration;

public class JournalStats {
    private Duration winDuration;
    private Duration lossDuration;
    private Duration averageDuration;
    private BigDecimal winLossRatio;
    private BigDecimal biggestWin;
    private BigDecimal biggestLoss;
    private Journal myJournal;
    public BigDecimal overalPnL;

    public JournalStats(Journal myJournal) {
        this.myJournal = myJournal;
    }

    public void updateAll(){
        computeWinLoss();
        computePnl();
        computeBiggestWin();
        computeBiggestLoss();
    }

    public void computeWinLoss() {
        int length = myJournal.getSize();
        int largest = 0;
        BigDecimal wins = new BigDecimal(0), loss = new BigDecimal(0);
        for(int i = 0; i< length;i++) {
            if(myJournal.getItem(i).getWinner()!=null) {
                if (myJournal.getItem(i).getWinner()) {
                    wins.add(new BigDecimal(1));
                } else {
                    loss.add(new BigDecimal(1));
                }
            }
        }
        winLossRatio = wins.divide(loss); //Needs to be fixed, Specifically checked for rounding errors TODO::: RoundingMode? only half steps
    }

    public void computePnl() {
        for(int i = 0; i < myJournal.getSize(); i++) {
            overalPnL = overalPnL.add(myJournal.getItem(i).getPnl()); //TODO::Write a Test for this
        }
    }

    public void computeBiggestWin() {
        BigDecimal largest = new BigDecimal(0);
        for(int i = 0; i < myJournal.getSize(); i++) {
            if(myJournal.getItem(i).getPnl().compareTo(largest) > 0) {
                largest = myJournal.getItem(i).getPnl();
            }
        }
        biggestWin = largest;
    }

    public void computeBiggestLoss() {
        //Iterate through and find largest value
        computeBiggestWin();
        BigDecimal largestLoss = biggestWin;
        for(int i = 0; i < myJournal.getSize(); i++) {
            if(myJournal.getItem(i).getPnl().compareTo(largestLoss) < 0) {
                largestLoss = myJournal.getItem(i).getPnl();
            }
        }
        biggestLoss = largestLoss;
    }





}
