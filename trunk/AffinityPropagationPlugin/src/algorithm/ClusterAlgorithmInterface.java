package algorithm;

public interface ClusterAlgorithmInterface {

    public String getShortName();

    public String getName();

    //  public void revertSettings();

    //  public void updateSettings(ClusterProperties properties);

    //  public ClusterProperties getSettings();
    public void halt();

    public void doCluster();
}
