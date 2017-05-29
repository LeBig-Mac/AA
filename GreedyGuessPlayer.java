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
    public ArrayList<Coordinate> arrayOfPastHits = new ArrayList<>();

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
        else if(toggle.killer == true){
            return killer();
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
        newGuess.row = possibleHitPoints.get(randomNum).row;
        newGuess.column = possibleHitPoints.get(randomNum).column;
        possibleHitPoints.remove(randomNum);
        
        lengthOfArrayHoldingCoords = possibleHitPoints.size();
        System.out.println("HUNTER");
        System.out.println(lengthOfArrayHoldingCoords);
        System.out.print(newGuess.row + " ");
        System.out.println(newGuess.column);
        // dummy return
        return newGuess;
    } // end of makeGuess()

    public Guess stalker() {
        int mike, tyson, floyd, mayweather, cool, pool, trigger;
        boolean switcher = false;
        trigger = 0;
        cool = 0;
        pool = 0;
        button = 0;
        Coordinate exitWound = new Coordinate();
        Guess stalkGuess = new Guess();
        int lengthOfArrayHoldingCoords, n;
        
        exitWound.row = woundShot.row;
        exitWound.column = woundShot.column;


        while(button == 0){
            if(uberEats == 4){
                trigger = 0;
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
                trigger = 0;
                if(0 <= (exitWound.column+1) && (exitWound.column+1) < 10){
                    Coordinate holdWound3 = new Coordinate();
                    holdWound3.row = exitWound.row;
                    holdWound3.column = exitWound.column+1;

                    for(cool=0; cool<pool; cool++){
                        if(arrayOfPastHits.get(cool).row == holdWound3.row && arrayOfPastHits.get(cool).column == holdWound3.column){
                            System.out.println("STALK WORKING 3");
                            trigger = 1;
                            uberEats++;
                            break;
                        }
                    } 
                    if(trigger == 0){
                        stalkGuess.row = exitWound.row;
                        stalkGuess.column = exitWound.column+1;
                        counter = 3;
                        uberEats++;
                        button = 1;
                    }                              
                }
                else{
                    uberEats++;
                
                }
            }
            else if(uberEats == 2){
                trigger = 0;
                if(0 <= (exitWound.row-1) && (exitWound.row-1) < 10){
                    Coordinate holdWound2 = new Coordinate();
                    holdWound2.row = exitWound.row-1;
                    holdWound2.column = exitWound.column;

                    for(cool=0; cool<pool; cool++){
                        if(arrayOfPastHits.get(cool).row == holdWound2.row && arrayOfPastHits.get(cool).column == holdWound2.column){
                            System.out.println("STALK WORKING 2");
                            trigger = 1;
                            uberEats++;
                            break;
                        }
                    } 
                    if(trigger == 0){
                        stalkGuess.row = exitWound.row-1;
                        stalkGuess.column = exitWound.column;
                        counter = 2;
                        uberEats++;
                        button = 1;
                    }              
                }
                else{
                    uberEats++;
                    
                }
            }
            else{
                if(0 <= (exitWound.row+1) && (exitWound.row+1) < 10){
                    Coordinate holdWound = new Coordinate();
                    holdWound.row = exitWound.row+1;
                    holdWound.column = exitWound.column;

                    for(cool=0; cool<pool; cool++){
                        if(arrayOfPastHits.get(cool).row == holdWound.row && arrayOfPastHits.get(cool).column == holdWound.column){
                            System.out.println("STALK WORKING 1");
                            trigger = 1;
                            uberEats++;
                            break;
                        }
                    }
                    if(trigger == 0){
                        stalkGuess.row = exitWound.row+1;
                        stalkGuess.column = exitWound.column;
                        counter = 1;
                        button = 1;
                        uberEats++;
                    }
                }
                else{
                    uberEats++;
                    
                }
            }
        }
        
        Coordinate stalkHolder = new Coordinate();
        stalkHolder.row = stalkGuess.row;
        stalkHolder.column = stalkGuess.column;
        arrayOfPastHits.add(stalkHolder);

        tyson = possibleHitPoints.size(); 
        
        for(mike=0; mike<tyson-1; mike++){
            if(possibleHitPoints.get(mike).row == stalkGuess.row && possibleHitPoints.get(mike).column == stalkGuess.column){
                possibleHitPoints.remove(mike);
            }       
        }

        lengthOfArrayHoldingCoords = possibleHitPoints.size();
        System.out.println("STALK");
        System.out.println(lengthOfArrayHoldingCoords);
        System.out.print(stalkGuess.row + " ");
        System.out.println(stalkGuess.column);
       
        // dummy return
        return stalkGuess;
    } // end of makeGuess()

    public Guess killer() {
        int canelo, alvarez, trigger, tool, fool, lengthOfArrayHoldingCoords;
        int pride = 0;
        Guess killGuess = new Guess();

        trigger = 0;
        fool = arrayOfPastHits.size();

        while(pride == 0){
            if(counter == 4){
                if(0 <= (executionShot.column-1) && (executionShot.column-1) < 10){
                    Coordinate holdWound4 = new Coordinate();
                    holdWound4.row = executionShot.row;
                    holdWound4.column = executionShot.column-1;

                    for(tool=0; tool<fool; tool++){
                        if(arrayOfPastHits.get(tool).row == holdWound4.row && arrayOfPastHits.get(tool).column == holdWound4.column){
                            System.out.println("WORKING 1");
                            trigger = 1;
                            counter = 3;
                            break;
                        }
                    }
                    if(trigger == 0){
                        killGuess.row = executionShot.row;
                        killGuess.column = executionShot.column-1;
                        
                        executionShot.column = executionShot.column-1;
                        
                        pride = 1;
                    }                          
                }
                else{ 
                    counter = 3;                   
                    break;
                }
            }
            else if(counter == 3){
                if(0 <= (executionShot.column+1) && (executionShot.column+1) < 10){
                    Coordinate holdWound3 = new Coordinate();
                    holdWound3.row = executionShot.row;
                    holdWound3.column = executionShot.column+1;

                    for(tool=0; tool<fool; tool++){
                        if(arrayOfPastHits.get(tool).row == holdWound3.row && arrayOfPastHits.get(tool).column == holdWound3.column){
                            System.out.println("WORKING 2");
                            trigger = 1;
                            counter = 4;
                            break;
                        }
                    }
                    if(trigger == 0){
                        killGuess.row = executionShot.row;
                        killGuess.column = executionShot.column+1;
                       
                        executionShot.column = executionShot.column+1;  
                            
                        pride = 1;
                    }                          
                }   
                else{
                    counter = 4;
                    break;
                }
            }
            else if(counter == 2){
                if(0 <= (executionShot.row-1) && (executionShot.row-1) < 10){
                    Coordinate holdWound2 = new Coordinate();
                    holdWound2.row = executionShot.row;
                    holdWound2.column = executionShot.column-1;

                    for(tool=0; tool<fool; tool++){
                        if(arrayOfPastHits.get(tool).row == holdWound2.row && arrayOfPastHits.get(tool).column == holdWound2.column){
                            System.out.println("WORKING 3");
                            trigger = 1;
                            counter = 1;
                            break;
                        }
                    }
                    if(trigger == 0){
                        killGuess.row = executionShot.row-1;
                        killGuess.column = executionShot.column;
                       
                        executionShot.row = executionShot.row-1; 
                       
                        pride = 1;
                    }                        
                }
                else{
                    counter = 1;
                    break;
                }
            }
            else{
                if(0 <= (executionShot.row+1) && (executionShot.row+1) < 10){
                    Coordinate holdWound = new Coordinate();
                    holdWound.row = executionShot.row;
                    holdWound.column = executionShot.column-1;

                    for(tool=0; tool<fool; tool++){
                        if(arrayOfPastHits.get(tool).row == holdWound.row && arrayOfPastHits.get(tool).column == holdWound.column){
                            System.out.println("WORKING 4");
                            trigger = 1;
                            counter = 2;
                            break;
                        }
                    }
                    if(trigger == 0){
                        killGuess.row = executionShot.row+1;
                        killGuess.column = executionShot.column;
                       
                        executionShot.row = executionShot.row+1;
                       
                        pride = 1;
                    }                              
                }
                else{
                    counter = 2;
                    break;
                }
            }
        }
        Coordinate killHolder = new Coordinate();
        killHolder.row = killGuess.row;
        killHolder.column = killGuess.column;
        arrayOfPastHits.add(killHolder);

        alvarez = possibleHitPoints.size(); 
        
        for(canelo=0; canelo<alvarez-1; canelo++){
            if(possibleHitPoints.get(canelo).row == killGuess.row && possibleHitPoints.get(canelo).column == killGuess.column){
                possibleHitPoints.remove(canelo);
            }       
        }

        lengthOfArrayHoldingCoords = possibleHitPoints.size();
        System.out.println("KILL");
        System.out.println(lengthOfArrayHoldingCoords);
        System.out.print(killGuess.row + " ");
        System.out.println(killGuess.column);
        // dummy return
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
                if(answer.shipSunk == null){
                    executionShot.row = guess.row;
                    executionShot.column = guess.column;
                    toggle.stalker = false;
                    toggle.killer = true; 
                }
                else{
                    toggle.hunter = true;
                    toggle.stalker = false;
                    uberEats = 1;
                    button = 0;
                }                     
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
        // To be implemented.

        // dummy return
        return true;
    } // end of noRemainingShips()

} // end of class GreedyGuessPlayer
