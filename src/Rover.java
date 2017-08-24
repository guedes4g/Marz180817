
public class Rover {

	int x;
	int y;
	char direction;
	RoverManager manager;
	
	public void setManager(RoverManager manager) {
		this.manager = manager;
	}
	
	
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

	
	public void updatePosition(char cmd) {
		switch (this.direction) {
			case 'N':
				if (cmd == 'R')
					this.direction = 'E';
				else if (cmd == 'L')
					this.direction = 'W';
				else if (cmd == 'M')
					if (this.manager.validatePosition(this.x, this.y + 1))
						this.y += 1;
			break;
			
			case 'E':
				if (cmd=='R')
					this.direction = 'S';
				else if (cmd == 'L')
					this.direction = 'N';
				else if (cmd == 'M')
					if (this.manager.validatePosition(this.x + 1, this.y))
						this.x += 1;
			break;
			
			case 'S':
				if (cmd == 'R')
					this.direction = 'W';
				else if (cmd == 'L')
					this.direction = 'E';
				else if (cmd == 'M')
					if (this.manager.validatePosition(this.x, this.y - 1))
						this.y -= 1;
			break;
			
			case 'W':
				if (cmd == 'R')
					this.direction = 'N';
				else if(cmd == 'L')
					this.direction = 'S';
				else if(cmd == 'M')
					if (this.manager.validatePosition(this.x - 1, this.y))
						this.x -= 1;
			break;
		}
	}
	
	public void evaluateCommands(String commands) {
		for (char cmd : commands.toCharArray())
			this.updatePosition(cmd);
	}

}
