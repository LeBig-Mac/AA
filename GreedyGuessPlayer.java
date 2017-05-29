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
    public ArrayList<Guess> arrayOfPastHits = new ArrayList<>();
    private int hitsTaken = 0;
    private int health = 0;

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
        c =0;
        while (c<=newWorld.shipLocations.size()-1){
            health=health+newWorld.shipLocations.get(c).coordinates.size();
            c++;
        }      
    } // end of initialisePlayer()

    @Override
    public Answer getAnswer(Guess guess) {
        
        Answer result = new Answer();
        int i =0, j=0;
        while (i<=newWorld.shipLocations.size()-1){
            while (j<=newWorld.shipLocations.get(i).coordinates.size()-1) {
                if (newWorld.shipLocations.get(i).coordinates.get(j).row == guess.row && newWorld.shipLocations.get(i).coordinates.get(j).column == guess.column) {
                    result.isHit = true;
                    System.out.println("hit");
                    newWorld.shipLocations.get(i).healthLeft--;
                    if (newWorld.shipLocations.get(i).healthLeft<=0) {
                        result.shipSunk = newWorld.shipLocations.get(i).ship;
                    }
                    hitsTaken++;
                    System.out.println("hits taken: " + hitsTaken);
                    return result;
                }
                else{
                    j++;

                }
                
            }
            j=0;
            i++;
        }

        if (result.isHit == false) {
            System.out.println("Miss");
        }
        return result;

    } // end of getAnswer()


    @Override
    public Guess makeGuess() {
        Guess guess = new Guess();
        if(toggle.hunter == true){
            guess = hunter();
            arrayOfPastHits.add(guess);
            return guess;
        }
        else if(toggle.stalker == true){
            guess = stalker();
            arrayOfPastHits.add(guess);
            return guess;   
        }
        else if(toggle.killer == true){
            guess = killer();
            arrayOfPastHits.add(guess);
            return guess;
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
            if (arrayOfPastHits.contains(stalkGuess)) {
            uberEats++;
            bottun=0;
        }
        }
        
        if(pool == 0){
            System.out.println("two");
            Coordinate addToShotList = new Coordinate();
            addToShotList.row = stalkGuess.row;
            addToShotList.column = stalkGuess.column;
            otherHitPoints.add(addToShotList);
        }
         

        
        
        arrayOfPastHits.add(stalkGuess);
        return stalkGuess;
    } // end of makeGuess()

    public Guess killer() {
        int canelo, alvarez;
        int pride = 0;
        Guess killGuess = new Guess();

        
        

        while(pride == 0){
            if(counter == 4){
                if(0 <= (executionShot.column-1) && (executionShot.column-1) < 10){
                    killGuess.row = executionShot.row;
                    killGuess.column = executionShot.column-1;
                    System.out.println(executionShot.column);
                    executionShot.column = executionShot.column-1;
                    System.out.println(executionShot.column);
                    pride = 1;                       
                }
                else{ 
                    pride = 1;                   
                    break;
                }
            }
            else if(counter == 3){
                if(0 <= (executionShot.column+1) && (executionShot.column+1) < 10){
                    killGuess.row = executionShot.row;
                    killGuess.column = executionShot.column+1;
                    System.out.println(executionShot.column);
                    executionShot.column = executionShot.column+1;  
                    System.out.println(executionShot.column);       
                    pride = 1;
                    
                }
                else{
                   
                    break;
                }
            }
            else if(counter == 2){
                if(0 <= (executionShot.row-1) && (executionShot.row-1) < 10){
                    killGuess.row = executionShot.row-1;
                    killGuess.column = executionShot.column;
                    System.out.println(executionShot.row);
                    executionShot.row = executionShot.row-1; 
                    System.out.println(executionShot.row);
                    pride = 1;
                    
                }
                else{
                    break;
                }
            }
            else{
                if(0 <= (executionShot.row+1) && (executionShot.row+1) < 10){
                    killGuess.row = executionShot.row+1;
                    killGuess.column = executionShot.column;
                    System.out.println(executionShot.row);
                    executionShot.row = executionShot.row+1;
                    System.out.println(executionShot.row);
                    pride = 1;
                    
                }
                else{
                    
                    break;
                }
            }
        }
        // dummy return
        arrayOfPastHits.add(killGuess);
        return killGuess;
    } // end of makeGuess()

    @Override
    public void update(Guess guess, Answer answer) {
        if(answer.isHit == true){
            if(toggle.hunter == true && toggle.stalker == false){
                toggle.hunter = false;
                toggle.stalker = true;
                woundShot.row = guess.row;
                woundShot.column = guess.column;
            }
            else if(toggle.stalker == true && toggle.killer == false){
                executionShot.row = guess.row;
                executionShot.column = guess.column;
                toggle.stalker = false;
                toggle.killer = true;                
            }
            else if(toggle.killer == true){
                if(answer.shipSunk == null){
                    toggle.stalker = false;
                    toggle.killer = true;
                }
                else{
                    toggle.stalker = false;
                    toggle.killer = false;
                    toggle.hunter = true;
                    uberEats = 1;
                    button = 0;
                }
            }
            else{
                
            }
        }
        else{
            if(toggle.killer == true){
                if(counter == 4){
                    executionShot.row = woundShot.row;
                    executionShot.column = woundShot.column-1;
                    counter = 3;
                    
                }
                else if(counter == 3){
                    executionShot.row = woundShot.row;
                    executionShot.column = woundShot.column+1;
                    counter = 4;
                    
                }
                else if(counter == 2){
                    executionShot.row = woundShot.row-1;
                    executionShot.column = woundShot.column;
                    counter = 1;
                    
                }
                else if(counter == 1){
                    executionShot.row = woundShot.row+1;
                    executionShot.column = woundShot.column;
                    counter = 2;
                    
                }
                else{
                    
                }
            }
        } 
        // To be implemented.randomNum
    } // end of update()


    @Override
    public boolean noRemainingShips() {
        System.out.println("hits taken: " + hitsTaken);
        System.out.println("health: " + health);
        if (hitsTaken == health) {
            return true;
        }
        // dummy return
        return false;
    } // end of noRemainingShips()

} // end of class GreedyGuessPlayer

