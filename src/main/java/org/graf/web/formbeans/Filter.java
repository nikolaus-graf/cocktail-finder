package org.graf.web.formbeans;

import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.List;

public class Filter {
	private List<String> zutaten = new ArrayList<>();	
	
	public Filter setZutaten(List<String> zutaten) {
		this.zutaten = zutaten;
		return this;
	}
	
	public List<String> getZutaten() {
		return zutaten;
	}
	
	public String filterString(){
		return zutaten.stream()
				.sorted()
				.collect(joining(", "));
	}
}
