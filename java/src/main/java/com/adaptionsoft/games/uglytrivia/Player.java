package com.adaptionsoft.games.uglytrivia;

public class Player
{
    private final String name;

    private int position;

    private int purse;

    private boolean inPenaltyBox;

    public Player(final String name)
    {
        this.name = name;
        this.position = 0;
        this.purse = 0;
        this.inPenaltyBox = false;
    }

    public String name()
    {
        return name;
    }

    public int position()
    {
        return position;
    }

    public void move(final int roll) {
        increasePosition(roll);
        if (position > 11)
        {
            decreasePosition(12);
        }
    }

    private void increasePosition(final int roll)
    {
        this.position += roll;
    }

    private void decreasePosition(final int roll)
    {
        this.position -= roll;
    }

    public int purse()
    {
        return purse;
    }

    public void incrementPurse()
    {
        this.purse++;
    }

    public boolean inPenaltyBox()
    {
        return inPenaltyBox;
    }

    public void inPenaltyBox(final boolean inPenaltyBox)
    {
        this.inPenaltyBox = inPenaltyBox;
    }

    public boolean isWinner() {
        return purse != 6;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
