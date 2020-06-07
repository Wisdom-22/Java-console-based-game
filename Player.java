public class Player{

		private String name = "";
		private int health = 0;
		private int attack = 0;
		private int defence = 0;
		private int [] items = new int [20];
		
			public Player(String n, int h, int a, int d, int [] i){
			
				name = n;
				health = h;
				attack = a;
				defence = d;
				
			}

				
			public void setName(String n){
				name = n;
			}
			
			public String getName(){
				return name;
			}

			public void setHealth(int h){
				health = h;
			}
			
			public int getHealth(){
				return health;
			}
			
			public void setAttack(int a){
				attack = a;
			}

			public int getAttack(){
				return attack;
			}
			
			public void setDefence(int d){
				defence = d;
			}
			
			public int getDefence(){
				return defence;
			}
			
			
			
}