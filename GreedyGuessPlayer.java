package player;
import java.util.ArrayList;
import java.util.Scanner;
import world.World;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Greedy guess player (task B).
 * Please implement this class.
 *
 * @author Youhan, Jeffrey
 */
public class GreedyGuessPlayer  implements Player{

    public ArrayList<Coordinate> possibleHitPoints = new ArrayList<>();
    public ArrayList<Coordinate> otherHitPoints = new ArrayList<>();

    World newWorld = new World();

    public class Coordinate {
        public int row;
        public int column;
    }
    
    public class Toggle {
        public boolean hunter;
        public boolean stalker;
        public boolean killer;
    }

    public int uberEats;
    public int button;
    public Toggle toggle = new Toggle();
    public Coordinate woundShot = new Coordinate();
    public Coordinate executionShot = new Coordinate();
    public int counter;

    @Override
    public void initialisePlayer(World world) {
        //creating and populating an array that contains all possible guess points
        int a, b, c, d, i;

        uberEats = 1;
        newWorld = world;

        toggle.hunter = true;   
        toggle.stalker = false;
        toggle.killer = false;    

        for(a=0; a<10; a++){
            if( (a%2) > 0){
                for(b=1; b<10; b+=2){
                    Coordinate holder = new Coordinate();
                    holder.row = a;
                    holder.column = b;
                    possibleHitPoints.add(holder);
                }
            }
            else{
                for(b=0; b<10; b+=2){
                    Coordinate holder2 = new Coordinate();
                    holder2.row = a;
                    holder2.column = b;
                    possibleHitPoints.add(holder2);
                }
            }
        }          
    } // end of initialisePlayer()

    @Override
    public Answer getAnswer(Guess guess) {
        return null;

    } // end of getAnswer()


    @Override
    public Guess makeGuess() {
        
        if(toggle.hunter == true){
            return hunter();
        }
        else if(toggle.stalker == true){
            return stalker();
        }
        else{
            return null;
        }
        
    } // end of makeGuess()

    public Guess hunter() {
        int d;
        Guess newGuess = new Guess();
        int lengthOfArrayHoldingCoords, n;
        lengthOfArrayHoldingCoords = possibleHitPoints.size();
        int randomNum = ThreadLocalRandom.current().nextInt(0, lengthOfArrayHoldingCoords);  
        System.out.println(randomNum);
        newGuess.row = possibleHitPoints.get(randomNum).row;
        newGuess.column = possibleHitPoints.get(randomNum).column;
        possibleHitPoints.remove(randomNum);
        

        System.out.println(lengthOfArrayHoldingCoords);
        System.out.print(newGuess.row + " ");
        System.out.println(newGuess.column);
        // dummy return
        return newGuess;
    } // end of makeGuess()

    public Guess stalker() {
        int mike, tyson, floyd, mayweather, cool, pool;
        boolean switcher = false;
        cool = 0;
        pool = 0;
        button = 0;
        Coordinate exitWound = new Coordinate();
        Guess stalkGuess = new Guess();
        int lengthOfArrayHoldingCoords, n;
        
        exitWound.row = woundShot.row;
        exitWound.column = woundShot.column;

        tyson = otherHitPoints.size(); 
        
        for(mike=0; mike<tyson; mike++){
            if(otherHitPoints.get(mike).row == stalkGuess.row && otherHitPoints.get(mike).column == stalkGuess.column){
                pool = 1;
                button = 1;
                uberEats++;
            }
            else{
                break;
            }
        }

        tyson = possibleHitPoints.size();        
        
        for(mike=0; mike<tyson; mike++){
            if(possibleHitPoints.get(mike).row == stalkGuess.row && possibleHitPoints.get(mike).column == stalkGuess.column){
                System.out.println("one");
                possibleHitPoints.remove(mike);
                cool = 1;
            }
            else{
                break;
            }
        }

        while(button == 0){
            if(uberEats == 4){
                if(0 <= (exitWound.column-1) && (exitWound.column-1) < 10){
                    stalkGuess.row = exitWound.row;
                    stalkGuess.column = exitWound.column-1;
                    counter = 4;
                    uberEats++;
                    button = 1;                       
                }
                else{ 
                    uberEats++;
                    button = 1;                   
                    break;
                }
            }
            else if(uberEats == 3){
                if(0 <= (exitWound.column+1) && (exitWound.column+1) < 10){
                    stalkGuess.row = exitWound.row;
                    stalkGuess.column = exitWound.column+1;
                    counter = 3;
                    uberEats++;
                    button = 1;
                    
                }
                else{
                    uberEats++;
                    break;
                }
            }
            else if(uberEats == 2){
                if(0 <= (exitWound.row-1) && (exitWound.row-1) < 10){
                    stalkGuess.row = exitWound.row-1;
                    stalkGuess.column = exitWound.column;
                    counter = 2;
                    uberEats++;
                    button = 1;
                    
                }
                else{
                    uberEats++;
                    break;
                }
            }
            else{
                if(0 <= (exitWound.row+1) && (exitWound.row+1) < 10){
                    stalkGuess.row = exitWound.row+1;
                    stalkGuess.column = exitWound.column;
                    counter = 1;
                    button = 1;
                    uberEats++;
                }
                else{
                    uberEats++;
                    break;
                }
            }
        }
        
        if(pool == 0){
            System.out.println("two");
            Coordinate addToShotList = new Coordinate();
            addToShotList.row = stalkGuess.row;
            addToShotList.column = stalkGuess.column;
            otherHitPoints.add(addToShotList);
        }
         

        // dummy return
        return stalkGuess;
    } // end of makeGuess()

    public Guess killer() {
        
        return null;
    } // end of makeGuess()

    @Override
    public void update(Guess guess, Answer answer) {
        if(answer.isHit == true){
            if(toggle.hunter == true){
                toggle.hunter = false;
                toggle.stalker = true;
                woundShot.row = guess.row;
                woundShot.column = guess.column;
                uberEats = 1;
            }
            else if(uberEats == 5){
                toggle.hunter = true;
                toggle.stalker = false;
                uberEats = 1;
                button = 0;
            }
        }
        else{
            if(uberEats == 5){
                toggle.hunter = true;
                toggle.stalker = false;
                uberEats = 1;
                button = 0;
            }
        }
        // To be implemented.randomNum
    } // end of update()


    @Override
    public boolean noRemainingShips() {
        // To be implemented.

        // dummy return
        return true;
    } // end of noRemainingShips()

} // end of class GreedyGuessPlayer
