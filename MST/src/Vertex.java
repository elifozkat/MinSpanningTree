import java.util.ArrayList;

public class Vertex {

	public String name;
	public int value;
	public ArrayList<Edge> edgeList;
	
	public Vertex(String name){
		this.name=name;
		value=Integer.MAX_VALUE;    
		edgeList = new ArrayList<>();
	}

	
	public String getName(){
		return name;
	}
	
	public void setName(String n){
		this.name=n;
	}
	
	public int getValue(){
		return value;
	}
	
	public void setValue(int i){
		this.value=i;
	}
	
	public void newEdge(Edge e){
		edgeList.add(e);
	}
	
	
}
