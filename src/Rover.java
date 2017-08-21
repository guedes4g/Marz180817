
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

	
	public void updatePosition(char cmd){
		switch(this.direction){
			case'N':
				if(cmd=='R'){
					this.direction = 'O';
				} else if(cmd=='L'){
					this.direction = 'L';
				} else if(cmd=='M'){
					this.x +=1;
				} else {}
			break;
			
			case'L':
				if(cmd=='R'){
					this.direction = 'N';
				} else if(cmd=='L'){
					this.direction = 'S';
				} else if(cmd=='M'){
					this.y +=1;
				} else {}
			break;
			case'S':
				if(cmd=='R'){
					this.direction = 'L';
				} else if(cmd=='L'){
					this.direction = 'O';
				} else if(cmd=='M'){
					this.x -=1;
				} else {}
			break;
			case'O':
				if(cmd=='R'){
					this.direction = 'S';
				} else if(cmd=='L'){
					this.direction = 'N';
				} else if(cmd=='M'){
					this.y +=1;
				} else {}
			break;
		}
	}

}
