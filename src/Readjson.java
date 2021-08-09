/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tarun
 */
import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
public class Readjson {
     public static void main(String[] args) {
      JSONParser parser = new JSONParser();
      try {
         Object obj = parser.parse(new FileReader("C:\\Users\\Tarun\\Documents\\NetBeansProjects\\Blockchain\\output.json"));
         JSONObject jsonObject = (JSONObject)obj;
         String phash = (String)jsonObject.get("previous_hash");
         String from = (String)jsonObject.get("From");
         String amt = (String)jsonObject.get("Amount");
         System.out.println("previous_hash: " + phash);
         System.out.println("From: " + from);
         System.out.println("Amount:"+amt );
         } catch(Exception e) {
         e.printStackTrace();
      }
   }
}
