
import java.util.*;

public class Population<T extends GeneTemplate> extends ArrayList<T>{
	
	public void addEntity(T ent){
		this.add(ent);
	}
	
	public void sortEntities(){
		Collections.sort(this);
	}
	
	public void scoreAllEntities(){
		int i;
		int k = this.size();
		
		for(i=0;i<k;i++){
			this.get(i).scoring();
		}
	}
	
	public ArrayList<T> getTopN(int n){
		ArrayList<T> tops = new ArrayList<T>(n);
		int i;
		
		sortEntities();
		for(i=0;i<n;i++){
			tops.add(this.get(i));
		}
		return tops;
	}
	
	public float getFitnessAvr(){
		float avr =0;
		int i=0;
		final int size = size();
		
		while(i<size){
			avr+=this.get(i).fitness;
			i++;
		}
		
		return avr/size;
	}
	
	public int getBestFitness(){
		sortEntities();
		return this.get(0).fitness;
	}
	
	public T getBestData(){
		sortEntities();
		return this.get(0);
	}

	@Override
	public String toString(){
		// TODO: Implement this method
		String str="";
		int i =0;
		final int size = this.size();
		
		str += "BestData : "+getBestData().toString() +"\n";
		
		while(i<size){
			str+= (i+1)+". "+this.get(i).toString()+"\n";
			i++;
		}
		str += "\n";
		return str;
	}
	
	
}
