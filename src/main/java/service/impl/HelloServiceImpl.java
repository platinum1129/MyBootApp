package service.impl;

import org.springframework.stereotype.Service;

import service.HelloService;

@Service
public class HelloServiceImpl implements HelloService {
	
	public int add(int a, int b) {
		return a + b;
	}
}
