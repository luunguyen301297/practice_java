package org.example.spring_clone.model;

import lombok.Data;

@Data
public class IPorn {

    private Ram ram;
    private Cpu cpu;

    IPorn(final Ram ram, final Cpu cpu) {
        this.ram = ram;
        this.cpu = cpu;
    }

    public void printSpecification() {
        System.err.println("RAM : " + ram.getSpecification() + " / " + ram.getProducer());
        System.err.println("CPU : " + cpu.getSpecification() + " / " + cpu.getProducer());
    }

}
