package com.iyad.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iyad.dao.Event;
import com.iyad.dao.EventDao;

public class GetEventController implements Controller {
	
	private static final Logger LOG = LogManager.getLogger();

	Event data = null;

	public GetEventController(Connection connection, HttpServletRequest request) throws SQLException {
		EventDao eventDao = new EventDao();
		data = eventDao.getEvent(connection, Integer.parseInt(request.getParameter("id")));
	}

	@Override
	public void write(HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm"));		
		writer.println(mapper.writeValueAsString(data));
	}

}
