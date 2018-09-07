public class Planet {

	/**  The reason we call them by double letters,
	 *  e.g. xxPos rather than xPos is to reduce the chance of typos. */
   
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel =xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public  double calcDistance(Planet p) {
		return Math.hypot(this.xxPos - p.xxPos, this.yyPos - p.yyPos);
	}

	public  double calcForceExertedBy(Planet p) {
		return G * this.mass * p.mass /Math.pow(this.calcDistance(p), 2);

	}

	public  double calcForceExertedByX(Planet p) {
		return this.calcForceExertedBy(p) * (p.xxPos - this.xxPos)/this.calcDistance(p);
	}

	public  double calcForceExertedByY(Planet p) {
		return this.calcForceExertedBy(p) * (p.yyPos - this.yyPos)/this.calcDistance(p);
	}
		
	public  Boolean euqals(Planet p) {
		return this == p;
	}

	public  double calcNetForceExertedByX(Planet[] allPlanets) {
		double Fx = 0.0;
		int i;
		for(i = 0; i< allPlanets.length; i++) {
			if(this.equals(allPlanets[i])) 
				continue;
			 Fx += this.calcForceExertedBy(allPlanets[i]) * (allPlanets[i].xxPos - this.xxPos)/this.calcDistance(allPlanets[i]);
		}
		return Fx;
		

	}

	public  double calcNetForceExertedByY(Planet[] allPlanets) {
		double Fy = 0.0;
		int i;
		for(i = 0; i< allPlanets.length; i++) {
			 if(this.equals(allPlanets[i])) 
			 	continue;
			 Fy += this.calcForceExertedBy(allPlanets[i]) * (allPlanets[i].yyPos - this.yyPos)/this.calcDistance(allPlanets[i]);
		}
		return Fy;
		

	}
  
	/** update the planet’s position and velocity instance variables */
  
	public void update(double dt, double Fx, double Fy) {
		double xxVel_new, yyVel_new, xxA, yyA, xxPos_new,  yyPos_new;
		xxA = Fx/this.mass;
		yyA = Fy/this.mass;

		xxVel_new = xxVel + dt * xxA;
		yyVel_new = yyVel + dt * yyA;
		xxPos_new = xxPos + dt * xxVel_new;
		yyPos_new = yyPos + dt * yyVel_new;

		this.xxVel = xxVel_new;
		this.yyVel = yyVel_new;
		this.xxPos = xxPos_new;
		this.yyPos = yyPos_new;



	}

	public void draw() {
		String imageToDraw = "./images/"+this.imgFileName;
		StdDraw.picture(this.xxPos,this.yyPos,imageToDraw);
	}


}
