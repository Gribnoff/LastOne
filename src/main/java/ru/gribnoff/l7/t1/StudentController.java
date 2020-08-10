package ru.gribnoff.l7.t1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Controller
@RequestMapping("/students")
class StudentController {
	private final StudentService studentService;
	private final StudentConverter studentConverter;

	public StudentController(@NotNull StudentService studentService, @NotNull StudentConverter studentConverter) {
		this.studentService = studentService;
		this.studentConverter = studentConverter;
	}

	@GetMapping
	public String studentList(Model model) {
		model.addAttribute("students", studentService.findAll());
		return "index";
	}

	@GetMapping("/{id}")
	public String studentPage(Model model, @PathVariable Integer id) throws NotFoundException {
		model.addAttribute("student", studentService.findById(id));
		return "student";
	}

	@GetMapping("/add")
	public String addStudentPage(Model model) {
		model.addAttribute("student", new StudentDTO());
		return "addstudent";
	}

	@PostMapping
	public String addStudent(StudentDTO studentDTO) {
		studentService.save(studentDTO);
		return "redirect:/students";
	}

	@GetMapping("/{id}/update")
	public String updateStudentPage(Model model, @PathVariable Integer id) throws NotFoundException {
		model.addAttribute("student", studentService.findById(id));
		return "updatestudent";
	}

	@PostMapping("/{id}/update")
	public String updateStudent(@ModelAttribute StudentDTO studentDTO, @PathVariable Integer id) {
		Student student = studentConverter.dtoToStudent(studentDTO);
		student.setId(id);
		studentService.update(student);
		return "redirect:/students";
	}

	@PostMapping("/{id}/delete")
	public String deleteStudent(@PathVariable Integer id) {
		studentService.deleteById(id);
		return "redirect:/students";
	}
}
