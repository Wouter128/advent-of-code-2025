package day1;

public class Dial {
    private int currentPosition;
    private int amountOfZeros;

    public Dial() {
        currentPosition = 50;
        amountOfZeros = 0;
    }

    public void moveLeft(int amount) {
        for (int i = 0; i < amount; i++) {
            currentPosition--;

            if (currentPosition == 0) {
                amountOfZeros++;
            }

            if (currentPosition == -1) {
                currentPosition = 99;
            }
        }
    }

    public void moveRight(int amount) {
        for (int i = 0; i < amount; i++) {
            currentPosition++;
            if (currentPosition == 100) {
                currentPosition = 0;
                amountOfZeros++;
            }
        }
    }

    public int getAmountOfZeros() {
        return amountOfZeros;
    }
}
