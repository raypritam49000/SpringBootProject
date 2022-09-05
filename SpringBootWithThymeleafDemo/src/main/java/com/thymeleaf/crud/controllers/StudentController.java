package com.thymeleaf.crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.thymeleaf.crud.dto.StudentDto;
import com.thymeleaf.crud.entity.Student;
import com.thymeleaf.crud.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/")
	public String home(Model model) {
		List<StudentDto> studentDtos = this.studentService.getAllStudents();
		System.out.println(studentDtos);
		model.addAttribute("title", "List of Students");
		model.addAttribute("students", studentDtos);
		return "index";
	}

	@GetMapping("/addStudentForm")
	public String addStudentForm(Model model) {
		model.addAttribute("title", "showAddStudentForm");
		return "addStudent";
	}

	@PostMapping("/handleAddStudent")
	public String handleAddStudent(@ModelAttribute StudentDto studentDto) {
		System.out.println(studentDto);
		boolean isSaved = this.studentService.createStudent(studentDto);

		if (isSaved) {
			return "redirect:/";
		} else {
			return "redirect:/addStudentForm";
		}
	}

	@GetMapping("/handleDeleteStudent/{id}")
	public String handleDeleteStudent(@PathVariable("id") Long id) {

		Boolean isDeleted = this.studentService.deleteStudent(id);

		if (isDeleted) {
			return "redirect:/";
		} else {
			return "redirect:/addStudentForm";
		}
	}

	@PostMapping("/updateStudentForm/{id}")
	public String updateStudentForm(@PathVariable("id") Long id, Model model) {
		Student student = this.studentService.getStudent(id);
		model.addAttribute("title", "UpdateStudentForm");
		model.addAttribute("student", student);
		return "updateStudentForm";
	}

	@PostMapping("/handleUpdateStudent/{id}")
	public String handleUpdateStudent(@PathVariable("id") Long id, StudentDto studentDto) {

		Boolean isUpdated = this.studentService.updateStudent(id, studentDto);

		if (isUpdated) {
			return "redirect:/";
		} else {
			return "redirect:/updateStudentForm/" + id;
		}
	}
}
