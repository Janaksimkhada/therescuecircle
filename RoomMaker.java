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
 * @version 01/02/2019
 */

public class RoomMaker
{
    /**
     * Constructor for objects of class RoomMaker
     */
    public RoomMaker()  {
    }

    /**
     * createRooms() creates all the rooms, links them, and populates them with Items.
     * It is static
     */
    public static Room createRooms(ArrayList<Item> inventory)
    {        
    
        Room carpark, assembly_area, valve, kitchen, lift, bathroom, bedroom, office;
      
        // create the rooms
        carpark = new Room("standing in carpark. \nThere is fire in a building. Be caution! while entering inside hotel. \nGo to receiption first and follow the next instruction.");
        office = new Room("in the Receiption, \n'This is an important announcement!' There is a fire in a building. Please do not leave your current room\nuntil we advise you to do so. We are coming to help you, stay Alert");
        valve = new Room("in front of old timber door, but it's locked.\nThere is a key in office. Talk about building security..."); 
        kitchen = new Room("in the kitchen, You see the fire blaze. \nTry to fill waterbottle and procesed to next room");
        lift = new Room("in a lift. Please ensure you press  'up' or 'down' button \n\n'WARNING:' Do not use in case of Fire");
        bedroom = new Room("walking into a Bedroom. \n\nVictim: Thank you for coming. \nYou: No worries, we are here for your service. \n\nRadio Instruction: Try to break the window glas and free from there. Hurry up, you do not have enough time.");
        bathroom = new Room("in the bathroom, it doesn't look like anyones here.");
        assembly_area= new Room("in the assembly area. \n\tCongratulation! you win the Game.\n");
                // start game outside
        
         // initialise room exits
        
        carpark.setExit("west", office);
        
        office.setExit("south",valve);
        office.setExit("east", lift);
        office.setExit("north", assembly_area);
        
        valve.setExit("east", lift);
        valve.setExit("up", null);
        
        lift.setExit("east", null);
        lift.setExit("up", kitchen);
        
        kitchen.setExit("east", bedroom);
        kitchen.setExit("down", null);
        kitchen.setExit("north", bathroom);
        
        bedroom.setExit(null, assembly_area);
        bedroom.setExit("stairs", office);
        
        bathroom.setExit("east", office);
        bathroom.setExit("west", kitchen);
        bathroom.setExit("south", bedroom);
        
        assembly_area.setExit(null, null);
          
        // Add items in room 
        Item key, firehose, waterbottle, ladder, telephone;
        
        carpark.addItem(new Item(null, 0));
        
        office.addItem(new Item("key", 1));
        office.addItem(new Item("telephone",1));
        
        valve.addItem(new Item("firehose", 1));        
        valve.addItem(new Item("ladder", 1));
        
        kitchen.addItem(new Item("waterbottle", 1));
        bathroom.addItem(new Item(null, 0));
        
        lift.addItem(new Item(null, 0));    
        
        bedroom.addItem(new Item("safetygear", 1));
        bedroom.addItem(new Item("telephone", 1));
        
              
        return carpark;
      }
    }
    
