package Codes;

public class Area {
	
	void Find_Area(float s){
		float area=s*s;
		System.out.println("Area Square:" + area);
	}
	
	void Find_Area(float l, float b){
		float area=l*b;
		System.out.println("Area Rectnagle:" + area);

	}
	
	void Find_Area(double c){
		double area = 3.1416 * c * c;
		System.out.println("Area Circle:" + area);
	}
	
	public static void main(String args[]) {
		Area a1 = new Area();
		
		a1.Find_Area(4);
		a1.Find_Area(4, 10);
		a1.Find_Area(3.0);

}
}
		
