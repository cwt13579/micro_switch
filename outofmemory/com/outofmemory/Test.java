package com.outofmemory;

public class Test {
    public static void main(String[] args) {
        try {
            Thread myThread = new Test().new TestThread();
            long threadStartTime = System.currentTimeMillis();
            myThread.start(); // **********Breakpoint 1
            System.out.println("Thread started");
            myThread.join();
            long threadExecutionTime = System.currentTimeMillis() - threadStartTime;
            System.out.println("Thread finished execution in " + threadExecutionTime + " milliseconds.");
        } catch(InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private class TestThread extends Thread {
        @Override
        public void run() { // **********Breakpoint 2
            try {
                sleep(5*1000);
            } catch(InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}