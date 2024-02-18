package com.thoughtclan.TodoPro.Repository;

import com.thoughtclan.TodoPro.Entity.DbNames;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DbNamesRepository  extends JpaRepository<DbNames,Integer> {


    DbNames findById(int id);

}
