package pl.examples;

import java.util.ArrayList;
import java.util.List;

import pl.core.Biconditional;
import pl.core.Conjunction;
import pl.core.Disjunction;
import pl.core.KB;
import pl.core.ModelImpl;
import pl.core.Negation;
import pl.core.Symbol;
import pl.util.Utils;

public class WumpusWorldKB extends KB {
	
	public WumpusWorldKB() {
		super();
		Symbol p11 = intern("P1,1");
		Symbol p12 = intern("P1,2");
		Symbol p21 = intern("P2,1");
		Symbol p22 = intern("P2,2");
		Symbol p31 = intern("P3,1");
		Symbol b11 = intern("B1,1");
		Symbol b21 = intern("B2,1");

		add(new Negation(p11));
		add(new Biconditional(b11, new Disjunction(p12, p21)));
		add(new Biconditional(b21, new Disjunction(p12, new Disjunction(p22, p31))));
		add(new Negation(b11));
		add(b21);
	}

	public static void main(String[] argv) {
		new WumpusWorldKB().dump();
		WumpusWorldKB kb = new WumpusWorldKB();
		
		List<ModelImpl> models = new ArrayList<>();
		int numOfSymbols = kb.symbols().size();
		int[][] truthTable = Utils.generateTruthTable(numOfSymbols);
		List<Symbol> symbols = new ArrayList<>(kb.symbols());
		
		for (int i=0; i<truthTable.length; i++) {
			ModelImpl model = new ModelImpl();
			for (int j=0; j<kb.symbols().size(); j++) {
				model.set(symbols.get(j), truthTable[i][j] == 0 ? false : true);
			}
			models.add(model);
		}
		
		
		Conjunction main = new Conjunction(kb.sentences.get(0), kb.sentences.get(1));
		for (int i=2;i<kb.sentences.size();i++) {
			main = new Conjunction(main, kb.sentences.get(i));
		}
		
		List<ModelImpl> selected = new ArrayList<>();
		
		for (ModelImpl model : models) {
			if(main.isSatisfiedBy(model))
				selected.add(model);
		}
		
		ModelImpl rhs = new ModelImpl();
		rhs.set(new Symbol("P1,2"), false);
		boolean ret = true;
		System.out.println("no. of selected: "+selected.size());
		for (ModelImpl m : selected) {
			if (!m.compareToModel(rhs))
				ret = false;			
		}
		System.out.println(ret);		
	}

}
