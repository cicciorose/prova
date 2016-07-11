package log;

import javafx.util.Pair;
/*dato un x y e angolo;
* calcola un punto a distanza d
* dal punto iniziale,tenendo conto
* dell'angolo CORNER che crea l'inclinaziane 
*/
public class CornerCalculate {
	/**
	 * x and y given a corner ; calculates a point at a distance 1 
	 * from the starting point ,taking into account that creates the inclination angle CORNER
	 * 
	 * @see Utility
	 * 
	 * @since 1.0
	 * 
	 * @param corner Insert Corner in double value in Degrees
	 * 
	 * @param X Insert X value of point in double 
	 * 
	 * @param Y Insert Y value of point in double 
	 * 
	 * @author Francesco Rose 
	 * 
	 * @return "Pair&lt;Double,Double&gt;" Where Key is X  Value is Y
	 * */
	public static Pair<Double,Double > newPointToCorner(double corner ,double X,double Y){
		return newPointToCorner(corner,X,Y,1);
	}
	/**
	 * x and y given a corner ; calculates a point at a distance <code>distance</code> 
	 * from the starting point ,taking into account that creates the inclination angle CORNER
	 * 
	 * @see Utility
	 * 
	 * @since 1.0
	 * 
	 * @param corner Insert Corner in double value in Degrees
	 * 
	 * @param X Insert X value of point in double 
	 * 
	 * @param Y Insert Y value of point in double 
	 * 
	 * @param distance Insert distance value
	 * 
	 * @author Francesco Rose 
	 * 
	 * @return "Pair&lt;Double,Double&gt;" Where Key is X  Value is Y 
	 * */
	public static Pair<Double,Double > newPointToCorner(double corner ,double X,double Y,double distance){
		double X1;
		double Y1;
		
//		if(corner<0){//TODO DA ABILITARE IN CASO DI ECCEZZIONE IllegalArgumentException CON VALORE DI ANGOLO NEGATIVO
//			corner=360-Math.abs(corner);
//		}
		
		if(corner==0 || corner==360){
			X1=X;
			Y1=Y-distance;
		}else if(corner==90){
			X1=X+distance;
			Y1=Y;
		}else if(corner==180){
			X1=X;
			Y1=Y+distance;
		}else if(corner==270){
			X1=X-distance;
			Y1=Y;
		}else if(corner > 0.0 && corner < 90.0){
			X1=X+(distance*Math.cos(corner));
			Y1=Y-(distance*Math.sin(corner));
		}else if(corner > 90 && corner < 180){
			X1=X+(distance*Math.sin(corner));
			Y1=Y+(distance*Math.cos(corner));
		}else if(corner > 180 && corner < 270){
			X1=X-(distance*Math.sin(corner));
			Y1=Y+(distance*Math.cos(corner));
		}else if(corner > 270 && corner < 360){
			X1=X-(distance*Math.cos(corner));
			Y1=Y+(distance*Math.sin(corner));
		}else{
			System.err.println("Errore formula X="+X+" Y="+Y+" alfa="+corner);
			throw new IllegalArgumentException("Errore formula X="+X+" Y="+Y+" alfa="+corner);
		}
		Pair<Double,Double> p=new Pair<Double,Double>(X1,Y1);		
		return p;
	}	
	
	public static void main(String[] args) {
			
		Pair<Double,Double> p=newPointToCorner(0, 1, 1, 1);
		System.out.println("x="+p.getKey()+" Y="+p.getValue());
			
	}

}