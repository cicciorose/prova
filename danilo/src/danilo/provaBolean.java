package danilo;
import java.util.ArrayList;

public class provaBolean {

	public static void main(String[] args) {
		ArrayList<Boolean> booleane=new ArrayList<Boolean>();
		booleane.add(true);
		booleane.add(false);
		booleane.add(true);
		booleane.add(true);
		
		
		String stringhe="";
		for(Boolean b:booleane){
			stringhe+=(b.toString()+"%");
		}
		
		
		ArrayList<Boolean> booleane1=new ArrayList<Boolean>();
		for(String s :stringhe.split("%")){
			booleane1.add(Boolean.parseBoolean(s));
			
			
		}
		
		for (boolean b:booleane1) {
			System.out.println(b);
		}

	}

}
