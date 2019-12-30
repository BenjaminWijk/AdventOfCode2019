package task3;

class DirectionAndDistance {
    final Direction direction;
    final int distance;

    DirectionAndDistance(char direction, int distance) {
        this.direction = Direction.of(direction);
        this.distance = distance;
    }
}