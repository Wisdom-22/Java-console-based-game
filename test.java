import java.util.*;
import java.io.*;

public class test{
	public static void main(String [] args){
	
						PrintWriter write = null;
						PrintWriter writeBoard = null;
						Scanner read = null;
						String line = "";
						int b =0;
						int index = 0;
						int [] leaderBoard = new int [5];
	try{
	
	read = new Scanner(new File("LeaderBoard.txt"));
						while(read.hasNextLine()){
										line = read.nextLine();
										//System.out.println(line);
										index = line.indexOf(':');
										
										int num = Integer.parseInt(line.substring(index+1));
										leaderBoard[b] = num;
										System.out.println(num + " 1111111111111111111111111kkfkfkfk ");
										b++;
									}
						read.close();
						
						
	
	}catch(Exception e){
	System.out.println(e.getMessage());
	
	}
	
	
	
	}
	
	
	}