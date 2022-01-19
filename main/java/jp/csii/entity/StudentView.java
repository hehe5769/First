package jp.csii.entity;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class StudentView {
	// id
	private int id;
	// name
	private String name;
	// sex
	private String sex;
	// classx
	private String classx;
	// sansuwu
	private int sansuwu;
	// kokugo
	private int kokugo;
	// adress
	private String adress;
	// birthday
	private Date birthday;
	// TOUROKUBI
	private Timestamp TOUROKUBI;
	// KOUSINNBI
	private Timestamp KOUSINNBI;
}
