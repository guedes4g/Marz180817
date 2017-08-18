
public class Rover {

	int x;
	int y;
	char direction;
	
	
	public Rover() {
		
	}
	public Rover(int x, int y, char direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public char getDirection() {
		return direction;
	}
	public void setDirection(char direction) {
		this.direction = direction;
	}
	
	public boolean move(char cmd){
		this.updateDirection(cmd);
		
		
		return false;
	}
	
	public void updateDirection(char cmd){
		switch(this.direction){
			case'N':
				if(cmd=='R'){
					
				} else if(cmd=='L'){
					
				} else if(cmd=='M'){
					
				} else {}
			break;
			
			case'L':
				if(cmd=='R'){
					
				} else if(cmd=='L'){
					
				} else if(cmd=='M'){
					
				} else {}
			break;
			case'S':
				if(cmd=='R'){
					
				} else if(cmd=='L'){
					
				} else if(cmd=='M'){
					
				} else {}
			break;
			case'O':
				if(cmd=='R'){
					
				} else if(cmd=='L'){
					
				} else if(cmd=='M'){
					
				} else {}
			break;
			
			
		
		}
	}

}
