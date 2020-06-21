public class Simulation {

    public static void main(String[] args) {
    	System.out.println("hello");
        // Write the code to run the simulation here. 
		// t = args[0]

		// dt = args[1]

		double T = Double.parseDouble(args[0]);
		double dT = Double.parseDouble(args[1]);
		double radius;


		String starField = "input/starfield.jpg";
		String planets = "input/planets.txt";
		String planetsOutput = "input/planetOutput.txt";
		String musicFile = "input/2001.wav";
        Universe u = new Universe(planets,planetsOutput,dT);
        
        u.readAndLoadUniverse(planets);
        radius = u.getRadius();
        
		StdDraw.setCanvasSize(500,500);
		StdDraw.setXscale(-radius,radius);
		StdDraw.setYscale(-radius,radius);
		
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenRadius(1.0);
        //StdAudio.play(StdAudio.read(musicFile));
		//StdAudio.play(musicFile);
        Particle current;
        int count = 0;
        for (int i = 0; i <= T / dT; i++) {
        	count++;
        	u.calculateForces();
        	u.updateVelocityAndPos();
        	u.timeStepUniverse();

			StdDraw.clear(StdDraw.WHITE);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.picture(0,0,starField);

        	for(int a = 0; a < u.getParticles().length; a++) {
        		//
        		current = u.getParticles()[a];
        		double currentX = (current.getX());
        		double currentY = (current.getY());
        		String path = "input/" + u.getParticles()[a].getFilename();
        		StdDraw.picture(currentX, currentY, path);
        	}

			StdDraw.show();
			StdDraw.pause(20);
        }
        
        u.writeUniverse(planetsOutput);
        StdAudio.close();
    }
}
