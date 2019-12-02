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
 * @version 31/01/2019
 */


public class Item
{
    private String itemName;
    private String description;
    private int quantity;
    
   /**
     * Constructor for objects of class Item
     */
   public Item(String d)
   {
      description = d;
      quantity = 0;
   }
   
   public Item(String d, int q)
   {
       description = d;
       quantity = q;
    }
    
      
   /**
     * This returns the description of an item.
     * @return description
     */
    public String getDesc()
    {
        return description;
    }
    
   /**
     * This returns the quantity of an item.
     * @return quantity
     */
    public int getQuantity()
    {
        return quantity;
    }
    
   public static int totalQuantity(ArrayList<Item>List)
    {
        int n = 0;
        int sum = 0;
        while (n<List.size())
        {
            Item i = List.get(n);
            sum += i.getQuantity();
            n++;
        }
        return sum; 
    }
    
   /**
     * findByName: given a string and an ArrayList of Items, 
     * find the Item with the matching name, or else return null
     */
     public static Item findByName(String s, ArrayList<Item> List) {
        int n=0;
        while (n < List.size()) {
            Item i = List.get(n);
            if (s.equals(i.getDesc())) 
                return i;
            n++;
        }
        return null;   
    }
   
}



    
  