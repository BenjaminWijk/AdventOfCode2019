package task3;

class DirectionAndDistance {
    //First letter in Up, Down, Left, Right (U,D,L,R)
    final Direction direction;
    final int distance;

    DirectionAndDistance(char direction, int distance) {
        this.direction = Direction.of(direction);
        this.distance = distance;
    }
}