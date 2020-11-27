package com.iyad.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iyad.dao.Event;
import com.iyad.dao.EventDao;

public class EventListController implements Controller {
	
	private static final Logger LOG = LogManager.getLogger();

	List<Event> data = null;

	public EventListController(Connection connection, HttpServletRequest request) {
		EventDao eventDao = new EventDao();
		data = eventDao.getEvents(connection);
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
