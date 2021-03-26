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

import com.juaracoding.main.model.Title;
import com.juaracoding.main.model.TitleRowMapper;


@RestController
@RequestMapping("/Title")
public class TitleControler {
	
	@Autowired
	JdbcTemplate jdbc ;
	
	public List<Title> getTitle() {
		String sql = "Select * from Title";
		List<Title> title = jdbc.query(sql,new TitleRowMapper());
		return title;

	}
	
	public int insertTitle(Title title) {
		return jdbc.update("INSERT INTO worker(`ID_Pekerjaan`, `Jabatan`, `Affected_From`) VALUES "
		+ "('" + title.getID_Pekerjaan() + "','" + title.getJabatan() + "','" + title.getAffected_From() + "')");
	}
	
	public int updateTitle(String ID_Pekerjaan, Title title) {
		return jdbc.update("UPDATE worker SET `Jabatan`='" + title.getJabatan() + "',`Affected_From`=" + title.getAffected_From() + " WHERE ID_Pekerjaan = '" + title.getID_Pekerjaan() + "'");
		
	}
	
	public int deleteTitle(String ID_Pekerjaan) {
		return jdbc.update("DELETE FROM `title` WHERE `ID` = '" + ID_Pekerjaan + "';");
	
	}

	@GetMapping("/")
	public List<Title> list() {
		return getTitle();
	}
	
	@PostMapping("/")
	public String add(@RequestBody Title title) {

		if (this.insertTitle(title) == 1) {
			return "Insert data berhasil";
		} else {
			return "Insert data gagal";
		}
	}
	
	@PutMapping("/{ID_Pekerjaan}")
	public ResponseEntity<?> update(@RequestBody Title title, @PathVariable String id) {
		try {
			updateTitle(id, title);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{ID}")
	public void delete(@PathVariable String ID) {
		deleteTitle(ID);
	}


}
