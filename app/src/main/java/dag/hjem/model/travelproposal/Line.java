package dag.hjem.model.travelproposal;

public class Line {
    private String lineName;
    private TransportationType type;

    public Line(String lineName, TransportationType type) {
        this.lineName = lineName;
        this.type = type;
    }

    public TransportationType getType() {
        return type;
    }

    public String getLineName() {

        return lineName;
    }

    @Override
    public String toString() {
        return type.getDescription() + "/" + lineName;
    }
}
