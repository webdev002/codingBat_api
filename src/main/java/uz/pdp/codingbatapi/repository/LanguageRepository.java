package uz.pdp.codingbatapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.codingbatapi.entity.Languages;
import uz.pdp.codingbatapi.entity.Users;

public interface LanguageRepository extends JpaRepository<Languages,Integer> {
    boolean existsByNameAndIdNot(String name, Integer id);
}
