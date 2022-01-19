package jp.csii.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.csii.entity.ClassView;
import jp.csii.repository.ClassRepository;

@Service
public class ClassService {

	@Autowired
	private ClassRepository classrepository;

	public List<ClassView> findAll() {
		return classrepository.findAll();
	}
}
