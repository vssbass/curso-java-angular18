package api_gestion_citas_medicas.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import api_gestion_citas_medicas.security.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	UserEntity findByUserName(String user);
}
