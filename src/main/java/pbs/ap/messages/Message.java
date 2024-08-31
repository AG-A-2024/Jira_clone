package pbs.ap.messages;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import pbs.ap.users.User;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Message extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, length = 500)
    public String text;

    @ManyToOne(optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public User sender;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    public LocalDateTime creationTime;

    @JsonProperty("senderId")
    public Long getSenderId() {
        return this.sender != null ? this.sender.id : null;
    }
}
