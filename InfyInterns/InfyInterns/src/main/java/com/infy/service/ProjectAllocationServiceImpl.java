package com.infy.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dto.MentorDTO;
import com.infy.dto.ProjectDTO;
import com.infy.entity.Mentor;
import com.infy.entity.Project;
import com.infy.exception.InfyInternException;
import com.infy.repository.MentorRepository;
import com.infy.repository.ProjectRepository;

import jakarta.transaction.Transactional;

@Service(value = "projectService")
@Transactional
public class ProjectAllocationServiceImpl implements ProjectAllocationService {

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	MentorRepository mentorRepository;

	@Override
	public Integer allocateProject(ProjectDTO project) throws InfyInternException {

		Optional<Mentor> optional = mentorRepository.findById(project.getMentorDTO().getMentorId());

		Mentor mentor = optional.orElseThrow(() -> new InfyInternException("Service.MENTOR_NOT_FOUND"));

		if (mentor.getNumberOfProjectsMentored() >= 3) {
			throw new InfyInternException("Service.CANNOT_ALLOCATE_PROJECT");
		}

		Project project1 = new Project();
		project1.setProjectId(project.getProjectId());
		project1.setProjectName(project.getProjectName());
		project1.setIdeaOwner(project.getIdeaOwner());
		project1.setReleaseDate(project1.getReleaseDate());
		project1.setMentor(mentor);

		mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored() + 1);

		Project project2 = projectRepository.save(project1);

		return project2.getProjectId();

	}

	@Override
	public List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws InfyInternException {

		List<Mentor> list1 = mentorRepository.findByNumberOfProjectsMentored(numberOfProjectsMentored);

		if (list1.isEmpty()) {
			throw new InfyInternException("Service.MENTOR_NOT_FOUND");
		}

		List<MentorDTO> list2 = list1.stream()
				.map(x -> new MentorDTO(x.getMentorId(), x.getMentorName(), x.getNumberOfProjectsMentored()))
				.collect(Collectors.toList());

		return list2;
	}

	@Override
	public void updateProjectMentor(Integer projectId, Integer mentorId) throws InfyInternException {
		Optional<Mentor> optional = mentorRepository.findById(mentorId);

		Mentor mentor = optional.orElseThrow(() -> new InfyInternException("Service.MENTOR_NOT_FOUND"));

		if (mentor.getNumberOfProjectsMentored() >= 3) {
			throw new InfyInternException("Service.CANNOT_ALLOCATE_PROJECT");
		}

		Optional<Project> optional2 = projectRepository.findById(projectId);

		Project project = optional2.orElseThrow(() -> new InfyInternException("Service.PROJECT_NOT_FOUND"));

		project.setMentor(mentor);
		mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored() + 1);

	}

	@Override
	public void deleteProject(Integer projectId) throws InfyInternException {
		Optional<Project> optional = projectRepository.findById(projectId);

		Project project = optional.orElseThrow(() -> new InfyInternException("Service.PROJECT_NOT_FOUND"));

		if (project.getMentor() == null) {
			projectRepository.delete(project);
		} else {
			Mentor mentor = project.getMentor();
			mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored() - 1);
			project.setMentor(null);
		}
	}
}
