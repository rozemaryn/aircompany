import org.testng.annotations.BeforeTest;
import planes.ExperimentalPlane;
import airport.Airport;
import models.ClassificationLevel;
import models.MilitaryType;
import org.testng.Assert;
import org.testng.annotations.Test;
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

    @BeforeTest
    void setUp() {
        airport = new Airport(PLANES);
    }

    //подумать над названиями тестов
    @Test
    public void hasTransportMilitaryPlane() {
        List<MilitaryPlane> bomberMilitaryPlanes = airport.getBomberMilitaryPlanes();

        for (MilitaryPlane militaryPlane : bomberMilitaryPlanes) {
            Assert.assertSame(militaryPlane.getType(), MilitaryType.TRANSPORT);
        }
    }

    @Test
    public void selectPassengerPlaneWithMaxCapacity() {
        Assert.assertEquals(airport.getPassengerPlaneWithMaxPassengersCapacity(), PLANE_WITH_MAX_PASSENGER_CAPACITY);
    }

    @Test
    public void nextPlaneMaxLoadCapacityHigherThanCurrent() {
        Assert.assertEquals(airport.sortByMaxLoadCapacity().getPlanes(), PLANES_SORTED_BY_MAX_LOAD_CAPACITY);
    }

    @Test
    public void hasBomberInMilitaryPlanes() {
        List<MilitaryPlane> bomberMilitaryPlanes = airport.getBomberMilitaryPlanes();

        for (MilitaryPlane militaryPlane : bomberMilitaryPlanes) {
            Assert.assertSame(militaryPlane.getType(), MilitaryType.BOMBER);
        }
    }

    @Test
    public void experimentalPlanesHasNoUnclassifiedLevel() {
        List<ExperimentalPlane> experimentalPlanes = airport.getExperimentalPlanes();

        for (ExperimentalPlane experimentalPlane : experimentalPlanes) {
            Assert.assertNotSame(experimentalPlane.getClassificationLevel(), UNCLASSIFIED);

        }
    }
}


