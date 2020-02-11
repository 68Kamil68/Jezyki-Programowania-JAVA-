package com.company;
import java.util.concurrent.ThreadLocalRandom;

public class Walker extends Thread {
    private String name;
    private com.company.State state;
    private final Elevator elevator;
    public Walker(String name, Elevator elevator)
    {
        this.name = name;
        this.state = com.company.State.BOT;
        this.elevator = elevator;
    }

    public void run()
    {
        while (true)
        {
            if(state == com.company.State.BOT)
            {
                System.out.println("Pieszy " + this.name + " spaceruje na dole");
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(10000, 25000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               this.state = com.company.State.QUEUEBOT;
                System.out.println("Pieszy " + this.name + " wszedl do kolejki na dole");
            }

            if(state == com.company.State.QUEUEBOT)
            {
                if((elevator.state == com.company.State.BOT && elevator.isFreeSpace()))
                {
                    System.out.println("Pieszy " + this.name + " wszedl do windy na dole");
                    elevator.AddWalkerInside();
                    this.state = com.company.State.ELEVATORTOTOP;
                }
                else
                {
                  //  System.out.println("Pieszy " + this.name + " czeka na dole");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
            }


            if(state == com.company.State.QUEUETOP)
            {
                if((elevator.state == com.company.State.TOP && elevator.isFreeSpace()))
                {
                    System.out.println("Pieszy " + this.name + " wszedl do windy na gorze");
                    elevator.AddWalkerInside();
                    this.state = com.company.State.ELEVATORTOBOT;
                }
                else
                {
                    //  System.out.println("Pieszy " + this.name + " czeka na dole");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
            }

            if (state == com.company.State.ELEVATORTOBOT)
            {
                if(elevator.state == com.company.State.BOT)
                {
                    this.state = com.company.State.BOT;
                }
                else
                {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
            }

            if (state == com.company.State.ELEVATORTOTOP)
            {
                if(elevator.state == com.company.State.TOP)
                {
                    this.state = com.company.State.TOP;
                }
                else
                {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
            }

            if(state == com.company.State.TOP)
            {
                System.out.println("Pieszy " + this.name + " spaceruje na gorze");
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(15000, 25000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.state = com.company.State.QUEUETOP;
                System.out.println("Pieszy " + this.name + " wszedl do kolejki na gorze");

            }

        }

    }
}
