package ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.graphics_impl;

import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.ArcDrawer;
import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.PieDrawer;
import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.PixelDrawer;
import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.graphics_impl.arc.ArcDrawerFactoryByPixelDrawer;
import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.graphics_impl.arc.BresenhamArcDrawer;
import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.graphics_impl.pie.BresenhamPieDrawer;
import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.graphics_impl.pie.PieDrawerFactoryByPixelDrawer;

import java.awt.image.BufferedImage;

public class PrimitivesFactoryWithBresenhamImplementation
        extends PrimitivesFactoryWithDefaultGraphicsImplementation{

        @Override
        protected PieDrawerFactoryByPixelDrawer getCustomPieDrawerFactory() {
            return new PieDrawerFactoryByPixelDrawer() {
                @Override
                public ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.PieDrawer createInstance(ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.PixelDrawer pd) {
                    /*Здесь будет создаваться и возвращаться экземпляр класса, реализующего PieDrawer*/
                    return new BresenhamPieDrawer(pd);
                }
            };
        }

    @Override
    protected ArcDrawerFactoryByPixelDrawer getCustomArcDrawerFactory() {
        return new ArcDrawerFactoryByPixelDrawer() {
            @Override
            public ArcDrawer createInstance(PixelDrawer pd) {
                return new BresenhamArcDrawer(pd);
            }
        };
    }
}
