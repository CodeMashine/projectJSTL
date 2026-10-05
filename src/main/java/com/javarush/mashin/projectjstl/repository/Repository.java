package com.javarush.mashin.projectjstl.repository;

import java.util.List;

public interface Repository<T> {
	List<T> getAll();
	T getById(int id);
	void add(T t);
	T remove(int id);
}
