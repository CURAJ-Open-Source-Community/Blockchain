/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import io.ipfs.api.*;
import io.ipfs.cid.*;
import io.ipfs.multihash.Multihash;
import io.ipfs.multiaddr.MultiAddress;


import java.lang.Thread;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
/**
 *
 * @author Tarun
 */
public class Pubsub {
    public static void main(String[] args)
    {
        //IPFS ipfs = new IPFS("192.168.1.100",5001);
        IPFS ipfs = new IPFS(new MultiAddress("/ip4/192.168.1.100/tcp/5001"));
    }
}
