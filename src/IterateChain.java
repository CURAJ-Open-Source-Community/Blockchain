import java.io.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class IterateChain {
public static void main(String args[]) throws IOException, ParseException {
	Object obj = new JSONParser().parse(new FileReader("key.json"));
	JSONObject jo = (JSONObject)obj;
    String id = (String) jo.get("key");
    String str = "gensis_block";
   System.out.println(id);
   String pcmd = "ipfs block get "+id+" > data.json";
   System.out.println(pcmd);
	while(!(str.equals(id))) {
		id = execmd(pcmd);
                pcmd = "ipfs block get "+id+" > C:\\Users\\Tarun\\Documents\\NetBeansProjects\\Blockchain\\data.json";
		 }
	
}
//static class to excute get command
public static String execmd(String cmd) throws IOException, ParseException {
	@SuppressWarnings("unused")
        String cmdprompt = "C:\\Windows\\System32\\cmd.exe"  ;      
	Process process = Runtime.getRuntime().exec(new String[]{cmdprompt, cmd});
        //Process process = Runtime.getRuntime().exec(cmd);
	//Object obj = new JSONParser().parse(new FileReader("data.json"));
         InputStreamReader r = new InputStreamReader(process.getInputStream());
	  BufferedReader br = new BufferedReader(r);
	  String line = "{\"Amount\":2000.0,\"ID\":\"QmVFoMhizqUWLckjabd5iAQjodypje1UZqWqt1PVs2ZmRp\",\"From\":\"ajay\",\"To\":\"vijay\"}";
	  //line = br.readLine();
          //System.out.println(line);
	  br.close();
          JSONParser parser = new JSONParser();
          JSONObject jo = (JSONObject) parser.parse(line);
        
        
	  //JSONObject jo = (JSONObject)obj;
	 System.out.println(jo);
	  String str = (String)jo.get("ID");
	  //System.out.println(str);
	  return str;
}

//static class to print data
public static String getnextid() throws FileNotFoundException, IOException, ParseException {
	Object obj = new JSONParser().parse(new FileReader("data.json"));
	  JSONObject jo = (JSONObject)obj;
	System.out.println(jo);
	
	return (String)jo.get("ID");
}
}
