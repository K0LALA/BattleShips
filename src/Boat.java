import java.util.Vector;

public class Boat {

    /**
     * Name of the boat
     */
    private final String name;
    /**
     * Size of the boat
     */
    private final int size;
    /**
     * First position of the boat.
     * <p>Full part is X position and decimal part is Y position (e.g. 5.07 for X=5 and Y=7)</p>
     */
    private final float position1;
    /**
     * Second position of the boat, same format as the first one.
     */
    private final float position2;

    /**
     * Number of hits
     */
    private int hits = 0;
    /**
     * If the boat is alive
     */
    private boolean isAlive = true;

    public Vector<Float> getPositions() {
        return positions;
    }

    /**
     * All the positions of the boat, same format as position1 and position2
     */
    private final Vector<Float> positions = new Vector<>();

    /**
     * Constructor for <code>Boat</code> class without the size parameter
     * @param name Name of the boat
     * @param position1 First position of the boat, can be at bottom-right corner of second position (order doesn't matter)
     * @param position2 Second position of the boat
     */
    public Boat(String name, float position1, float position2) {
        this.name = name;
        this.position1 = position1;
        this.position2 = position2;

        calculatePositions();
        this.size = calculateSize();
    }

    private int calculateSize() {
        return positions.size();
    }

    /**
     * Convert two encoded positions to a <code>Vector</code> of positions
     */
    private void calculatePositions () {
        // Decode positions into 4 individuals positions
        int pos1X = (int) this.position1;
        int pos1Y = (int) Math.round((double) (this.position1 - pos1X) * 100);
        int pos2X = (int) this.position2;
        int pos2Y = (int) Math.round((double) (this.position2 - pos2X) * 100);

        // ChatGPT simplified version:
        int startX = Math.min(pos1X, pos2X);
        int endX = Math.max(pos1X, pos2X);
        int startY = Math.min(pos1Y, pos2Y);
        int endY = Math.max(pos1Y, pos2Y);

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                float encodedPosition = x + (float) y / 100;
                positions.add(encodedPosition);
            }
        }
    }

    public boolean isHit(float position) {
        boolean isHit = positions.contains(position);

        if(isHit) {
            hits++;
            if(hits >= size)
                isAlive = false;
        }

        return isHit;
    }

    public boolean isDead() {
        return !isAlive;
    }

    public String getName() {
        return name;
    }
}
