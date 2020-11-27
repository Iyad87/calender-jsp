package com.iyad.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.iyad.dao.Event;
import com.iyad.dao.EventDao;

public class SaveEventController implements Controller {
	
	private static final Logger LOG = LogManager.getLogger();

	public SaveEventController(Connection connection, HttpServletRequest request) throws SQLException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		EventDao eventDao = new EventDao();
		Event e = new Event();
		if (null != request.getParameter("id")) {
			e.setId(Integer.parseInt(request.getParameter("id")));
		}
		e.setTitle(request.getParameter("title"));
		e.setRemark(request.getParameter("remark"));
		try {
			e.setStart(new Timestamp(dateFormat.parse(request.getParameter("start")).getTime()));
		} catch (ParseException e1) {
		}
		try {
			e.setEnd(new Timestamp(dateFormat.parse(request.getParameter("end")).getTime()));
		} catch (ParseException e1) {
		}
		e.setIsOption(Integer.parseInt(request.getParameter("option")));
		e.setIsPublic(Integer.parseInt(request.getParameter("public")));
		e.setType(request.getParameter("type"));
		eventDao.saveEvent(connection, e);
	}

	@Override
	public void write(HttpServletResponse response) throws IOException {
	}

}
