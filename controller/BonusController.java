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
import com.juaracoding.main.model.Bonus;
import com.juaracoding.main.model.BonusRowMapper;


@RestController
@RequestMapping("/bonus")
public class BonusController {
	
	@Autowired
	JdbcTemplate jdbc;

	public List<Bonus> getbonus() {
		String sql = "SELECT * FROM bonus";
		List<Bonus> bonus = jdbc.query(sql, new BonusRowMapper());
		return bonus;
	}
	
	public int insertBonus(Bonus bonus) {
		return jdbc.update("INSERT INTO bonus(ID_Pekerja, Bonus_Date, Bonus_Amount) values ('"+ bonus.getID_Pekerja() + "',"
				+ "'" + bonus.getBonus_Date() + "','" + bonus.getBonus_Amount() + "')");
	}
	
	public int updateBonus(String ID_Pekerja, Bonus bonus) {
		return jdbc.update("UPDATE absensi SET Bonus_Date = '" + bonus.getBonus_Date()
				+ "', Bonus_Amount = '" + bonus.getBonus_Amount() + "' WHERE ID_Pekerja = '" + bonus.getID_Pekerja() + "' ");
	}
	
	public int deleteBonus(String ID_Pekerja) {
		return jdbc.update("DELETE FROM bonus WHERE id = '" + ID_Pekerja + "'");
	}
	
	@PostMapping("/")
	public String add(@RequestBody Bonus bonus) {

		if (this.insertBonus(bonus) == 1) {
			return "Insert data berhasil";
		} else {
			return "Insert data gagal";
		}
	}
	
	@GetMapping("/")
	public List<Bonus> list() {
		return getbonus();
	}
	
	@PutMapping("/{ID_Pekerja}")
	public ResponseEntity<?> update(@RequestBody Bonus bonus, @PathVariable String ID_Pekerja) {
		try {
			updateBonus(ID_Pekerja, bonus);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/{ID_Pekerja}")
	public void delete(@PathVariable String ID_Pekerja) {
		deleteBonus(ID_Pekerja);
	}

}
