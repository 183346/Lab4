package it.polito.tdp.anagrammiModello;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Model {
	List<LinkedList<String>> matrice = new LinkedList<LinkedList<String>>();
	
	List <String> parole = new LinkedList<String>();
	List<String> combinazioni = new LinkedList<String>();
	Set<String> controllo = new HashSet<String>();

	public boolean controlloInput(String text) {
		
		//solo caratteri
		Pattern p = Pattern.compile("^[a-zA-Z]+$");
		Matcher m = p.matcher(text);
		boolean b= m.matches();
		
		return b;
	}

	public List<String> anagramma(String text) {
		parole.clear();
		List<String> lettereMancanti = new LinkedList<String>();
		for(int i=0;i<text.length();i++){
			lettereMancanti.add(text.substring(i,i+1));
		}
					
		ricorri("",lettereMancanti);
		
		return parole;
	}
	
	private void ricorri(String word,List<String> lettereMancanti){
		
		if(lettereMancanti.size()==0){
			if(!parole.contains(word)){
			parole.add(word);
			parole.add("\n");
			}
		}else{
			
			for(String lettera:lettereMancanti){
				
				List<String> tempo = new LinkedList<String>(lettereMancanti);
				tempo.remove(lettera);
				ricorri(word+lettera,tempo);
							
			}
			
			
			
			
		}
		
		
	}

	public List<String> anagrammaIter(String text) {
		combinazioni.clear();
		controllo.clear();
		String res="";
		List<String> parole = new LinkedList<String>();
		String tempok="";
		String tempoj="";
		
		
		//
		int n=text.length();
		for(int l=0;l<n;l++){
			combinazioni.add(text.substring(l,l+1));
			
		}
		// anagramma
		for(int k=0;k<n;k++){
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					tempok=combinazioni.get(k);
					tempoj=combinazioni.get(j);
					combinazioni.set(k, tempoj);
					combinazioni.set(j, tempok);
					
					
					//trasformo in stringa
					for(int t=0;t<n;t++){
						 res=res+combinazioni.get(t);
					}
					
						controllo.add(res);res="";
						
					
					
					
						matrice.add((LinkedList<String>) combinazioni);
				}
				
				
				
				/*if(!matrice.containsAll(combinazioni)){
				matrice.add((LinkedList<String>) combinazioni);}*/
				
			}
			
		}
		 //sistemazione in stringa
		/*for(LinkedList<String> combi:matrice){
			for(int jj=0;jj<combi.size();jj++){
				String tempo=combi.get(jj);
				parole.add(tempo);
			}
			parole.add("\n");
		}*/
		for(String s:controllo){
			parole.add(s);
			parole.add("\n");
		}
		
		return parole;
	
	}
}
