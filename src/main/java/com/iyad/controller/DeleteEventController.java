package com.iyad.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.iyad.dao.EventDao;

public class DeleteEventController implements Controller {
	
	private static final Logger LOG = LogManager.getLogger();

	public DeleteEventController(Connection connection, HttpServletRequest request) throws SQLException {
		EventDao eventDao = new EventDao();
		eventDao.deleteEvent(connection, Integer.parseInt(request.getParameter("id")));
	}

	@Override
	public void write(HttpServletResponse response) throws IOException {
	}

}
