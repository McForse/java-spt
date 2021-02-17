package ru.mirea.pr7.facade;

public class Computer {
    public void run() {
        CPU processor = new CPU();
        Memory memory = new Memory();
        HardDrive drive = new HardDrive();
        processor.processData();
        memory.load();
        drive.readData();
    }
}
