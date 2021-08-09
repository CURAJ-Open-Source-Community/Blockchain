/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tarun
 */
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
public class Createjson {
    public static void main(String args[]) {
      //Creating a JSONObject object
      JSONObject jsonObject = new JSONObject();
      //Inserting key-value pairs into the json object
      
      // read hash of the last block
      
      jsonObject.put("previous_hash", "2");
      jsonObject.put("From", "abhay");
      jsonObject.put("To", "Dhawan");
      jsonObject.put("Amount", "12222");
      try {
         FileWriter file = new FileWriter("output.json");
         file.write(jsonObject.toString());
         file.close();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      // ipfs block put output.json > abc.txt
      
      
        // read hash of the new block from abc.txt
        
        
        //ipfs pubsub pub block hash
      
      System.out.println("JSON file created: "+jsonObject);
   }
}
