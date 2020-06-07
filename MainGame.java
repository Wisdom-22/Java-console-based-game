import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;      //helps to format dates

public class MainGame{
	public static void main(String [] args){
		
			Scanner scan = new Scanner(System.in);
			Random rand = new Random();
			Date date = new Date();
			
			LocalDateTime myDate = LocalDateTime.now();//this will help us create new dates for the log file. new Dates = unique files 
			DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-mm-yyyy HH-MM-SS");//Pattern i want the data to apppear as 
			
			int [] weapons = new int [3];  //index[0] = spear,   index[1] = shield    
			
			System.out.println("#-#-#-#-#-#-#-#-#-# Game Over-view #-#-#-#-#-#-#-#-#-# ");
			System.out.println("The has a 3 difficulty/mode, choose the one you are comfortable with. The rooms and enemies are randomly generated. ");
			System.out.println("After each battle there are health pickups, also weapons are available if you are lucky enough to get them");
			System.out.println("There is a mode called Tiger Fusion, this mode allows you to blow through all enemies in that particular room, handy if you choose a hard difficulty but as always, not going to be easy to get");
			System.out.println();
			
			
			System.out.println("Enter your name");
			String name = scan.nextLine();
			
			System.out.println("Enter the name you want to call your enemies. Names like Goblin, Troll, Gnomes, Gargoyle etc");
			String enemies = scan.nextLine();
			
			System.out.println("");
												//name,health,attack,defence
			Player gamer = new Player(name,100,40,20,weapons);//creating the player
			
			Player goblin = new Player(enemies,20,10,15,weapons);//hench men
			
			//Difficuty options
			System.out.println("Easy = 3 - 6");
			System.out.println("Medium = 7 - 11");
			System.out.println("Hard = 12 - 20");
			
			System.out.println("");
			
			//difficulty choice
			System.out.println("Press 1 for Easy");
			System.out.println("Press 2 for Medium ");
			System.out.println("Press 3 for Hard");
			
			System.out.println("");
			
			System.out.println("Enter your difficulty");
			int difficulty = scan.nextInt();
			String mode = "";
			System.out.println("");
			
			int numberOfRooms = 0;
			int [] rooms = null;
			//depending on the choice of the player it will select one section
			if(difficulty == 1){
				numberOfRooms = rand.nextInt(6) +3;
				rooms = new int[numberOfRooms];  //array for the rooms	
				mode = "Easy";
			}
			else if(difficulty == 2){
				numberOfRooms = rand.nextInt(11) + 7;
				rooms = new int[numberOfRooms];  //array for the rooms
				mode = "Medium";
			}
			else if(difficulty == 3){
				numberOfRooms = rand.nextInt(20) + 12;
				rooms = new int[numberOfRooms];  //array for the rooms
				mode = "Hard";
			}
				
						int gamerAttack = 0;
						int gamerDefence = 0;
						int goblinAttack = 0;
						int goblinDefence = 0;
						int gamerDamage = 0;
						int damage = 0;
						int spear = 0;
						int shield = 0;
						int potion = 0;
						int spearCheck = 0;
						int shieldCheck = 0;
						int potionCheck = 0;
						int initialHealth = 0;
						int initialHealthCheck = 0;
						int tiger = 0;
						
						int [] arrayOfEnemies = new int[numberOfRooms];
						// for the rooms
						int counter = 0;
						int x =0;
			
						PrintWriter write = null;
						PrintWriter writeBoard = null;
						Scanner read = null;
						
						int [] leaderBoard = new int [5];
						String [] leaderBoard2 ={"0","0","0","0","0"}; //new String [5];
						
						String dateAsFileName = myDate.format(formatDate); //turning the data into a formatted form that will appear as the file name for the logs
				
			//writing to the console and writing to the text file
			try{
					write = new PrintWriter(new FileWriter("Log " + dateAsFileName + ".txt"));//date as a file name is here creating files with different time and date
					read = new Scanner(new File("LeaderBoard.txt"));
					
					//Reading the data from the LeaderBoard so it will compare scores 
									int b = 0;
									int index = 0;
									String line = "";
									
									 while(read.hasNextLine()){
										line = read.nextLine();
										//System.out.println(line);
										index = line.indexOf(':');
										
										int num = Integer.parseInt(line.substring(index+1));
										leaderBoard[b] = num;
										leaderBoard2[b] =line.substring(0,index) + ":" + Integer.toString(num); //turning the value in num to String 
										//System.out.println(num + " 1111111111111111111111111kkfkfkfk ");
										b++;
									}
						read.close();
					
					writeBoard = new PrintWriter("LeaderBoard.txt");
				
					
					write.println(gamer.getName() + " played at " +  date + " with difficulty " + mode + ". There were " + numberOfRooms + " rooms in the dungeon.");
					
					write.println();
					
					System.out.println();
					while(counter < rooms.length){       //for number of rooms		
						System.out.println("******************** Room " + (counter + 1) + " ********************");
						write.println("******************** Room " + (counter + 1) + " ********************");						
							int a = 0;
							int numberOfEnemies = rand.nextInt(numberOfRooms) + 1;//number of enemies in each room
							
							while(a < numberOfEnemies){ // for the number of enemies in the room
								x = (a +1);
								System.out.println(" ----------------" + goblin.getName() + " " + x + " -------------");//getting the name of the goblin.So i can know the number of enemy in the room
								write.println(" ---------------- " + goblin.getName() + " " + x + " ------------- ");
								
								/***********************************BATTLE GROUND***************************************************/
									while(gamer.getHealth() > 0 && goblin.getHealth() > 0){ //while gamer and enemy still have life, do battle
												
												gamerAttack = rand.nextInt(gamer.getAttack()) + 1 + weapons[0];//getting player attack and using his weapons
												gamerDefence = rand.nextInt(gamer.getDefence()) + 1 + weapons[1]; //getting player defence
												goblinAttack = rand.nextInt(goblin.getAttack()) + 1; //getting the goblin attack
												goblinDefence = rand.nextInt(goblin.getDefence()) +1;     //getting the goblin health
												
													if(gamerAttack > goblinDefence){
														gamerDamage = gamerAttack - goblinDefence;
														goblin.setHealth(goblin.getHealth() - gamerDamage);
														gamerDamage = gamerDamage + gamerDamage;  //points for the gamer
														
														System.out.println(gamer.getName() + " attacked and did some damage");
														write.println(gamer.getName() + " attacked and did some damage");
													}
													else{
														System.out.println(goblin.getName() + " blocked your attack");
														write.println(goblin.getName() + " blocked your attack");
													}
													
													if(goblinAttack > gamerDefence){
														damage = goblinAttack - gamerDefence;
														gamer.setHealth(gamer.getHealth() - damage);
														System.out.println(goblin.getName() + " attacked and did some damage ");
														write.println(goblin.getName() + " attacked and did some damage ");
													}
													else
													{
														System.out.println(gamer.getName() + " blocked the attack");
														write.println(gamer.getName() + " blocked the attack");
													}
													System.out.println(gamer.getName() + " Attack:" + gamerAttack);
													System.out.println(goblin.getName() + " Defence:" + goblinDefence);
													System.out.println();
													
													System.out.println(goblin.getName() + " Attack:" + goblinAttack);
													System.out.println(gamer.getName() + " Defence:" + gamerDefence);
													System.out.println();
													
													System.out.println(gamer.getName() + " Health:" + gamer.getHealth());
													System.out.println(goblin.getName() + " Health:" + goblin.getHealth());
													System.out.println();
													
													write.println(gamer.getName() + " Attack: " + gamerAttack);
													write.println(goblin.getName() + " Defence: " + goblinDefence);
													write.println();
													
													write.println(goblin.getName() + " Attack: " + goblinAttack);
													write.println(gamer.getName() + " Defence: " + gamerDefence);
													write.println();
													
													write.println(gamer.getName() + " Health: " + gamer.getHealth());
													write.println(goblin.getName() + " Health: " + goblin.getHealth());
													
													int endOfArray = numberOfRooms;
													write.println();
													System.out.println();
													
									}
									
												//depends on your certain conditions i have laid out
												int battlePickups = rand.nextInt(30);
													
													if(battlePickups > 0 && battlePickups <= 3){
														gamer.setHealth(gamer.getHealth() + 5);
														System.out.println("You had an increase in health of 5 after the battle with " + goblin.getName() + " " + x);
														System.out.println();
														
														write.println("You had an increase in health of 5 after the battle with " + goblin.getName() + " " + x);
														write.println();
													}
													else if(battlePickups > 10 && battlePickups <=12 ){
														gamer.setHealth(gamer.getHealth() + 8);
														System.out.println("You had an increase in health of 8 after the battle with " + goblin.getName() + " " + x);
														System.out.println();
														
														write.println("You had an increase in health of 8 after the battle with " + goblin.getName() + " " + x);
														write.println();
														
													}else if(battlePickups == 15){
														initialHealth = gamer.getHealth();
														initialHealthCheck = 1;
														
														System.out.println("You have unlocked The Spirit of the TIGER FUSION!!!!!");
														System.out.println("You have the power to Destroy all " + goblin.getName() + " in the room");
														
														write.println("You have unlocked The Spirit of the TIGER!!!!!");
														write.println("You have the power to Destroy all " + goblin.getName() + " in the room");
														
														gamer.setAttack(100);
														
														System.out.println();
														write.println();
													}
													
									
											//if his health goes below zero in battle leave the battle cause you are dead
											if(gamer.getHealth() < 0){
												break;
											}
											
								a++;
								goblin = new Player(enemies,20,10,15,weapons);//creating a new goblin for each room.	
							}
							
								//Giving you back your health after the tiger mode is done 
								if(initialHealthCheck == 1){
									System.out.println("Your Tiger Mode has ended back to your original health " ); 
									gamer.setHealth(initialHealth);
															//since i am not writing this part to note pad no need to set health twice
									write.println("Your Tiger Mode has ended back to your original health" );
									
									
									initialHealthCheck = 0;
									tiger = tiger + 1;
								}
								
									arrayOfEnemies[counter] = x;  //storing the amount of enemies in a room
									x = 0;
									
											//for weapons or Weapons pickup
										if(numberOfRooms == 5 && gamer.getHealth() > 0 && spearCheck == 0){
											System.out.println("Congratulations you have gotten a PHEONIX SPEAR");
											write.println("Congratulations you have gotten a PHEONIX SPEAR");
											spear = 8;//spear power
											weapons[0] = spear;          //putting the spear in the weapons array
											spearCheck = 1;
										}
										if(numberOfRooms == 8 && gamer.getHealth() > 0 && shieldCheck ==0){
												System.out.println("Congratulations you have acquired the SHIELD OF ARCHILES");
												write.println("Congratulations you have acquired the shield of Archiles");
												shield = 10; //shield power
												weapons[1] = shield;           //putting the shield in the weapons array	
												shieldCheck = 1;
										}
										
										if(numberOfRooms == 12 && gamer.getHealth() > 0 && potionCheck == 0){
											System.out.println("Your health has been increased");
											write.println("Your health has been increased");
											potion =15;
											
											gamer.setHealth(gamer.getHealth() + potion);//health increased
											weapons[2] = potion;          //putting the portion in the weapons array
											 
											write.println("New Health" + ":" + gamer.getHealth());
											potionCheck = 1;
										}
										
										//POWER UP your weapons when you get it
										if(spearCheck == 1 && gamerDamage > 30){
											System.out.println("Congratulations you have powered up your PHEONIX SPEAR");
											System.out.println();
											write.println("Congratulations you hava powered up your PHEONIX SPEAR");
											write.println();
											weapons[0] = weapons[0] + 5;
											
										}else if(shieldCheck == 1 && gamerDamage > 40){
											System.out.println("Congratulations you have powered up your SHIELD OF ARCHILES");
											System.out.println();
											write.println("Congratulations you hava powered up your SHIELD OF ARCHILES");
											write.println();
											weapons[1] = weapons[1] + 8;
										}
										else if(potionCheck == 1 && gamerDamage > 50){
											System.out.println("Congratulations you have acquired more HEALTH POTION ");
											System.out.println();
											write.println("Congratulations you have acquired more HEALTH POTION");
											write.println();
											weapons[2] = weapons[2] + 10;
										}
											
										
											System.out.println();
											write.println();	
						counter++;
					}
					
																	
									if(gamer.getHealth() < 0){
										System.out.println(goblin.getName() + " attacked you and killed you");
										System.out.println("GAME OVER!!!!!!!!!!!");
										System.out.println();
										
										write.println(goblin.getName() + " attacked you and killed you");
										write.println("GAME OVER!!!!!!!!!!!!!!!");
										write.println();
									}
									else{
										System.out.println(gamer.getName() + " won.");
										System.out.println("ENJOY YOUR SPOILS YOU MIGHTY WORRIOR");	
										System.out.println();
										
										
										write.println(gamer.getName() + " won.");
										write.println("ENJOY YOUR SPOILS YOU MIGHTY WORRIOR");
										write.println();
									}
						/*LeaderBoard is an int array is used so we can compare scores
							LeaderBoard2 is a string so you can display the score on the LeaderBoard.txt
									
							*/							//LOG
						
						write.println("The Dungeon details is as follows " + gamer.getName() + " got ");
						
							for(int a = 0; a<arrayOfEnemies.length; a++){
								System.out.println("Room " +(a+1) +" had : " + arrayOfEnemies[a] + " " + goblin.getName() + "s in the room");
								write.println("Room " +(a+1) +" had : " + arrayOfEnemies[a] + " " + goblin.getName() + "s in the room");
							}
								System.out.println();
								write.println(""); 
								
								write.println(gamer.getName() + " score is " + gamerDamage);
								System.out.println(gamer.getName() + " score is " + gamerDamage);
								
								System.out.println();
								write.println();
							
								write.println("Weapons");
								System.out.println("Weapons");
								
								System.out.println(gamer.getName() + " got " + spearCheck + " spear during the battle");
								System.out.println(gamer.getName() + " got " + shieldCheck + " shield during the battle");
								System.out.println(gamer.getName() + " got " + potionCheck + " potion during the battle");
								System.out.println(gamer.getName() + " got " + tiger + " TIGER FUSION during the battle");
								
								write.println(gamer.getName() + " got " + spearCheck + " spear during the battle");
								write.println(gamer.getName() + " got " + shieldCheck + " shield during the battle");
								write.println(gamer.getName() + " got " + potionCheck + " potion during the battle");
								write.println(gamer.getName() + " got " + tiger + " TIGER FUSION during the battle");
								
								System.out.println();
								
									  int indexes = 0;
									  int count = 0;
									for(count = 0; count< leaderBoard.length; count++){	
									
											if(leaderBoard[count] < gamerDamage){
												int temp = leaderBoard[count]; 
												leaderBoard[count] = gamerDamage;
												indexes = count;
												//gamerDamage = temp;
												break;
											}
											
													//System.out.println(gamer.getName() + " Score :" + leaderBoard[count]); //remove
													//writeBoard.println(" Score :" + leaderBoard[count]);
									}
									
									for(count = 0; count< leaderBoard2.length; count++){	
											if(count == indexes){
												leaderBoard2[count] = gamer.getName() + " Score :" + leaderBoard[count];
												//writeBoard.println(nameOfPlayers[0] + " Score :" + leaderBoard2[indexes]);
											}
											
											writeBoard.println(leaderBoard2[count]);
											System.out.println(leaderBoard2[count]);
									}
									
				
			}catch(Exception exception){
				System.out.println(exception.getMessage());
				System.exit(1);
				
			}
			
			write.close();
			writeBoard.close();
			//read.close();
	}
	
}