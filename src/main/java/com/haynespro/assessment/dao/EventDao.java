package com.haynespro.assessment.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EventDao {

	private static final Logger LOG = LogManager.getLogger();
	
	public static Map<String, String> NORMAL_COLORS = new HashMap<>();
	static {
		NORMAL_COLORS.put("show", "#c4a000");
		NORMAL_COLORS.put("event", "#ce5c00");
		NORMAL_COLORS.put("group_event", "#4e9a06");
		NORMAL_COLORS.put("classes", "#204a87");
		NORMAL_COLORS.put("workshop", "#5c3566");
		NORMAL_COLORS.put("private", "#a40000");
	}
	public static Map<String, String> OPTIONAL_COLORS = new HashMap<>();
	static {
		OPTIONAL_COLORS.put("show", "#fcf08d");
		OPTIONAL_COLORS.put("event", "#fcd090");
		OPTIONAL_COLORS.put("group_event", "#ade279");
		OPTIONAL_COLORS.put("classes", "#cbe0ff");
		OPTIONAL_COLORS.put("workshop", "#efceec");
		OPTIONAL_COLORS.put("private", "#fdbebe");
	}

	private String getColor(Event e) {
		return e.getIsOption() == 0 ? NORMAL_COLORS.get(e.getType()) : OPTIONAL_COLORS.get(e.getType());
	}

	public List<Event>

	getEvents(Connection connection) {

		List<Event> events = new ArrayList<>();

		try {

			String sql = "SELECT * FROM events";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Event e = new Event();
				e.setId(rs.getInt("id"));
				e.setTitle(rs.getString("title"));
				e.setRemark(rs.getString("remark"));
				e.setStart(rs.getTimestamp("start"));
				e.setEnd(rs.getTimestamp("end"));
				e.setIsOption(rs.getInt("option"));
				e.setIsPublic(rs.getInt("public"));
				e.setType(rs.getString("type"));
				e.setColor(getColor(e));
				events.add(e);
			}

		} catch (Exception e) {
			LOG.error(e);
		}

		return events;
	}

	public void saveEvent(Connection connection, Event event) throws SQLException {
		
		if (event.getId() == 0) {
			insert(connection, event);
		} else {
			update(connection, event);
		}
		
	}
	
	private void insert(Connection connection, Event event) throws SQLException {


		try{
			 String INSERT_Event_SQL = "INSERT INTO events" + "  (id, title, remark, start, end, option,public,type , ) VALUES "
					+ " (?, ?, ?,?,?,?,?,?);";
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_Event_SQL);
			preparedStatement.setInt(1, event.getId());
			preparedStatement.setString(2, event.getTitle());
			preparedStatement.setString(3, event.getRemark());
			preparedStatement.setTimestamp(4, event.getStart());
			preparedStatement.setTimestamp(5, event.getEnd());
			preparedStatement.setInt(6, event.getIsOption());
			preparedStatement.setInt(7, event.getIsPublic());
			preparedStatement.setString(8, event.getType());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
		    LOG.error(e);
		}
	}


	private void update(Connection connection, Event event)  throws SQLException{

		try{
			String UPDATE_Event_SQL = "update events set title = ?,remark = ?, start =? , end = ?, option = ? , public = ?, type =? ,where id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_Event_SQL);

			preparedStatement.setInt(1, event.getId());
			preparedStatement.setString(2, event.getTitle());
			preparedStatement.setString(3, event.getRemark());
			preparedStatement.setTimestamp(4, event.getStart());
			preparedStatement.setTimestamp(5, event.getEnd());
			preparedStatement.setInt(6, event.getIsOption());
			preparedStatement.setInt(7, event.getIsPublic());
			preparedStatement.setString(8, event.getType());

		} catch (SQLException e){
			LOG.error(e);


		}
	}

	public Event getEvent(Connection connection, int id) throws SQLException {

		Event event = null;
		try {
			String SELECT_EVENT_BY_ID = "select id,title, remark, start ,end , option , public, type  from events where id =?";
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EVENT_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String title = rs.getString("title");
				String remark = rs.getString("remark");
				Timestamp start = rs.getTimestamp("start");
				Timestamp end = rs.getTimestamp("end");
				int option = rs.getInt("option");
				int type = rs.getInt("type");
				String public_event = rs.getString("public");


				event = new Event(id,title, remark, start,end,public_event,option,type);
			}

		} catch (SQLException e) {
			LOG.error(e);
		}
		return event;
	}

	public void updateEvent(Connection connection, Event event) {
		
		throw new UnsupportedOperationException("To be implemented");

	}

    public void deleteEvent(Connection connection, int id) throws SQLException {

		try {
			String DELETE_EVENTS_SQL = "delete from events where id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EVENTS_SQL);
			preparedStatement.setInt(1, id);
		} catch ( SQLException e){
			LOG.error(e);
		}
    }
}
