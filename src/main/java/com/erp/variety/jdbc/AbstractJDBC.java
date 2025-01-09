package com.erp.variety.jdbc;

import java.sql.SQLException;
import java.util.List;

public abstract class AbstractJDBC {

	public abstract List<?> findAll() throws SQLException;
	public abstract Object getRecord(Object entity) throws SQLException;
	public abstract String getCorrelativo() throws SQLException;
	public abstract String save(Object entity) throws SQLException;
	public abstract String edit(Object entity) throws SQLException;
	public abstract String delete(Object entity) throws SQLException;
}
