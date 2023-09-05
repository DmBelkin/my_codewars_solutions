import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface ClosestPairOfPointsInLinearithmicTime_3KYU {

//    DESCRIPTION:
//    Given a number of points on a plane, your task is to find two points with the smallest distance between them in linearithmic O(n log n) time.
//
//    Example
//  1  2  3  4  5  6  7  8  9
//          1
//          2    . A
//3                . D
//4                   . F
//5             . C
//6
//        7                . E
//8    . B
//9                   . G
//    For the plane above, the input will be:
//
//            [
//            [2,2], // A
//            [2,8], // B
//            [5,5], // C
//            [6,3], // D
//            [6,7], // E
//            [7,4], // F
//            [7,9]  // G
//            ]
//            => closest pair is: [[6,3],[7,4]] or [[7,4],[6,3]]
//            (both answers are valid)
//    The two points that are closest to each other are D and F.
//    Expected answer should be an array with both points in any order.
//
//    Goal
//    The goal is to come up with a function that can find two closest points for any arbitrary array of points, in a linearithmic time.
//
//    Point class is preloaded for you as:
//
//    public class Point {
//        public double x, y;
//
//        public Point() {
//            x = y = 0.0;
//        }
//
//        public Point(double x, double y) {
//            this.x = x;
//            this.y = y;
//        }
//
//        @Override
//        public String toString() {
//            return String.format("(%f, %f)", x, y);
//        }
//
//        @Override
//        public int hashCode() {
//            return Double.hashCode(x) ^ Double.hashCode(y);
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//            if (obj instanceof Point) {
//                Point other = (Point) obj;
//                return x == other.x && y == other.y;
//            } else {
//                return false;
//            }
//        }
//    }

    public static List<Point> closestPair(List<Point> points) {
        return divideAndConquer(points.stream().sorted((o1, o2) -> Double.compare(o1.x,o2.x))
                .collect(Collectors.toList()).toArray(new Point[points.size()]),points.size());
    }
    private static List<Point> divideAndConquer(Point[] pointSet, int size){

        if(size<=3){
            double minDist=Double.MAX_VALUE;
            Point aMin = null;
            Point bMin = null;
            for(int i=0; i<size-1;i++){
                Point a = pointSet[i];
                for(int j=i+1; j<size; j++){
                    Point b =  pointSet[j];
                    double dist = Math.abs(a.x-b.x)+Math.abs(a.y-b.y);
                    if(dist<minDist ){
                        minDist=dist;
                        aMin = a;
                        bMin = b;
                    }
                }
            }
            return Arrays.asList(aMin,bMin);
        }

        int mid = size/2;

        Point midPoint = pointSet[mid];

        List<Point> lMinPoints = divideAndConquer(Arrays.copyOfRange(pointSet, 0, mid), mid);
        List<Point> rMinPoints = divideAndConquer(Arrays.copyOfRange(pointSet, mid, size), size-mid);

        double mDistL = calculateDist(lMinPoints.get(0),lMinPoints.get(1));
        double mDistR = calculateDist(rMinPoints.get(0),rMinPoints.get(1));
        Point aMin = null;
        Point bMin = null;
        double minDist;
        if(mDistL<mDistR){
            minDist = mDistL;
            aMin = lMinPoints.get(0);
            bMin = lMinPoints.get(1);
        } else{
            minDist = mDistR;
            aMin = rMinPoints.get(0);
            bMin = rMinPoints.get(1);
        }
        double streamMinDist = minDist;
        Point[] midSetSorted = Arrays.stream(pointSet).filter(point -> Math.abs(midPoint.x-point.x)<streamMinDist)
                .sorted((o1, o2) -> Double.compare(o1.y,o2.y)).toArray(Point[]::new);

        if(midSetSorted.length==0){
            return Arrays.asList(aMin,bMin);
        }
        for(int i=0; i<midSetSorted.length-1; i++){
            Point point = midSetSorted[i];
            for(int j=i+1; j<midSetSorted.length &&  Math.abs(midSetSorted[j].y - point.y) < minDist; j++){
                if(calculateDist(midSetSorted[i],midSetSorted[j])<minDist){
                    minDist = calculateDist(midSetSorted[i],midSetSorted[j]);
                    aMin = midSetSorted[j];
                    bMin = midSetSorted[i];
                }
            }
        }
        return Arrays.asList(aMin,bMin);
    }

    private static double calculateDist(Point a, Point b){
        return Math.abs(a.x-b.x)+Math.abs(a.y-b.y);
    }


}

class Point {
    public double x, y;

    public Point() {
        x = y = 0.0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(%f, %f)", x, y);
    }

    @Override
    public int hashCode() {
        return Double.hashCode(x) ^ Double.hashCode(y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point other = (Point) obj;
            return x == other.x && y == other.y;
        } else {
            return false;
        }
    }
}
