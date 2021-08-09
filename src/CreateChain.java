import java.io.*;
//import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class CreateChain {
  @SuppressWarnings("unchecked")
public static void main(String args[]) throws IOException, ParseException {
	  InputStreamReader r = new InputStreamReader(System.in);
	  BufferedReader br = new BufferedReader(r);
	  String id  = getID();
	  System.out.println("Transection From: ");
	  String from = br.readLine();
	  System.out.println("Transetion TO: ");
	  String to = br.readLine();
	  System.out.println("Amount: ");
	  String amount = br.readLine();
	  double amt = Double.parseDouble(amount);
	  
	  JSONObject jo = new JSONObject();
	  jo.put("ID",id);
	  jo.put("From",from);
	  jo.put("To",to);
	  jo.put("Amount",amt);
	  
	  long blockno = getblocknumber();
	  updateblocknumber(blockno);
	  String bno = Long.toString(blockno);
	  PrintWriter pw = new PrintWriter(bno+".json");
	  pw.write(jo.toJSONString());
	  pw.flush();
	  pw.close();
	  //exedaemon("ipfs daemon");
	  String idnext = execmd("ipfs block put "+bno+".json");
	  System.out.println(idnext);
          JSONObject jo1 = new JSONObject();
	  jo1.put("key",idnext);
	  try {
         FileWriter file = new FileWriter("key.json");
         file.write(jo1.toString());
         file.close();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
	 // update(idnext);
	  }
  
  // static method to get key
 /* public static String getID() {
	  String id=null;
	  JSONParser jparser = new JSONParser();
	  try(FileReader r = new FileReader("key.json")){
		  Object obj = jparser.parse(r);
		  JSONObject jo = (JSONObject)obj;
		   id = (String)jo.get("key");
		  
	  }catch(FileNotFoundException e) {
		  e.printStackTrace();
	  }catch(IOException e) {
		  e.printStackTrace();
	  }catch (org.json.simple.parser.ParseException e) {
		e.printStackTrace();
	}
	return id;
  }*/
  public static String getID() throws FileNotFoundException, IOException, ParseException {
	  Object obj = new JSONParser().parse(new FileReader("key.json"));
	  JSONObject jo = (JSONObject)obj;
	  
	return (String)jo.get("key"); 
  }
  
  //static method to get block number
  public static long getblocknumber() throws FileNotFoundException, IOException, ParseException {
	  Object obj = new JSONParser().parse(new FileReader("blocknumber.json"));
	  JSONObject jo = (JSONObject)obj;
	  long blocknumber = (long)jo.get("NO");
	  return blocknumber;
	  //return Long.toString(blocknumber);
  }
  
  // static method to put block on chain 
  public static String execmd(String cmd)throws IOException{
	  Process process = Runtime.getRuntime().exec(cmd);
	  InputStreamReader r = new InputStreamReader(process.getInputStream());
	  BufferedReader br = new BufferedReader(r);
	  String line = null;
	  line = br.readLine();
	  br.close();
	  return line;
  }
  //static method to run daemon
  public static void exedaemon(String cmd) throws IOException {
	  @SuppressWarnings("unused")
	Process process = Runtime.getRuntime().exec(cmd);
	  
  }
  //static method to update blocknumber
  @SuppressWarnings("unchecked")
public static void updateblocknumber(long bno) throws FileNotFoundException {
	  JSONObject jo = new JSONObject();
	  jo.put("NO",bno+1);
	  PrintWriter pw = new PrintWriter("blocknumber.json");
	  pw.write(jo.toJSONString());
	  pw.flush();
	  pw.close();
	  
  }
  
  //static method to update key
  @SuppressWarnings("unchecked")
public static void updatekey(String idnext) throws FileNotFoundException {
	  PrintWriter pw = new PrintWriter("key.json");
	  JSONObject jo = new JSONObject();
	  jo.put("key",idnext);
	  pw.write(jo.toJSONString());
	  pw.flush();
	  pw.close();
  }
}
