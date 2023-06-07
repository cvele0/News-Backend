package rs.raf.demo.repositories.subject;

import rs.raf.demo.entities.Subject;

import java.util.List;

public interface SubjectRepository {
    Subject addSubject(Subject subject);
    List<Subject> allSubjects();
    Subject findSubject(Integer id);
    void deleteSubject(Integer id);
}
