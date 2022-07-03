
public class Planet{
	private static final double G=6.67e-11;
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public Planet(double xP,double yP,double xV,double yV,double m,String img){
		this.xxPos=xP;
		this.yyPos=yP;
		this.xxVel=xV;
		this.yyVel=yV;
        this.mass=m;
        this.imgFileName=img;
	}
	public Planet(Planet p){
        this.xxPos=p.xxPos;
        this.yyPos=p.yyPos;
        this.xxVel=p.xxVel;
        this.yyVel=p.yyVel;
        this.mass=p.mass;
        this.imgFileName=p.imgFileName;
	}
	public double calcDistance(Planet p){
		double dx=this.xxPos-p.xxPos;
		double dy=this.yyPos-p.yyPos;
		double dr=Math.sqrt(dx*dx+dy*dy);
		return dr;
	}
	public double calcForceExertedBy(Planet p){
        double dr=calcDistance(p);
        return (G*this.mass*p.mass/dr/dr);
	} 
	public double calcForceExertedByX(Planet p){
		double dx=p.xxPos-this.xxPos;
		return calcForceExertedBy(p)*dx/calcDistance(p);
	}
	public double calcForceExertedByY(Planet p){
		double dy=p.yyPos-this.yyPos;
		return calcForceExertedBy(p)*dy/calcDistance(p);
	}
	public double calcNetForceExertedByX(Planet[] planets){
		double netForceX=0;
		for(Planet p:planets){
			if(!this.equals(p)){
				netForceX+=this.calcForceExertedByX(p);
			}
		}
		return netForceX;
	}
	public double calcNetForceExertedByY(Planet[] planets){
		double netForceY=0;
		for(Planet p:planets){
			if(!this.equals(p)){
				netForceY+=this.calcForceExertedByY(p);
			}
		}
		return netForceY;
	}
	public void update(double time,double fx,double fy){
		double ax=fx/this.mass;
		double ay=fy/this.mass;
        this.xxVel+=ax*time;
        this.yyVel+=ay*time;
        this.xxPos+=this.xxVel*time;
        this.yyPos+=this.yyVel*time;
	}
	public void draw(){
		double xP=this.xxPos;
		double yP=this.yyPos;
		String img="images/"+this.imgFileName;
		StdDraw.picture(xP,yP,img);
	}
}