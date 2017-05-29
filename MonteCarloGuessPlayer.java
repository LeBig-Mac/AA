package player;
import java.util.ArrayList;
import java.util.Scanner;
import world.World;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Monte Carlo guess player (task C).
 * Please implement this class.
 *
 * @author Youhan, Jeffrey
 */
public class MonteCarloGuessPlayer  implements Player{

    World newWorld = new World();

    public ArrayList<Carlito> arrayOfHitPoints = new ArrayList<>();
    public ArrayList<Coordinate> arrayOfPastHits = new ArrayList<>();
    

    public class Toggle {
        public boolean monteCarloHunter;
        public boolean stalker;
        public boolean killer;
    }
    public class Carlito {
        public Coordinate pos = new Coordinate();
        public int score;   
    }

    public class Coordinate {
        public int row;
        public int column;
    }

    public class ScoreSheet {
        public int four;
        public int three;
        public int two;
        public int one;
    }

    public int scoreWeWant;
    public boolean dirty;
    public int uberEats;
    public int button;
    public int counter;
    public Coordinate executionShot = new Coordinate();
    public Coordinate woundShot = new Coordinate();
    public Toggle monteToggle = new Toggle();
    public ScoreSheet monteScore = new ScoreSheet();

    @Override
    public void initialisePlayer(World world) {
        int a, b, c, d, i, j;
        a = 0;
        b = 0;
        newWorld = world;

        dirty = true;
        scoreWeWant = 4;
        i = newWorld.numRow;
        j = newWorld.numColumn;

        monteScore.four = 0;
        monteScore.three = 0;
        monteScore.two = 0;
        monteScore.one = 0;

        
        monteToggle.monteCarloHunter = true;
        monteToggle.stalker = false;
        monteToggle.killer = false;
        
        for(a=0; a<i; a++){
            for(b=0; b<j; b++){
                if(0 <= (a-4) && (a+4) < i && 0 <= (b-4) && (b+4) < j){
                    Carlito holderPoint = new Carlito();
                    holderPoint.pos.row = a;
                    holderPoint.pos.column = b;
                    holderPoint.score = 4;
                    arrayOfHitPoints.add(holderPoint);
                    if(arrayOfHitPoints.contains(holderPoint)){
                        System.out.println("TRUEEEE");
                    }
                    monteScore.four = monteScore.four+1;
                    System.out.println(monteScore.four);
                    System.out.print(holderPoint.pos.row + " ");
                    System.out.println(holderPoint.pos.column);

                }
                if(0 <= (a-3) && (a+3) < i && 0 <= (b-3) && (b+3) < j){
                    Carlito holderPoint2 = new Carlito();
                    holderPoint2.pos.row = a;
                    holderPoint2.pos.column = b;
                    holderPoint2.score = 3;
                    arrayOfHitPoints.add(holderPoint2);
                    monteScore.three = monteScore.three+1;
                    System.out.println(monteScore.three);
                    System.out.print(holderPoint2.pos.row + " ");
                    System.out.println(holderPoint2.pos.column);
                }
                if(0 <= (a-2) && (a+2) < i && 0 <= (b-2) && (b+2) < j){
                    Carlito holderPoint3 = new Carlito();
                    holderPoint3.pos.row = a;
                    holderPoint3.pos.column = b;
                    holderPoint3.score = 2;
                    arrayOfHitPoints.add(holderPoint3);
                    monteScore.two++;
                }
                else{
                    Carlito holderPoint4 = new Carlito();
                    holderPoint4.pos.row = a;
                    holderPoint4.pos.column = b;
                    holderPoint4.score = 1;
                    arrayOfHitPoints.add(holderPoint4);
                    monteScore.one++;
                }
            }
        }

    } // end of initialisePlayer()

    public void createAnArray(int a, int b, int i, int j, ArrayList<Coordinate> array){
        for(a=0; a<i; a++){
            if(a == 0 || ((a+1)%5 == 0)){         
                for(b=0; b<j; b++){
                    if(dirty == true){
                        Coordinate holder3 = new Coordinate();
                        holder3.row = a;
                        holder3.column = b;
                        array.add(holder3);
                    }
                    else{
                        Coordinate holder3 = new Coordinate();
                        holder3.row = b;
                        holder3.column = a;
                        array.add(holder3);
                    }
                }
            }
            else{

            }
        }
    }

    public void createAnArray2(int a, int b, int i, int j, ArrayList<Coordinate> array){
        for(a=0; a<i; a++){
            if(a == 0 || (a%4 == 0)){   
                for(b=0; b<j; b++){
                    Coordinate holder4 = new Coordinate();
                    holder4.row = a;
                    holder4.column = b;
                    array.add(holder4);
                    }
            }
            else{

            }
        }     
    }

    @Override
    public Answer getAnswer(Guess guess) {
        // To be implemented.

        // dummy return
        return null;
    } // end of getAnswer()


    @Override
    public Guess makeGuess() {
        if(monteToggle.monteCarloHunter == true){
            return monteCarloHunter();
        }
        else if(monteToggle.stalker == true){
            return stalker();
        }
        else if(monteToggle.killer == true){
            return killer();
        }
        else{
            return null;
        }
        // dummy return
    } // end of makeGuess()

    public Guess monteCarloHunter() {
        int d, lengthOfArray, b;
        Guess newGuess = new Guess();
        b = 0;
        lengthOfArray = arrayOfHitPoints.size();

        if(0 < monteScore.four){
            System.out.println("here1");
            System.out.println(monteScore.four);
            scoreWeWant = 4;
            monteScore.four = monteScore.four-1;
        }
        else if(0 < monteScore.three){
            System.out.println("here2");
            scoreWeWant = 3;
            monteScore.three = monteScore.three-1;
        }
        else if(0 < monteScore.two){
            System.out.println("here3");
            scoreWeWant = 2;
            monteScore.two = monteScore.two-1;
        }
        else{
            System.out.println("here4");
            scoreWeWant = 1;
            monteScore.one = monteScore.one-1;
        }

        for(d=0; d<lengthOfArray; d++){
            if(arrayOfHitPoints.get(d).score == scoreWeWant){
                newGuess.row = arrayOfHitPoints.get(d).pos.row;
                newGuess.column = arrayOfHitPoints.get(d).pos.column;
                b = d;
                Coordinate holderCoord = new Coordinate();
                holderCoord.row = newGuess.row;
                holderCoord.column = newGuess.column;
                arrayOfPastHits.add(holderCoord);
            }
            else{

            }
        }

        arrayOfHitPoints.remove(b);
        // dummy return
        return newGuess;
    } // end of makeGuess()


    public Guess stalker() {
        int mike, tyson, floyd, mayweather, cool, pool;
        boolean switcher = false;
        pool = 0;
        button = 0;
        Coordinate exitWound = new Coordinate();
        Guess stalkGuess = new Guess();
        int lengthOfArrayHoldingCoords, n;
        
        exitWound.row = woundShot.row;
        exitWound.column = woundShot.column;

        while(button == 0){
            if(uberEats == 4){
                if(0 <= (exitWound.column-1) && (exitWound.column-1) < 10){
                    Coordinate holdWound4 = new Coordinate();
                    holdWound4.row = exitWound.row;
                    holdWound4.column = exitWound.column-1;

                    if(arrayOfPastHits.contains(holdWound4)){
                        uberEats++;
                        break;
                    }
                    else{
                        stalkGuess.row = exitWound.row;
                        stalkGuess.column = exitWound.column-1;
                        counter = 4;
                        uberEats++;
                        button = 1;
                    }    
                       
                }
                else{ 
                    uberEats++;
                    button = 1;                   
                    break;
                }
            }
            else if(uberEats == 3){
                if(0 <= (exitWound.column+1) && (exitWound.column+1) < 10){
                    Coordinate holdWound3 = new Coordinate();
                    holdWound3.row = exitWound.row;
                    holdWound3.column = exitWound.column+1;

                    if(arrayOfPastHits.contains(holdWound3)){
                        uberEats++;
                        break;
                    }
                    else{
                        stalkGuess.row = exitWound.row;
                        stalkGuess.column = exitWound.column+1;
                        counter = 3;
                        uberEats++;
                        button = 1;
                    }                               
                }
                else{
                    uberEats++;
                    break;
                }
            }
            else if(uberEats == 2){
                if(0 <= (exitWound.row-1) && (exitWound.row-1) < 10){
                    Coordinate holdWound2 = new Coordinate();
                    holdWound2.row = exitWound.row-1;
                    holdWound2.column = exitWound.column;

                    if(arrayOfPastHits.contains(holdWound2)){
                        uberEats++;
                        break;
                    }
                    else{
                        stalkGuess.row = exitWound.row-1;
                        stalkGuess.column = exitWound.column;
                        counter = 2;
                        uberEats++;
                        button = 1;
                    }               
                }
                else{
                    uberEats++;
                    break;
                }
            }
            else{
                if(0 <= (exitWound.row+1) && (exitWound.row+1) < 10){
                    Coordinate holdWound = new Coordinate();
                    holdWound.row = exitWound.row+1;
                    holdWound.column = exitWound.column;

                    if(arrayOfPastHits.contains(holdWound)){
                        uberEats++;
                        break;
                    }
                    else{
                        stalkGuess.row = exitWound.row+1;
                        stalkGuess.column = exitWound.column;
                        counter = 1;
                        button = 1;
                        uberEats++;
                    }
                }
                else{
                    uberEats++;
                    break;
                }
            }
        }
        
        tyson = arrayOfHitPoints.size();

        for(mike=0; mike<tyson; mike++){
            if(arrayOfHitPoints.get(mike).pos.row == stalkGuess.row && arrayOfHitPoints.get(mike).pos.column == stalkGuess.column){
                arrayOfHitPoints.remove(mike);
            }
            else{
                break;
            }
        }

        /*
        if(pool == 0){
            System.out.println("two");
            Coordinate addToShotList = new Coordinate();
            addToShotList.row = stalkGuess.row;
            addToShotList.column = stalkGuess.column;
            otherHitPoints.add(addToShotList);
        }*/
         
        System.out.println("here 2");
        // dummy return
        return stalkGuess;
    } // end of makeGuess()

    public Guess killer() {
        int pride = 0;
        int floyd, mayweather;
        Guess killGuess = new Guess();

        while(pride == 0){
            if(counter == 4){
                if(0 <= (executionShot.column-1) && (executionShot.column-1) < 10){
                    killGuess.row = executionShot.row;
                    killGuess.column = executionShot.column-1;
                  
                    executionShot.column = executionShot.column-1;
               
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
                   
                    executionShot.column = executionShot.column+1;  
                       
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
                
                    executionShot.row = executionShot.row-1; 
                
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
               
                    executionShot.row = executionShot.row+1;
             
                    pride = 1;
                    
                }
                else{
                    
                    break;
                }
            }
        }
        System.out.println("here 3");

        mayweather = arrayOfHitPoints.size();
        
        for(floyd=0; floyd<mayweather; floyd++){
            if(arrayOfHitPoints.get(floyd).pos.row == killGuess.row && arrayOfHitPoints.get(floyd).pos.column == killGuess.column){
                arrayOfHitPoints.remove(floyd);
            }
            else{
                break;
            }
        }

        // dummy return
        return killGuess;
    } // end of makeGuess()

    public void checkContains(ArrayList<Coordinate> array, Guess stalkGuess){
        int mike, tyson;

        tyson = array.size();

        for(mike=0; mike<tyson; mike++){
            if(array.get(mike).row == stalkGuess.row && array.get(mike).column == stalkGuess.column){
                array.remove(mike);
            }
            else{
                break;
            }
        }
    }

    @Override
    public void update(Guess guess, Answer answer) {
        if(answer.isHit == true){
            if(monteToggle.monteCarloHunter == true && monteToggle.stalker == false){
                monteToggle.monteCarloHunter = false;
                monteToggle.stalker = true;
                woundShot.row = guess.row;
                woundShot.column = guess.column;
            }
            else if(monteToggle.stalker == true && monteToggle.killer == false){
                executionShot.row = guess.row;
                executionShot.column = guess.column;
                monteToggle.stalker = false;
                monteToggle.killer = true;                
            }
            else if(monteToggle.killer == true){
                if(answer.shipSunk == null){
                    monteToggle.stalker = false;
                    monteToggle.killer = true;
                }
                else{
                    monteToggle.stalker = false;
                    monteToggle.killer = false;
                    monteToggle.monteCarloHunter = true;
                    uberEats = 1;
                    button = 0;
                }
            }
            else{
                
            }
        }
        else{
            if(monteToggle.killer == true){
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
            else{

            }
        } 

    } // end of update()


    @Override
    public boolean noRemainingShips() {
        // To be implemented.

        // dummy return
        return true;
    } // end of noRemainingShips()

} // end of class MonteCarloGuessPlayer
