package com.erp.variety.jdbc;

import java.sql.SQLException;
import java.util.List;

public abstract class AbstractJDBC {

	public abstract List<?> findAll() throws SQLException;
	public abstract Object getRecord(Object entity) throws SQLException;
	public abstract void save(Object entity) throws SQLException;
	public abstract void edit(Object entity) throws SQLException;
	public abstract void delete(Object entity) throws SQLException;
}
