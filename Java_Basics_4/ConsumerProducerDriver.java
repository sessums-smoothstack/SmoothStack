/*
    SmoothStacks

    name: ConsumerProducerDriver.java
    contributor: Sam Sessums
    description:
        "Write a program with one thread (the producer) that produces items (in this case, simple ints).
        Another thread (the consumer) consumes items. 
        For communacation purposes, both threads have access to a bounded buffer which is basically an array. "
        
        
        Assumptions:
            None for now

        Problems:
            Currently Broken

        classes: ConsumerProducerDriver (Driver)

            ConsumerProducerDriver.main(String[] args)
                args :- nothing for now
                Creates the shared resource (intBuffer) and a single instance of a Producer and Consumer.
                Creates two threads with the Producer and Consumer and starts them both.

            intBuffer(int size) - Constructor
                size :- int for buffer size (array)
                Shared resource for the producer and consumer
                Operates as a queue
            
            intBuffer.size() - Method
                returns int field size

            intBuffer.intAt(int index) - Method
                index :- index for the buffer
                returns an int at the index

            intBuffer.push(int i) - Synchronized Method
                i :- int to push into the buffer
                appends the int to the buffer or waits if the buffer is full
                currently broken

            intBuffer.pop() - Synchronized Method
                returns the 0th index of the buffer, waits if the buffer is empty
                currently broken


            Producer(intBuffer buffer, int produceSize) - Constructor - Implements Runnable
                buffer :- Shared resource to populate with integers
                produceSize :- size for amount to produce

            Producer(intBuffer buffer) - Constructor
                buffer :- Shared resource to populate with integers

            Producer.run() - Overrides Runnable.run() Method
                Produces random integers shared resource intBuffer appends them

            Consumer(intBuffer buffer, int consumeSize) - Constructor - Implements Runnable
                buffer :- Shared resource to consume integers from
                consumeSize :- size for amount to consume by

            Consumer(intBuffer buffer) - Constructor
                buffer :- Shared resource to consume integers from

            Consumer.run() - Overrides Runnable.run() Method
                Consumes shared resource intBuffer ints            
            

        *notes:
            Loosely Structured after "https://docs.oracle.com/javase/tutorial/essential/concurrency/guardmeth.html"

*/



import java.io.IOException;
import java.nio.*;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class ConsumerProducerDriver{

    public static class intBuffer{
        int[] intBuffer;
        int size = 0;
        boolean empty = true;
        boolean full = false;

        intBuffer(int size){
            intBuffer = new int[size];
        }

        public int size(){
            return size;
        }

        public int intAt(int index){
            if (index >= size || index < 0){
                throw new IndexOutOfBoundsException();
            }
            
            return intBuffer[index];

        }

        public synchronized void push(int i) throws InterruptedException{
            // buffer is full, wait for consumer
            while (full){
                try{
                    wait();
                }
                catch(InterruptedException e){

                }
            }

            intBuffer[size] = i;
            size++;
            empty = false;

            if (size == intBuffer.length){
                full = true;
                throw new InterruptedException();
            }
            notifyAll();
        }

        public synchronized int pop(){
            // buffer is empty, wait for Producer
            while(empty){
                try{
                    wait();
                }
                catch(InterruptedException e){

                }
            }
            
            int popped = intBuffer[0];

            for(int i = 1; i <= size; i++){
                intBuffer[i-1] = intBuffer[i];
            }
            size--;

            if(size == 0){
               empty = true;
            }
            else if (full){
                full = false;
            }
            
            notifyAll();
            return popped;
        }

    }

    public static class Producer implements Runnable{
        intBuffer buffer = null;
        int produceSize = 100; // default
        Random intGen = new Random();

        Producer(intBuffer buffer){
            this.buffer = buffer;
        }

        Producer(intBuffer buffer, int produceSize){
            this.buffer = buffer;
            this.produceSize = produceSize;
        }

        @Override
        public void run(){
            for(int i = 0; i < produceSize; i++){
                int pushing = intGen.nextInt(100000);
                System.out.printf("Pushing int: %d\n", pushing);
                try {
                    buffer.push(pushing);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(buffer.size());
                try{
                    Thread.sleep(intGen.nextInt(200));
                }
                catch(InterruptedException e){}
            }
            
        }
    }

    public static class Consumer implements Runnable{
        intBuffer buffer = null;
        int consumeSize = 100; // default
        Random intGen = new Random();
        
        Consumer(intBuffer buffer){
            this.buffer = buffer;
        }

        Consumer(intBuffer buffer, int consumeSize){
            this.buffer = buffer;
            this.consumeSize = consumeSize;
        }

        @Override
        public void run(){
            for (int i = 0; i < consumeSize; i++){
                int popped = buffer.pop();
                System.out.printf("Removing int: %d\n", popped);
                System.out.println(buffer.size());
                try{
                    Thread.sleep(intGen.nextInt(200));
                }
                catch(InterruptedException e){}
            }
            
        }
            
    }

    public static void main(String[] args){
        intBuffer intBuff = new intBuffer(10);
        try{
            
            Producer prod = new Producer(intBuff);
            Consumer cons = new Consumer(intBuff);

            Thread t1 = new Thread(prod);
            Thread t2 = new Thread(cons);

            t1.start();
            t2.start();

        }
        catch(Exception e){
            System.err.println(e);
        }
    }
}
