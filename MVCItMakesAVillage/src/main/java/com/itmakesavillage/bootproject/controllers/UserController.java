package com.itmakesavillage.bootproject.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itmakesavillage.bootproject.data.ProjectDAO;
import com.itmakesavillage.bootproject.data.ProjectVolunteerDAO;
import com.itmakesavillage.bootproject.data.UserDAO;
import com.itmakesavillage.bootproject.data.VolunteerDAO;
import com.itmakesavillage.jpaproject.entities.Address;
import com.itmakesavillage.jpaproject.entities.ItemsCommitted;
import com.itmakesavillage.jpaproject.entities.ItemsNeeded;
import com.itmakesavillage.jpaproject.entities.Project;
import com.itmakesavillage.jpaproject.entities.ProjectVolunteer;
import com.itmakesavillage.jpaproject.entities.State;
import com.itmakesavillage.jpaproject.entities.User;
import com.itmakesavillage.jpaproject.entities.Volunteer;

@Controller
public class UserController {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private VolunteerDAO volunteerDAO;
	@Autowired
	private ProjectVolunteerDAO pvDAO;
	@Autowired
	private ProjectDAO projectDAO;

	@RequestMapping(path = "removeVolunteer.do", method = RequestMethod.POST)
	public String removeVolunteer(Model model, Integer userId, Integer projectId, HttpSession session,
			RedirectAttributes redir) {
		User user = userDAO.findUser(userId);
		Project project = projectDAO.findProject(projectId);
		ProjectVolunteer pv = pvDAO.findPV(projectId, userId);
		pvDAO.deleteProject(pv, project);
//		List<ItemsCommitted> committedList = pv.getItemsCommitted();
//		pv.setCompany(null);
//		System.out.println(committedList);
//		pv = pvDAO.deleteallItemsforPV(pv);
//		pv.setItemsCommitted(new ArrayList<>());
//		System.out.println(pv.getItemsCommitted());
//		user.getVolunteer().removeProject(project);
//		System.out.println(userId);
//		System.out.println(projectId);
//		
//		volunteerDAO.updateVolunteer(user.getId(), user.getVolunteer());
		redir.addAttribute("projectId", project.getId());
		
		return "redirect:viewProject.do";
	}

	@RequestMapping(path = "addVolunteer.do", method = RequestMethod.POST)
	public String addVolunteer(Model model, Integer hours, Integer userId, Project project, RedirectAttributes redir) {
		User user = userDAO.findUser(userId);
		ProjectVolunteer pv = pvDAO.findPV(project.getId(), userId);
		pv.setHoursPledged(hours);
		pv = pvDAO.updatePV(pv);
		user.getVolunteer().addProject(project);
		user = userDAO.updateUser(user.getId(), user);
		redir.addAttribute("projectId", project.getId());

		return "redirect:viewProject.do";
	}

	@RequestMapping(path = "submitHours.do", method = RequestMethod.POST)
	public String submitHours(Integer userId, Integer hours, Integer projectId, Model model, RedirectAttributes redir) {
		List<ProjectVolunteer> pv = new ArrayList<>();
		Project project = projectDAO.findProject(projectId);
		pv.addAll(project.getProjectVolunteer());
		for (ProjectVolunteer projectVolunteer : pv) {
			if (projectVolunteer.getVolunteer().getUserid() == userId) {

				projectVolunteer.setHoursActual(hours);
				projectVolunteer = pvDAO.updatePV(projectVolunteer);
				redir.addAttribute("projectId", projectId);
				return "redirect:viewProject.do";
			}
		}

		redir.addAttribute("projectId", projectId);
		return "redirect:viewProject.do";

	}

	@RequestMapping(path = "createAccount.do", method = RequestMethod.POST)
	public String createAccount(HttpSession session, User user, RedirectAttributes redir) {
		List<User> users = userDAO.getAllUser();
		List<String> emails = new ArrayList<>();
		boolean emailInUse = false;
		boolean usernameInUse = false;
		for (User user2 : users) {
			emails.add(user2.getEmail());
		}
		if (emails.contains(user.getEmail())) {
			emailInUse = true;
			redir.addFlashAttribute("emailInUse", emailInUse);
			return "redirect:createAccount.do";
		}
		List<String> usernames = new ArrayList<>();
		for (User user2 : users) {
			usernames.add(user2.getUserName());
		}
		if (usernames.contains(user.getUserName())) {
			usernameInUse = true;
			redir.addFlashAttribute("usernameInUse", usernameInUse);
			return "redirect:createAccount.do";
		}

		user.setActive(true);
		user = userDAO.createUser(user);
		session.setAttribute("user", user);
		System.out.println(user);
		return "redirect:createProfile.do";
	}

	@RequestMapping(path = "createAccount.do", method = RequestMethod.GET)
	public String goToCreateAccount() {

		return "createAccount";
	}

	@RequestMapping(path = "editAccount.do", method = RequestMethod.POST)
	public String editAccount(HttpSession session, User user) {
		System.out.println(user);

		user = userDAO.updateUser(user.getId(), user);
		session.setAttribute("user", user);

		return "redirect:account.do";
	}

	@RequestMapping(path = "createProfile.do", method = RequestMethod.POST)
	public String createProfile(Volunteer volunteer, Integer stateId, Address address, HttpSession session,
			@RequestParam("dob") String[] dob) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			State state = projectDAO.getStateById(stateId);
			address = projectDAO.createAddress(address);
			address.setState(state);
			volunteer.setAddress(address);
			volunteer.setDob(dob[0]);
			volunteer.setUser(user);
			volunteer = volunteerDAO.createVolunteer(volunteer);
			user = userDAO.findUser(volunteer.getUserid());
			session.setAttribute("user", user);
		}
		return "redirect:account.do";
	}

	@RequestMapping(path = "createProfile.do", method = RequestMethod.GET)
	public String goToCreateProfile(Model model) {
		model.addAttribute("stateList", projectDAO.getAllStates());
		return "createProfile";
	}

	@RequestMapping(path = "adminFindUser.do", method = RequestMethod.GET)
	public String adminFindUser(HttpSession session, Model model, String keyword) {
		User admin = (User) session.getAttribute("user");
		if (!admin.getRole().equals("admin")) {
			return "index";
		}

		Set<Volunteer> volunteerList = volunteerDAO.searchVolunteer(keyword);
		Set<User> userList = userDAO.searchUser(keyword);
		for (Volunteer volunteer : volunteerList) {
			userList.add(volunteer.getUser());
		}
		boolean notFound = false;
		if (userList.isEmpty()) {
			notFound = true;
			model.addAttribute("notFound", notFound);
			return "admin";
		}
		model.addAttribute("userList", userList);
		return "admin";
	}

	@RequestMapping(path = "adminEditProfile.do", method = RequestMethod.GET)
	public String adminEditProfile(HttpSession session, Model model, int id) {
		User admin = (User) session.getAttribute("user");
		if (!admin.getRole().equals("admin")) {
			return "index";
		}
		model.addAttribute("stateList", projectDAO.getAllStates());
		User user = userDAO.findUser(id);
		model.addAttribute(user);
		return "adminEditProfile";
	}

	@RequestMapping(path = "adminEditProfile.do", method = RequestMethod.POST)
	public String adminEditProfile(@RequestParam(name = "dob") String[] dob, Integer stateId, Address address,
			HttpSession session, Model model, User user, Volunteer volunteer) {
		System.out.println(user);
		User admin = (User) session.getAttribute("user");
		if (!admin.getRole().equals("admin")) {
			return "index";
		}
		State state = projectDAO.getStateById(stateId);

		address = projectDAO.createAddress(address);
		address.setState(state);
		volunteer.setAddress(address);
		volunteer.setDob(dob[0]);
		Volunteer vol = volunteerDAO.updateVolunteer(volunteer.getUserid(), volunteer);
		user.setVolunteer(vol);
		userDAO.updateUser(user.getId(), user);
		model.addAttribute("user", user);
		return "redirect:admin.do";
	}

	@RequestMapping(path = "editProfile.do", method = RequestMethod.POST)
	public String editProfile(@RequestParam(name = "date") String[] dob, Address address, Integer stateId,
			Volunteer volunteer, HttpSession session) {
		System.out.println(dob[0]);
		User user = (User) session.getAttribute("user");
		System.out.println(volunteer);
		address = projectDAO.createAddress(address);
		State state = projectDAO.getStateById(stateId);
		address.setState(state);
		volunteer.setAddress(address);
		volunteer.setDob(dob[0]);
		volunteer = volunteerDAO.updateVolunteer(volunteer.getUserid(), volunteer);
		user = userDAO.findUser(user.getId());
		session.setAttribute("user", user);
		System.out.println(user);
		return "redirect:account.do";

	}

	@RequestMapping(path = "editProfile.do", method = RequestMethod.GET)
	public String goToEditProfile(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return "index";
		}
		model.addAttribute("stateList", projectDAO.getAllStates());
		return "editProfile";
	}

	@RequestMapping(path = "reactivate.do", method = RequestMethod.POST)
	public String reactivateAccount(Integer id, HttpSession session) {
		User user = userDAO.findUser(id);
		user.setActive(true);
		user = userDAO.updateUser(id, user);
		session.setAttribute("user", user);
		return "redirect:account.do";
	}

	@RequestMapping(path = "deactivate.do", method = RequestMethod.POST)
	public String deactivateAccount(Integer id, HttpSession session) {
		User user = userDAO.findUser(id);
		user.setActive(false);
		user = userDAO.updateUser(id, user);
		user = null;
		session.setAttribute("user", user);
		return "redirect:home.do";
	}

	@RequestMapping(path = "adminReactivate.do", method = RequestMethod.POST)
	public String adminReactivateAccount(Integer id, HttpSession session) {
		User admin = (User) session.getAttribute("user");
		if (!admin.getRole().equals("admin")) {
			return "index";
		}
		User user = userDAO.findUser(id);
		user.setActive(true);
		user = userDAO.updateUser(id, user);
		return "redirect:admin.do";
	}

	@RequestMapping(path = "adminDeactivate.do", method = RequestMethod.POST)
	public String adminDeactivateAccount(Integer id, HttpSession session) {
		User admin = (User) session.getAttribute("user");
		if (!admin.getRole().equals("admin")) {
			return "index";
		}
		User user = userDAO.findUser(id);
		user.setActive(false);
		user = userDAO.updateUser(id, user);
		user = null;
		return "redirect:admin.do";
	}

	@RequestMapping(path = "viewProfile.do", method = RequestMethod.POST)
	public String viewUserProfil(Integer viewId, Model model) {

		model.addAttribute("userView", userDAO.findUser(viewId));

		return "viewProfile";
	}

	@RequestMapping(path = "updateItemsCommitted.do", method = RequestMethod.POST)
	public String updateItemsCommitted(Integer committedId, Integer itemQuantity, Integer projectId, Model model,
			RedirectAttributes redir) {

		ItemsCommitted itemsCommitted = pvDAO.findItemsCommittedById(committedId);
		ProjectVolunteer pv = itemsCommitted.getProjectVolunteer();
//		itemsCommitted.setQuantity(itemQuantity);

		Project project = pv.getProject();
		List<ItemsNeeded> neededList = project.getItemsNeeded();
		ItemsNeeded itemNeeded = null;
		for (ItemsNeeded needed : neededList) {
			if (needed.getItem().equals(itemsCommitted.getItem())) {
				itemNeeded = needed;
				break;
			}
		}
//    	ItemsCommitted originalCommittedItem = null;
//    	for(ItemsCommitted item : pv.getItemsCommitted()) {
//    		if(item.getItem().equals(itemNeeded.getItem())) {
//    			originalCommittedItem = item;
//    		}
//    	}
		if (itemsCommitted.getQuantity() > itemQuantity) {
			int quantity = itemsCommitted.getQuantity() - itemQuantity;
			itemNeeded.setQuantity(quantity + itemNeeded.getQuantity());
		}
		if (itemsCommitted.getQuantity() < itemQuantity) {
			int quantity = itemQuantity - itemsCommitted.getQuantity();
			itemNeeded.setQuantity(itemNeeded.getQuantity() - quantity);
			if (itemNeeded.getQuantity() < 0) {
				itemNeeded.setQuantity(0);
			}
		}

		project.removeItemsNeeded(itemNeeded);
		project.addItemsNeeded(itemNeeded);

		itemsCommitted.setQuantity(itemQuantity);
		// calls .equals which is only set to the id
		// this should update any changes to the item
		pv.removeItemsCommitted(itemsCommitted);
		pv.addItemsCommitted(itemsCommitted);
		System.out.println("****" + itemQuantity);
		System.out.println("****" + pv);
		if (itemQuantity <= 0) {
			pv.removeItemsCommitted(itemsCommitted);
			pvDAO.deleteItemsCommitted(itemsCommitted);

		}
		System.out.println("****" + pv);

		pv = pvDAO.updatePV(pv);
		project = projectDAO.updateProject(project.getId(), project);

		redir.addAttribute("projectId", project.getId());

		return "redirect:viewProject.do";
	}

	@RequestMapping(path = "addItemsCommitted.do", method = RequestMethod.POST)
	public String addItemsCommitted(Integer itemId, Integer itemQuantity, Integer projectId, Integer pvId, Model model,
			RedirectAttributes redir) {

		Project project = projectDAO.findProject(projectId);
		ProjectVolunteer pv = pvDAO.findPVById(pvId);
		ItemsCommitted itemC = new ItemsCommitted();
		itemC.setItem(projectDAO.getItem(itemId));
		itemC.setProjectVolunteer(pv);
		itemC.setQuantity(itemQuantity);
		for (ItemsCommitted itemsCommitted : pv.getItemsCommitted()) {
			if (itemsCommitted.getItem().equals(itemC.getItem())) {
				redir.addAttribute("projectId", project.getId());

				return "redirect:viewProject.do";
			}
		}
		itemC = pvDAO.createItemsCommitted(itemC);

		ItemsNeeded itemN = new ItemsNeeded();
		for (ItemsNeeded itemNeeded : project.getItemsNeeded()) {
			if (itemNeeded.getItem().equals(itemC.getItem())) {
				itemN = itemNeeded;
				break;
			}
		}

		itemN.setQuantity(itemN.getQuantity() - itemQuantity);

		if (itemN.getQuantity() < 0) {
			itemN.setQuantity(0);
		}

		project.removeItemsNeeded(itemN);
		project.addItemsNeeded(itemN);

		pv.removeItemsCommitted(itemC);
		pv.addItemsCommitted(itemC);

		project = projectDAO.updateProject(project.getId(), project);
		pv = pvDAO.updatePV(pv);

		redir.addAttribute("projectId", project.getId());

		return "redirect:viewProject.do";
	}
}
