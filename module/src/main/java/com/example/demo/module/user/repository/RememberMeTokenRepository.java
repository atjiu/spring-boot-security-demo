package com.example.demo.module.user.repository;

import com.example.demo.module.user.model.RememberMeToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RememberMeTokenRepository extends JpaRepository<RememberMeToken, Integer> {
	RememberMeToken getBySeries(String series);

	void deleteByUsername(String username);

	List<RememberMeToken> getAllByUsernameOrderByDate(String username);
}
