package pl.examples;

import java.util.ArrayList;
import java.util.List;

import pl.core.Conjunction;
import pl.core.Implication;
import pl.core.KB;
import pl.core.ModelImpl;
import pl.core.Symbol;
import pl.util.Utils;

public class ModusPonensKB extends KB {
	
	public ModusPonensKB() {
		super();
		Symbol p = intern("P");
		Symbol q = intern("Q");
		add(p);
		add(new Implication(p, q));
		
	}
	
	public static void main(String[] argv) {
		new ModusPonensKB().dump();
		ModusPonensKB kb = new ModusPonensKB();
		
		List<ModelImpl> models = new ArrayList<>();
		int numOfSymbols = kb.symbols().size();
		int[][] truthTable = Utils.generateTruthTable(numOfSymbols);
		List<Symbol> symbols = new ArrayList<>(kb.symbols());
		
		for (int i=0; i<truthTable.length; i++) {
			ModelImpl model = new ModelImpl();
			for (int j=0; j<kb.symbols().size(); j++) {
				model.set(symbols.get(j), truthTable[i][j] == 1);
			}
			models.add(model);
		}
		
		Conjunction conj = new Conjunction(kb.sentences.get(0), kb.sentences.get(1));
		List<ModelImpl> selected = new ArrayList<>();
		
		for (ModelImpl m : models) {
			if(conj.isSatisfiedBy(m))
				selected.add(m);
		}
		System.out.println("no. of selected: "+selected.size());
		ModelImpl rhs = new ModelImpl();
		rhs.set(new Symbol("Q"), true);
		boolean ret = true;
		for (ModelImpl m : selected) {
			if (!m.compareToModel(rhs))
				ret = false;			
		}
		System.out.println(ret);
	}

}
