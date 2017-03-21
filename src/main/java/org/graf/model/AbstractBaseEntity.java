package org.graf.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractBaseEntity implements Serializable {
    public static final String SEQ_GENERATOR = "SEQ_GENERATOR";
    public static final int DEFAULT_ALLOCATION_SIZE = 1;
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "seq_cocktail_finder", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "seq_cocktail_finder"),
                    @Parameter(name = "increment_size", value = "1")
            })
    @GeneratedValue(generator = "seq_cocktail_finder", strategy = GenerationType.SEQUENCE)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(getClass().equals(o.getClass()))) {
            return false;
        }
        AbstractBaseEntity entityBase = (AbstractBaseEntity) o;
        return id != null && entityBase.id != null && Objects.equals(id, entityBase.id);
    }

    @Override
    public final int hashCode() {
        return 31;
    }
}
