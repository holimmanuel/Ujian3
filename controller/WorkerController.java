package com.juarakoding.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.main.model.Absensi;
import com.juaracoding.main.model.AbsensiRowMapper;
import com.juaracoding.main.model.Biodata;
import com.juaracoding.main.model.BiodataRowMapper;
import com.juaracoding.main.model.Worker;
import com.juaracoding.main.model.WorkerRowMapper;

@RestController
@RequestMapping("/Worker")
public class WorkerController {
	
	@Autowired
	JdbcTemplate jdbc ;
	
	public List<Worker> getWorker() {
		
		String sql = "Select * from Worker";
		
		List <Worker> worker =  jdbc.query(sql,new WorkerRowMapper());
		
		return worker;		
		
	}
	
	public int insertWorker(Worker worker) {
		return jdbc.update("INSERT INTO worker(`ID`, `Nama_Depan`, `Nama_Belakang`, `Gaji`, `Tgl_Masuk`, `Pekerjaan`) VALUES "
		+ "('" + worker.getID() + "','" + worker.getNama_Depan() + "','" + worker.getNama_Belakang() + "','" + worker.getGaji() + "','" + worker.getTgl_Masuk() + "','" + worker.getPekerjaan() +"')");
	}
	
	public int updateWorker(String ID, Worker worker) {
		return jdbc.update("UPDATE worker SET `Nama_Depan`='" + worker.getNama_Depan() + "',`Nama_Belakang`=" + worker.getNama_Belakang()+"',`Gaji`='" + worker.getGaji()
				+ "',`Tgl_Masuk`=" + worker.getTgl_Masuk()+"',`Pekerjaan`=" + worker.getPekerjaan() + " WHERE ID = '" + worker.getID() + "'");
		
	}
	
	public int deleteWorker(String ID) {
		return jdbc.update("DELETE FROM `worker` WHERE `ID` = '" + ID + "';");
	
	}
	
	@PostMapping("/")
	public String add(@RequestBody Worker worker) {

		if (this.insertWorker(worker) == 1) {
			return "Insert data berhasil";
		} else {
			return "Insert data gagal";
		}
	}
	
	@PutMapping("/{ID}")
	public ResponseEntity<?> update(@RequestBody Worker worker, @PathVariable String id) {
		try {
			updateWorker(id, worker);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

	@GetMapping("/")
	public List<Worker> list() {
		return getWorker();
	}
	
	@DeleteMapping("/{ID}")
	public void delete(@PathVariable String ID) {
		deleteWorker(ID);
	}
	
	public List <Worker>getGaji(){		
		String sql = "SELECT * FROM worker ORDER BY Gaji DESC LIMIT 5";
		List <Worker> worker = jdbc.query(sql, new WorkerRowMapper());
		return worker;
		
	}
	
	public List <Worker>getPekerjaan(){
		
		String sql = "CALL 'Pekerjaan'()";
		
		List <Worker> worker = jdbc.query(sql, new WorkerRowMapper());
		return worker;		
	}
	
	public List<Worker> listGaji() {
		String sql = "SELECT * FROM tbl_worker WHERE salary in (SELECT salary FROM tbl_worker GROUP BY salary HAVING COUNT(*) > 1 );";
		List<Worker> worker = jdbc.query(sql, new WorkerRowMapper());
		return worker;
	}

}
