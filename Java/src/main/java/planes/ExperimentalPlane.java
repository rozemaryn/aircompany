package planes;

import models.ClassificationLevel;

public class ExperimentalPlane extends Plane {

    private final ClassificationLevel classificationLevel;

    public ExperimentalPlane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity, ClassificationLevel classificationLevel) {
        super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
        this.classificationLevel = classificationLevel;
    }

    public ClassificationLevel getClassificationLevel() {
        return classificationLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ExperimentalPlane that = (ExperimentalPlane) o;

        return classificationLevel == that.classificationLevel;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (classificationLevel != null ? classificationLevel.hashCode() : 0);
        return result;
    }

    // нужно ли их вообще переопределять? здесь же используется методы родителя, которые и так наследуются?
    // плюс тут новая характеристика, classificationLevel, вроде тогда надо переопределить…
//    @Override
//    public boolean equals(Object o) {
//
//        return super.equals(o);
//    }
//
//    @Override
//    public int hashCode() {
//
//        return super.hashCode();
//    }

    @Override
    public String toString() {
        return super.toString().replace("}",
                ", classificationLevel = " + classificationLevel +
                        '}');
    }
}



