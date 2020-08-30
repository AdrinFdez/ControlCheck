
package acme.features.authenticated.discussionforumuser;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.discussionForums.AuthenticatedDiscussionForum;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/authenticated-discussion-forum/")
public class AuthenticatedDiscussionForumUserController extends AbstractController<Authenticated, AuthenticatedDiscussionForum> {

	@Autowired
	private AuthenticatedDiscussionForumUserAddService		addService;
	@Autowired
	private AuthenticatedDiscussionForumUserDeleteService	deleteService;


	// Constructors -----------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.ADD, BasicCommand.CREATE, this.addService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}
}
