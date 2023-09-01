package planes;
import java.util.Objects;

public class PassengerPlane extends Plane{

    private final int passengersCapacity;

    public PassengerPlane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity, int passengersCapacity) {
        super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
        this.passengersCapacity = passengersCapacity;
    }

    public int getPassengersCapacity() {
        return passengersCapacity;
    }

    @Override
    public String toString() {
        return super.toString().replace("}",
                ", passengersCapacity = " + passengersCapacity +
                '}');
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PassengerPlane that = (PassengerPlane) o;

        return passengersCapacity == that.passengersCapacity;
    }
//вроде и такой метод ок, или так неправильно?
// Objects.hash(Object... values) should be used in cases
// when you want a hash of a sequence of objects,
// e.g. when defining your own hashCode method and want
// a simply-coded hash for multiple values that make up the identity of your object.
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passengersCapacity);
    }
}
