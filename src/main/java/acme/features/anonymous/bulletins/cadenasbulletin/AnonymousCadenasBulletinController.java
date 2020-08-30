
package acme.features.anonymous.bulletins.cadenasbulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bulletins.CadenasBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/cadenas-bulletin/")
public class AnonymousCadenasBulletinController extends AbstractController<Anonymous, CadenasBulletin> {

	// Internal state -------------------------------------------------------

	@Autowired
	private AnonymousCadenasBulletinListService		listService;

	@Autowired
	private AnonymousCadenasBulletinCreateService	createService;


	// Constructors ---------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
