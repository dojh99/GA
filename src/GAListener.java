public interface GAListener {
    public void AGenerationEnded(int generation,Population population, double average, double bestFitness);

    public void finished(Result result_Temp);
}
