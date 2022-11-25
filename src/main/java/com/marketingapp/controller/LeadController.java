package com.marketingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import com.marketingapp.dto.LeadData;
import com.marketingapp.entities.Lead;
import com.marketingapp.service.LeadService;
import com.marketingapp.util.EmailService;

@Controller
public class LeadController {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private LeadService leadService;
	
	//Handler Methods
	@RequestMapping("/viewLeadPage")
	public String ViewcreateLeadPage() {
		return "create_lead";
	}
	
//  first approach
	@RequestMapping("/saveLead")
	public String saveLead(@ModelAttribute("lead") Lead l, ModelMap model) {
		leadService.saveOneLead(l);
		emailService.sendsimpleMail(l.getEmail(),"test","Test Email");
		model.addAttribute("msg","Lead is saved!!");
		return "create_lead";
	}
	
	//second approach
//	@RequestMapping("/saveLead")
//	public String saveLead(@RequestParam("firstName") String fname,@RequestParam("lastName") String lname,@RequestParam("email")String email,@RequestParam("mobile")String mobile){
//		Lead lead=new Lead();
//		lead.setFirstName(fname);
//		lead.setLastName(lname);
//		lead.setEmail(email);
//		lead.setMobile(mobile);
//		leadService.saveOneLead(lead);
//			return "create_lead";
			
//
//		//third approach
// 	    @RequestMapping("/saveLead")
//		public String saveLead(LeadData data) {
// 	    	Lead l=new Lead();
// 	    	l.setFirstName(data.getFirstName());
// 	    	l.setLastName(data.getLastName());
// 	    	l.setEmail(data.getEmail());
// 	    	l.setMobile(data.getMobile());
// 	    	leadService.saveOneLead(l);
//			return "create_lead";
//		}
	
	@RequestMapping("/listAll")
	public String listAllLeads(ModelMap model) {
		List<Lead> leads= leadService.listAllLeads();
		model.addAttribute("leads",leads);
		return "list_leads";		
	}
	
	
	@RequestMapping("/deleteLead")
	public String deleteLead(@RequestParam("id") long id,ModelMap model) {
		leadService.deleteOneLead(id);
		List<Lead> leads= leadService.listAllLeads();
		model.addAttribute("leads",leads);
		return "list_leads";	
	}	
	
	@RequestMapping("/updateLead")
	public String updateLead(@RequestParam("id") long id, Model model) {
		Lead lead = leadService.getById(id);
		model.addAttribute("lead",lead);
		return "update_lead";	
	}
	
	@RequestMapping("/updateOneLead")
	public String updateOneLead(@ModelAttribute("lead") Lead l, ModelMap model) {
		leadService.saveOneLead(l);
		List<Lead> leads=leadService.listAllLeads();
		model.addAttribute("leads",leads);
		return "list_leads";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
