import airport.Airport;
import models.ClassificationLevel;
import models.MilitaryType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import planes.ExperimentalPlane;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;

import java.util.Arrays;
import java.util.List;

import static models.ClassificationLevel.UNCLASSIFIED;

public class AirportTest {
    private static final List<Plane> PLANES = Arrays.asList(
            new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
            new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
            new PassengerPlane("Boeing-747", 980, 16100, 70500, 242),
            new PassengerPlane("Airbus A320", 930, 11800, 65500, 188),
            new PassengerPlane("Airbus A330", 990, 14800, 80500, 222),
            new PassengerPlane("Embraer 190", 870, 8100, 30800, 64),
            new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
            new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196),
            new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER),
            new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("F-15", 1500, 12000, 10000, MilitaryType.FIGHTER),
            new MilitaryPlane("F-22", 1550, 13000, 11000, MilitaryType.FIGHTER),
            new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryType.TRANSPORT),
            new ExperimentalPlane("Bell X-14", 277, 482, 500, ClassificationLevel.SECRET),
            new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500, ClassificationLevel.TOP_SECRET)
    );

    private static final List<? extends Plane> PLANES_SORTED_BY_MAX_LOAD_CAPACITY = Arrays.asList(
            new ExperimentalPlane("Bell X-14", 277, 482, 500,
                    ClassificationLevel.SECRET),
            new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500,
                    ClassificationLevel.TOP_SECRET),
            new MilitaryPlane("F-15", 1500, 12000, 10000, MilitaryType.FIGHTER),
            new MilitaryPlane("F-22", 1550, 13000, 11000, MilitaryType.FIGHTER),
            new PassengerPlane("Embraer 190", 870, 8100, 30800, 64),
            new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
            new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
            new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196),
            new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
            new PassengerPlane("Airbus A320", 930, 11800, 65500, 188),
            new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER),
            new PassengerPlane("Boeing-747", 980, 16100, 70500, 242),
            new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 80000, MilitaryType.BOMBER),
            new PassengerPlane("Airbus A330", 990, 14800, 80500, 222),
            new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryType.TRANSPORT)
    );


    private static final PassengerPlane PLANE_WITH_MAX_PASSENGER_CAPACITY = new PassengerPlane("Boeing-747", 980, 16100, 70500, 242);

    private Airport airport;

    @Before
    public void setUp() {
        airport = new Airport(PLANES);
    }
    @Test
    public void whenSearchPlanesByTypeThenReturnPlanesWithTheseTypes() {
        List<MilitaryPlane> planes = airport.getMilitaryPlanesByType(MilitaryType.TRANSPORT);

        for (MilitaryPlane militaryPlane : planes) {
            Assert.assertSame(MilitaryType.TRANSPORT, militaryPlane.getType());
        }

        List<MilitaryPlane> bomberMilitaryPlanes = airport.getMilitaryPlanesByType(MilitaryType.BOMBER);

        for (MilitaryPlane militaryPlane : bomberMilitaryPlanes) {
            Assert.assertSame(MilitaryType.BOMBER, militaryPlane.getType());
        }
    }

    @Test
    public void selectPassengerPlaneWithMaxCapacity() {
        Assert.assertEquals(PLANE_WITH_MAX_PASSENGER_CAPACITY, airport.getPassengerPlaneWithMaxPassengersCapacity());
    }

    @Test
    public void nextPlaneMaxLoadCapacityHigherThanCurrent() {
        Assert.assertEquals(PLANES_SORTED_BY_MAX_LOAD_CAPACITY, airport.sortByMaxLoadCapacity().getPlanes());
    }

    @Test
    public void experimentalPlanesHasNoUnclassifiedLevel() {
//        List<Plane> planes = airport.getPlanesByType(ExperimentalPlane.class.getSimpleName());
        List<ExperimentalPlane> nonUnclassifiedExperimentalPlanes = airport.getNonUnclassifiedExperimentalPlanes();

        for (ExperimentalPlane experimentalPlane : nonUnclassifiedExperimentalPlanes) {
            Assert.assertNotSame(UNCLASSIFIED, experimentalPlane.getClassificationLevel());
        }
    }
}


