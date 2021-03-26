package com.juaracoding.main.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BonusRowMapper implements RowMapper<Bonus> {

	@Override
	public Bonus mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Bonus bonus = new Bonus();
		bonus.setID_Pekerja(rs.getInt("ID_Pekerja"));
		bonus.setBonus_Date(rs.getString("bonus_date"));
		bonus.setBonus_Amount(rs.getDouble("Bonus_Amount"));
		
		
		// TODO Auto-generated method stub
		return bonus;
	}

}