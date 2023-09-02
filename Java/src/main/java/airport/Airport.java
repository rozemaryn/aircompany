package airport;
import models.ClassificationLevel;
import planes.ExperimentalPlane;
import models.MilitaryType;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;

import java.util.*;
import java.util.stream.Collectors;

public class Airport {
    private final List<Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = new ArrayList<>(planes);
    }

    public List<PassengerPlane> getPassengerPlanes() {
        return planes.stream()
                .filter(PassengerPlane.class::isInstance)
                .map(PassengerPlane.class::cast)
                .collect(Collectors.toList());
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        return planes.stream()
                .filter(MilitaryPlane.class::isInstance)
                .map(MilitaryPlane.class::cast)
                .collect(Collectors.toList());
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        return planes.stream()
                .filter(ExperimentalPlane.class::isInstance)
                .map(ExperimentalPlane.class::cast)
                .collect(Collectors.toList());
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        List<PassengerPlane> passengerPlanes = getPassengerPlanes();
        return Collections.max(passengerPlanes, Comparator.comparing(PassengerPlane::getPassengersCapacity));
    }

    public List<MilitaryPlane> getMilitaryPlanesByType(MilitaryType type) {
        return getMilitaryPlanes().stream()
                .filter(plane -> plane.getType().equals(type))
                .collect(Collectors.toList());
    }

    public Airport sortByMaxDistance() {
        planes.sort(Comparator.comparingInt(Plane::getMaxFlightDistance));
        return this;
    }

    public Airport sortByMaxSpeed() {
        planes.sort(Comparator.comparingInt(Plane::getMaxSpeed));
        return this;

    }

    public Airport sortByMaxLoadCapacity() {
        planes.sort(Comparator.comparingInt(Plane::getMaxLoadCapacity));
        return this;
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    @Override
    public String toString() {
        return "Airport{ Planes = " + planes + '}';
    }

    public List<ExperimentalPlane> getNonUnclassifiedExperimentalPlanes() {
        return getExperimentalPlanes().stream()
                .filter(plane -> !plane.getClassificationLevel().equals(ClassificationLevel.UNCLASSIFIED))
                .collect(Collectors.toList());
    }
}
