/*
    SmoothStacks

    name: DeadLock.java
    contributor: Sam Sessums
    description:
        "Write a program to create a deadlock between two threads."
        Driver for two threads to block each other and wait forever.
        
        Assumptions:
            None for now

        classes: DeadLock (Driver)
            
            DeadLock.Person(String name) - Conctructor
                name :-  field for Person

                Person.getName() - Method
                    returns the name field
                
                Person.shakeHands(Person p) - Synchronized Method
                    p :- the other person who this is shaking hands with
                    prints the name of the other individual who this is shaking hands with

                Person.releaseHand(Person p) - Synchronized Method "Blocked Method"
                    p :- the person who wants to "release hands";
                    prints the name of this who is trying release hands. Won't execute.

            DeadLock.main()
                Creates two Person instances and two threads. Each thread contains a runnable, where run() uses each single Person
                instance shakeHands method with the other Person instance. Once each thread is started they will get block and wait
                forever.

        *notes:
            Loosely Structured after "https://docs.oracle.com/javase/tutorial/essential/concurrency/deadlock.html

*/

import java.util.concurrent.*;

public class DeadLock {
    static class Person {
        private final String name;
        public Person(String name){
            this.name = name;
        }

        public String getName(){
            return this.name;
        }

        public synchronized void shakeHands(Person p){
            System.out.println(p.getName());
            p.releaseHand(this);
        }

        public synchronized void releaseHand(Person p){
            System.out.println(p.getName());
        }
    }
    

    public static void main(String[] args){
        final Person sam = new Person("Sam");
        final Person george = new Person("George");

        
        // Java Docs start each thread immediately
        // doesnt cause deadlock 
        Thread t1 = new Thread(new Runnable(){
            public void run(){
                sam.shakeHands(george);
            }});

        Thread t2 = new Thread(new Runnable(){
            public void run(){
                george.shakeHands(sam);
            }});

            t1.start();

            //get rid of deadlock
            /*
            try{
                Thread.sleep(1);
            }
            catch(InterruptedException e){}
            */
            t2.start();
    }
}
