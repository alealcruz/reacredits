package org.alealcruz.creditsbuilder.main;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.alealcruz.creditsbuilder.misc.Disclaimer;
import org.alealcruz.creditsbuilder.misc.FinalInformation;
import org.alealcruz.creditsbuilder.misc.Instructions;
import org.alealcruz.creditsbuilder.model.Credit;
import org.alealcruz.creditsbuilder.logic.Generation;

public class Main {

	public static void main(String[] args) {
		
		Set<String> set = new HashSet<>(Arrays.asList(args));
		String xmlFromInvokation = "contentv3.xml";
		String outputFileName = "credits";
		String imgPattern = "IMG_MAT6PRI_";
		String vidPattern = "VID_MAT6PRI_";
		String reaPattern = "REA03_";
		
		System.out.println(Disclaimer.getDisclaimerText());
		
		Instructions.invokation(set, xmlFromInvokation, outputFileName, imgPattern, vidPattern, reaPattern, args);
		
		String content = Generation.getFileContent(xmlFromInvokation);
		List<String> pages = Generation.getPages(content, "<instance class=\"exe.engine.node.Node\" reference=");
		Set<Credit> IMG_listWithoutRepetitions = new HashSet<Credit>(), VID_listWithoutRepetitions = new HashSet<Credit>();
		int pageNumber = 0;
		for (String page : pages) {
			pageNumber++;
			try {
				Generation.findInstancesOnlyImages("&lt;img", "&gt;", imgPattern, reaPattern, page, IMG_listWithoutRepetitions,
						pageNumber,true);
			} catch (Exception e) {
				System.out.println("Exception while processing image files\n");
			}
			
			try {
				Generation.findInstancesOnlyVideos("&lt;video", "/video&gt;", vidPattern, reaPattern, page,
						VID_listWithoutRepetitions, pageNumber);
			} catch (Exception e) {
				System.out.println("Exception while processing video files\n");
			}
		}

		List<Credit> listIMG = Generation.sortByPage(IMG_listWithoutRepetitions);
		List<Credit> listVID = Generation.sortByPage(VID_listWithoutRepetitions);

		List<Credit> finalList = new ArrayList<Credit>();
		Generation.mergeTwoOrderedList(finalList, listIMG, listVID);
		
		System.out.println("\nIt was found IMAGE and/or VIDEO " + finalList.size() + " elements along " + Generation.differentPages(finalList) + " different pages");
		List<List<Credit>> listOfLists = Generation.doListOfLists(finalList);
		Generation.writeCreditsInOutputFile(listOfLists, outputFileName + "_" + new Timestamp((new Date()).getTime()) + ".html");
		
		FinalInformation.showAsciiArt();
		
	}
	
}
