package com.study.springboot.developer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

class JunitCycleTest {
    @BeforeAll
    static void beforeAll() {
        System.out.println("beforeAll");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("beforeEach");
    }

    @Test
    void test1() {
        System.out.println("test1");
        System.out.println("test1");
        System.out.println("test1");
    }

    @Test
    void test2() {
        System.out.println("test2");
        System.out.println("test2");
        System.out.println("test2");
    }

    @Test
    void test3() {
        System.out.println("test3");
        System.out.println("test3");
        System.out.println("test3");
    }

    @AfterEach
    public void AfterEach() {
        System.out.println("AfterEach");
    }

    @AfterAll
    static void AfterAll() {
        System.out.println("AfterAll");
    }
}
