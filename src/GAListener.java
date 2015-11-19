public interface GAListener
{
	public void AGenerationEnded(int generation,double average,double bestFitness);
	public void finished(Result result_Temp);
}
