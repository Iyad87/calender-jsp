package com.haynespro.assessment;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.haynespro.assessment.controller.Controller;
import com.haynespro.assessment.controller.EventListController;
import com.haynespro.assessment.controller.GetEventController;
import com.haynespro.assessment.controller.SaveEventController;
import com.haynespro.assessment.controller.UpdateEventController;

public class DispacherServlet extends HttpServlet {

	private static final long serialVersionUID = -7019000865045768765L;

	private static final Logger LOG = LogManager.getLogger();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try (Connection connection = getConnection()) {
		
			Controller controller = null;
			
			LOG.info("Requested path: " +request.getPathInfo());
			switch(request.getPathInfo()) {
			case "/event-list":
				controller = new EventListController(connection, request);
				break;
			case "/get-event":
				controller = new GetEventController(connection, request);
				break;
			}
			
			if (null != controller) {
				controller.write(response);
			} else {
				throw new IllegalArgumentException("Invalid path: " + request.getPathInfo());
			}

		} catch (SQLException e1) {
			LOG.error(e1.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try (Connection connection = getConnection()) {
		
			LOG.info("Requested path: " +request.getPathInfo());
			switch(request.getPathInfo()) {
			case "/save-event":
				new SaveEventController(connection, request);
				break;
			case "/update-event":
				new UpdateEventController(connection, request);
				break;
			default: 
				throw new IllegalArgumentException("Invalid path: " + request.getPathInfo());
			}

		} catch (SQLException e1) {
			LOG.error(e1.getMessage());
		}
	}
	
	@Override
	public void init() throws ServletException {
		LOG.info("Servlet " + this.getServletName() + " has started.");
	}

	@Override
	public void destroy() {
		LOG.info("Servlet " + this.getServletName() + " has stopped.");
	}

	private Connection getConnection() {
		Connection connection = null;
		try {
			InitialContext context = new InitialContext();
			Context envContext  = (Context) context.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/maindb");
			connection = ds.getConnection();
			LOG.info("Got connection");
		} catch (NamingException | SQLException e) {
			LOG.error(e.getMessage());
		}
		return connection;
	}
}
