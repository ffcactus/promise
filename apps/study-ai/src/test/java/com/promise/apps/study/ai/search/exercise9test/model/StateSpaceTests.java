package com.promise.apps.study.ai.search.exercise9test.model;

import com.promise.apps.study.ai.search.exercise9.model.ConvexPolygon;
import com.promise.apps.study.ai.search.exercise9.model.Point;
import com.promise.apps.study.ai.search.exercise9.model.StateSpace;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

public class StateSpaceTests {
    @Test
    void StateSpaceCase1() {
        Point from = new Point(0, 0);
        Point to = new Point(0, 100);

        ConvexPolygon polygon = new ConvexPolygon(List.of(
                new Point(25, 25),
                new Point(75, 25),
                new Point(25, 75),
                new Point(75, 75)));
        var stateSpace = new StateSpace(from, to, List.of(polygon));

    }
}
