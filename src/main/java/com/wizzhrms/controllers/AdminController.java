package com.wizzhrms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.wizzhrms.dto.CommonResponseDto;
import com.wizzhrms.dto.EmployeeDto;
import com.wizzhrms.dto.OrganizationalDetailsDto;
import com.wizzhrms.dto.OrganizationalEventsDto;
import com.wizzhrms.dto.ProjectsDto;
import com.wizzhrms.dto.RolesDto;
import com.wizzhrms.entity.OrganizationDetails;
import com.wizzhrms.entity.OrganizationalEvents;
import com.wizzhrms.entity.Projects;
import com.wizzhrms.entity.Roles;
import com.wizzhrms.service.OrganizationDetailsService;
import com.wizzhrms.service.OrganizationEventService;
import com.wizzhrms.service.ProjectService;
import com.wizzhrms.service.RolesService;
import com.wizzhrms.service.constants.Constants;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("admin")
@Slf4j
public class AdminController {

	@Autowired
	OrganizationDetailsService orgDtlsService;

	@Autowired
	OrganizationEventService orgEventsService;

	@Autowired
	RolesService rolesService;
	
	@Autowired
	ProjectService projectService;

	@GetMapping("/organizationdetails")
	public ModelAndView getOrganizationDetails() {

		ModelAndView mv = orgDtlsService.findById(Constants.ORG_DETAILS_ID);
		return mv;

	}

	@SuppressWarnings("unused")
	@PostMapping(value = "/addOrganizationalDetails", consumes = {
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = "application/json")
	public ModelAndView addOrganizationDetails(@ModelAttribute OrganizationalDetailsDto orgDto,
			@RequestParam("logo") MultipartFile files) {

		log.info("Form Data {} ", orgDto);
		OrganizationDetails saveOrganizationDetails = orgDtlsService.saveOrganizationDetails(orgDto, files);
		ModelAndView mv = new ModelAndView("redirect:/admin/organizationdetails");
		return mv;
	}

	@GetMapping("/organizationalEvents")
	public ModelAndView getOrganizationEvents() {

		ModelAndView mv = new ModelAndView("organizational_events.html");
		return mv;

	}

	@GetMapping(value = "/organizationalEventsData")
	public ResponseEntity<List<OrganizationalEventsDto>> getOrganizationEventsData() {

		return ResponseEntity.status(HttpStatus.OK).body(orgEventsService.getOrganizationalEventsData());

	}

	@PostMapping(value = "/addOrganizationalEvents", consumes = {
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = "application/json")
	public ResponseEntity<CommonResponseDto> addOrganizationEvents(@ModelAttribute OrganizationalEventsDto orgEventsDto,
			@RequestParam("inviteCard") MultipartFile files) {

		OrganizationalEvents saveEvent = orgEventsService.saveEvent(orgEventsDto, files);
		if (saveEvent.getId() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(new CommonResponseDto("Event Created"));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new CommonResponseDto("Error Creating Event"));
		}

	}

	@GetMapping("/organizationEmployees")
	public ModelAndView organizationEmployees() {

		ModelAndView mv = new ModelAndView("employees.html");
		return mv;

	}
	
	@PostMapping("/addUpdEmployee")
	public void addUpdEmployee(@ModelAttribute EmployeeDto empDto) {
		
		
		
	}

	@GetMapping("/rolesAndOther")
	public ModelAndView rolesAndOther() {

		ModelAndView mv = new ModelAndView("rolesAndOther.html");
		return mv;

	}

	@PostMapping("/addUpdRole")
	public ResponseEntity<Roles> addUpdRole(@RequestBody RolesDto rolesDto) {

		return ResponseEntity.status(HttpStatus.OK).body(rolesService.addUpdRoles(rolesDto));

	}
	
	@GetMapping("/getRoles")
	public ResponseEntity<List<RolesDto>> addUpdRole() {

		return ResponseEntity.status(HttpStatus.OK).body(rolesService.getRoles());

	}
	
	@PostMapping("/addUpdProject")
	public ResponseEntity<Projects> addUpdProject(@RequestBody ProjectsDto projectDto) {

		return ResponseEntity.status(HttpStatus.OK).body(projectService.addUpdProject(projectDto));

	}
	
	@GetMapping("/getProjects")
	public ResponseEntity<List<ProjectsDto>> getProjects() {

		return ResponseEntity.status(HttpStatus.OK).body(projectService.getProjects());

	}

}
