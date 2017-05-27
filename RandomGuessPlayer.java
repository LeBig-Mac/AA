package player;

import java.util.Scanner;
import world.World;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

/**
 * Random guess player (task A).
 * Please implement this class.
 *
 * @author Youhan, Jeffrey
 */
public class RandomGuessPlayer implements Player{

    private World gameWorld;
    private ArrayList<Guess> shots = new ArrayList<Guess>();
    private ArrayList<Guess> coords = new ArrayList<Guess>();
    private int hitsTaken = 0;

    @Override
    public void initialisePlayer(World world) {
        gameWorld = world;

        int i,j,a=0,b=0,c=0,d=0;
        i = gameWorld.numRow;
        j = gameWorld.numColumn;
        
        for (a=0;a<=i-1;a++) {
            b=0;
            
            
            while (b<=j-1) {
                
                Guess guesss = new Guess();
                guesss.row = a;
                guesss.column = b;
                coords.add(guesss);
                b++;
            }
            
        }

    } // end of initialisePlayer()

    @Override
    public Answer getAnswer(Guess guess) {
        Answer result = new Answer();
        int i =0, j=0;
        while (i<=gameWorld.shipLocations.size()-1){
            while (j<=gameWorld.shipLocations.get(i).coordinates.size()-1) {
                if (gameWorld.shipLocations.get(i).coordinates.get(j).row == guess.row && gameWorld.shipLocations.get(i).coordinates.get(j).column == guess.column) {
                    result.isHit = true;
                    System.out.println("hit");

                    hitsTaken++;
                    return result;
                }
                j++;
            }
            j=0;
            i++;
        }

        
        return result;
    } // end of getAnswer()


    @Override
    public Guess makeGuess() {

        int ranVal = ThreadLocalRandom.current().nextInt(0, coords.size());

        Guess ranGuess = new Guess();
        ranGuess.row = coords.get(ranVal).row;
        ranGuess.column = coords.get(ranVal).column;
        
        //coords.remove(ranVal);
        //System.out.println(ranGuess.row);
        //System.out.println(ranGuess.column);
        return ranGuess;
    } // end of makeGuess()


    @Override
    public void update(Guess guess, Answer answer) {

    } // end of update()


    @Override
    public boolean noRemainingShips() {
        // To be implemented.
        if (hitsTaken == 17) {
            return false;
        }
        // dummy return
        return false;
    } // end of noRemainingShips()

} // end of class RandomGuessPlayer
