package com.hiru.core_dsa_problems.datastructures.graph.problems;

import com.hiru.core_dsa_problems.commons.MyLogger;

import java.util.HashMap;
import java.util.Random;

//////////////////////////// Class Snake
class Snake {
    int from;
    int to;

    public Snake(int from, int to) {
        if (from <= to) {
            throw new RuntimeException("Invalid Snake: " + from
                    + "-->" + to);
        }
        this.from = from;
        this.to = to;
    }
}

//////////////////////////// Class Ladder
class Ladder {
    int from;
    int to;

    public Ladder(int from, int to) {
        if (from >= to) {
            throw new RuntimeException("Invalid Ladder: " + from
                    + "-->" + to);
        }
        this.from = from;
        this.to = to;
    }
}

/**
 * SNAKE AND LADDER ( Find Min Dice throws)
 * GFG >>> https://www.geeksforgeeks.org/snake-ladder-problem-2/amp/
 */
public class SnakeAndLadder {
    final int START = 1;
    final int END = 30;
    final Random random = new Random();
    HashMap<Integer, Integer> snakesAndLadder = new HashMap<>(); //Snakes and ladders

    //////////////////////////////////
    public SnakeAndLadder() {
        //Init Ladders
        snakesAndLadder.put(3, 22);
        snakesAndLadder.put(5, 8);
        snakesAndLadder.put(11, 26);
        snakesAndLadder.put(20, 29);
        // Init Snakes
        snakesAndLadder.put(27, 1);
        snakesAndLadder.put(21, 9);
        snakesAndLadder.put(17, 4);
        snakesAndLadder.put(19, 7);
    }

    public static void main(String[] args) {
        SnakeAndLadder game = new SnakeAndLadder();
        // game.playGame();// Simple play game
        System.out.println("MIN THROWS >>>> " + game.CORE_getMinDiceThrows(1));
    }

    /////////////////////////////////////// Throw Dice
    private int HOBBY_throwDice() {
        return Math.floorMod(random.nextInt(), 6) + 1; // 1-6 range convert
    }

    /////////////////////////////////////// Get Next Position
    private int HOBBY_getNextPositionForPlay(int position, int dice) {
        // Get next position
        int nextPosition = position + dice;
        // CASE1
        if (nextPosition > END) {
            // Debug log
            MyLogger.info("Dice: " + dice + " :: " + position + " >>>> " + position);
            return position; // NO-OP
        }
        // CASE2 Check if ladder or snake and get next to next position
        else if (snakesAndLadder.containsKey(nextPosition)) {
            // LOGGING-DEBUG
            if (snakesAndLadder.get(nextPosition) > nextPosition) {
                MyLogger.info("|||||| LADDER: Dice :" + dice + " :: " + position + " >>>> " + nextPosition + " || " + snakesAndLadder.get(nextPosition));
            } else {
                MyLogger.info("~~~~~~ SNAKE: Dice : " + dice + " :: " + position + " >>>> " + nextPosition + " ~~ " + snakesAndLadder.get(nextPosition));
            }
            return snakesAndLadder.get(nextPosition); // Process extra
        } else {
            //CASE3
            // just return next position
            MyLogger.info("Dice: " + dice + " :: " + position + " >>>> " + nextPosition);
            return nextPosition;
        }
    }

    ///////////////////////////////////////////// Find min moves ( UNRELATED TO PROBLEM) -- Board is not required at all
    private void HOBBY_playGame() {
        MyLogger.info("START-----------------------------------------------");
        int position = START;
        // Final goal
        while (position != END) {
            // Throw dice
            int dice = HOBBY_throwDice();
            // Get next pos
            int nextPosition = HOBBY_getNextPositionForPlay(position, dice);

            position = nextPosition;
        }
        MyLogger.info("END-----------------------------------------------");
        // Got some solution -- >
    }


    /////////////////////////////////////// Get Next Position
    private int CORE_nextPosition(int position, int dice) {
        // Get next position
        int nextPosition = position + dice;
        // CASE1 Check if ladder or snake and get next to next position
        if (snakesAndLadder.containsKey(nextPosition)) {
            return snakesAndLadder.get(nextPosition); // Process extra
        }
        return nextPosition;
    }


    ///////////////////////////////////////////// Find min moves ( UNRELATED TO PROBLEM) -- Board is not required at all

    /**
     * THIS IS TRICKY PROBLEM -- YOU WILL GET STUCK IN INFINITE loop if you dont take care of repeative snake actions.
     * Ignore snake paths Since it end up loops. :-)
     *
     * @param position
     *
     * @return
     */
    private int CORE_getMinDiceThrows(int position) {
        // Base case -----Happy
        if (position == END) {
            return 0; // NO THROW REQUIRED FOR THIS POSITION..Simply return 0
        }
        // Base case -----Sad
        if (position > END) {
            return Integer.MAX_VALUE;
        }

        //Cases -- possibilities
        int minThrows = Integer.MAX_VALUE;
        // N cases ( RECURSIVE CHOICES )
        for (int dice = 1; dice < 7; dice++) {
            // Get Next position from current state
            int nextPosition = CORE_nextPosition(dice, position); // Recurse
            // DISCARD SNAKE ( learning after lot more debugging)
            if (nextPosition < position) {
                continue;
            }
            // >>>>>>>>>>>> RECURSE --
            int numDiceThrowsInSubtree = CORE_getMinDiceThrows(nextPosition);

            // Callee returned invalid state
            if (numDiceThrowsInSubtree == Integer.MAX_VALUE) {
                continue; // Skip that path // LEARNING--NEVER RETURN FROM BACKTRACKING PORTION EXCEPT BASE CASES :-)
            }

            // Callee returned VALID state
            minThrows = Math.min(minThrows, numDiceThrowsInSubtree);
        }
        return 1 + minThrows; // Count current throw and add min of all subtree throws
    }
}
