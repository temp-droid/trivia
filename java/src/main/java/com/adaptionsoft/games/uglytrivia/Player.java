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

    public void move(final Roll roll)
    {
        increasePosition(roll);
        if (position > 11)
        {
            decreasePosition(new Roll(12));
        }
    }

    private void increasePosition(final Roll roll)
    {
        this.position += roll.value();
    }

    private void decreasePosition(final Roll roll)
    {
        this.position -= roll.value();
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
