package planes;

public abstract class Plane {
    private final String model;
    private final int maxSpeed;
    private final int maxFlightDistance;
    private final int maxLoadCapacity;

    protected Plane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity) {
        this.model = model;
        this.maxSpeed = maxSpeed;
        this.maxFlightDistance = maxFlightDistance;
        this.maxLoadCapacity = maxLoadCapacity;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{model = " + model +
                ", maxSpeed = " + maxSpeed +
                ", maxFlightDistance = " + maxFlightDistance +
                ", maxLoadCapacity = " + maxLoadCapacity + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plane other = (Plane) o;
        return maxSpeed == other.maxSpeed &&
                maxFlightDistance == other.maxFlightDistance &&
                maxLoadCapacity == other.maxLoadCapacity &&
                model.equals(other.model);
    }

    @Override
    public int hashCode() {
        int result = model.hashCode();
        result = 31 * result + maxSpeed;
        result = 31 * result + maxFlightDistance;
        result = 31 * result + maxLoadCapacity;
        return result;
    }

    public String getModel() {
        return model;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getMaxFlightDistance() {
        return maxFlightDistance;
    }

    public int getMaxLoadCapacity() {
        return maxLoadCapacity;
    }
}