package log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 
 * It offers functions to write the log or exceptions to a file  
 *  
 * @author Francesco Rose 
 *  
 * @version 1.0
 * 
 * */

public final class LogManager {   
	/**
	 * Append the {@link Exception} converted to a string in the file named <code>filename</code>
	 * 
	 * @param e {@link Exception} to append in the file
	 * 
	 * @param fileName Name of the file where to append {@link Exception}
	 * 
	 * @return Void
	 * 
	 * @author Francesco Rose
	 * 
	 * */
	static void println(Exception e,String fileName){
		String temp="";
		if(e instanceof MyException){
			println(((MyException)e).toString(), fileName);
		}
		else{
			for(StackTraceElement s:((Exception) e).getStackTrace()){
				temp+=("\t at "+s.toString()+"\n");
		}
		Date date=new Date();
		println( e.toString()+" "+date.toString()+"\n"+temp,fileName);
		}
	}
	/**
	 * Append the String s in the file named <code>filename</code>
	 * 
	 * @param s String to append in the file
	 * 
	 * @param fileName Name of the file where to append s
	 * 
	 * @return Void
	 * 
	 * @author Francesco Rose
	 * 
	 * @since 1.0
	 * 
	 * */	
	static synchronized void println(String s,String fileName) {
		
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		PrintWriter out = null;
		try {
		    fileWriter = new FileWriter(fileName, true);
		    bufferedWriter = new BufferedWriter(fileWriter);
		    out = new PrintWriter(bufferedWriter);
		    out.println(s);
		    out.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		finally {
		    if(out != null)
			    out.close();
		    try {
		        if(bufferedWriter != null)
		            bufferedWriter.close();
		    } catch (IOException e) {
		        //
		    }
		    try {
		        if(fileWriter != null)
		            fileWriter.close();
		    } catch (IOException e) {
		        //
		    }
		}		
	}
	 //TODO da eliminare
     /**
      *
      * @deprecated USE ONLY FOR TEST
      * 
      * 
      * */
     public static void main(String[] args) throws IOException  {
    		
    	 try
    	    {
    	       throw new IOException("ci1235");
    	    }
    	    catch (IOException e)
    	    {
    	      	System.err.println(e.toString());
    	    	e.printStackTrace();
    	    	LogManager.println(e, "log.txt");
    	    }
    	 
	}	
}
