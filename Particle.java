public class Particle {
	private double x;
	private double y;
	private double vx;
	private double vy;
	private double fx;
	private double fy;
	private double mass;
	private String fileName;
	
    public Particle(double x,double y,double vx,double vy, double mass, String fileName ) {
    		this.x = x;
    		this.y = y;
    		this.vx = vx;
    		this.vy = vy;
    		this.mass = mass;
    		this.fileName = fileName;
    		this.fx = 0;
    		this.fy = 0;
    }
    
    public static Particle buildParticleFromFile (String[] line) {
    		return new Particle(Double.parseDouble(line[0]),Double.parseDouble(line[1]),Double.parseDouble(line[2]),Double.parseDouble(line[3]),Double.parseDouble(line[4]),line[5]);
    }
    public double getMass() {
		return mass;
	}
    public double getX() {
		return x;
	}
	public double getY() {
		 return y;
	}
	public void updateX(double change) {
		this.x = change;
	}
	public void updateY(double change) {
		this.y = change;
	}
	public double getFx() {
		 return fx;
	} 
	public double getFy() {
		 return fy;
	} 
	public void updateFx(double change) {
		this.fx = change;
	}
	public void updateFy(double change) {
		this.fy = change;
	}
    public double getVx() {
		return vx;
	}
    public double getVy() {
		return vy;
	}
    public String getFilename() {
    		return fileName;
    }
	public void updateVx(double change) {
		this.vx = change;
	}
	public void updateVy(double change) {
		this.vy = change;
	}
    public double getDistance(Particle other) {
    		return Math.sqrt(
    			Math.pow(this.x - other.getX(), 2) +
				Math.pow(this.y - other.getY(), 2));
    }

    public void clearForces() {
    	this.fx = 0;
    	this.fy = 0;
	}

    public String toString() {
		String s = "this particle is " + fileName;
		return s;
    }
    

    
    
}
