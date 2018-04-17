import java.util.ArrayList;
import java.util.List;



public class Node<T> {
		
    private T data;
    private List<Node<T>> edges;
    
    
    public Node(){
    	edges=new ArrayList<>();
    }
    
    public Node(T data) {
    	this();
    	this.data=data;    	
    }
    
   
	public List<Node<T>> getArches() {
		return edges;
	}
	
	public T getData() {
		return data;
	}


	public void setData(T data) {
		this.data = data;
	}
    public boolean connect(Node<T> node) {
    	if(this==node){
    		try {
				throw new Exception("Autoconnessione non permessa");
			} catch (Exception e) {					
				e.printStackTrace();
			}
    	}
    	if(edges!=null && !edges.contains(node)){
    		if(node.edges!=null && !node.edges.contains(this)){
        		this.edges.add(node);
        		node.edges.add(this);
        		return true;
        	}else{
        		
        		try {
					throw new Exception("Archi collegati male");
				} catch (Exception e) {					
					e.printStackTrace();
				}
        	}
    	}    	
    	return false;    	
    }
    
    boolean disconnect(Node<T>node){
    	if(isConnetted(node)){
    		node.edges.remove(this);
    		this.edges.remove(node);
    		return true;
    	}
    	return false;
    }
    
    boolean isConnetted(Node<T>node){
    	if(edges!=null && edges.contains(node)){
    		if(node.edges!=null && node.edges.contains(this)){
    			return true;
    		}else{
    			try {
					throw new Exception("Archi collegati male");
				} catch (Exception e) {					
					e.printStackTrace();
				}
    		}
    	}
    	return false;
    }
    
    public static void main(String[] args) {
		Node<Integer>n=new Node<>();
		Node<Integer>n1=new Node<>();
		System.out.println(n.isConnetted(n1));
		System.out.println(n1.isConnetted(n));
		System.out.println("------connect------");
		n.connect(n1);

		System.out.println(n.isConnetted(n1));

		System.out.println(n1.isConnetted(n));
		System.out.println("------disconnect------");
		n1.disconnect(n);
		System.out.println(n.isConnetted(n1));
		System.out.println(n1.isConnetted(n));
	}
	
    
}
