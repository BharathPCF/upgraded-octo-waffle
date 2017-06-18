package com.bharath.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bharath.model.security.Authority;
import com.bharath.model.security.AuthorityName;
import com.bharath.model.security.User;
import com.bharath.repository.UserRepository;
import com.bharath.security.JwtTokenUtil;
import com.bharath.security.JwtUser;
import com.bharath.security.service.UserService;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class UserRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
	private UserService userService;
      
    @Autowired
    private UserDetailsService userDetailsService;

	private final UserRepository user;
    
	@Autowired
	public UserRestController(UserRepository clinicService) {
        this.user = clinicService;
    }
	
	@InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
    
	 @RequestMapping("/authDetails/{userName}")
	    public ModelAndView showUser(@PathVariable("userName") String userName) {
	        ModelAndView mav = new ModelAndView();
	        mav.addObject("user",this.user.findByUsername(userName));
	        mav.setViewName("authDetails");
	        return mav;
	    }
       
	 
	 @RequestMapping(value = "/authDetails/{userId}/edit", method = RequestMethod.GET)
	 public ModelAndView initUpdateOwnerForm(@PathVariable("userId") long userId, Model model,HttpSession session) {
	        User user = this.user.findById(userId);
	        String password  = user.getPassword();
	        session.setAttribute("password", password);
	        ModelAndView mav = new ModelAndView();
	        mav.addObject("user",user);
	        mav.setViewName("insureform");
	        return mav;
	    }
//	 public ModelAndView initUpdateUser(@PathVariable("Id") int Id, Model model) {
//		 ModelAndView mav = new ModelAndView();
//		   mav.addObject("user",this.user.findById(Id));
//	       mav.setViewName("insureform");
//	        return mav;
	    

	    @SuppressWarnings({ "unchecked", "rawtypes" })
		@RequestMapping(value = "/authDetails/{userId}/update", method = RequestMethod.POST)
	    public ModelAndView processUpdateUserForm(@Valid User user, BindingResult result, @PathVariable("userId") long userId,HttpSession session) {
			    
			    	
			    	ModelAndView modelAndView = new ModelAndView();
			    	user.setId(userId);
			    	String password = (String)session.getAttribute("password");   
			    	user.setPassword(password);
			    	ArrayList n = new ArrayList();
			    	Authority b = new Authority();
			    	b.setId(new Long(1));
			    	b.setName(AuthorityName.ROLE_ADMIN);
			    	n.add(b);
			    	
			    	user.setAuthorities(n);
						this.user.save(user);
						
						//modelAndView.addObject("successMessage", "User has been registered successfully");
						modelAndView.addObject("user",this.user.findById(userId));
						
						String url = "/index";
						modelAndView.setViewName(url);
						
					//}
					return modelAndView;
	    }
	 
    @RequestMapping(value = "user", method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }
    @RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
    
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
			
		}
		return modelAndView;
	}
}
