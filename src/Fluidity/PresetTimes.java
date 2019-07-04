package Fluidity;

public enum PresetTimes {
	aC			(1, 6, "AC", 		"LD"),
	affCX		(2, 3, "CX", 		"LD"),
	negPrep		(3, 4, "Neg Prep", 	"LD"),
	nC			(4, 7, "NC", 		"LD"),
	negCx		(5, 3, "CX", 		"LD"), 
	affPrep		(6, 4, "Aff Prep", 	"LD"),
	oneAR		(7, 4, "AR", 		"LD"),
	negPrepLeft	(8, 0, "Neg Prep", 	"LD"),
	nR			(9, 6, "2NR", 		"LD"),
	affPrepLeft	(10, 0, "Aff Prep", "LD"),
	twoAR		(11, 3, "2AR", 		"LD");
	
/*	oneAC		(1, 8, "1AC", 		"Policy"),
	firstAffCX	(2, 3, "CX", 		"Policy"),
	firstNegPrep(3, 10, "Neg Prep", "Policy"),
	oneNC		(4, 8, "1NC", 		"Policy"),
	firstNegCx	(5, 3, "CX", 		"Policy"),
	firstAffPrep(6, 10, "Aff Prep", "Policy"),
	secAC		(7, 8, "2AC", 		"Policy"),
	secAffCX	(8, 3, "CX", 		"Policy"),
	secNegPrep	(9, 0, "Neg Prep", 	"Policy"),
	secNC		(10, 8, "2NC", 		"Policy"),
	secNegCX	(11, 3, "CX",	 	"Policy"),
	firstNR		(12, 5, "1NR",		"Policy"),
	secAffPrep	(13, 0, "Aff Prep", "Policy"),
	firstAR		(14, 5, "1AR",		"Policy"),
	secNR		(15, 5, "2NR",		"Policy"),
	secAR		(16, 5, "2AR", 		"Policy"),
	
	firstPro	(1, 4, "1st Pro", "Pofo"),
	firstCon	(2, 4, "1st Con", "Pofo"),
	crossFire	(2, 3, "CF",	  "Pofo"),
	secPro		(4, 4, "2nd Pro", "Pofo"),
	secCon		(5, 4, "2nd Con", "Pofo"), 
	secCrossFire(6, 3, "CF",	  "Pofo"),
	firstSum	(7, 2, "Pro Sum", "Pofo"),
	secSum		(8, 2, "Neg Sum", "Pofo"),
	grandCF		(9, 6, "CF", 	  "Pofo"),
	firstFocus	(10, 0, "1st FF", "Pofo"),
	secFocus	(11, 3, "2nd FF", "Pofo");	*/

	public static 	String basePrep = "4:00",
					affPrepTracker 	= basePrep, 
					negPrepTracker 	= basePrep,
					userSetTime 	= "6:00";
	public static PresetTimes currentSpeech = aC;
	
	private int order;
	private int numMinutes;
	private String name;
//	private String event;
	
	private PresetTimes (int order, int numMinutes, String name, String event){
		this.order = order;
		this.numMinutes = numMinutes;
		this.name = name;
//		this.event = event;
	}

	public static void storePrepTime(){
		if (PresetTimes.isItAffPrepTime()) {
			PresetTimes.affPrepTracker = TimerModel.getTime();
			System.out.println("storing aff prep which is " + affPrepTracker);
		}
		else if (PresetTimes.isItNegPrepTime()) {
			PresetTimes.negPrepTracker = TimerModel.getTime();
			System.out.println("storing neg prep which is " +negPrepTracker);	
		}
	}
	
	public static void setCurPresetTimeTrue(PresetTimes s) {
		PresetTimes.currentSpeech = s;
	}

	public static PresetTimes getCurSpeech(){
		for (PresetTimes speech : PresetTimes.values()) {
			if (speech.checkIfCurUsed(speech) == true)
				return speech;
		}
		return null;
	}

	private int getOrder() {
		return order;
	}
	public int getNumMinutes(){
		return numMinutes;
	}
	public String getName() {
		return name;
	}
	private boolean checkIfCurUsed(PresetTimes pt) {
		if (currentSpeech == pt)
			return true;
		else
			return false;
	}
	public static boolean anySpeechIsOn() {
		if (getCurSpeech() == null)
			return false;
		else
			return true;
	}

	public static boolean isItPrepTime() {
		return isItAffPrepTime() || isItNegPrepTime();
	}

	public static boolean isItAffPrepTime() {
		PresetTimes s = getCurSpeech();
		if (s == null)
			return false;
		else if (s.equals(affPrep) || s.equals(affPrepLeft))
			return true;
		else
			return false;
	}

	public static boolean isItNegPrepTime() {
		PresetTimes s = getCurSpeech();
		if (s == null)
			return false;
		else if (s.equals(negPrep) || s.equals(negPrepLeft))
			return true;
		else
			return false;
	}

	public static void switchToNextPresetTime(){
		int curSpeechNum = getCurSpeech().getOrder(); 
		for (PresetTimes speech : PresetTimes.values()) {
			if (speech.getOrder() == curSpeechNum + 1){
				setCurPresetTimeTrue(speech);
			}
		}
	}	
}