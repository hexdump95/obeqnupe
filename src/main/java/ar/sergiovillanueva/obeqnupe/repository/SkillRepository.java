package ar.sergiovillanueva.obeqnupe.repository;

import ar.sergiovillanueva.obeqnupe.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
}
