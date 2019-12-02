import java.util.*;

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
 * @version 27/01/2019
 */

public class Room 
{
    private String description;
    private HashMap<String,Room> exits;       
    private ArrayList<Item> items;
                 

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "in a kitchen" or "in a carpark."
     */
    
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String,Room>();
        items = new ArrayList<Item>();
    }

    /**
     * Define an exit from this room.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * Return the description of the room (the one that was defined in the
     * constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a long description of this room, in the form:
     *     You are in the bedroom.
     *     Exits: north west
     */
         //Long description that show your current location in printWelcome.
    public String getLongDescription()
    {
        String ldescription =  "You are " + getShortDescription() + ".\n" + getExitString();
        if (items.size() > 0) {
            ldescription += "\nItems locate in this Room are: \n";
            for (int n = 0; n < items.size(); n++) {
                Item item = items.get(n);
                ldescription += "\t" + item.getDesc() + "\n";
            }
        }
        return ldescription;
    }
    
    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String direction : keys) {
            returnString += " " + direction;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     */
    public Room getExit(String direction) 
    {
        Room newroom = exits.get(direction);
        if (newroom == null) {
            System.out.println("There is no door, use another exit!");
        }
        return newroom;
    }
    
    /**
     * add an item to the room
     */
    public void addItem(Item i) {
        items.add(i);
    }
    
    /**
     * Get the whole list
     */
    public ArrayList<Item> getItems() {
        return items;
    }
    
    public void setDescription(String s) {
        description = s;    // short description
    }
    
    
    public Room respond(Command c, ArrayList<Item> inventory) {
        String commandWord = c.getCommandWord();
        String object = c.getSecondWord();
        if (commandWord.equals("go")) {
            return goRoom(c, inventory);
        }
        if (commandWord.equals("take")) {
            doTake(c, inventory);
        } 
        else if (commandWord.equals("wear")) {
            doWear(c, inventory);
        }
        else if (commandWord.equals("call")) {
            doCall(c, inventory);
        }
        else if (commandWord.equals("drink")) {
            doDrink(c, inventory);
        }
        
        else if (commandWord.equals("drop")) {
            doDrop(c, inventory);
        } 
        else {           
            System.out.println("Sorry mate, I don't think you can "
               + commandWord + " here!");
        }
        
        return null;    
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new room,
     * otherwise print an error message.
     * print long description: if getExit() returns null, we can't go that way
     * Note that there may be cases where getExit returns the same room as the one we're in;
     */

    private Room goRoom(Command command, ArrayList<Item> inv) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return null;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
            return null;
        } else {
            System.out.println(nextRoom.getLongDescription());
            return nextRoom;
        }
    }
    
    /**
     * doDrop: 
     *   First, checks if user specified a thing to be dropped
     *   Second, checks that it's there in inventory
     *   Third, deletes that item from inventory, and adds to currentRoom
     *   Last, prints a message about it.
     */
    public void doDrop(Command c, ArrayList<Item> inventory) {
        if (! c.hasSecondWord()) {  
            System.out.println("Drop what?");
            return;
        }
        String s = c.getSecondWord();
        Item i = Item.findByName(s, inventory);
        if (i == null) {
            System.out.println("You don't have a " + s);
            return;
        }
        inventory.remove(i);    
        //currentRoom.addItem(i);
        getItems().add(i);     
        System.out.println("You have dropped a " + s);
    }
    
    
    public void doTake(Command c, ArrayList<Item> inventory) {
        if (! c.hasSecondWord()) 
        {  
            System.out.println("Take what?");
            return;
        }
        String s = c.getSecondWord();
        ArrayList<Item> rinventory = getItems();
        Item i = Item.findByName(s, rinventory);
        if (i == null) {
            System.out.println("There isn't a " + s + " here.");
            return;
        }
        rinventory.remove(i);    
        inventory.add(i);
        System.out.println("You have taken the " + s);
    }
    
     
    public void doWear(Command c, ArrayList<Item> inventory) {
        if (! c.hasSecondWord()) 
        {  
            System.out.println("Wear what?");
            return;
        }
        String s = c.getSecondWord();
        ArrayList<Item> rinventory = getItems();
        Item i = Item.findByName(s, rinventory);
        if (i == null) {
            System.out.println("There isn't a " + s + " here.");
            return;
        }
        rinventory.remove(i);    
        inventory.add(i);
        System.out.println("You have wear the " + s);
    }
    
    public void doDrink(Command c, ArrayList<Item> inventory) {
        if (! c.hasSecondWord()) 
        {  
            System.out.println("Drink what?");
            return;
        }
        String s = c.getSecondWord();
        ArrayList<Item> rinventory = getItems();
        Item i = Item.findByName(s, rinventory);
        if (i == null) {
            System.out.println("There is no " + s + " in bottle.");
            return;
        }
        rinventory.remove(i);    
        inventory.add(i);
        System.out.println("You have drink the " + s);
    }
    public void doCall(Command c, ArrayList<Item> inventory) 
    {
        if (! c.hasSecondWord()) 
        {  
            System.out.println("To whom?");
            return;
        }
        String s = c.getSecondWord();
        ArrayList<Item> rinventory = getItems();
        Item i = Item.findByName(s, rinventory);
        if (i == null) {
            System.out.println("There isn't a " + s + "  here.");
            return;
        }
        else{
        System.out.println("You have informed to control room " + s);
        }
     } 
  

}

