package uz.pdp.codingbatapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.codingbatapi.entity.Users;

public interface UsersRepository extends JpaRepository<Users,Integer> {
    boolean existsByPasswordAndEmail(Integer password, String email);
    boolean existsByPasswordAndIdNot(Integer password, Integer id);

}
