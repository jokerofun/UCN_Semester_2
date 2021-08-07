package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import Db.ProcedureODDb;
import model.Entry;
import model.ProcedureOD;

public class ProcedureODController {
	private ProcedureOD procedureOD = ProcedureOD.getProcedureODInstance();
	private int ok;
	ProcedureODDb procedureODDb;

	public ProcedureODController() throws DataAccessException {
		procedureODDb = new ProcedureODDb();
	}

	public void ProcedureODSetup() throws DataAccessException {
		ArrayList<Entry> entryList = new ArrayList<>();
		try {
			entryList.addAll(procedureODDb.getAllEntries());
			procedureOD.procedureODSetup(entryList);
		} catch (DataAccessException e) {
			throw new DataAccessException("Could not read data base result set", e);
		}
	}

	public ArrayList<Integer> orderPriority(ArrayList<Integer> procedureList) {
		ArrayList<Integer> orderedProcedures = new ArrayList<>();
		ArrayList<Entry> priorityEntry = new ArrayList<>();
		priorityEntry = prioritySort(procedureList);
		for (int j = 0; j < procedureList.size(); j++) {
			ok = 1;
			int procedure = procedureList.get(j);
			priorityEntry.forEach(g -> compare(procedure, g.getB(), orderedProcedures, g.getA()));
			if (ok == 1) {
				orderedProcedures.add(procedure);
				procedureList.remove(j);
				j = -1;
			}
		}
		
		return orderedProcedures;
	}

	private void compare(int a, int b, ArrayList<Integer> orderedProcedures, int pre) {
		int check = 1;
		if (a == b) {
			if(orderedProcedures.size() != 0) {
			for (int i = 0; i < orderedProcedures.size(); i++) {
				if (pre != orderedProcedures.get(i)) {
					check = 0;
				}else {
					check = 1;
				}
			}
			if(check == 0) {
				ok = 0;
			   }
			}
			else {
				check = 0;
				ok =0;
			}
	   }
	}

	private ArrayList<Entry> prioritySort(ArrayList<Integer> procedureList) {
		ArrayList<Entry> priorityEntry = new ArrayList<>();
		HashMap<Integer, ArrayList<Integer>> sortingMap = new HashMap<>();
		ArrayList<Integer> priorityList = new ArrayList<>();
		for (int s = 0; s < procedureOD.matrixSize(); s++) {
			for (int i = 0; i < procedureOD.matrixSize(); i++) {
				if (procedureOD.getPriority(s, i) == 1) {
					priorityList.add(i);
				}
			}
			ArrayList<Integer> newList = new ArrayList<>();
			newList.addAll(priorityList);
			sortingMap.put(s, newList);
			priorityList.clear();
		}
		/*sortingMap.forEach((k,v) -> {System.out.print(k + " --- ");
		v.forEach(t -> {System.out.print(t);});
		System.out.println();});
		*/
		for (int d = 0; d < procedureList.size(); d++) {
			for (int j = 0; j < procedureList.size(); j++) {
				if (recursiveCheck(procedureList.get(d), procedureList.get(j), sortingMap) == 1) {
					priorityEntry.add(new Entry(procedureList.get(d), procedureList.get(j)));
				}
			}
		}
		//priorityEntry.forEach(l -> {System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP " + l.getA()+ " " + l.getB());});
		return priorityEntry;
	}

	private int recursiveCheck(int a, int b, HashMap<Integer, ArrayList<Integer>> sortingMap) {
		int result = 0;
		if (sortingMap.containsKey(a)) {
			Iterator<Integer> iterate = sortingMap.get(a).iterator();
			while (iterate.hasNext()) {
				int next = iterate.next();
				if (next == b) {
					result = 1;
				}
				if(result != 1) {
				result = recursiveCheck(next, b, sortingMap);
				}
			}
		}
		//System.out.println(a + " " + b +"\n ___________\n " + result);
		return result;
	}
}
