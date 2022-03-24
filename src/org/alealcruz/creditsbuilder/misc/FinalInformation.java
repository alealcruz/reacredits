package org.alealcruz.creditsbuilder.misc;

import java.util.List;

import org.alealcruz.creditsbuilder.logic.Generation;
import org.alealcruz.creditsbuilder.model.Credit;

public class FinalInformation {
	
	public static void showResults(List<Credit> finalList) {
		
		System.out.println("\nIt was found IMAGE and/or VIDEO " + finalList.size() + " elements along " + Generation.differentPages(finalList) + " different pages");
		
	}
	
	public static void showAsciiArt() {
		
		System.out.println("\n╔═══╦═══╗░░╔══╗╔╗░░╔╗░░╔═══╦═══╗░░░░░░░\n" + 
				"║╔═╗║╔═╗║░░║╔╗║║╚╗╔╝║░░║╔═╗║╔═╗║░░░░░░░\n" + 
				"║║░╚╣║░╚╝░░║╚╝╚╬╗╚╝╔╝░░║╚══╣║░║║░░░░░░░\n" + 
				"║║░╔╣║█╔╦══╣╔═╗║╚╗╔╝╔══╬══╗║╚═╝║░░░░░░░\n" + 
				"║╚═╝║╚═╝╠══╣╚═╝║░║║░╚══╣╚═╝║╔═╗║░░░░░░░\n" + 
				"╚═══╩═══╝░░╚═══╝░╚╝░░░░╚═══╩╝░╚╝░░░░░░░\n" + 
				"╔═══╦╗░░░░░░░░░░░░░╔╗░░░░░╔╗░░░░░░░░╔╗░\n" + 
				"║╔═╗║║░░░░╔╗░░░░░░░║║░░░░░║║░░░░░░░░║║░\n" + 
				"║║░║║║╔══╗╚╬══╦═╗╔═╝╠═╦══╗║║░░╔══╦══╣║░\n" + 
				"║╚═╝║║║║═╣╔╣╔╗║╔╗╣╔╗║╔╣╔╗║║║░╔╣║═╣╔╗║║░\n" + 
				"║╔═╗║╚╣║═╣║║╔╗║║║║╚╝║║║╚╝║║╚═╝║║═╣╔╗║╚╗\n" + 
				"╚╝░╚╩═╩══╝║╠╝╚╩╝╚╩══╩╝╚══╝╚═══╩══╩╝╚╩═╝\n" + 
				"░░░░░░░░░╔╝║░░░░░░░░░░░░░░░░░░░░░░░░░░░\n" + 
				"░░░░░░░░░╚═╝░░░░░░░░░░░░░░░░░░░░░░░░░░░\n" + 
				"");
		
	}

}
