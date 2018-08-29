package hr.tvz.hrapp.domain.relationship_est_employees;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author vedrana.soljic
 */
@Entity
@Table(name = "RELATIONSHIP_EST_EMPLOYEES")
public class RelationshipEstEmployees implements Serializable {

    @EmbeddedId
    private RelationshipCompositeKey relationshipCompositeKey;

    public RelationshipEstEmployees() {
    }

    public RelationshipEstEmployees(RelationshipCompositeKey relationshipCompositeKey) {
        this.relationshipCompositeKey = relationshipCompositeKey;
    }

    public RelationshipCompositeKey getRelationshipCompositeKey() {
        return relationshipCompositeKey;
    }

    public void setRelationshipCompositeKey(RelationshipCompositeKey relationshipCompositeKey) {
        this.relationshipCompositeKey = relationshipCompositeKey;
    }
}
