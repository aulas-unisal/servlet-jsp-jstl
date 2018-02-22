package br.unisal.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface DAOInterface<T> {
	void delete(Long id) throws ClassNotFoundException, SQLException, IOException;

	void update(T t) throws ClassNotFoundException, SQLException, IOException;

	void insert(T t) throws ClassNotFoundException, SQLException, IOException;

	List<T> findAll() throws ClassNotFoundException, SQLException, IOException;

	T findById(Long id) throws ClassNotFoundException, SQLException, IOException;
}
