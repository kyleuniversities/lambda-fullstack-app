package com.lambda.lambda.common.helper.number.coordinates;

import com.lambda.lambda.common.helper.number.DoubleHelper;
import com.lambda.lambda.common.util.number.coordinates.Line;
import com.lambda.lambda.common.util.number.coordinates.LineSegment;
import com.lambda.lambda.common.util.number.coordinates.Point;
import com.lambda.lambda.common.utility.number.coordinates.LineIntersectionFinder;

/**
 * Helper class for Coordinates Operations
 */
public class CoordinatesHelper {
    /**
     * Gets the enclosing line of a line segment
     */
    public static Line getEnclosingLine(LineSegment segment) {
        double x1 = segment.getStart().getX();
        double y1 = segment.getStart().getY();
        double x2 = segment.getEnd().getX();
        double y2 = segment.getEnd().getY();
        double a = y2 - y1;
        double b = x1 - x2;
        double c = y1 * (x2 - x1) - x1 * (y2 - y1);
        return Line.newInstance(a, b, c);
    }

    /**
     * Gets the midpoint of a Line Segment
     */
    public static Point getMidpoint(LineSegment segment) {
        double x = DoubleHelper.average(segment.getStart().getX(), segment.getEnd().getX());
        double y = DoubleHelper.average(segment.getStart().getY(), segment.getEnd().getY());
        return Point.newInstance(x, y);
    }

    /**
     * Gets the perpendicular bisector of line segment
     */
    public static Line getPerpendicularBisector(LineSegment segment) {
        Line line = CoordinatesHelper.getEnclosingLine(segment);
        Point midpoint = CoordinatesHelper.getMidpoint(segment);
        return CoordinatesHelper.getPerpendicularLineToPoint(line, midpoint);
    }

    /**
     * Gets the perpendicular line to point
     */
    public static Line getPerpendicularLineToPoint(Line line, Point point) {
        double a = -line.getB();
        double b = line.getA();
        double x2 = point.getX() + b;
        double y2 = point.getY() - a;
        Point pointPrime = Point.newInstance(x2, y2);
        return Line.newInstance(point, pointPrime);
    }

    /**
     * Gets the intersection of two lines
     */
    public static Point intersection(Line f, Line g) {
        return LineIntersectionFinder.newInstance().find(f, g);
    }

    /**
     * Private Constructor to prevent instantiation
     */
    private CoordinatesHelper() {
        super();
    }
}
