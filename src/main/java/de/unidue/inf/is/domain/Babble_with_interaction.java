package de.unidue.inf.is.domain;

import de.unidue.inf.is.domain.Babble;

public class Babble_with_interaction implements Comparable<Babble_with_interaction>{

		private Babble content;
		private String interaction;
		
		public Babble_with_interaction(Babble content, String interaction){
			this.content = content;
			this.interaction = interaction;
		}
		
		public Babble getContent(){
			return content;
		}
		
		public String getinteraction(){
			return interaction;
		}
		
		public int compareTo(Babble_with_interaction Bab){
			return content.compareTo(Bab.getContent());
		}
	
}
