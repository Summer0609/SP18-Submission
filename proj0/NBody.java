public class NBody{
	public static double readRadius(String fileName){
      In in=new In(fileName);
      int amount=in.readInt();
      double radius=in.readDouble();
      return radius;
	}

  public static Planet[] readPlanets(String fileName){
    In in=new In(fileName);
    int amount=in.readInt();
    double radius=in.readDouble();
    Planet[] planets=new Planet[amount];
    int index=0;
    //the purpose of using the while loop is to access memory in the body of the loop
    //Actually,we can not access memories in the body of our for loop!!!
    while(index<amount){
    	double xP=in.readDouble();
    	double yP=in.readDouble();
    	double xV=in.readDouble();
    	double yV=in.readDouble();
    	double m=in.readDouble();
    	String img=in.readString();
    	planets[index]=new Planet(xP,yP,xV,yV,m,img);
    	index++;
    }
    return planets;
  }
  public static void main(String[] args){
      double T = Double.parseDouble(args[0]);
      double dt = Double.parseDouble(args[1]);
      String filename = args[2];
      double radius = readRadius(filename);
      Planet[] planets = readPlanets(filename);
      StdDraw.enableDoubleBuffering();
      StdDraw.setScale(-radius, radius);
      StdDraw.clear();
      //StdDraw.picture(0, 0, "./images/starfield.jpg");
      //for (Planet p : planets) {
          //p.draw();
      //}
      
      double time=0;
      int amount=planets.length;
      while(time<=T){
        double[]xForces=new double[amount];
        double[]yForces=new double[amount];
        for(int i=0;i<amount;i++){
          Planet p=planets[i];
          xForces[i]=p.calcNetForceExertedByX(planets);
          yForces[i]=p.calcNetForceExertedByY(planets);
        }
        for(int i=0;i<amount;i++){
          Planet p=planets[i];
          p.update(dt,xForces[i],yForces[i]);
        }
        StdDraw.picture(0, 0, "./images/starfield.jpg");
        for (Planet planet : planets) {
          planet.draw();
        }
        StdDraw.show();
        StdDraw.pause(10);
        time += dt;
      }
      StdOut.printf("%d\n", planets.length);
      StdOut.printf("%.2e\n", radius);
      for (int i = 0; i < planets.length; i++) {
          StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
          planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
      }
  }
}