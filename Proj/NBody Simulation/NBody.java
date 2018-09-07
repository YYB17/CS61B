
public class NBody {
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		/*draw the background */
		String imageToDraw = "./images/starfield.jpg";
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0,0,imageToDraw);


		
		// for(Planet p: planets) {
		// 	p.draw();
		// }

		StdDraw.enableDoubleBuffering();
		double time = 0.0;


         double[] audio = StdAudio.read("audio/2001.mid");
         StdAudio.play(audio);
        //StdAudio.play("audio/2001.mid");

		while(time <= T) {
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			StdDraw.setScale(-radius, radius);
			StdDraw.clear();
			StdDraw.picture(0,0,imageToDraw);
			for(int i = 0; i< planets.length; i++){
				xForces[i]=planets[i].calcNetForceExertedByX(planets);
				yForces[i]=planets[i].calcNetForceExertedByY(planets);
				planets[i].update(dt, xForces[i], yForces[i]);


				planets[i].draw();
				
				
				
			}
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
			
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for(int i =0; i<planets.length; i++){
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
							planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
		}


	}

	public static Planet[] readPlanets(String filename) {
		In in = new In(filename);
		int N = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[N];
		for(int i = 0; i< N; i++) {
			planets[i] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());


		}
		return planets;
	}


	public static double readRadius(String filename) {
		In in = new In(filename);

		/*the number of planets*/
		int N = in.readInt();

		/*the radius of the universe*/
		double radius = in.readDouble();
		return radius;

	}
}
