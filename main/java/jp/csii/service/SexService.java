package jp.csii.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.csii.entity.SexEntity;
import jp.csii.repository.SexRepository;

@Service
public class SexService {

	@Autowired
	private SexRepository sexrepository;

	public List<SexEntity> findAll() {
		return sexrepository.findAll();
	}
}
