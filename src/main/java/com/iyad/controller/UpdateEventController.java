package com.iyad.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.iyad.dao.Event;
import com.iyad.dao.EventDao;

public class UpdateEventController implements Controller {
	
	private static final Logger LOG = LogManager.getLogger();

	public UpdateEventController(Connection connection, HttpServletRequest request) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
		EventDao eventDao = new EventDao();
		Event e = new Event();
		e.setId(Integer.parseInt(request.getParameter("id")));
		try {
			e.setStart(new Timestamp(dateFormat.parse(request.getParameter("start")).getTime()));
		} catch (ParseException e1) {
		}
		try {
			e.setEnd(new Timestamp(dateFormat.parse(request.getParameter("end")).getTime()));
		} catch (ParseException e1) {
		}
		eventDao.updateEvent(connection, e);
	}

	@Override
	public void write(HttpServletResponse response) throws IOException {
	}

}
