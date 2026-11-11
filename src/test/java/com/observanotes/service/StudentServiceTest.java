
package com.observanotes.service;

import com.observanotes.dto.StudentRequest;
import com.observanotes.dto.StudentResponse;
import com.observanotes.model.Student;
import com.observanotes.repository.StudentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentCaptor;

import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student;

    @BeforeEach
    void setUp() {

        student = new Student();
        // Utilisation de ReflectionTestUtils pour définir l'ID de l'étudiant
        ReflectionTestUtils.setField(student, "id", 1L);

        student.setFirstName("Assane");
        student.setLastName("Thiao");
        student.setEmail("assane@example.com");
    }

    // Test 1 : créer un étudiant
    // Ce premier test vérifie deux choses :
    //1. La réponse retournée par le service est correcte
    //2. L'étudiant transmis au Repository contient les bonnes informations
    @Test
    void shouldCreateStudent() {

        StudentRequest request = new StudentRequest(
            "Assane",
            "Thiao",
            "assane@example.com"
        );

        // Mock du comportement du repository pour simuler la sauvegarde de l'étudiant
        when(studentRepository.save(any(Student.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        StudentResponse response = studentService.createStudent(request);

        assertNotNull(response);
        assertEquals("Assane", response.firstName());
        assertEquals("Thiao", response.lastName());
        assertEquals("assane@example.com", response.email());

        // Vérification que le repository a été appelé avec les bons arguments
        ArgumentCaptor<Student> captor =
            ArgumentCaptor.forClass(Student.class);

        verify(studentRepository).save(captor.capture());

        assertEquals("Assane", captor.getValue().getFirstName());
        assertEquals("Thiao", captor.getValue().getLastName());
    }

    // Test 2 : récupérer tous les étudiants
    // Ce test vérifie que le service retourne bien la liste des étudiants
    // et que le repository est bien appelé pour récupérer les étudiants
    @Test
    void shouldGetAllStudents() {

        Student secondStudent = new Student();

        secondStudent.setFirstName("Marie");
        secondStudent.setLastName("Dupont");
        secondStudent.setEmail("marie@example.com");

        // Mock du comportement du repository pour simuler la récupération de tous les étudiants
        when(studentRepository.findAll())
            .thenReturn(List.of(student, secondStudent));

        List<StudentResponse> responses =
            studentService.getAllStudents();

        assertEquals(2, responses.size());
        assertEquals("Assane", responses.get(0).firstName());
        assertEquals("Marie", responses.get(1).firstName());

        verify(studentRepository).findAll();
    }

    // Test 3 : récupérer un étudiant existant
    // Ce test vérifie que le service retourne bien l'étudiant correspondant à l'ID fourni
    // et que le repository est bien appelé pour récupérer l'étudiant
    @Test
    void shouldGetStudentById() {

        when(studentRepository.findById(1L))
            .thenReturn(Optional.of(student));

        StudentResponse response =
            studentService.getStudentById(1L);

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("Assane", response.firstName());

        verify(studentRepository).findById(1L);
    }

    // Test 4 : récupérer un étudiant inexistant
    // Ce test vérifie que le service retourne null lorsque l'étudiant n'existe pas
    // et que le repository est bien appelé pour récupérer l'étudiant
    @Test
    void shouldReturnNullWhenStudentDoesNotExist() {

        when(studentRepository.findById(99L))
            .thenReturn(Optional.empty());

        StudentResponse response =
            studentService.getStudentById(99L);

        assertNull(response);

        verify(studentRepository).findById(99L);
    }

    // Test 5 : modifier un étudiant existant
    // Ce test vérifie que le service modifie bien l'étudiant correspondant à l'ID fourni
    // et que le repository est bien appelé pour récupérer et sauvegarder l'étudiant
    @Test
    void shouldUpdateStudent() {

        StudentRequest request = new StudentRequest(
            "Assane",
            "Thiao",
            "new.email@example.com"
        );

        when(studentRepository.findById(1L))
            .thenReturn(Optional.of(student));

        when(studentRepository.save(any(Student.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        StudentResponse response =
            studentService.updateStudent(1L, request);

        assertNotNull(response);
        assertEquals("Assane", response.firstName());
        assertEquals("Thiao", response.lastName());
        assertEquals("new.email@example.com", response.email());

        verify(studentRepository).findById(1L);
        verify(studentRepository).save(student);
    }

    // Test 6 : modifier un étudiant inexistant
    // Ce test vérifie que le service retourne null lorsque l'étudiant n'existe pas
    // et que le repository n'est pas appelé pour sauvegarder l'étudiant
    @Test
    void shouldReturnNullWhenUpdatingNonExistingStudent() {

        StudentRequest request = new StudentRequest(
            "Assane",
            "Thiao",
            "assane@example.com"
        );

        when(studentRepository.findById(99L))
            .thenReturn(Optional.empty());

        StudentResponse response =
            studentService.updateStudent(99L, request);

        assertNull(response);

        verify(studentRepository, never()).save(any(Student.class));
    }

    // Test 7 : supprimer un étudiant
    // Ce test vérifie que le service appelle bien le repository pour supprimer l'étudiant
    @Test
    void shouldDeleteStudent() {

        studentService.deleteStudent(1L);

        verify(studentRepository).deleteById(1L);
    }
}
