package uz.pdp.codingbatapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AnswerSend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String text;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false)
    private Users usersId;

    @ManyToOne
    @JoinColumn(name = "task_id",nullable = false)
    private Task taskId;
}
