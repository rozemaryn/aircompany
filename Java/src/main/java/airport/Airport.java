package airport;

import planes.ExperimentalPlane;
import models.MilitaryType;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;

import java.util.*;

public class Airport {
    //неразбериха с типами, где-то подстановочный, где-то нет, не понимаю, почему так
    private final List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = new ArrayList<>(planes);
    }

    // мб как-то всё увязать в один метод?
    // попыталась, но есть проблема кастинга

//    public List<? extends Plane> getPlanesByType(Class<? extends Plane> type) {
//        List<? extends Plane> planesByType = new ArrayList<>();
//
//        for (int i = 0; i < planes.size(); i++) {
//            if (type.isInstance(planes.get(i))) {
//                planesByType.add((type.getName()) planes.get(i));
//            }
//        }
//
//        return planesByType;
//    }

    public List<PassengerPlane> getPassengerPlanes() {
        List<PassengerPlane> passengerPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane instanceof PassengerPlane) {
                passengerPlanes.add((PassengerPlane) plane);
            }
        }
        return passengerPlanes;
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        List<MilitaryPlane> militaryPlanes = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane instanceof MilitaryPlane) {
                militaryPlanes.add((MilitaryPlane) plane);
            }
        }
        return militaryPlanes;
    }

        public List<ExperimentalPlane> getExperimentalPlanes() {
            List<ExperimentalPlane> experimentalPlanes = new ArrayList<>();
            for (Plane plane : planes) {
                if (plane instanceof ExperimentalPlane) {
                    experimentalPlanes.add((ExperimentalPlane) plane);
                }
            }
            return experimentalPlanes;
        }

    // много текста, как это исправить?
    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        List<PassengerPlane> passengerPlanes = getPassengerPlanes();
        return Collections.max(passengerPlanes, Comparator.comparing(PassengerPlane::getPassengersCapacity));
    }

    //переписала эти 2 метода в один, см. ниже
    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        List<MilitaryPlane> transportMilitaryPlanes = new ArrayList<>();
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();

        for (MilitaryPlane plane : militaryPlanes) {
            if (plane.getType() == MilitaryType.TRANSPORT) {
                transportMilitaryPlanes.add(plane);
            }
        }
        return transportMilitaryPlanes;
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        List<MilitaryPlane> bomberMilitaryPlanes = new ArrayList<>();
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();
        for (MilitaryPlane plane : militaryPlanes) {
            if (plane.getType() == MilitaryType.BOMBER) {
                bomberMilitaryPlanes.add(plane);
            }
        }
        return bomberMilitaryPlanes;
    }

    public List<MilitaryPlane> getMilitaryPlanesByType(MilitaryType type) {
        List<MilitaryPlane> planesByType = new ArrayList<>();
        List<MilitaryPlane> militaryPlanes = getMilitaryPlanes();

        for (MilitaryPlane plane : militaryPlanes) {
            if (plane.getType() == type) {
                planesByType.add(plane);
            }
        }
        return planesByType;
    }

    public Airport sortByMaxDistance() {
        planes.sort((plane1, plane2) -> Integer.compare(plane1.getMaxFlightDistance(), plane2.getMaxFlightDistance()));
        return this;
    }

    public Airport sortByMaxSpeed() {
        planes.sort((plane1, plane2) -> Integer.compare(plane1.getMaxSpeed(), plane2.getMaxSpeed()));
        return this;

    }

    public Airport sortByMaxLoadCapacity() {
        planes.sort((plane1, plane2) -> Integer.compare(plane1.getMaxLoadCapacity(), plane2.getMaxLoadCapacity()));
        return this;
    }

    public List<? extends Plane> getPlanes() {
        return planes;
    }

    @Override
    public String toString() {
        return "Airport{ Planes = " + planes + '}';
    }
}
