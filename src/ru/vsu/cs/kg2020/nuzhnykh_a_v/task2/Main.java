package ru.vsu.cs.kg2020.nuzhnykh_a_v.task2;

import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.graphics_impl.PrimitivesFactoryWithBresenhamImplementation;
import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.testing.TestArcs;

public class Main {

    public static void main(String[] args) throws Exception {
        TestArcs.startTest(new PrimitivesFactoryWithBresenhamImplementation(),
                TestArcs.IMG_YOUR|TestArcs.IMG_IDEAL|TestArcs.IMG_DIFF,
                TestArcs.TEST_PIE, true, "./results");
    }
}
