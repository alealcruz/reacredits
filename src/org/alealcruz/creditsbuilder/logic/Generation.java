package org.alealcruz.creditsbuilder.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.alealcruz.creditsbuilder.model.Credit;

public class Generation {
	
	public static int differentPages(List<Credit> list) {
		int result = 0;
		
		Set<Integer> set = new HashSet<Integer>();
		for(Credit image:list) {
			if(!set.contains(image.getPage())) {
				set.add(image.getPage());
				result++;
			}
		}
		
		return result;
	}

	public static void writeCreditsInOutputFile(List<List<Credit>> list, String creditsFile) {
		
		FileWriter file = null;
		int pageNumber = 0;
		
		try {
			file = new FileWriter(creditsFile);
			file.write("<div class=\"exe-fx exe-paginated\">\n");
			file.write("<h2>Página 1</h2>\n" + 
					"<div>\n" + 
					"<figure class=\"exe-figure exe-image position-center license-CC-BY-NC-SA\" style=\"text-align: center;\"><img src=\"resources/IMG_MAT6PRI_REA01_PORTADA_V01.png\" alt=\"Imagen de la portada del REA 1 del tercer ciclo de primaria de matemáticas\" title=\"Portada\" hspace=\"auto\" style=\"height: 140px; display: block; margin-left: auto; margin-right: auto;\" />\n" + 
					"<figcaption class=\"figcaption\"><span style=\"font-size: 10pt;\"><span class=\"author\">Elaboración propia</span>. <span class=\"title\"><em>Portada para los REA del tercer ciclo de Matemáticas de Educación Primaria</em></span> <span class=\"license\"><span class=\"sep\">(</span><a href=\"http://creativecommons.org/licenses/?lang=es\" rel=\"license nofollow noopener\" target=\"_blank\" title=\"Creative Commons BY-NC-SA\">CC BY-NC-SA</a><span class=\"sep\">)</span></span></span></figcaption>\n" + 
					"</figure>\n" + 
					"</div>");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		for(List<Credit> item:list) {
			pageNumber = item.get(0).getPage();
			try {
				file.write("\n<h2>Página " + pageNumber + "</h2>\n");
				file.write(processCreditsPage(item));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			pageNumber++;
			file.write("\n<h2>Página " + pageNumber + "</h2>\n");
			file.write("<div>Los símbolos pictográficos utilizados son propiedad del Gobierno de Aragón y han sido creados por Sergio Palao para ARASAAC (http://www.arasaac.org) , que los distribuye bajo Licencia Creative Commons BY-NC-SA</div>\n");
			pageNumber++;
			file.write("\n<h2>Página " + pageNumber + "</h2>\n"); 
			file.write("<div>Las fichas ofrecidas al alumnado a lo largo del REA han sido realizadas por todas las personas elaboradoras de contenidos de la presente obra y tienen licencia CC BY-SA.</div>\n</div>");
			file.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public static String processCreditsPage(List<Credit> list) {
		String result = "";
		int i = 0;
		int total = list.size();
		
		while (i < total) {
			if((total - i) >= 3) {
				result = result + "\n<div class=\"exe-layout-3-cols exe-clear\">";
				result = result + "\n<div class=\"exe-col exe-col-" + 1 + "\">";
				result = result + textElementAssociated(list.get(i));
				result = result + "\n</div>";
				result = result + "\n<div class=\"exe-col exe-col-" + 2 + "\">";
				result = result + textElementAssociated(list.get(i+1));
				result = result + "\n</div>";
				result = result + "\n<div class=\"exe-col exe-col-" + 3 + "\">";
				result = result + textElementAssociated(list.get(i+2));
				result = result + "\n</div>";
				result = result + "\n</div>";
				i = i+3;
			}else if((total - i) == 2) {
				result = result + "\n<div class=\"exe-layout-2-cols exe-clear\">";
				result = result + "\n<div class=\"exe-col exe-col-" + 1 + "\">";
				result = result + textElementAssociated(list.get(i));
				result = result + "\n</div>";
				result = result + "\n<div class=\"exe-col exe-col-" + 2 + "\">";
				result = result + textElementAssociated(list.get(i+1));
				result = result + "\n</div>";
				result = result + "\n</div>";
				i = i+2;
			}else if((total - i) == 1) {
				result = result + "\n<div>";
				result = result + textElementAssociated(list.get(i));
				result = result + "\n</div>";
				i++;
			}
		}
		return result;
	}

	
	public static String textElementAssociated(Credit item) {
		String result = "";
		if (item.getResource().contains("VID_")) { //if (item.getResource().contains("VID_MAT6PRI_REA")) { // VID
			result = "<figure class=\"exe-figure exe-image position-center license-CC-BY-NC-SA\" style=\"text-align: center;\"><video width=\"300\" height=\"150\" style=\"display: block; margin-left: auto; margin-right: auto; height: 140px; width: 196px;\" hspace=\"auto\" controls=\"controls\" class=\"mediaelement\"> <source src=\"";
			result = result + item.getResource();
			result = result + "\" type=\"video/mp4\" /></video> <figcaption class=\"figcaption\"><span style=\"font-size: 10pt;\"><span class=\"author\">Elaboración propia</span>. <span class=\"title\"><em>El reto: Pon en marcha tu empresa</em></span> <span class=\"license\"><span class=\"sep\">(</span><a href=\"http://creativecommons.org/licenses/?lang=es\" rel=\"license nofollow noopener\" target=\"_blank\" title=\"Creative Commons BY-NC-SA\">CC BY-NC-SA</a><span class=\"sep\">)</span></span></span></figcaption> </figure>";
		} else { // IMG
			result = "<figure class=\"exe-figure exe-image position-center license-CC-BY-NC-SA\" style=\"text-align: center;\"><img src=\"";
			result = result + item.getResource() + "\" alt=\"" + item.getAlt() + "\" ";
			result = result
					//+ " title=\"Portada\" hspace=\"auto\" style=\"height: 140px; display: block; margin-left: auto; margin-right: auto;\" /> <figcaption class=\"figcaption\"><span style=\"font-size: 10pt;\"><span class=\"author\">Elaboración propia</span>. <span class=\"title\"><em>Portada para los REA del tercer ciclo de Matemáticas de Educación Primaria</em></span> <span class=\"license\"><span class=\"sep\">(</span><a href=\"http://creativecommons.org/licenses/?lang=es\" rel=\"license nofollow noopener\" target=\"_blank\" title=\"Creative Commons BY-NC-SA\">CC BY-NC-SA</a><span class=\"sep\">)</span></span></span></figcaption> </figure>";
					//+ " title=\"Portada\" hspace=\"auto\" style=\"height: 140px; display: block; margin-left: auto; margin-right: auto;\" /> <figcaption class=\"figcaption\"><span style=\"font-size: 10pt;\"><span class=\"author\">Elaboración propia</span>. <span class=\"title\"><em>Portada para los REA del tercer ciclo de Matemáticas de Educación Primaria</em></span> <span class=\"license\"><span class=\"sep\">(</span><a href=\"http://creativecommons.org/licenses/?lang=es\" rel=\"license nofollow noopener\" target=\"_blank\" title=\"Creative Commons BY-NC-SA\">CC BY-NC-SA</a><span class=\"sep\">)</span></span></span></figcaption> </figure>";
					+ " title=\"" + item.getTitle() + "\" hspace=\"auto\" style=\"height: 140px; display: block; margin-left: auto; margin-right: auto;\" /> <figcaption class=\"figcaption\"><span style=\"font-size: 10pt;\"><span class=\"author\">Elaboración propia</span>. <span class=\"title\"><em>" + item.getTitle() + "</em></span> <span class=\"license\"><span class=\"sep\">(</span><a href=\"http://creativecommons.org/licenses/?lang=es\" rel=\"license nofollow noopener\" target=\"_blank\" title=\"Creative Commons BY-NC-SA\">CC BY-NC-SA</a><span class=\"sep\">)</span></span></span></figcaption> </figure>";
			
		}
		return result;
	}

	public static List<String> getPages(String content, String splitValue) {
		return Arrays.asList(content.split(splitValue));
	}

	public static String getFileContent(String fileName) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e1) {
			System.out.println("Provided XML file name not found!" + e1.toString());//e1.printStackTrace();
		}
		String fileContent = "";
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			fileContent = sb.toString();
			return fileContent;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				return fileContent;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileContent;
	}

	public static List<String> findInstances(String value, String text) {
		List<String> items = new ArrayList<String>();
		int position = 0;
		String remain = text;
		position = remain.indexOf(value);
		while (position < remain.length() && position > -1) {
			if (position != -1) {
				items.add(remain.substring(position, remain.indexOf("&quot;", position)));
			}
			remain = remain.substring(remain.indexOf("&quot;", position));
			position = remain.indexOf(value);
		}
		return items;
	}

	public static Set<Credit> findInstancesWithoutRepetitions(String value, String text) {
		Set<Credit> items = new HashSet<Credit>();
		int position = 0;
		String remain = text;
		String aux = "";
		Credit IMG_aux = null;
		position = remain.indexOf(value);
		while (position < remain.length() && position > -1) {
			if (position != -1) {
				if (value.contains("IMG")) {
					aux = remain.substring(position, remain.indexOf("&quot; width", position));
					IMG_aux = new Credit(aux.substring(0, aux.indexOf("&quot;")),
							aux.substring(aux.indexOf("alt=&quot;") + 10), 0);
					items.add(IMG_aux);
				}
			}
			remain = remain.substring(remain.indexOf("&quot; width", position));
			position = remain.indexOf(value);
		}
		return items;
	}

	public static void findInstancesWithoutRepetitionsInGivenPage(String value, String text, Set<Credit> items,
			int pageNumber) {
		int position = 0, end = 0;
		String remain = text;
		String aux = "";
		Credit IMG_aux = null;
		position = remain.indexOf(value);
		while (position < remain.length() && position > -1) {
			if (position != -1) {
				if (value.contains("IMG")) {
					end = remain.indexOf("&quot; width", position);
					if (end > 0 && end > position) {
						aux = remain.substring(position, end);
						IMG_aux = new Credit(aux.substring(0, aux.indexOf("&quot;")),
								aux.substring(aux.indexOf("alt=&quot;") + 10), pageNumber);
						items.add(IMG_aux);
					}
				}
			}
			if (end > 0 && end > position) {
				remain = remain.substring(remain.indexOf("&quot; width", position));
				position = remain.indexOf(value);
			} else
				position = -1;
		}
	}

	public static void findInstancesWithoutRepetitionsInGivenPageVersion2(String value, String text, Set<Credit> items,
			int pageNumber) {

		int position = 0, end = 0;
		String remain = text;
		String aux = "";
		Credit IMG_aux = null;
		position = remain.indexOf(value);
		while (position < remain.length() && position > -1) {
			if (position != -1) {
				if (value.contains("img")) {
					end = remain.indexOf("/&gt;", position);
					if (end > 0 && end > position) {
						aux = remain.substring(position, end);
						IMG_aux = new Credit(
								aux.substring(aux.indexOf("resources/IMG"),
										aux.indexOf(("&quot;"), aux.indexOf("resources/IMG"))),
								aux.substring(aux.indexOf("alt=&quot;") + 10), pageNumber);
						items.add(IMG_aux);
					}
				}
			}
			if (end > 0 && end > position) {
				remain = remain.substring(remain.indexOf("&quot; width", position));
				position = remain.indexOf(value);
			} else
				position = -1;
		}
	}

	public static List<Credit> sortByPage(Set<Credit> set) {
		List<List<Credit>> list = new ArrayList<List<Credit>>();
		List<Credit> result = new ArrayList<Credit>();
		int max = 0;
		for (Credit image : set) {
			if (image.getPage() > max)
				max = image.getPage();
		}

		for (int i = 0; i < max; i++)
			list.add(new ArrayList<Credit>());

		for (Credit image : set)
			list.get(image.getPage() - 1).add(image);

		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.get(i).size(); j++)
				result.add(list.get(i).get(j));
		}

		return result;
	}

	public static void findInstancesOnlyImages(String startingTag, String finishingTag, String searchTag,
			String textContained, String textBlock, Set<Credit> items, int pageNumber,boolean includingPictograms) {

		List<String> elements = Arrays.asList(textBlock.split(searchTag));

		Credit imageAux = null;
		String before = "";
		String after = "";
		String everything = "";
		String item = "";
		String itemBefore = "";
		int altText, endingAltText;
		int titleText, endingTitleText;

		for (int i = 0; i < elements.size() && (includingPictograms?true:pageNumber !=2); i++) {
			item = elements.get(i).replace("\n", "");
			if (item.contains(textContained) && item.contains("alt=&quot;") && elements.get(i - 1).contains(startingTag)
					&& elements.get(i - 1).lastIndexOf(startingTag) != -1) {
				itemBefore = elements.get(i - 1).replace("\n", "");
				before = itemBefore.substring(itemBefore.lastIndexOf(startingTag));
				after = item.substring(0, item.indexOf(finishingTag));
				everything = before.replace("\n", "") + searchTag + after.replace("\n", "") + finishingTag;
				altText = everything.indexOf("alt=&quot;") + 10;
				endingAltText = (everything.substring(altText)).indexOf("&quot;") + altText;
				
				//code for get title parameter from HTML
				titleText = everything.indexOf("title=&quot;") + 12;
				endingTitleText = (everything.substring(titleText)).indexOf("&quot;") + titleText;
				
				if (everything.indexOf("resources/"+searchTag.split("_")[0]) != -1) {
				//if (everything.indexOf("resources/IMG") != -1) {
					imageAux = new Credit(
							everything.substring(everything.indexOf("resources/"+searchTag.split("_")[0]),
						    //everything.substring(everything.indexOf("resources/IMG"),
									everything.indexOf(("&quot;"), everything.indexOf("resources/"+searchTag.split("_")[0]))),
									//everything.indexOf(("&quot;"), everything.indexOf("resources/IMG"))),
							everything.substring(altText, endingAltText), pageNumber,
							everything.substring(titleText, endingTitleText));
					items.add(imageAux);
				}
			}
		}
	}

	public static void findInstancesOnlyVideos(String startingTag, String finishingTag, String searchTag,
			String textContained, String textBlock, Set<Credit> items, int pageNumber) {
		List<String> elements = Arrays.asList(textBlock.split(searchTag));
		Credit imageAux = null;
		String before = "";
		String after = "";
		String everything = "";
		String item = "";
		String itemBefore = "";

		for (int i = 0; i < elements.size(); i++) {
			item = elements.get(i).replace("\n", "");
			if (item.contains(textContained) &&
					i > 0 && elements.get(i - 1).contains(startingTag)
					&& elements.get(i - 1).lastIndexOf(startingTag) != -1) {
				itemBefore = elements.get(i - 1).replace("\n", "");
				before = itemBefore.substring(itemBefore.lastIndexOf(startingTag));
				after = item.substring(0, item.indexOf(finishingTag));
				everything = before.replace("\n", "") + searchTag + after.replace("\n", "") + finishingTag;
				if (everything.indexOf("src=&quot;") != -1) {
					imageAux = new Credit(
							everything.substring(everything.indexOf("src=&quot;") + 10,
									everything.indexOf(("&quot;"), everything.indexOf("src=&quot;") + 11)),
							"",
							pageNumber);
					items.add(imageAux);
				}
			}
		}
	}

	public static boolean isPresent(List<Credit> list, Credit image) {
		boolean enc = false;
		int i = 0;
		while (i < list.size() && !enc) {
			if (list.get(i).getResource().equals(image.getResource()))
				enc = true;
			else
				i++;
		}
		return enc;
	}

	public static void mergeTwoOrderedList(List<Credit> finalList, List<Credit> listIMG, List<Credit> listVID) {
		int i = 0, j = 0;
		while (i < listIMG.size() && j < listVID.size()) {
			if (listIMG.get(i).getPage() <= listVID.get(j).getPage()) {
				if (listIMG.get(i).getPage() != 1 && !isPresent(finalList, listIMG.get(i)))
					finalList.add(listIMG.get(i));
				i++;
			} else {
				if (listVID.get(j).getPage() != 1 && !isPresent(finalList, listVID.get(j)))
					finalList.add(listVID.get(j));
				j++;
			}
		}
		if (i < listIMG.size()) {
			for (int k = i; k < listIMG.size(); k++) {
				if (listIMG.get(k).getPage() != 1 && !isPresent(finalList, listIMG.get(k)))
					finalList.add(listIMG.get(k));
			}
		} else {
			for (int k = j; k < listVID.size(); k++) {
				if (listVID.get(k).getPage() != 1 && !isPresent(finalList, listVID.get(k)))
					finalList.add(listVID.get(k));
			}
		}
	}

	public static File createCreditFile(String filename) {
		File file = null;
		try {
			file = new File(filename);
			if (file.exists())
				file.delete();
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return file;
	}
	
	public static List<List<Credit>> doListOfLists(List<Credit> list){
		List<List<Credit>> result = new ArrayList<List<Credit>>();
		List<Credit> temp = null;
		int page = 0;
		for(Credit image:list) {
			if(image.getPage()!=page) {
				page = image.getPage();
				temp = new ArrayList<Credit>();
				result.add(temp);
			}
			temp.add(image);
		}
		return result;
	}

}
