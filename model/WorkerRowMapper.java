package com.juaracoding.main.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
public class WorkerRowMapper implements RowMapper<Worker> {	
	@Override
	public Worker mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Worker worker = new Worker();
		worker.setID(rs.getInt("ID"));
		worker.setNama_Depan(rs.getString("Nama_Depan"));
		worker.setNama_Belakang(rs.getString("Nama_Belakang"));
		worker.setGaji(rs.getInt("Gaji"));
		worker.setTgl_Masuk(rs.getString("Tgl_Masuk"));
		worker.setPekerjaan(rs.getString("Pekerjaan"));
		
		return worker;
		
		
	}

}
