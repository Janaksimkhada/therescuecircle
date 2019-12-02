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
 * @version 28/01/2019
 */

  
public class Player
{
private String playerName;
private Room currentRoom;
private int maxQuantity;
private Item itemInfo;
private int currentQuantity;
    private HashMap<String, Item> items;
 
public Player(String playerName, Room currentRoom, int Quantity)
{
this.playerName = playerName;
this.currentRoom = currentRoom;
this.maxQuantity = maxQuantity;
currentQuantity = Quantity;
        items = new HashMap<String, Item>();
}
 
public String getPlayerName()
{
return playerName;
}

public int getMaxQuantity()
{
    return maxQuantity;
}
  
public void setCurrentQuantity(int quantity)
{
    currentQuantity = quantity;
}

public int getCurrentQuantity(int quantity)
{
    return currentQuantity;
}

public Item getItem(String itemName)
{
    return items.get(itemName);
}
     
public void putItem(String name, Item theItem)
{
    this.items.put(name, theItem);
    itemInfo = theItem;
}
     
public void setItem(String itemName, Item giveItem)
{
    items.put(itemName, giveItem);
}
     
public String getItemDescription()
{
    if(items.size() == 0)
    return "There are no items in this room.";
    else
    {    
    String returnString = "Items:";
        Set<String> keys = items.keySet();
        for(String item : keys) 
            returnString += " " + item;
        return returnString;
    }
}
     
public String returnItems()
{
    String returnString = "";
        Set<String> keys = items.keySet();
        for(String item : keys) 
            returnString = item;
        return returnString;
}
     
public void removeItem(String itemName)
{
    items.remove(itemName);
}
     
public String playersInventory(String item)
{
    Item getItem = items.get(item);
    if(items.size() == 0)
    return "You do not have any items!";
    else
    {    
    String returnString = "Inventory:";
        Set<String> itemList = items.keySet();
        for(String items : itemList) 
            returnString += " " + items;
        return returnString + "\nThe total items you are carrying is " ;
   }
}
}