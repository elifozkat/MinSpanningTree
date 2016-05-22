
public class Edge {
	
	public int weight;
	public Vertex end1;
	public Vertex end2;
	
	public Edge(Vertex end1, Vertex end2, int weight){
		this.end1=end1;
		this.end2=end2;
		this.weight=weight;
		
	}
	
	public Vertex opposite(Vertex v){
		if(v==end1){
			return end2;
		}else
			return end1;
	}
	
	public int getWeight(){
		return weight;
	}
	

	public void removeEdge(){
		end1.edgeList.remove(this);
		end2.edgeList.remove(this);

	}
	
	
}
