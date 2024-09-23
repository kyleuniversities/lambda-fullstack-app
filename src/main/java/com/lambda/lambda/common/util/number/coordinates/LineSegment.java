package com.lambda.lambda.common.util.number.coordinates;

/**
 * Structure class for a 2D Line Segement
 */
public final class LineSegment {
    // Instance Fields
    private Point start;
    private Point end;

    // New Instance Method
    public static LineSegment newInstance(Point start, Point end) {
        return new LineSegment(start, end);
    }

    // Constructor Method
    private LineSegment(Point start, Point end) {
        super();
        this.start = start;
        this.end = end;
    }

    // Accessor Methods
    public Point getStart() {
        return this.start;
    }

    public Point getEnd() {
        return this.end;
    }

    // To String Method
    @Override
    public String toString() {
        return String.format("%s <-> %s", this.getStart().toString(), this.getEnd().toString());
    }
}
