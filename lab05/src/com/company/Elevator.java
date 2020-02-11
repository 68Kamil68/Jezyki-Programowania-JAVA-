package com.company;

public class Elevator extends Thread {
    private int capacity = 4;
    public com.company.State state;
    private int WalkersInside;

    public Elevator() {
        this.state = com.company.State.BOT;
        this.WalkersInside = 0;
    }

    public void ChangeState() throws InterruptedException {
        if (this.state == com.company.State.TOP) {
            System.out.println("winda jedzie na dol");
            Thread.sleep(10000);
            System.out.println("winda dojechala na dol");
            this.state = com.company.State.BOT;
            EmptyElevator();
        }
        if (this.state == com.company.State.BOT) {
            System.out.println("winda jedzie na gore");
            Thread.sleep(10000);
            System.out.println("winda dojechala na gore");
            this.state = com.company.State.TOP;
            EmptyElevator();
        }
    }

    public int GetWalkersInside() {
        return this.WalkersInside;
    }

    public void EmptyElevator() {
        this.WalkersInside = 0;
    }

    public boolean isFreeSpace() {
        return this.WalkersInside < 3;
    }

    public void AddWalkerInside() {
        if(this.WalkersInside < this.capacity) {
            this.WalkersInside += 1;
        }
    }

    public void run() {
        while (true) {
            if (this.GetWalkersInside() < 3) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            else {
                try {
                    ChangeState();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
