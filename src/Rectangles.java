import java.io.*;
import java.util.*;

public class Rectangles {

    static class Point {
        float x, y; // The coordinates of a point

        // The constructor that contains the coordinates
        Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

        // Determine if two instances represent the same point
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Point)) {
                return false;
            }
            Point other = (Point) obj;
            return this.x == other.x && this.y == other.y;
        }

    }

    // The method for determining the number of rectangles
    static int countRectangles(List<Point> points) {
        int count = 0; // the number of rectangles - initial 0

        // We go through the list of points
        for (int i = 0; i < points.size() - 1; i++) {
            for (int j = i + 1; j < points.size(); j++) {
                // Pair of points - used to determine the other two corners of a rectangle
                Point p1 = points.get(i);
                Point p2 = points.get(j);

                // We do not consider points on the same axis
                if (p1.x != p2.x && p1.y != p2.y) {
                    // We form the other corners of the rectangle
                    Point p3 = new Point(p1.x, p2.y);
                    Point p4 = new Point(p2.x, p1.y);

                    // If they are found in the list, then we increase the number of rectangles
                    if (points.contains(p3) && points.contains(p4)) {
                        count++;
                    }
                }
            }
        }

        return count / 2; // We divide the number of rectangles by 2 to eliminate duplicates
    }

    public static void main(String[] args) throws IOException {
        // The list of points
        List<Point> points = new ArrayList<>();

        File file = new File("src\\rectanglesInput.txt");

        BufferedReader buffer = new BufferedReader(new FileReader(file));

        String input;

        // While we have characters in input
        while ((input = buffer.readLine()) != null) {
            // We split the input in order to obtain pairs of coordinates
            String[] coordinates = input.split(", ");

            for (String coord : coordinates) {
                // We need to ignore the brackets, because we only need the values
                String[] pairs = coord.replaceAll("[()]", "").split(" ");

                // Then we split each pair
                String[] pairsCoord = pairs[0].split(",");

                // And get the x, y coordinates
                float x = Integer.parseInt(pairsCoord[0]);
                float y = Integer.parseInt(pairsCoord[1]);

                // Finally, we add them to the list of points
                points.add(new Point(x, y));
            }
        }

        buffer.close();

        int rectanglesNumber = countRectangles(points);

        System.out.println(rectanglesNumber);

    }
}
