package log;

import java.util.Date;
/**
 * 
 * Event that occurs during the execution of a program that interrupts 
 * the normal flow of instructions
 * 
 * @see Exception
 * @since  1.0
 * @author Francesco Rose 
 * 
 * */
public class MyException extends Exception  {

	private static final long serialVersionUID = 1L;
    /**
     * Constructs an {@code MyException} with {@code null}
     * as its error detail message.
     */
    public MyException() {
        super("FRANCESCO_ROSE_EXCEPTION "+new Date());
    }
    /**
     * Constructs an {@code MyException} with the specified detail message.
     *
     * @param message
     *        The detail message (which is saved for later retrieval
     *        by the {@link #getMessage()} method)
     */
    public MyException(String message) {
        super("FRANCESCO_ROSE_EXCEPTION "+new Date()+" "+message);
    }
    
    /**
     * Constructs an {@code MyException} with the specified detail message
     * and cause.
     *
     * <p> Note that the detail message associated with {@code cause} is
     * <i>not</i> automatically incorporated into this exception's detail
     * message.
     *
     * @param message
     *        The detail message (which is saved for later retrieval
     *        by the {@link #getMessage()} method)
     *
     * @param cause
     *        The cause (which is saved for later retrieval by the
     *        {@link #getCause()} method).  (A null value is permitted,
     *        and indicates that the cause is nonexistent or unknown.)
     *
     * @since 1.6
     */
    public MyException(String message, Throwable cause) {
        super("FRANCESCO_ROSE_EXCEPTION "+new Date()+" "+message, cause);
    }
    /**
     *
     *
     *
     *
     * @since 1.1
     */
    @Override
    public void printStackTrace() {
    	System.err.println("BY FRANCESCO ROSE ");
    	super.printStackTrace();
    }
    /**
     * Constructs a new exception with the specified cause and a detail
     * message of <tt>(cause==null ? null : cause.toString())</tt> (which
     * typically contains the class and detail message of <tt>cause</tt>).
     * This constructor is useful for exceptions that are little more than
     * wrappers for other throwables (for example, {@link
     * java.security.PrivilegedActionException}).
     *
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A <tt>null</tt> value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     * @since  1.4
     */
    public MyException(Throwable cause) {
        super(cause);
    }
    @Override
    public String toString() {
    	String temp="";
    	for(StackTraceElement s:((Exception) this).getStackTrace()){
    		temp+=("\t at "+s.toString()+"\n");
    	}
    	return super.toString()+"\n"+temp;
    }
    
    
	public static void main(String[] args)   {
		

		for(int i=0;i<10;i++)
		try 
		{
			if(i>5)
				throw new MyException("errore "+i);
		}catch(MyException m){
			m.printStackTrace();
		}
		
		
		
	}
	
	
}
