package com.promise.apps.study.ai.search.exercise9.model;

import java.util.List;

/**
 * Defines the convex polygonal obstacle in the problem.
 */
public class ConvexPolygon {
    private final List<Point> vertices;

    public ConvexPolygon(List<Point> vertices) {
        this.vertices = vertices;
    }

    public List<Point> getVertices() {
        return vertices;
    }
}
