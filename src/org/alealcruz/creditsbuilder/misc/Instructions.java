package org.alealcruz.creditsbuilder.misc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Instructions {
	
	
	
	
	
	public static void invokation( Set<String> set, Map<String,String> map, String xmlFromInvokation, String outputFileName, String imgPattern, String vidPattern, String reaPattern, String[] args) {

		boolean enc = false;
		int i = 0;
		
		if(set.contains("-help") || set.contains("-usage")){
			System.out.println("This script was intented for REA credit automation based on exeLearning xml file");
			System.out.println("Options:");
			System.out.println(" -help | -usage --> it shows this information");
			System.out.println(" -xml --> in case of XML name has a different name from exeLearning default name (default file name: 'contentv3.xml')");
			System.out.println(" -output --> in case you want an output file different from default output file name (default file name: 'credits_TIMESTAMP.html')");
			System.out.println(" -img --> in case you want to specify an starting image file name (default file name: 'IMG_MAT6PRI_')");
			System.out.println(" -vid --> in case you want to specify an starting image file name (default file name: 'VID_MAT6PRI_')");
			System.out.println(" -rea --> in case you want to specify an starting image file name (default file name: 'REA03_')");
		}else {
			if(set.contains("-xml")) {
				while(!enc && i<args.length) {
					if(args[i].contains("-xml") && (i+1)<args.length && args[i]!=null) {
						xmlFromInvokation = args[i+1];
						enc = true;
					}else
						i++;
				}
			}else
				System.out.println("Nothing related with XML input file name was specified so '" + xmlFromInvokation +  "' XML default file name will be used");
			map.put("xml", xmlFromInvokation);
			
			enc = false;i = 0;
			if(set.contains("-output")) {
				while(!enc && i<args.length) {
					if(args[i].contains("-output") && (i+1)<args.length && args[i]!=null) {
						outputFileName = args[i+1];
						enc = true;
					}else
						i++;
				}
			}else
				System.out.println("Nothing related with output file name was specified so '" + outputFileName +  "_TIMESTAMP.xml' output default file name will be used");
			map.put("output", outputFileName);
			
			enc = false;i = 0;
			if(set.contains("-img")) {
				while(!enc && i<args.length) {
					if(args[i].contains("-img") && (i+1)<args.length && args[i]!=null) {
						imgPattern = args[i+1];
						enc = true;
					}else
						i++;
				}
			}else
				System.out.println("Nothing related with image pattern was specified so 'IMG_MAT6PRI_' text will be used");
			map.put("img", imgPattern);
			
			enc = false;i = 0;
			if(set.contains("-vid")) {
				while(!enc && i<args.length) {
					if(args[i].contains("-vid") && (i+1)<args.length && args[i]!=null) {
						vidPattern = args[i+1];
						enc = true;
					}else
						i++;
				}
			}else
				System.out.println("Nothing related with video pattern was specified so 'VID_MAT6PRI_' text will be used");
			map.put("vid", vidPattern);
			
			enc = false;i = 0;
			if(set.contains("-rea")) {
				while(!enc && i<args.length) {
					if(args[i].contains("-rea") && (i+1)<args.length && args[i]!=null) {
						reaPattern = args[i+1];
						enc = true;
					}else
						i++;
				}
			}else
				System.out.println("Nothing related with image pattern was specified so 'IMG_MAT6PRI_' text will be used");
			map.put("rea", reaPattern);
		}
		
		System.out.println("Example: --> java -jar creditsBuilder.jar -xml contentv3.xml -output creditsREAXX -img IMG_MAT6PRI_ -vid VID_MAT6PRI_ -rea REA03_");
	}

}
