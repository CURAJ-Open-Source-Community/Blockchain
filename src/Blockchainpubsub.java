import java.io.*;
import io.ipfs.api.*;
import java.lang.Thread;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

//1st thread to implement subscribe block
class SubscribeBlock extends Thread{
	public void run() {
		try {
			System.out.println("In subscride");
			//String cmd = "sudo ipfs pubsub sub block > block.txt";
                        
                        //for windows 
                        String cmd = "cmd.exe ," + "/c,"+ "Start,"+ "sub.bat";
                        
                        //for linux
                        //String[] cmd = { "sh", "sub.sh"};
                         //p = Runtime.getRuntime().exec(cmd); 
                        Runtime.getRuntime().exec(cmd);
			//execmd objcmd = new execmd();
			//objcmd.exesubcmd(cmd);
			
		}catch(Exception e) {
			System.out.println("Exception cought in Subscribeblock");
		}
	}
}
//2nd thread to implement create block
class PublishBlock extends Thread{
	@SuppressWarnings("unchecked")
	public void run() {
		while(true) {
		try {
			Blockinfo objinfo = new Blockinfo();
			String id = objinfo.getId();
			InputStreamReader r = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(r);
			System.out.println("Transection From: ");
			String from = br.readLine();
			System.out.println("Transection To: ");
			String to = br.readLine();
			System.out.println("Amount: ");
			String amt = br.readLine();
			double amount = Double.parseDouble(amt);
			
			//Create json object to transection
			JSONObject jo = new JSONObject();
			jo.put("ID",id);
			jo.put("From",from);
			jo.put("To",to);
			jo.put("Amount",amount);
			
			//put JSONObject to file block.json
			PrintWriter pw = new PrintWriter("block.json");
			pw.write(jo.toJSONString());
			pw.flush();
			pw.close();
			
			//method call to genrate hash of newly created block
			execmd objcmd = new execmd();
			String hash = objcmd.exehashcmd("ipfs block put block.json");
			//method call to publish genrated hash of the block
			String command = "ipfs pubsub pub block"+" "+hash;
			//System.out.println(command);
			StringBuilder newline=new StringBuilder();
                        newline.append(command);
                        newline.append(System.getProperty("line.separator"));
                        //newline.append("def");
                        objcmd.exepubcmd(command);
                        //objcmd.exepubcmd(newline.toString());
                        command = "ipfs pubsub pub block "+"\r\n";
			//System.out.println(command);
			objcmd.exepubcmd(command);
			objinfo.updatekey(hash);
		}catch(Exception e) {
			//System.out.println("Exception occured while Creating and publishing block");
			e.printStackTrace();
		}
		}
	}
}

// seprate class to excute commands on shell
class execmd{
	
	public String exehashcmd(String cmd) throws IOException {
		Process process = Runtime.getRuntime().exec(cmd);
		InputStreamReader r = new InputStreamReader(process.getInputStream());
		BufferedReader br = new BufferedReader(r);
		String line = null;
		line = br.readLine();
		br.close();
		return line;
		}
	
	public void exepubcmd(String cmd) throws IOException {
		@SuppressWarnings("unused")
		Process process = Runtime.getRuntime().exec(cmd);
	}
	
	public void exesubcmd(String cmd) {
		try {
			@SuppressWarnings("unused")
			Process process = Runtime.getRuntime().exec(cmd);
			InputStreamReader r = new InputStreamReader(process.getInputStream());
			BufferedReader br = new BufferedReader(r);
			String line = null;
			line = br.readLine();
			br.close();
			System.out.println(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

//seprate class to block related info 
class Blockinfo{
	
	public String getId() throws FileNotFoundException, IOException, ParseException {
		Object obj = new JSONParser().parse(new FileReader("key.json"));
		JSONObject jo = (JSONObject)obj;
		return (String)jo.get("ID");
	}
	
	@SuppressWarnings("unchecked")
	public void updatekey(String hash) {
		PrintWriter pw;
		try {
			JSONObject jo = new JSONObject();
			jo.put("ID",hash);
			pw = new PrintWriter("key.json");
			pw.write(jo.toJSONString());
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("Exception occured in updating key");
		}
	
		
	}
}

// Main class 
public class Blockchainpubsub {
public static void main(String args[]) throws InterruptedException {
	 PublishBlock objpub = new PublishBlock();
	 objpub.start();
	 SubscribeBlock objsub = new SubscribeBlock();
	// objsub.sleep(50000000);
	 objsub.start();
         //IPFS ipfs=
 }
}
