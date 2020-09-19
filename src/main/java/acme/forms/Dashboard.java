
package acme.forms;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	private static final long	serialVersionUID	= 1L;

	Integer						totalNumberNotices;
	Integer						totalNumberTechnologyRecords;
	Integer						totalNumberToolRecords;

	//Cota inferior intervalo
	Double						minMoneyInquires1;
	Double						maxMoneyInquires1;
	Double						avgMoneyInquires1;
	Double						stdMoneyInquires1;

	//Cota superior intervalo
	Double						minMoneyInquires2;
	Double						maxMoneyInquires2;
	Double						avgMoneyInquires2;
	Double						stdMoneyInquires2;

	//Cota inferior intervalo
	Double						minMoneyOvertures1;
	Double						maxMoneyOvertures1;
	Double						avgMoneyOvertures1;
	Double						stdMoneyOvertures1;

	//Cota superior intervalo
	Double						minMoneyOvertures2;
	Double						maxMoneyOvertures2;
	Double						avgMoneyOvertures2;
	Double						stdMoneyOvertures2;

	//averages

	Double						avgInvestmentRoundPerEntrepreneur;
	Double						avgInvestmentRoundApplicationPerEntrepreneur;
	Double						avgInvestmentRoundApplicationPerInvestor;

	//Gráficos
	List<List<String>>			ratioOfTechnologiesPerSource;
	List<List<String>>			numberOfTechnologiesGroupedBySector;
	List<List<String>>			ratioOfToolsPerSource;
	List<List<String>>			numberOfToolsGroupedBySector;

	List<List<String>>			ratioOfInvestmentRoundByKind;
	List<List<String>>			ratioOfInvestmentRoundApplicationByStatus;

	//Time Series
	String[][]					numberOfApplicationsPendingPerDayFromLast15Days;
	String[][]					numberOfApplicationsAcceptedPerDayFromLast15Days;
	String[][]					numberOfApplicationsRejectedPerDayFromLast15Days;

	// Control Check
	List<List<String>>			ratioOfInvestmentRoundByYomp;
	List<List<String>>			ratioOfInvestmentRoundApplicationByLink;
	List<List<String>>			ratioOfInvestmentRoundApplicationByPassword;

}
