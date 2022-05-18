package uz.pdp.appapicodingbat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String text;
    @OneToOne
    private Task task;
    @OneToOne
    private User user;
    private boolean is_correct;
}
