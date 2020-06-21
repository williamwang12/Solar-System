import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Universe {
	int numParticles;
	Particle[] particles;
	double radius;
	double deltaT;
	String fileName;
	double currentTime = 0.0;

	public Universe(String planetsFileName, String universeFileName, double dt) {
		this.fileName = planetsFileName;
		this.deltaT = dt;
	}
	
	public void readAndLoadUniverse(String fileName) {
		File f;
		FileReader fr;
		BufferedReader br;
		int len;
		String[] s;

		try {
	        
	        f = new File(fileName);
			fr = new FileReader(f);
			br = new BufferedReader(fr);
	        
			len = Integer.parseInt(br.readLine().trim());
			this.radius = Double.parseDouble(br.readLine().trim());
			
			particles = new Particle[len];
			
			for (int i = 0; i < len; i++) {
				String currentLine = br.readLine().trim();
				// Use "\\s+" to match multiple spaces in a row
				s = currentLine.split("\\s+");
				Particle p = Particle.buildParticleFromFile(s);
				particles[i] = p;
			}
			br.close();
			fr.close();
		}
	
		catch(Exception e) {
			
			System.out.println("Uh oh.");
			e.printStackTrace();
			
		}
		
		numParticles = particles.length;
	}
	
	public void writeUniverse(String fileName) {
		File f;
		FileWriter fw;
		PrintWriter pw;
		try {
			f = new File(fileName);
			fw = new FileWriter(f);
			pw = new PrintWriter(fw);
			
			pw.printf("%d\n", this.numParticles);
			pw.printf("%.2e\n", this.radius);
			for (int i = 0; i < this.particles.length; i++) {
				Particle p = particles[i];
				pw.printf(
						"%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
						p.getX(),
						p.getY(),
						p.getVx(),
						p.getVy(),
						p.getMass(),
						p.getFilename());

			}
			
			pw.close();
			fw.close();
		}
		catch (Exception e) {
			System.out.println("Uh oh.");
			e.printStackTrace();
		}
	}
	
	Particle current;
	double totalForce;
	double G = 6.67 * Math.pow(10, -11);
	double xChange;
	double yChange;
	double fxChange;
	double fyChange;
	double r;
	double netForce;
	
	public void calculateForces(){

		for (int i = 0; i < particles.length; i++) {
			particles[i].clearForces();
		}

		for(int i = 0; i < particles.length; i++) {
			
			current = particles[i]; 
			
			for(int a = 0; a < particles.length; a++) {

				if (i == a) continue;
				
				r = current.getDistance(particles[a]);
				
				totalForce = (current.getMass() * particles[a].getMass() * G) / Math.pow(r, 2);
				
				xChange = particles[a].getX() - current.getX();
				yChange = particles[a].getY() - current.getY();

				fxChange = totalForce * (xChange/r);
				fyChange = totalForce * (yChange/r);
				
				current.updateFx(current.getFx() + fxChange);
				current.updateFy(current.getFy() + fyChange);
			}
		}
	}
	
	double aX;
	double aY;

	public void updateVelocityAndPos() {
		for(int i = 0; i < particles.length; i++) {
			
			aX = particles[i].getFx()/particles[i].getMass();
			aY = particles[i].getFy()/particles[i].getMass();

			double newVx = particles[i].getVx() + (deltaT * aX);
			double newVy = particles[i].getVy() + (deltaT * aY);
			particles[i].updateVx(newVx);
			particles[i].updateVy(newVy);
			
			particles[i].updateX(particles[i].getX() + (deltaT * newVx));
			particles[i].updateY(particles[i].getY() + (deltaT * newVy));
		}		
	}
	
	public Particle[] getParticles() {
		return particles;
	}
	public double getRadius() {
		return radius;
	}
	public void timeStepUniverse() {
		currentTime += deltaT;
	}
	public String toString() {
		return "Sss";
	}
}