package web.app.pavelk.message4.base1.model.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.app.pavelk.message4.base1.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, String> {
}



