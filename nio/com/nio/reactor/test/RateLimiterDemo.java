package com.nio.reactor.test;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author 作者姓名 cwt
 * @version 创建时间：2017年5月19日 下午2:25:37 类说明
 */

public class RateLimiterDemo {

	public static void main(String args[]) throws InterruptedException {
		
		testNolimiter();
		Thread.sleep(100*1000);
		testlimiter();
	}

	public static void testNolimiter() {
		Long start = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			System.out.println("testNolimiter#" + i);
		}
		Long end = System.currentTimeMillis();

		System.out.println(end - start);
	}

	public static void testlimiter() {
		Long start = System.currentTimeMillis();
		RateLimiter limiter = RateLimiter.create(1);
		for (int i = 0; i < 100; i++) {
			limiter.acquire();
			System.out.println("testlimiter#" + i);
		}
		Long end = System.currentTimeMillis();

		System.out.println(end - start);
	}
}
