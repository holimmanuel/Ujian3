package com.juaracoding.main.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
public class TitleRowMapper implements RowMapper<Title> {	
	@Override
	public Title mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Title title = new Title();
		title.setID_Pekerjaan(rs.getInt("ID_Pekerjaan"));
		title.setJabatan(rs.getString("ID_Jabatan"));
		title.setAffected_From(rs.getString("Affected_From"));
		
		return title;
		
		
	}
	
}
