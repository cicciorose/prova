import java.util.ArrayList;
import java.util.List;

public class Graph<T> {
	
	List<Node<T>> nodes;
	
	public Graph() {
		nodes = new ArrayList<Node<T>>();
	}
	
	public Node<T> getNode(int i){
		
		if(nodes.size()>i){
			return nodes.get(i);
		}else{
			return null;
		}
	}
	
	public boolean addFirstNode(T data){
		if(nodes.size()==0){
			return addNode(data,-1);
		}
		return false;
	}
	
	public boolean addNode(T data,int index){
		if(nodes.size()>0){
			Node<T> n=getNode(index);
			if(n!=null){
				Node<T> node=new Node<T>(data);
				if(n.connect(node)){
					nodes.add(node);
					return true;
				}else{
					System.out.println(n.getData()+" Graph.addNode() "+node.getData());
				}
			}
		}else{
			Node<T>node=new Node<>(data);
			nodes.add(node);
			return true;
		}
		return false;
	}
	
	public boolean addNode(Node<T> node,int index){
		Node<T> n=getNode(index);
		if(n!=null){
			return n.connect(node);
		}else{
			return false;
		}
	}
	
	public boolean remove(int index){
		Node<T>node = getNode(index);
		if(node!=null){
			for(Node<T> n:node.getArches()){
				node.disconnect(n);
			}
			nodes.remove(index);
			return true;
		}
		return false;
	}
	
	public boolean remove(Node<T> node){
		if(nodes.contains(node)){
			for(Node<T> n:node.getArches()){
				node.disconnect(n);
			}
			nodes.remove(node);
			return true;
		}
		return false;
		
	}

	public boolean connect(int index,int index1){
		Node<T> n=getNode(index);
		Node<T> n1=getNode(index1);
		if(n==null){System.err.println("first" +" i:"+index+" i1:"+index1);}

		if(n1==null){System.err.println("second" +" i:"+index+" i1:"+index1);}
		return n.connect(n1);
	}
	
	public List<Node<T>> cammino(int index,T dataFind){
		Node<T> node=getNode(index);
		if(node==null){
			return null;
		}
		return cammino(new ArrayList<>(),new ArrayList<>(),node,dataFind);
	}
	
	public List<Node<T>> cammino(List<Node<T>> nodes,List<Node<T>> visited, Node<T> node,T dataFind){
		if(visited.contains(node)){
			return null;//gia visitato
		}
		visited.add(node);
		if(node.getData().equals(dataFind)){
			nodes.add(node);
			return nodes;
		}else{
			for(Node<T> n : node.getArches()){
				List<Node<T>> ls=nodes;
				ls=cammino(nodes,visited,n,dataFind);
				if(ls!=null && !ls.isEmpty()){
					ls.add(node);
					return ls;
				}
			}
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		int i=0;
		String s="{";
		for(Node<T> n : this.nodes){
			s+="(["+(i++)+"],"+n.getData()+") ";
		}
		return s+"}";
	}
	
	public static void main(String[] args) {
		
		Graph<Integer> g=new Graph<>();
		//1 -> 0
		//2 -> 1
		//3 -> 2
		//4 -> 3
		//5 -> 4
		g.addFirstNode(1);
		g.addNode(2, 0);
		g.addNode(3, 1);
		g.addNode(4, 0);
		g.addNode(5, 1);System.out.println("G: "+g);
		g.connect(4, 3);
		
		//Node<Integer> n=g.getNode(1);
		List<Node<Integer>> l=g.cammino(0, 4);
		for(Node<Integer> n:l){
			System.out.println(n.getData());
		}
	}

}
