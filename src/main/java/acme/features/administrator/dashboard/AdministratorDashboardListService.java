
package acme.features.administrator.dashboard;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investmentRoundApplications.InvestmentRoundApplication;
import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorDashboardListService implements AbstractListService<Administrator, Dashboard> {

	@Autowired
	AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalNumberNotices", "totalNumberTechnologyRecords", "totalNumberToolRecords", "minMoneyInquires1", "maxMoneyInquires1", "avgMoneyInquires1", "stdMoneyInquires1", "minMoneyInquires2", "maxMoneyInquires2",
			"avgMoneyInquires2", "stdMoneyInquires2", "minMoneyOvertures1", "maxMoneyOvertures1", "avgMoneyOvertures1", "stdMoneyOvertures1", "minMoneyOvertures2", "maxMoneyOvertures2", "avgMoneyOvertures2", "stdMoneyOvertures2",
			"ratioOfTechnologiesPerSource", "numberOfTechnologiesGroupedBySector", "ratioOfToolsPerSource", "numberOfToolsGroupedBySector", "avgInvestmentRoundPerEntrepreneur", "avgInvestmentRoundApplicationPerEntrepreneur",
			"avgInvestmentRoundApplicationPerInvestor", "ratioOfInvestmentRoundByKind", "ratioOfInvestmentRoundApplicationByStatus", "numberOfApplicationsPendingPerDayFromLast15Days", "numberOfApplicationsAcceptedPerDayFromLast15Days",
			"numberOfApplicationsRejectedPerDayFromLast15Days", "ratioOfInvestmentRoundByYomp", "ratioOfInvestmentRoundApplicationByLink", "ratioOfInvestmentRoundApplicationByPassword");

	}

	@Override
	public Collection<Dashboard> findMany(final Request<Dashboard> request) {
		String idioma = request.getLocale().getLanguage();
		List<Dashboard> result = new ArrayList<>();
		Dashboard d = new Dashboard();

		Integer totalNumberOfNotices = this.repository.totalNumberOfNotices();
		Integer totalNumberOfTechnologyRecords = this.repository.totalNumberOfTechnologyRecords();
		Integer totalNumberOfToolRecords = this.repository.totalNumberOfToolRecords();

		d.setTotalNumberNotices(totalNumberOfNotices);
		d.setTotalNumberTechnologyRecords(totalNumberOfTechnologyRecords);
		d.setTotalNumberToolRecords(totalNumberOfToolRecords);

		Double[][] minMaxAvgStdFromInquires1 = this.repository.MinMaxAvgStdFromInquires1();
		Double[][] minMaxAvgStdFromInquires2 = this.repository.MinMaxAvgStdFromInquires2();

		d.setMinMoneyInquires1(minMaxAvgStdFromInquires1[0][0]);
		d.setMaxMoneyInquires1(minMaxAvgStdFromInquires1[0][1]);
		d.setAvgMoneyInquires1(minMaxAvgStdFromInquires1[0][2]);
		d.setStdMoneyInquires1(minMaxAvgStdFromInquires1[0][3]);

		d.setMinMoneyInquires2(minMaxAvgStdFromInquires2[0][0]);
		d.setMaxMoneyInquires2(minMaxAvgStdFromInquires2[0][1]);
		d.setAvgMoneyInquires2(minMaxAvgStdFromInquires2[0][2]);
		d.setStdMoneyInquires2(minMaxAvgStdFromInquires2[0][3]);

		Double[][] minMaxAvgStdFromOvertures1 = this.repository.MinMaxAvgStdFromOvertures1();
		Double[][] minMaxAvgStdFromOvertures2 = this.repository.MinMaxAvgStdFromOvertures2();

		d.setMinMoneyOvertures1(minMaxAvgStdFromOvertures1[0][0]);
		d.setMaxMoneyOvertures1(minMaxAvgStdFromOvertures1[0][1]);
		d.setAvgMoneyOvertures1(minMaxAvgStdFromOvertures1[0][2]);
		d.setStdMoneyOvertures1(minMaxAvgStdFromOvertures1[0][3]);

		d.setMinMoneyOvertures2(minMaxAvgStdFromOvertures2[0][0]);
		d.setMaxMoneyOvertures2(minMaxAvgStdFromOvertures2[0][1]);
		d.setAvgMoneyOvertures2(minMaxAvgStdFromOvertures2[0][2]);
		d.setStdMoneyOvertures2(minMaxAvgStdFromOvertures2[0][3]);

		List<List<String>> technologiesPerSource = this.repository.getRatioOfTechnologiesPerSource();
		List<List<String>> technologiesBySector = this.repository.getNumberOfTechnologiesBySector();

		List<List<String>> toolsPerSource = this.repository.getRatioOfToolsPerSource();
		List<List<String>> toolsBySector = this.repository.getNumberOfToolsBySector();

		d.setRatioOfTechnologiesPerSource(technologiesPerSource);
		d.setNumberOfTechnologiesGroupedBySector(technologiesBySector);
		d.setRatioOfToolsPerSource(toolsPerSource);
		d.setNumberOfToolsGroupedBySector(toolsBySector);

		Double avgInvestmentRoundPerEntrepreneur = this.repository.avgInvestmentRoundPerEntrepreneur();
		Double avgInvestmentRoundApplicationPerEntrepreneur = this.repository.avgInvestmentRoundApplicationPerEntrepreneur();
		Double avgInvestmentRoundApplicationPerInvestor = this.repository.avgInvestmentRoundApplicationPerInvestor();

		List<List<String>> ratioOfInvestmentRoundByKind = this.repository.getRatioOfInvestmentRoundByKind();
		List<List<String>> ratioOfInvestmentRoundApplicationByStatus = this.repository.getRatioOfInvestmentRoundApplicationByStatus();

		d.setAvgInvestmentRoundPerEntrepreneur(avgInvestmentRoundPerEntrepreneur);
		d.setAvgInvestmentRoundApplicationPerEntrepreneur(avgInvestmentRoundApplicationPerEntrepreneur);
		d.setAvgInvestmentRoundApplicationPerInvestor(avgInvestmentRoundApplicationPerInvestor);

		d.setRatioOfInvestmentRoundByKind(ratioOfInvestmentRoundByKind);
		d.setRatioOfInvestmentRoundApplicationByStatus(ratioOfInvestmentRoundApplicationByStatus);

		//ACCEPTED
		Calendar ldt1 = Calendar.getInstance(TimeZone.getDefault());
		ldt1.add(Calendar.DAY_OF_MONTH, -15);
		Date queryDate = ldt1.getTime();
		Calendar today = Calendar.getInstance(TimeZone.getDefault());
		List<InvestmentRoundApplication> listAccepted = this.repository.findAccepted(queryDate);
		String[][] datosAccepted = new String[15][2];
		Integer iA = 0;
		String fecha1 = "";
		while (ldt1.before(today)) {
			Calendar temp = Calendar.getInstance(TimeZone.getDefault());
			Integer cont = 0;
			for (InvestmentRoundApplication appl : listAccepted) {
				temp.setTime(appl.getUpdateMoment());
				if (temp.get(Calendar.DAY_OF_MONTH) == ldt1.get(Calendar.DAY_OF_MONTH) && temp.get(Calendar.MONTH) == ldt1.get(Calendar.MONTH) && temp.get(Calendar.YEAR) == ldt1.get(Calendar.YEAR)) {
					cont++;
				}
			}
			datosAccepted[iA][0] = cont.toString();
			if (idioma.equals("en")) {
				fecha1 = Integer.toString(ldt1.get(Calendar.YEAR)) + "/" + Integer.toString(ldt1.get(Calendar.MONTH) + 1) + "/" + Integer.toString(ldt1.get(Calendar.DAY_OF_MONTH));
			} else {
				fecha1 = Integer.toString(ldt1.get(Calendar.DAY_OF_MONTH)) + "/" + Integer.toString(ldt1.get(Calendar.MONTH) + 1) + "/" + Integer.toString(ldt1.get(Calendar.YEAR));
			}
			datosAccepted[iA][1] = fecha1;
			ldt1.add(Calendar.DAY_OF_MONTH, 1);
			iA++;
		}
		d.setNumberOfApplicationsAcceptedPerDayFromLast15Days(datosAccepted);

		//REJECTED
		Calendar ldt2 = Calendar.getInstance(TimeZone.getDefault());
		ldt2.add(Calendar.DAY_OF_MONTH, -15);
		List<InvestmentRoundApplication> listRejected = this.repository.findRejected(queryDate);
		String[][] datosRejected = new String[15][2];
		Integer iR = 0;
		String fecha2 = "";
		while (ldt2.before(today)) {
			Calendar temp = Calendar.getInstance(TimeZone.getDefault());
			Integer cont = 0;
			for (InvestmentRoundApplication appl : listRejected) {
				temp.setTime(appl.getUpdateMoment());
				if (temp.get(Calendar.DAY_OF_MONTH) == ldt2.get(Calendar.DAY_OF_MONTH) && temp.get(Calendar.MONTH) == ldt2.get(Calendar.MONTH) && temp.get(Calendar.YEAR) == ldt2.get(Calendar.YEAR)) {
					cont++;
				}
			}
			datosRejected[iR][0] = cont.toString();
			if (idioma.equals("en")) {
				fecha2 = Integer.toString(ldt2.get(Calendar.YEAR)) + "/" + Integer.toString(ldt2.get(Calendar.MONTH) + 1) + "/" + Integer.toString(ldt2.get(Calendar.DAY_OF_MONTH));
			} else {
				fecha2 = Integer.toString(ldt2.get(Calendar.DAY_OF_MONTH)) + "/" + Integer.toString(ldt2.get(Calendar.MONTH) + 1) + "/" + Integer.toString(ldt2.get(Calendar.YEAR));
			}
			datosRejected[iR][1] = fecha2;
			ldt2.add(Calendar.DAY_OF_MONTH, 1);
			iR++;
		}
		d.setNumberOfApplicationsRejectedPerDayFromLast15Days(datosRejected);

		//PENDING
		Calendar ldt3 = Calendar.getInstance(TimeZone.getDefault());
		ldt3.add(Calendar.DAY_OF_MONTH, -15);
		List<InvestmentRoundApplication> listPending = this.repository.findPending();
		String[][] datosPending = new String[15][2];
		Integer iP = 0;
		String fecha3 = "";
		while (ldt3.before(today)) {
			Integer cont = 0;
			for (InvestmentRoundApplication appl : listPending) {
				if (appl.getCreationMoment().before(ldt3.getTime())) {
					if (appl.getUpdateMoment() == null || appl.getUpdateMoment().after(ldt3.getTime())) {
						cont++;
					}
				}
			}
			datosPending[iP][0] = cont.toString();
			if (idioma.equals("en")) {
				fecha3 = Integer.toString(ldt3.get(Calendar.YEAR)) + "/" + Integer.toString(ldt3.get(Calendar.MONTH) + 1) + "/" + Integer.toString(ldt3.get(Calendar.DAY_OF_MONTH));
			} else {
				fecha3 = Integer.toString(ldt3.get(Calendar.DAY_OF_MONTH)) + "/" + Integer.toString(ldt3.get(Calendar.MONTH) + 1) + "/" + Integer.toString(ldt3.get(Calendar.YEAR));
			}
			datosPending[iP][1] = fecha3;
			ldt3.add(Calendar.DAY_OF_MONTH, 1);
			iP++;
		}
		d.setNumberOfApplicationsPendingPerDayFromLast15Days(datosPending);

		// Control Check

		List<List<String>> ratioOfInvestmentRoundByYomp = new ArrayList<List<String>>();
		List<String> list0Yomp = new ArrayList<String>();
		list0Yomp.add("Yes");
		list0Yomp.add(String.valueOf(this.repository.getRatioOfInvestmentRoundByYomp()));
		ratioOfInvestmentRoundByYomp.add(list0Yomp);
		List<String> list1Yomp = new ArrayList<String>();
		list1Yomp.add("No");
		list1Yomp.add(String.valueOf(100.0 - this.repository.getRatioOfInvestmentRoundByYomp()));
		ratioOfInvestmentRoundByYomp.add(list1Yomp);

		List<List<String>> ratioOfInvestmentRoundApplicationByLink = new ArrayList<List<String>>();
		List<String> list0Link = new ArrayList<String>();
		list0Link.add("Yes");
		list0Link.add(String.valueOf(this.repository.getRatioOfInvestmentRoundApplicationByLink()));
		ratioOfInvestmentRoundApplicationByLink.add(list0Link);
		List<String> list1Link = new ArrayList<String>();
		list1Link.add("No");
		list1Link.add(String.valueOf(100.0 - this.repository.getRatioOfInvestmentRoundApplicationByLink()));
		ratioOfInvestmentRoundApplicationByLink.add(list1Link);

		List<List<String>> ratioOfInvestmentRoundApplicationByPassword = new ArrayList<List<String>>();
		List<String> list0Pass = new ArrayList<String>();
		list0Pass.add("Yes");
		list0Pass.add(String.valueOf(this.repository.getRatioOfInvestmentRoundApplicationByPassword()));
		ratioOfInvestmentRoundApplicationByPassword.add(list0Pass);
		List<String> list1Pass = new ArrayList<String>();
		list1Pass.add("No");
		list1Pass.add(String.valueOf(100.0 - this.repository.getRatioOfInvestmentRoundApplicationByPassword()));
		ratioOfInvestmentRoundApplicationByPassword.add(list1Pass);

		d.setRatioOfInvestmentRoundByYomp(ratioOfInvestmentRoundByYomp);
		d.setRatioOfInvestmentRoundApplicationByLink(ratioOfInvestmentRoundApplicationByLink);
		d.setRatioOfInvestmentRoundApplicationByPassword(ratioOfInvestmentRoundApplicationByPassword);

		result.add(d);

		return result;
	}
}
