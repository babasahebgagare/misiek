package structs;

class PPINetworkProjectionContext {

    private PPINetwork motherNetwork;

    public PPINetworkProjectionContext(PPINetwork motherNetwork) {
        this.motherNetwork = motherNetwork;
    }

    public PPINetwork getMotherNetwork() {
        return motherNetwork;
    }

    public void setMotherNetwork(PPINetwork motherNetwork) {
        this.motherNetwork = motherNetwork;
    }
}
