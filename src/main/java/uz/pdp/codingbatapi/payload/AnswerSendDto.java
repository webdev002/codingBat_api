package uz.pdp.codingbatapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.codingbatapi.entity.Task;
import uz.pdp.codingbatapi.entity.Users;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerSendDto {

    @NotNull(message = "text bosh bolishi mumkin emas")
    private String text;

    @NotNull(message = "usersId bosh bolishi mumkin emas!")
    private Integer usersId;


    @NotNull(message = "taskId bosh bolishi mumkin emas")
    private Integer taskId;
}
