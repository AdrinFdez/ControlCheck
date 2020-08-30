
package acme.features.anonymous.bulletins.ruedaBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bulletins.RuedaBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/rueda-bulletin/")
public class AnonymousRuedaBulletinController extends AbstractController<Anonymous, RuedaBulletin> {

	@Autowired
	private AnonymousRuedaBulletinListService	listService;

	@Autowired
	private AnonymousRuedaBulletinCreateService	createService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
