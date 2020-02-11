package com.company;

public class Main {

    public static void main(String[] args) {
        Elevator elevator = new Elevator();
        Walker a = new Walker("a", elevator);
        Walker b = new Walker("b", elevator);
        Walker c = new Walker("c", elevator);
        Walker d = new Walker("d", elevator);
        Walker e = new Walker("e", elevator);
        Walker f = new Walker("f", elevator);
        Walker g = new Walker("g", elevator);
        Walker h = new Walker("h", elevator);
        elevator.start();
        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
        f.start();
        g.start();
        h.start();
	// write your code here
    }
}
