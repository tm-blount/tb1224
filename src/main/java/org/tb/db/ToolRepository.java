package org.tb.db;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tb.db.entities.Tool;

@Repository
public interface ToolRepository extends CrudRepository<Tool, Integer> {
    // We don't need anything fancy (yet), so we can do a simple join
    // Might not scale, depending, but you also would change this at scale.
    @Query("SELECT t FROM tools t JOIN t.toolType tt WHERE t.id = :id")
    Tool findById(@Param("id") int id);
}
