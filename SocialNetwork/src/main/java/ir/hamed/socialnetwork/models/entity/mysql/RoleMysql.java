package ir.hamed.socialnetwork.models.entity.mysql;

import ir.hamed.socialnetwork.models.entity.ERole;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "rolesmysql")
public class RoleMysql {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @NotNull
    private ERole name;

    public RoleMysql() {

    }

    public RoleMysql(ERole name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}
