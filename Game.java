import java.util.ArrayList;

/**
 *  This class is the main class of the "The Rescue Circle" application. 
 *  "The rescue Circle" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Janak Simkhada
 * @version 18/01/2019
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private ArrayList<Item> inventory;
      
    
    private ArrayList<Item> getInventory() 
    {
        return inventory;
    }
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        inventory = new ArrayList<Item>();
        currentRoom =RoomMaker.createRooms(inventory);
    }
    
       
          
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Did you enjoyed the Game?");
        System.out.println("\t\tThank you for playing.");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("\t\tTHE END");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        //Insert JOptionpane dialogbox to insert player name
        System.out.println();
        System.out.println("\t\tWelcome to the Game 'The Rescue Circle'");
        System.out.println("This is an exciting, fun and simple text-based game. \nThe player will only win the game if s/he able to unlock all the room and rescue\nthe victm who stuck in a building during a fire in his pleasent stay. ");
        System.out.println("@author  Janak Simkhada \n@version 23/01/2019");
        System.out.println("  ");
        System.out.println("Type 'help' if you need help.");
        System.out.println("Type 'inventory' if you need to show items you are carrying");
        System.out.println("-------------------------------------------------------------------------------------- ");
        System.out.println("  ");
        System.out.println(currentRoom.getLongDescription());
                
    }
    
    
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
     private boolean processCommand(Command command) 
    {
        // boolean wantToQuit = false;
  
        if(command.isUnknown()) 
        {
            System.out.println("I don't know what you mean...");
            return false;
        }
  
        String commandWord = command.getCommandWord();
        
        if(commandWord.equals("help"))
        { 
            printHelp();
        }
                 
        if(commandWord.equals("quit"))
        { 
            return true;
        }
        else if (commandWord.equals("inventory"))
        {
            printInventory();
        }          
        
        else if(commandWord.equals("back"))
        {
        if(command.hasSecondWord())
           System.out.println("Please type 'back' to go back.");
        else
           goBack();
        }
         
        else if (commandWord.equals("look"))
        {
            System.out.println(currentRoom.getLongDescription());} 
        else {
            Room r = currentRoom.respond(command, inventory);
            if (r!=null) currentRoom = r;
        }
         
        return false;
    }
  
   
    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Are you lost? \tDon't worry, we have a solution.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
             
    }

     
    
    private void goBack()
    {
       if(currentRoom!=null)
          {
         System.out.println("You cannot go back any farther.");
           }
       else
        {
        System.out.println(currentRoom.getLongDescription());
         }
    }
    
    /**
     * printInventory prints the contents of "inventory"
     */
    private void printInventory()
    {
        if(inventory.size()==0)
        {
            System.out.println("You do not have anything");
        }
        else
        {
            System.out.print("You are carrying item: ");
            for (int n =0; n<inventory.size(); n++)
            {
                Item item = (Item) inventory.get(n);
                System.out.print(" " + item.getDesc());
            }
            System.out.println();
            System.out.println("Total Quantity: " + Item.totalQuantity(inventory));
        }
      }
   
    
  
    
   /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
   */    
     private boolean quit(Command command) 
     {
        if(command.hasSecondWord()) 
        {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true; 
        }
   }
   
       
 }
