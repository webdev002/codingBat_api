package uz.pdp.codingbatapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.codingbatapi.entity.AnswerSend;

public interface AnswerSendRepository extends JpaRepository<AnswerSend,Integer> {
    boolean existsByText(String text);
    boolean existsByTextAndIdNot(String text, Integer id);
}
